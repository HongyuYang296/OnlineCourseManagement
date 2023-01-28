package com.example.dataclean.controller;

import com.example.dataclean.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {
    @Autowired
    private UserService service;
    private UserRepository repository;





    @GetMapping("/users")
    public String showUserList(@Param("keyword") String keyword, Model model,RedirectAttributes ra) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New");
        if (keyword == null){
            List<User> listUsers = service.listAll();
            model.addAttribute("listUsers", listUsers);
            return "users";
        }
        else {
            System.out.println("keyword: "+ keyword);
            String key = "\""+keyword+"\"";;
            System.out.println("keyword: "+ key);
            List<User> listUsers = service.searchResult(key);
            System.out.println("list: "+ listUsers);
            model.addAttribute("listUsers", listUsers);
            ra.addAttribute("message", "Search result of "+ keyword);
            return "users";
        }

    }



    @RequestMapping("users/getOne")
    @ResponseBody
    public Optional<User> getOne(Integer id, Model model) {
        Optional<User> user = service.getOne(id);
        model.addAttribute("user", user);
        return service.getOne(id);
    }

    @RequestMapping("users/searchError/getOne")
    @ResponseBody
    public Optional<User> getOneSearch(Integer id, Model model) {
        Optional<User> user = service.getOne(id);
        model.addAttribute("user", user);
        return service.getOne(id);
    }


    @PostMapping(value = "/users/save")
    public String saveUser(User user,RedirectAttributes ra) throws UserNotFoundException {
            try{
                service.save(user);
                ra.addFlashAttribute("message", "The user "+user.getFirstname()+" "+user.getLastname()+"  has been update successfully");
                return "redirect:/users";
            } catch (Exception e) {
                ra.addFlashAttribute("message", user.getFirstname()+" "+user.getLastname()+ " is already in database");
                return "redirect:/users";
            }
    }

    @RequestMapping(value = "/users/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateUser(Integer id, User user, Model model, RedirectAttributes ra) {
        return updateUsers(id, user, model, ra,"redirect:/users");
    }

    @RequestMapping(value = "/users/searchError/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateSearchedUser(Integer id, User user, Model model, RedirectAttributes ra) {
        return updateUsers(id, user, model, ra, "redirect:/users/searchError");
    }

    @RequestMapping (value = "/users/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Integer id, RedirectAttributes ra,Model model) {
        try{
            ra.addFlashAttribute("message", "The user "+service.get(id).getFirstname()+ " " +service.get(id).getLastname()+" has been deleted");
            service.delete(id);
            return "redirect:/users";
        }catch (Exception e){
            System.out.println("error");
            ra.addFlashAttribute("message", "some error");
            return "redirect:/users";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "cant find");
            return "redirect:/users";
        }
    }

    @RequestMapping (value = "/users/searchError/delete",  method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteError(Integer id, RedirectAttributes ra) {
        try{
            ra.addFlashAttribute("message", "The user "+ service.get(id).getFirstname()+ " " +service.get(id).getLastname()+" has been deleted");
            service.delete(id);
            return "redirect:/users/searchError";
        }catch (Exception e){
            System.out.println("error");
            ra.addFlashAttribute("message", "some error");
            return "redirect:/users/searchError";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "cant find users");
            return "redirect:/users/searchError";
        }

    }

    private String updateUsers(Integer id, User user, Model model, RedirectAttributes ra, String url) {
        try {
            model.addAttribute("user", user);
            service.update(user);
            ra.addFlashAttribute("message", "The user "+service.get(id).getFirstname()+" "+service.get(id).getLastname()+"  has been update successfully");
            return url;
        } catch (Exception e) {
            ra.addFlashAttribute("message", "code is not correct!");
            return url;
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "cant find");
            return url;
        }
    }


    @GetMapping(value = "/users/searchError")
    public String getResult(@Param("keyword") String keyword,Model model){
        model.addAttribute("user", new User());
        if (keyword == null){
            List<User> listUsers = service.searchError();
            model.addAttribute("listUsers", listUsers);
        }
        else {
            String key = "\""+keyword+"\"";;
            List<User> listUsers = service.searchResult(key);
            model.addAttribute("listUsers", listUsers);
        }
        return "update_database";
    }

    @GetMapping(value = "/users/searchError/result")
    public String searchError(Model model, RedirectAttributes ra) {
        model.addAttribute("user", new User());

        try {
            List<User> listUsers = service.searchError();
            if (listUsers.isEmpty()){
                ra.addFlashAttribute("message", "no error found");
            }else {
                ra.addFlashAttribute("message", "mistake found");
            }
            return "redirect:/users/searchError";

        } catch (Exception e) {
            ra.addFlashAttribute("message", "some error");
            return "redirect:/users/searchError";
        }

    }

}



















