package org.untoc_camp.controller.ranking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ranking")
public class RankingHomeController {
    @GetMapping("/home")
    public String rankingHome() {
        return "ranking";
    }
}
