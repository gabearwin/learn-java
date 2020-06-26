package xyz.gabear.learn.javase.exercise;

/**
 * 异常学习
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        printAnswer();
        System.out.println("---------------------");
        System.out.println("return answer is " + returnAnswer());
    }

    private static void throwEx() {
        throw new RuntimeException("抛出了异常");
    }

    private static void printAnswer() {
        try {
            throwEx();
            System.out.println("A");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("B");
        } finally {
            System.out.println("C");
        }
        System.out.println("D");
    }

    private static String returnAnswer() {
        try {
            throwEx();
            return "A";
        } catch (Exception e) {
            e.printStackTrace();
            return "B";
        } finally {
            return "C";
        }
        // System.out.println("end"); // 这一行编译时就会出错。因为代码执行到不了这里。
    }
}

/*
java.lang.RuntimeException: 抛出了异常
	at xyz.gabear.exercise.ExceptionDemo.throwEx(ExceptionDemo.java:14)
	at xyz.gabear.exercise.ExceptionDemo.printAnswer(ExceptionDemo.java:19)
	at xyz.gabear.exercise.ExceptionDemo.main(ExceptionDemo.java:8)
B
C
D
---------------------
java.lang.RuntimeException: 抛出了异常
	at xyz.gabear.exercise.ExceptionDemo.throwEx(ExceptionDemo.java:14)
	at xyz.gabear.exercise.ExceptionDemo.returnAnswer(ExceptionDemo.java:32)
	at xyz.gabear.exercise.ExceptionDemo.main(ExceptionDemo.java:10)
return answer is C
*/
