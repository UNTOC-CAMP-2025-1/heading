package org.untoc_camp.controller.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterHomeController {
    @GetMapping("/register-home")
    public String registerHome() {
        return "register_home.html";
    }
}
