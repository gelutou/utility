package oliver.design_mode.proxy.static_proxy;

/**
 * @author Oliver
 * @date 2021/2/22 15:19
 * @description
 */
public class Client {

    public static void main(String[] args) {
        Intermediary intermediary = new Intermediary(new Landlord());
        intermediary.rent();
    }
}
