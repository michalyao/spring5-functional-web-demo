package top.yoryor.webfluxdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by yaoyao on 2017/10/2 上午10:49.
 */
@Controller
public class PageController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
