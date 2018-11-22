package com.mamba.shop.config;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

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
        registry.addResourceHandler("/admin/webjars/**").addResourceLocations("/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("/secure/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("/admin/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/resources/favicon.ico");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor =
                new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }

    @Bean
    public LocaleResolver localeResolver(){
        CookieLocaleResolver cookieLocaleResolver =
                new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("en"));
        cookieLocaleResolver.setCookieName("locale");
        cookieLocaleResolver.setCookieMaxAge(4800);
        return cookieLocaleResolver;
    }

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/i18n/application");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver multipartResolver =
                new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20971520);
        multipartResolver.setMaxInMemorySize(1048576);
        return multipartResolver;
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
