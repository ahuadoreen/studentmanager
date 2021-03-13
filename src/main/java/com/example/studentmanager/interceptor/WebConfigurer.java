package com.example.studentmanager.interceptor;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Value("${web.upload-path}")
    private String mImagesPath;

    @Autowired
    private TokenInterceptor loginInterceptor;

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + mImagesPath);
    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/images/**",
                "/swagger**/**","/**/api-docs/**");
    }

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        Components components = new Components();
        components
                .addParameters("token", new HeaderParameter().required(true).name("token").schema(new StringSchema()).required(true))
                .addParameters("username", new HeaderParameter().required(true).name("username").schema(new StringSchema()).required(true));
        return new OpenAPI()
                .components(components)
                .info(new Info()
                        .title("Student Manager API")
                        .description("Student manager server api.学生管理系统后台API.")
                        .version(appVersion)
                        .license(new License()
                                .name("Apache2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation")
                        .url("https://www.jianshu.com/nb/41542276"));
    }

    /**
     * 添加全局的请求头参数
     */
    @Bean
    public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> {
                    String summary = operation.getSummary();
                    if(summary != null){
                        if(!summary.equals("Logs user into the system"))
                            operation.addParametersItem(new HeaderParameter().$ref("#/components/parameters/username"))
                                    .addParametersItem(new HeaderParameter().$ref("#/components/parameters/token"));
                    }else{
                        operation.addParametersItem(new HeaderParameter().$ref("#/components/parameters/username"))
                                .addParametersItem(new HeaderParameter().$ref("#/components/parameters/token"));
                    }
                });
    }
}
