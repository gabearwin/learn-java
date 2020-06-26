package xyz.gabear.learn.javase.annotation.example;

public class MyAnnotationTest {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setId(10); // 查询id为10的用户

        User user2 = new User();
        user2.setUserName("xiong"); // 查询用户名为xiong的用户
        user2.setAge(20);

        User user3 = new User();
        user3.setEmail("a@sina.com,b@163.com,c@qq.com"); // 查询邮箱为其中任意一个的记录

        System.out.println(MyDB.getSQL(user1));
        System.out.println(MyDB.getSQL(user2));
        System.out.println(MyDB.getSQL(user3));

        /*****************************************/

        Group group = new Group();
        group.setCnName("技术产品中心");
        group.setContact("a@tech.cn");

        System.out.println(MyDB.getSQL(group));
    }

}
/*
select * from user where 1=1  and id = 10
select * from user where 1=1  and user_name = 'xiong' and age = 20
select * from user where 1=1  and email in('a@sina.com','b@163.com','c@qq.com')
select * from group where 1=1  and cn_name = '技术产品中心' and contact = 'a@tech.cn'
*/
