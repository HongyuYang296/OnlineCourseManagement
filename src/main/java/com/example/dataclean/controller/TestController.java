package com.example.dataclean.controller;

import com.example.dataclean.user.User;
import com.example.dataclean.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TestController {
@Autowired
private UserService service;

//    @GetMapping("/test")
//    public String showUserList(@Param("keyword") String keyword, Model model, RedirectAttributes ra) {
//        model.addAttribute("user", new User());
//        model.addAttribute("pageTitle", "Add New");
//        if (keyword == null){
//            List<User> listUsers = service.listAll();
//            model.addAttribute("listUsers", listUsers);
//            return "test";
//        }
//        else {
//            System.out.println("keyword: "+ keyword);
//            String key = "\""+keyword+"\"";;
//            System.out.println("keyword: "+ key);
//            List<User> listUsers = service.searchResult(key);
//            System.out.println("list: "+ listUsers);
//            model.addAttribute("listUsers", listUsers);
//            ra.addAttribute("message", "Search result of "+ keyword);
//            return "test";
//        }
//
//    }


    @GetMapping("/tests")
    public String showUserLists() {
        return "test";

    }
}
