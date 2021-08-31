package cl.previred.prueba.config;

import java.util.Properties;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cl.previred.prueba.interceptors.MdcInterceptor;



@SuppressWarnings("deprecation")
@Configuration
@SpringBootApplication
@EnableAutoConfiguration(exclude = { XADataSourceAutoConfiguration.class })
@ComponentScan(basePackages = { "cl.previred.prueba" })
public class GlobalConfiguration extends WebMvcConfigurerAdapter {
   
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/")
		.setViewName("redirect:/swagger-ui.html");
	registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public HandlerInterceptor addMDCInterceptor() {
	return new MdcInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(addMDCInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
	ReloadableResourceBundleMessageSource bundle = getBundle();
	bundle.setDefaultEncoding("UTF-8");
	Properties fileEncodings = new Properties();
	fileEncodings.setProperty("application-messages", "UTF-8");
	bundle.setFileEncodings(fileEncodings);
	bundle.setFallbackToSystemLocale(true);
	bundle.setBasename("classpath:application-messages");
	return bundle;
    }
    
    
    
    private ReloadableResourceBundleMessageSource getBundle() {
	return new ReloadableResourceBundleMessageSource();
    }
}
