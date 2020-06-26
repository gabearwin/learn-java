package xyz.gabear.learn.javase.exercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 在List的循环中直接删除元素，后面的元素都会向前移动一位，导致出现错乱。
 * 所以不要直接在循环中删除元素。应该用迭代器。
 */
public class ListRemoveDemo {
    public static void main(String[] args) {
        // 错误方式
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        for (int i = 0; i < list.size(); i++) {
            String cur = list.get(i);
            if ("1".equals(cur)) {
                list.remove(i);
                // i=i-1; // 这里可以修正index使程序正常运行
            } else {
                list.set(i, cur + "1");
            }
        }
        System.out.println(list); // [2, 31]

        // 正确方式
        list.clear();
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String cur = it.next();
            if ("1".equals(cur)) {
                it.remove();
            }
        }
        System.out.println(list); // [2, 3]
    }
}
