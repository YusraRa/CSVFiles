//package com.tsfn.config;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//
//import com.tsfn.filter.ForceMultipartParsingFilter;
//
//@Configuration
//public class AppConfig {
//    @Bean
//    public FilterRegistrationBean<ForceMultipartParsingFilter> forceMultipartParsingFilter() {
//        FilterRegistrationBean<ForceMultipartParsingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new ForceMultipartParsingFilter());
//        registrationBean.addUrlPatterns("/upload_csv");
//        registrationBean.addUrlPatterns("/api/csv/upload");
//        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return registrationBean;
//    }
//}
