package com.mamba.shop.config;

import com.mamba.shop.service.DownloadFile;
import com.mamba.shop.service.impl.IDownloadFile;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScans({
        @ComponentScan("com.mamba.shop.dao"),
        @ComponentScan("com.mamba.shop.controller"),
        @ComponentScan("com.mamba.shop.service")
})
@PropertySources({
        @PropertySource("classpath:db.properties"),
        @PropertySource("classpath:mail.properties"),
        @PropertySource("classpath:app.properties")
})
public class AppConfiguration {

    private final Environment environment;

    @Autowired
    public AppConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Qualifier(value = "iDownLoadFile")
    public DownloadFile downloadFile(){
        String dirname = environment.getProperty("app.path_save");
        return new IDownloadFile(dirname);
    }

    @Bean
    public JavaMailSender getJavaMail(){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(environment.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("mail.port")));
        mailSender.setUsername(environment.getProperty("mail.username"));
        mailSender.setPassword(environment.getProperty("mail.password"));

        Properties propertiesJavaMail = new Properties();
        propertiesJavaMail.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
        propertiesJavaMail.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        propertiesJavaMail.put("mail.transport.protocol", environment.getProperty("mail.transport.protocol"));
        propertiesJavaMail.put("mail.debug", environment.getProperty("mail.debug"));

        mailSender.setJavaMailProperties(propertiesJavaMail);
        return mailSender;
    }

    @Bean
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        factoryBean.setPackagesToScan("com.mamba.shop.entity");

        Properties propertiesHibernate = new Properties();
        propertiesHibernate.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        propertiesHibernate.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        propertiesHibernate.put("hibernate.max_fetch_depth", environment.getProperty("hibernate.max_fetch_depth"));

        factoryBean.setHibernateProperties(propertiesHibernate);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager hibernateTransactionManager =
                new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactoryBean().getObject());

        return hibernateTransactionManager;
    }

}
