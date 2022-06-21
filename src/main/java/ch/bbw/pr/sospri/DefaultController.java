package ch.bbw.pr.sospri;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String index() {
        return "members";
    }

    @RequestMapping("/noSecurity")
    public String noSecurity() {
        return "Everybody can see this";
    }

}
