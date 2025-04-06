package org.untoc_camp.controller.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterHomeController {
    @GetMapping("/home")
    public String registerHome() {
        return "register_home.html";
    }
}
