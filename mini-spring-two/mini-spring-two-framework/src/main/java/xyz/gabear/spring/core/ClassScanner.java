package xyz.gabear.spring.core;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类扫描、加载工具类
 */
public class ClassScanner {
    public static List<Class<?>> scanClasses(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<>();
        String path = packageName.replace(".", "/");
        // 通过当前类加载器获取指定路径下所有的文件资源
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (resource.getProtocol().contains("jar")) {           // 如果是 jar 文件
                JarURLConnection jarURLConnection = (JarURLConnection) resource.openConnection();
                String jarFilePath = jarURLConnection.getJarFile().getName();
                classList.addAll(getClassesFromJar(jarFilePath, path));
            } else if (resource.getProtocol().contains("file")) {   // 如果是 file 文件
                // file:/Users/gaxiong/Documents/GitProject/mini-spring/mini-spring/test/out/production/classes/xyz/gabear/salary
                String filePath = URLDecoder.decode(resource.getFile(), "UTF-8");
                classList.addAll(getClassesFromFile(filePath, path));
            }
        }
        return classList;
    }

    /**
     * 从 jar 文件中加载类
     *
     * @param jarFilePath jar 文件路径
     * @param path        指定加载 path 路径下的 class
     * @return 加载好的 class 集合
     */
    private static List<Class<?>> getClassesFromJar(String jarFilePath, String path) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        JarFile jarFile = new JarFile(jarFilePath);
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String entryName = jarEntry.getName();// xyz/gabear/spring/test/Test.class
            if (entryName.startsWith(path) && entryName.endsWith(".class")) {
                // 对每个.class文件资源获取其类全限定名并加载此类
                String classFullName = entryName.replace("/", ".").substring(0, entryName.length() - 6);
                classes.add(Class.forName(classFullName));
            }
        }
        return classes;
    }

    /**
     * 从 file 中加载类
     *
     * @param filePath file 文件路径
     * @param path     指定加载 path 路径下的 class
     * @return 加载好的 class 集合
     */
    private static List<Class<?>> getClassesFromFile(String filePath, String path) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        List<String> classNameList = new ArrayList<>();
        getAllClassName(filePath, path, classNameList);
        for (String name : classNameList) {
            classes.add(Class.forName(name));
        }
        return classes;
    }

    private static void getAllClassName(String filePath, String path, List<String> classNameList) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().endsWith(".class")) {
                    classNameList.add(path.replace("/", ".") + "." + file.getName().substring(0, file.getName().length() - 6));
                }
            } else if (file.isDirectory()) {
                getAllClassName(filePath + "/" + file.getName(), path + "/" + file.getName(), classNameList);
            }
        }
    }
}
