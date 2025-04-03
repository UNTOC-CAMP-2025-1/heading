package org.untoc_camp.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginHomeController {
    @GetMapping("/login_home")
    public String registerHome() {
        return "login_home.html";
    }
}
