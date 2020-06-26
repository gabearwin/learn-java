package xyz.gabear.learn.javase.annotation.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyDB {

    @SuppressWarnings("unchecked")
    public static String getSQL(Object tableBean) {
        StringBuilder sql = new StringBuilder();
        // 1.获取到Class
        Class c = tableBean.getClass();
        // 2.获取到table的名字
        boolean isExist = c.isAnnotationPresent(Table.class);
        if (!isExist) {
            return null;
        }
        Table table = (Table) c.getAnnotation(Table.class);
        sql.append("select * from ").append(table.value()).append(" where 1=1 ");

        // 3.遍历所有的字段
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            // 4.处理每个字段对应的sql

            // 4.1 拿到字段名
            boolean fExist = field.isAnnotationPresent(Column.class);
            if (!fExist) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();

            // 4.2 拿到字段的值
            String fieldName = field.getName();
            // 拼接出get方法的名字
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Object filedValue = null;
            try {
                // 根据方法名字用反射获取方法对象
                Method getMethod = c.getMethod(getMethodName);
                filedValue = getMethod.invoke(tableBean);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 4.3拼装sql
            if (filedValue == null || (filedValue instanceof Integer && (Integer) filedValue == 0)) {
                continue;
            }
            sql.append(" and ").append(columnName);
            // 如果是String类型的sql语句需要拼接上单引号
            if (filedValue instanceof String) {
                // 如果字符串中包含逗号，则说明是in集合查找
                if (((String) filedValue).contains(",")) {
                    String[] values = ((String) filedValue).split(",");
                    sql.append(" in(");
                    for (String v : values) {
                        sql.append("'").append(v).append("'").append(",");
                    }
                    // 删掉最后一个逗号
                    sql.deleteCharAt(sql.length() - 1);
                    sql.append(")");
                } else {
                    sql.append(" = ").append("'").append(filedValue).append("'");
                }
            } else if (filedValue instanceof Integer) {
                sql.append(" = ").append(filedValue);
            }
        }
        return sql.toString();
    }
}
