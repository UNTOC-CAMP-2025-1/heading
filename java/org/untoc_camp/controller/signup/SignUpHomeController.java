package org.untoc_camp.controller.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpHomeController {
    @GetMapping("/home")
    public String registerHome() {
        return "signup.html";
    }
}
