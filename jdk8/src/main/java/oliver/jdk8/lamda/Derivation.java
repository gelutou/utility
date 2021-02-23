package oliver.jdk8.lamda;

/**
 * 描述 推导lamda表达时的演变
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/23 11:26:31
 */
public class Derivation {

    public static void main(String[] args) {
        //1.普通调用方式
        GoLamda goLamda = new GoLamdaPrint();
        goLamda.print("1");

        //2. 静态内部类调用
        GoLamda goLamda02 = new StaticGoLamdaPrint();
        goLamda02.print("1");

        class LocalInnerPrint implements GoLamda{
            @Override
            public void print(String str) {
                System.out.println("go lamda 3...");
            }
        }
        //3. 局部内部类
        GoLamda goLamda03 = new LocalInnerPrint();
        goLamda03.print("1");

        //4. 匿名内部类，必须借助接口或父类创建
        GoLamda goLamda04 = new GoLamda() {
            @Override
            public void print(String str) {
                System.out.println("go lamda 4...");
            }
        };
        goLamda04.print("1");
        //lamda 既然是一个函数式接口，只会有一个方法，并且匿名内部类没有名称，那么类和方法都可以简化不写
        GoLamda goLamda05 = str -> {
            //当花括号内只有一行时能够省略花括号，参数可以去掉参数类型，要去都去，如果只有一个参数时才能去掉小括号
            System.out.println("go lamda final...");
            //System.out.println("go lamda final...");
        };
        goLamda05.print("1");
    }

    /**
     * <p>Description: 第二种方式，创建静态内部类，这样不用多创建一个文件</p>
     * <p>Date: 2021-02-23 13:51</p>
     * @author Oliver
     **/
    static class StaticGoLamdaPrint implements GoLamda{
        @Override
        public void print(String str) {
            System.out.println("go lamda 2...");
        }
    }
}

/**
 * <p>Description: 一般我们定义一个函数式接口，来进行实现</p>
 * <p>Date: 2021-02-23 11:27</p>
 * @author Oliver
 **/
interface GoLamda{
    /**
     * <p>Description: 随便打印</p>
     * <p>Date: 2021-02-23 11:34</p>
     * @author Oliver
     **/
    void print(String str);
}
/**
 * <p>Description: 这是常规实现GoLamda接口</p>
 * <p>Date: 2021-02-23 11:28</p>
 * @author Oliver
 **/
class GoLamdaPrint implements GoLamda {
    @Override
    public void print(String str) {
        System.out.println("go lamda 1...");
    }
}
