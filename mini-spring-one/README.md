访问地址 [http://localhost:8080/study_mvc_war_exploded/controller/test](http://localhost:8080/study_mvc_war_exploded/controller/test)

```text
Spring容器扫描到类：xyz.gabear.mvc.annotation.Controller.class
Spring容器扫描到类：xyz.gabear.mvc.annotation.Qualifier.class
Spring容器扫描到类：xyz.gabear.mvc.annotation.RequestMapping.class
Spring容器扫描到类：xyz.gabear.mvc.annotation.Service.class
Spring容器扫描到类：xyz.gabear.mvc.controller.StudyController.class
Spring容器扫描到类：xyz.gabear.mvc.service.impl.StudyServiceImpl.class
Spring容器扫描到类：xyz.gabear.mvc.service.StudyService.class
Spring容器扫描到类：xyz.gabear.mvc.servlet.DispatcherServlet.class
/controller/test, public void xyz.gabear.mvc.controller.StudyController.test(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,java.lang.String)
/controller/hello, public java.lang.String xyz.gabear.mvc.controller.StudyController.hello()
请求地址为:/controller/test, IP:0:0:0:0:0:0:0:1
20
调用了 StudyServiceImpl 类中的 insert 方法
调用了 StudyServiceImpl 类中的 delete 方法
调用了 StudyServiceImpl 类中的 update 方法
调用了 StudyServiceImpl 类中的 select 方法
请求地址为:/controller/hello, IP:0:0:0:0:0:0:0:1
Hello world!
```