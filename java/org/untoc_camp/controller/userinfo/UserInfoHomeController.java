package org.untoc_camp.controller.userinfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userinfo")
public class UserInfoHomeController {
    @GetMapping("/home/{nickname}")
    public String home(@PathVariable String nickname, Model model) {
        model.addAttribute("nickname", nickname);
        return "userinfo";
    }
}
