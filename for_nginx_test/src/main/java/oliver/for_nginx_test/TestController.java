package oliver.for_nginx_test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oliver
 * @date 2021/2/20 16:01
 * @description
 */
@RestController
public class TestController {

    @RequestMapping(value = "/print")
    public String print(){
        System.out.println("i'm in");
        return "i'm in";
    }
}
