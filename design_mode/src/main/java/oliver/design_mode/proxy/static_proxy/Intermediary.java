package oliver.design_mode.proxy.static_proxy;

/**
 * @author Oliver
 * @date 2021/2/22 15:14
 * @description
 */
public class Intermediary implements Rent{
    private Landlord landlord;
    public Intermediary(){}

    public Intermediary(Landlord landlord){
        this.landlord = landlord;
    }
    @Override
    public void rent() {
        landlord.rent();
        signContract();
    }

    public void signContract(){
        System.out.println("签合同");
    }
}
