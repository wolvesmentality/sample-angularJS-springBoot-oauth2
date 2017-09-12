package com.veosaf.bookRental;

import javax.servlet.DispatcherType;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@Configuration
public class WebConfiguration {
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}
	
    @Bean
    public FilterRegistrationBean urlRewriteFilterfilterRegistrationBean() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new UrlRewriteFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        filterRegistrationBean.addUrlPatterns("/*");

        filterRegistrationBean.addInitParameter("confPath", "/WEB-INF/urlrewrite.xml");

        return filterRegistrationBean;
    }
}