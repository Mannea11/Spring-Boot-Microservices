package MicroServiceCart.Cart.Config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filter= new FilterRegistrationBean();
        filter.setFilter(new JwtFilter());
        filter.addUrlPatterns("/cart/restricted/*");
        filter.addUrlPatterns("/cart/add/*");
        filter.addUrlPatterns("/cart/delete/*");
        filter.addUrlPatterns("/cart/deletecart/{cartId}/*");
        return filter;
    }
}