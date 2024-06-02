package dgg.motorsphere.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularController {
    @RequestMapping("/")
    public String index(){return "index.html";}
}
