package com.example.dataclean.controller;


import com.example.dataclean.codelist.*;
import com.example.dataclean.user.User;
import com.example.dataclean.user.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Controller
public class CodeListController {

    @Autowired
    private CodeListServer service;

//    @GetMapping("/statistics")
//    public String showStatistic(Model model) {
//        List<CodeList> listUsers = service.listAll();
//        model.addAttribute("listUsers", listUsers);
//        return "statistics";
//
//    }

    @RequestMapping("/usersData")
    @ResponseBody
    public List<CodeList> test() throws JsonProcessingException {

//        List<Object[]> map = null;
        List<CodeList> listUsers = service.listAll();
//        for (CodeList codeList: listUsers) {
//            Object[] ob = ;
//
//        }
        String json2 = null;
        System.out.print("list users:  ");
        System.out.print(listUsers.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(listUsers);
            System.out.println(json);
            json2 = json;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return listUsers;
    }



    @GetMapping("/codeList")
    public String showUserList(@Param("keyword") String keyword, Model model,RedirectAttributes ra) {
        model.addAttribute("user", new CodeList());
        model.addAttribute("pageTitle", "Add New");
        if (keyword == null){
            List<CodeList> listUsers = service.listAll();
            model.addAttribute("listUsers", listUsers);
        }
        else {
            System.out.println("keyword: "+ keyword);
            String key = "\""+keyword+"\"";;
            System.out.println("keyword: "+ key);
            List<CodeList> listUsers = service.searchResult(key);
            System.out.println("list: "+ listUsers);
            model.addAttribute("listUsers", listUsers);
            ra.addAttribute("message", "Search result of "+ keyword);
        }
        return "codeList";

    }

    @RequestMapping(value = "/codeList/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateUser(Integer id, CodeList user, Model model, RedirectAttributes ra) {
        return updateUsers(id, user, model, ra,"redirect:/codeList");
    }


    private String updateUsers(Integer id, CodeList user, Model model, RedirectAttributes ra, String url) {
        try {
            model.addAttribute("user", user);
            service.update(user);
            ra.addFlashAttribute("message", service.get(id).getName()+" has been update successfully");
            return url;
        } catch (Exception e) {
            ra.addFlashAttribute("message", "code is not correct!");
            return url;
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "cant find");
            return url;
        }
    }

    @RequestMapping("codeList/getOne")
    @ResponseBody
    public Optional<CodeList> getOne(Integer id, Model model) {
        Optional<CodeList> user = service.getOne(id);
        model.addAttribute("user", user);
        return service.getOne(id);
    }


    @PostMapping(value = "/codeList/save")
    public String saveUser(CodeList user,RedirectAttributes ra) throws UserNotFoundException {
        try{
            service.save(user);
            ra.addFlashAttribute("message", "The user "+ user.getName()+ " has been update successfully");
            return "redirect:/codeList";
        } catch (Exception e) {
            ra.addFlashAttribute("message", user.getName()+ " is already in database");
            return "redirect:/codeList";
        }
    }


    @RequestMapping (value = "/codeList/delete",  method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id, RedirectAttributes ra) {
        try{
            ra.addFlashAttribute("message", "The user "+service.get(id).getName()+ " has been deleted");
            System.out.print("The user "+service.get(id).getName()+ " has been deleted");
            service.delete(id);
            return "redirect:/codeList";
        }catch (Exception e){
            System.out.println("error");
            ra.addFlashAttribute("message", "some error");
            return "redirect:/codeList";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "cant find");
            return "redirect:/codeList";
        }
    }

    }


