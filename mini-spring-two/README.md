# mini-spring-two

### 项目结构
```text
.
├── framework
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── xyz
│       │   │       └── gabear
│       │   │           └── spring
│       │   │               ├── beans                               //Bean管理
│       │   │               │   ├── Autowired.java
│       │   │               │   ├── Bean.java
│       │   │               │   └── BeanFactory.java
│       │   │               ├── context
│       │   │               ├── core                                //扫描加载类
│       │   │               │   └── ClassScanner.java
│       │   │               ├── starter                             //Spring容器启动类
│       │   │               │   └── MiniSpringApplication.java
│       │   │               └── web
│       │   │                   ├── handler                         //URL->Controller的映射管理
│       │   │                   │   ├── HandlerManager.java
│       │   │                   │   └── MappingHandler.java
│       │   │                   ├── mvc                             //MVC相关注解
│       │   │                   │   ├── Controller.java
│       │   │                   │   ├── RequestMapping.java
│       │   │                   │   └── RequestParam.java
│       │   │                   ├── server                          //嵌入Tomcat容器
│       │   │                   │   └── TomcatServer.java
│       │   │                   └── servlet                         //主servlet入口
│       │   │                       ├── DispatcherServlet.java
│       │   │                       └── TestServlet.java
│       │   └── resources
│       └── test
│           ├── java
│           └── resources
├── test
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── xyz
│       │   │       └── gabear
│       │   │           └── salary                                  //一个模拟项目，使用上面的mini-spring框架
│       │   │               ├── Application.java
│       │   │               ├── controller
│       │   │               │   └── SalaryController.java
│       │   │               └── service
│       │   │                   └── SalaryService.java
│       │   └── resources
│       └── test
│           ├── java
│           └── resources
```

### 执行方式
- 方式一：
    - 直接运行 [test/src/main/java/xyz/gabear/salary/Application.java](mini-spring-two-test/src/main/java/xyz/gabear/salary/Application.java)
    - 浏览器访问 `http://localhost:6699/get_salary.json?experience=3&name=100`
- 方式二：
    - 打包 `./gradlew clean build`
    - 运行 `java -jar test/build/libs/test-1.0-SNAPSHOT.jar`
    - 浏览器访问 `http://localhost:6699/get_salary.json?experience=3&name=100`