package me.afua.securitydemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String showIndex()
    {
        return "index";
    }

    @RequestMapping("/admin")
    public String showAdminIndex()
    {
        return "index";
    }

    @RequestMapping("/user")
    public String showOrdinaryUserIndex()
    {
        return "index";
    }
}
