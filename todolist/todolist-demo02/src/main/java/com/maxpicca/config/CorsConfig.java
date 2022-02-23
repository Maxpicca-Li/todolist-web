package com.maxpicca.config;

/**
 * @author Maxpicca
 * @Date 2021-02-24 15:02
 */

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局配置类解决跨域问题
 * TODO 如果系统开发接口复杂，可以集成swagger2
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 访问路径
                // TODO 服务器部署
                // .allowedOrigins("http://localhost:8080")  // 本地

                // .allowedOrigins("http://localhost:80")  // 服务器 plan1
                // .allowedOrigins("http://182.92.198.13:80")  // 服务器 plan2
                .allowedOrigins("*")  //服务器 plan3

                // .allowedOriginPatterns("*")
                // .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS") // 方法
                .allowedMethods("*") // 方法
                .allowCredentials(false) // 是否允许携带数据
                .maxAge(30*1000) // 探测请求的有效期
                .allowedHeaders("*");
    }

    /**
     * 基于Filter的全局配置
     * @return
     */
    /*@Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.addAllowedOrigin("http://localhost:8080/#");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }*/

}
