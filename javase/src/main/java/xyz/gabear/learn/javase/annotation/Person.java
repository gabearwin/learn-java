package xyz.gabear.learn.javase.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited // 允许子类继承
@Documented // 生成javadoc时会包含注解
public @interface Person {
    String name();

    int age() default 18;
}

@Person(name = "xiong")
class MyClass {
    @Person(name = "yan", age = 20)
    public String friend() {
        return "OK";
    }
}

class ParseAnnotation {
    public static void main(String[] args) {
        Class clazz = MyClass.class;

        // 第一种解析方式
        boolean isPresent = clazz.isAnnotationPresent(Person.class);
        if (isPresent) {
            Person person = (Person) clazz.getAnnotation(Person.class);
            System.out.println(person.name() + ", " + person.age()); // xiong, 18
        }

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            // 第二种解析方式
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Person) {
                    Person person = (Person) annotation;
                    System.out.println(person.name() + ", " + person.age()); // yan, 20
                }
            }
        }
    }
}