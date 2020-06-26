package xyz.gabear.learn.javase.exercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Child {
    private String name;
    private Integer age;
}

public class StreamDemo {
    public static void main(String[] args) {
        // 现在有一个List<Child>，里面可能有名字相同的Child，我们要对每个Child取出一条数据，当名字相同时取出age最大的一条
        List<Child> childList = new ArrayList<>();
        childList.add(new Child("gaoxiong", 23));
        childList.add(new Child("yanqiang", 25));
        childList.add(new Child("gaoxiong", 25));
        childList.add(new Child("gaoxiong", 24));
        childList.add(new Child("gabear", 24));

        // 第一种：先用名字进行分组，再对同一个名字下的List进行排序，每个List取出age最大的那条数据
        Collection<Child> results1 = childList.stream().collect(
                Collectors.groupingBy(Child::getName, Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            list.sort(Comparator.comparingInt(Child::getAge).reversed());
                            return list.get(0);
                        })
                )).values();
        System.out.println(results1);

        // 第二种：先用名字进行分组，再对同一个名字下的List进行reduce操作，取出age最大的那条数据。
        // 对比第一种方式，第一种对List排序要nlog(n)，而第二种reduce只要 o(n)
        Collection<Child> results2 = childList.stream().collect(
                Collectors.groupingBy(Child::getName, Collectors.collectingAndThen(
                        Collectors.reducing((o1, o2) -> o1.getAge() - o2.getAge() > 0 ? o1 : o2),
                        Optional::get)
                )).values();
        System.out.println(results2);

        // 第三种：直接聚合成Map，对相同的name的记录，取出age更大的一条
        Collection<Child> results3 = childList.stream().collect(
                Collectors.toMap(Child::getName, Function.identity(), (o1, o2) -> o1.getAge() - o2.getAge() > 0 ? o1 : o2)
        ).values();
        System.out.println(results3);
    }
}

/*
[Child(name=yanqiang, age=25), Child(name=gabear, age=24), Child(name=gaoxiong, age=25)]
[Child(name=yanqiang, age=25), Child(name=gabear, age=24), Child(name=gaoxiong, age=25)]
[Child(name=yanqiang, age=25), Child(name=gabear, age=24), Child(name=gaoxiong, age=25)]
*/
