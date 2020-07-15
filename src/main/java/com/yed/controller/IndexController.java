package com.yed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author CaoSS
 * @Date 20/7/6 15:21
 * @Version 1.0
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }
    public static void main(String[] args) {
        int s = 23045;
        char $ = '恩';
        System.out.println(s-(-1));
        System.out.println($);
    }
}
