package org.untoc_camp.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginHomeController {
    @GetMapping("/home")
    public String registerHome() {
        return "login_home.html";
    }
}
