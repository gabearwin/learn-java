### 集成Mybatis Plus步骤
1. 在pom.xml文件引入相关的jar包依赖
2. 实现XxxMapper接口。通过此接口来操作数据持久化
3. 对XxxDO实体进行注解的定义，如：数据库表名，字段的定义
4. 如需修改Plus默认配置，需实现MybatisPlusConfig类
5. 如需要自定义一个方法，需实现XxxMapper.xml，来定义自定义SQL

### 自动更新系统级字段
1. 公共元数据处理器
2. 为XxxDO配置注解

### 集成验证框架步骤
1. 在pom.xml引入相关的jar包支持
2. 在待验证的实体里面添加相应的注解
3. 在Controller中添加相应的注解
4. 做参数校验工具类，完成service层的参数校验

### 实现统一异常处理功能
1. 实现一个异常处理的类，并且用@ControllerAdvice修饰

### 集成CaffeineCache缓存功能
1. 
    @Cacheable : 缓存数据，一般用在查询方法上。将查询到的数据进行缓存 
    @CachePut : 更新方法上，将数据从缓存中进行更新
    @CacheEvict : 删除缓存
2. pom.xml cache相关的jar包支持
3. CacheManager Bean
4. 使用注解，标识我们的方法哪些需要缓存

### 集成Guava令牌桶实现全局限流功能
1. 先pom.xml引入Guava工具包的支持
2. 定义一个拦截器，实现令牌的发放和获取
3. 将拦截器配置到web系统中

### 使用TraceId实现日志跟踪
1. 建立一个过滤器，在过滤器中给线程设置TraceId
2. 将日志配置文件进行修改，把TraceId打印到日志中

### 文件上传下载
1. 文件上传的Controller，负责处理文件上传
2. 文件上传的服务接口，通过接口的形式来定义出文件上传的功能
3. 实现文件上传业务逻辑
4. 文件下载，采用静态路径映射的方式实现

### 数据导出功能
1. pom.xml把相关的jar配置好
2. UserController新增加数据导出的方法
3. 要实现数据导出的功能
    * 定义导出实体
    * 分批加载数据，分批使用EasyExcel导出
    * 将导出的文件上传
    
### 导出功能异步化
1. 先创建线程池
2. 将导出方法使用@Aync注解标记为异步执行

### 使用Swagger2帮助我们生成API文档
1. pom.xml引入jar包
2. 配置Swagger2的配置类
3. Controller及相关的实体中写对应的注解