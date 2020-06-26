## 常用注解功能
```java
    /**
     * @Test 将一个普通的方法修饰成为一个测试方法
     *      @Test(expected=XX.class) 异常
     *      @Test(timeout=毫秒) 超时
     * @BeforeClass 他会在所有方法运行前被执行，static修饰
     * @AfterClass 他会在所有方法运行结束后被执行，static修饰
     * @Before 会在每个测试方法被运行前执行一次
     * @After 会在每个测试方法被运行后执行一次
     * @Ignore 所修饰的测试方法会被测试运行器忽略
     * @RunWith 修改测试运行器 需要继承org.junit.runner.Runner
     */
```

## 执行顺序
1. @BeforeClass修饰的方法会在所有方法被调用前被调用，该方法是静态的，所以当测试类被加载后接着就会运行它，且在内存中只会存在一份实例，比较适合加载配置文件。
2. @AfterClass修饰的方法会在所有方法被调用后被调用，该方法是静态的，通常用来对资源的清理，如关闭数据库连接。
3. @Before和@After会在每个测试方法前后各执行一次。
```text
    beforeClass
    before
    test-1
    after
    before
    test-2
    after
    afterClass
```

## 测试套件
```java
@RunWith(Suite.class)
@Suite.SuiteClasses({TaskTest1.class, TaskTest2.class, TaskTest3.class})
public class SuiteTest {
    // 不能包含其他方法...
}
```

## 参数化设置
```java
// 更改默认的测试运行器
@RunWith(Parameterized.class)
public class ParameterTest {

    // 声明变量来存放预期值和结果值
    int expected = 0;
    int input1 = 0;
    int input2 = 0;

    // 声明一个返回值为Collection的公共静态方法，并使用@Parameters进行修饰
    @Parameterized.Parameters
    public static Collection<Object[]> param() {
        return Arrays.asList(new Object[][]{
                {3, 1, 2},
                {4, 2, 2}
        });
    }

    // 声明一个带有参数的公共构造函数，并在其中为类变量赋值
    public ParameterTest(int expected, int input1, int input2) {
        this.expected = expected;
        this.input1 = input1;
        this.input2 = input2;
    }

    @Test
    public void testAdd() {
        assertEquals(expected, new Calculate().add(input1, input2));
    }
}
```
## 其他
- 使用 `mvn clean package` 对项目打包时会执行所有测试用例并返回结果
- 想要打包时跳过测试用例，可以使用 `mvn clean package -Dmaven.test.skip=true` 命令