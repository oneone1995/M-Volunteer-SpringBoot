package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.model.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangl on 2017/2/9.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对志愿者显示");
        model.addAttribute("msg", msg);
        return "index";
    }
}
