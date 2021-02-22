package oliver.design_mode.proxy.static_proxy;

/**
 * @author Oliver
 * @date 2021/2/22 15:13
 * @description
 */
public class Landlord implements Rent{
    @Override
    public void rent() {
        System.out.println("我要出租房屋");
    }
}
