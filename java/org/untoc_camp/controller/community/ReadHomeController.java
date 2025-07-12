package org.untoc_camp.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/community")
public class ReadHomeController {

    @GetMapping("/read/home/{id}")
    public String readContent(@PathVariable Long id, Model model) {
        model.addAttribute("postId", id);
        return "community_content"; // community_content.html
    }
}
