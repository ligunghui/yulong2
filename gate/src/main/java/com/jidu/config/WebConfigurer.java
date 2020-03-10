package com.jidu.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfigurer implements WebMvcConfigurer {


//    @Value("${vocs.user.fileUpload.rootSavePath}")
//    private String rootSavePath;

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        if(ConsUtils.IS_LINUX){
            registry.addResourceHandler("/upload/**").addResourceLocations("file:/yulong/upload/");
        }else {
            registry.addResourceHandler("/upload/**").addResourceLocations("file:/C:/upload/");
        }

    }


    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    /**
     * 文件上传配置
     * @return
     */
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //文件最大
//        factory.setMaxFileSize("10240KB"); //KB,MB
//        /// 设置总上传数据总大小
//        factory.setMaxRequestSize("102400KB");
//        return factory.createMultipartConfig();
//    }


}
