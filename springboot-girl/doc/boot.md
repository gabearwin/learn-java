# 起步
### 创建项目
- 新建项目，选择Spring Initializr
- 启动项目
    - 直接运行`BootApplication.java`
    - 进入项目目录，执行`mvn spring-boot:run`命令
    - 先编译项目`mvn install`，进入target目录，执行`java -jar project-name.jar`命令

### 属性配置
- 文件
    - 在application.properties文件中配置相关属性
    - 在application.yml文件中配置属性

- 引用
    1. 方式一
    
        在application.yml配置文件中
        ```text
            name: yan
            age: 18
            person: "name is ${name} and age is ${age}"
        ```
        在外部使用
        ```text
            @Value("${name}")
            private String name;
        
            @Value("${age}")
            private Integer age;
        
            @Value("${person}")
            private String person;
        ```
    2. 方式二
    
        在application.yml配置文件中对配置分组
        ```text
            person:
              name: yan
              age: 18
        ```
        编写对应属性类
        ```java
            @ConfigurationProperties(prefix = "person")
            @Component
            public class PersonProp {
                private String name;
                private Integer age;
            
                public String getName() {
                    return name;
                }
            
                public void setName(String name) {
                    this.name = name;
                }
            
                public Integer getAge() {
                    return age;
                }
            
                public void setAge(Integer age) {
                    this.age = age;
                }
            }
        ```
        在外部使用
        ```text
            @Autowired
            private PersonProp person;
            
            person.getName() + person.getAge();     
        ```
    3. 配置不同环境
    
        配置文件编写如下
        ```text
        # application-dev.yml文件配置开发环境
            server:
              port: 8080
            person:
              name: yan
              age: 18
              
       
        # application-prod.yml文件配置生产环境
            server:
              port: 8081
            person:
              name: yan
              age: 20
              
        # 在application.yml文件中指定当前环境
            spring:
              profiles:
                active: dev
        ```
启动项目
- 通过IDEA直接启动开发环境，对应URL`http://localhost:8080/hello`
- 编译项目`mvn install`，使用`java -jar target/project-name.jar --spring.profiles.active=prod`
启动线上环境，对应URL`http://localhost:8081/hello`








