package org.untoc_camp.controller.etc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchHomeController {
    @GetMapping("/search/home")
    public String search() {
        return "search";
    }
}
