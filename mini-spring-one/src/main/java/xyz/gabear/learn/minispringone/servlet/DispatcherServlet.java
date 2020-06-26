package xyz.gabear.learn.minispringone.servlet;

import xyz.gabear.learn.minispringone.annotation.Controller;
import xyz.gabear.learn.minispringone.annotation.Qualifier;
import xyz.gabear.learn.minispringone.annotation.RequestMapping;
import xyz.gabear.learn.minispringone.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 核心控制器
 */
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 4140745521931720859L;
    // 保存配置文件
    private Properties properties = new Properties();
    // 类的全路径名集合
    private List<String> classNameList = new ArrayList<>();
    // 容器IOC
    private Map<String, Object> ioc = new HashMap<>();
    // 保存URL和Controller的关系
    private Map<String, Method> handlerMapping = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        // 2.扫描用户设定包下的所有类，将类全限定名加入classNameList
        doLoadClass(properties.getProperty("scanPackage"));
        // 3.拿到扫描到的类，通过反射实例化，并放入IOC容器中 (K, V) => (beanName, bean)
        doInstance();
        // 4.初始化HandlerMapping(将URL和method对应上)
        initHandlerMapping();
        // 5.实现注入
        doIoc();
    }

    private void doLoadConfig(String location) {
        // 将web.xml中contextConfigLocation对应的value值的文件加载到流
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(location);
        try {
            // 加载流中的配置文件
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关流
            if (null != resourceAsStream) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doLoadClass(String packageName) {
        // 类加载器加载指定位置的文件。指定位置为 xyz/gabear/mvc
        URL resource = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File dir = new File(resource.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                doLoadClass(packageName + "." + file.getName());
            } else {
                String className = packageName + "." + file.getName().replace(".class", "");
                classNameList.add(className);
                System.out.println("Spring容器扫描到类：" + packageName + "." + file.getName());
            }
        }
    }

    private void doInstance() {
        if (classNameList.isEmpty()) {
            return;
        }
        try {
            for (String className : classNameList) {
                Class<?> clazz = Class.forName(className);
                // 把带有@Controller和@Service注解的类放入IOC容器
                if (clazz.isAnnotationPresent(Controller.class)) {
                    Controller controller = clazz.getAnnotation(Controller.class);
                    String key = controller.value();
                    if (key != null && !"".equals(key)) {
                        ioc.put(key, clazz.newInstance());
                    } else {
                        ioc.put(toLowerFirstWord(clazz.getSimpleName()), clazz.newInstance());
                    }
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = clazz.getAnnotation(Service.class);
                    String key = service.value();
                    if (key != null && !"".equals(key)) {
                        ioc.put(key, clazz.newInstance());
                    } else {
                        ioc.put(toLowerFirstWord(clazz.getSimpleName()), clazz.newInstance());
                    }
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private String toLowerFirstWord(String str) {
        return (str.charAt(0) + "").toLowerCase() + str.substring(1);
    }

    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(Controller.class)) {
                continue;
            }
            String baseUrl = "";
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
                baseUrl = annotation.value();
            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                String methodUrl = "";
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                methodUrl = (baseUrl + "/" + annotation.value()).replaceAll("/+", "/");
                // 将请求的URL和对应的方法放入handlerMapping
                handlerMapping.put(methodUrl, method);
                try {
                    ioc.put(methodUrl, clazz.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                System.out.println(methodUrl + ", " + method);
            }
        }
    }

    private void doIoc() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Qualifier.class)) {
                    Qualifier annotation = field.getAnnotation(Qualifier.class);
                    String key = null;
                    String value = annotation.value();
                    if (value != null && !"".equals(value)) {
                        key = value;
                    } else {
                        key = field.getName();
                    }
                    try {
                        field.set(entry.getValue(), ioc.get(key));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().println("500! Server Error.");
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (handlerMapping.isEmpty()) {
            return;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        // 裁剪URL并把多个/替换成一个
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        System.out.println("请求地址为:" + url + ", IP:" + req.getRemoteAddr());
        if (!handlerMapping.containsKey(url)) {
            resp.getWriter().println("400! Not Found.");
            return;
        }
        Method method = handlerMapping.get(url);
        // 获取方法参数列表
        Class<?>[] parameterTypes = method.getParameterTypes();
        // 获取请求的参数
        Map<String, String[]> parameterMap = req.getParameterMap();
        Object[] paramValues = new Object[parameterTypes.length];
        // 使用请求参数填充方法申明参数列表
        for (int i = 0; i < parameterTypes.length; i++) {
            String paramClassName = parameterTypes[i].getSimpleName();
            if ("HttpServletRequest".equals(paramClassName)) {
                paramValues[i] = req;
                continue;
            }
            if ("HttpServletResponse".equals(paramClassName)) {
                paramValues[i] = resp;
                continue;
            }
            if ("String".equals(paramClassName)) {
                for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
                    String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
                    paramValues[i] = value;
                }
            }
        }
        // 利用反射机制来调用
        method.invoke(this.ioc.get(url), paramValues);
    }
}
