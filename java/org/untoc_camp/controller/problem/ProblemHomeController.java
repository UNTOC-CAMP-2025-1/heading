package org.untoc_camp.controller.problem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProblemHomeController {
    @GetMapping("/problem")
    public String home(){
        return "problem";
    }
}
