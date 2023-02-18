package xyz.gabear.learn.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 类名称：Swagger2Config
 * ********************************
 * <p>
 * 类描述：Swagger2配置类
 *
 * @author
 * @date 下午9:08
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * Swagger2信息
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // API 基本信息
                .apiInfo(apiInfo())

                // 设置允许暴露的接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imooc.zhangxiaoxi.alllearning.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API基本信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ALL-LEARNING项目")
                .description("综合课程所有知识点，整合实战项目。")
                .contact(new Contact("张小喜", "", ""))
                .version("1.0.0")
                .build();
    }

}
