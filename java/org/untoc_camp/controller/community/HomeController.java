package org.untoc_camp.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "community_home.html";
    }
}
