package com.mamba.shop.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mamba.shop")
public class WebConfig extends WebMvcConfigurerAdapter {

   @Bean
   public InternalResourceViewResolver viewResolver(){
       InternalResourceViewResolver resolver =
               new InternalResourceViewResolver();
       resolver.setViewClass(JstlView.class);
       resolver.setPrefix("/WEB-INF/views/");
       resolver.setSuffix(".jsp");
       return resolver;
   }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
        registry.addResourceHandler("/secure/webjars/**").addResourceLocations("/webjars/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

//   @Bean
//   public MessageSource messageSource(){
//       ResourceBundleMessageSource bundleMessageSource =
//               new ResourceBundleMessageSource();
//       bundleMessageSource.setBasename("messages");
//       return bundleMessageSource;
//   }
//
//    @Override
//    public Validator getValidator() {
//        LocalValidatorFactoryBean localValidatorFactoryBean =
//                new LocalValidatorFactoryBean();
//        localValidatorFactoryBean.setValidationMessageSource(messageSource());
//        return localValidatorFactoryBean;
//    }
}
