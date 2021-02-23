package oliver.design_mode.proxy.dynamic_proxy;

/**
 * 描述
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/23 10:38:24
 */
public class Client {

    public static void main(String[] args) {

        //真实角色
        Landlord landlord = new Landlord();
        //代理角色，动态生成
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        //通过调用程序处理角色来处理我们要调用的接口对象
        pih.setTarget(landlord);
        //这里的代理对象是自动生成的，这样就可以不用每个代理对象对应一个代理类
        Rent proxy = (Rent) pih.getProxy();
        proxy.rent();
    }
}
