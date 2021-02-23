package oliver.design_mode.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Oliver
 * @date 2021/2/22 16:44
 * @description 用于生成代理类
 */
public class ProxyInvocationHandler implements InvocationHandler {

    /**
     * <p>被代理的接口</p>
     **/
    private Object target;

    public void setTarget(Object object) {
        this.target = object;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }

    /**
     * <p>Description: 处理代理实例，返回结果 </p>
     * @author Oliver
     **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质就是使用反射机制实现
        return method.invoke(target, args);
    }
}
