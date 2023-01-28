package com.example.dataclean.controller;

import com.example.dataclean.statistics.Statistic;
import com.example.dataclean.upload.UploadService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class PageController {

    @Autowired
    private UploadService service;
//    public static String uploadDirectory = System.getProperty()
    // deploy address of index.html
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @GetMapping(value = "/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping(value = "/index2")
    public String button() {
        return "index2";
    }


////
//    @GetMapping(value = "/test/search")
//    public String test(@Param("searchDate") String searchDate,Model model, RedirectAttributes ra) {
//        List<Attendance> listUsers  = service.searchResult(searchDate);
//        if (listUsers.isEmpty()) {
//            ra.addAttribute("message", "no record found");
//        }
//        else {
//            ra.addAttribute("message", listUsers.size()+" Search result");
//        }
//        return "redirect:/test";
//    }


//    @GetMapping(value = "/test")
//    public String showAttendanceList(@Param("searchDate") String searchDate,@Param("searchCode") String searchCode, Model model){
//        if (searchDate == null || searchDate.isEmpty() && searchCode == null ){
//            System.out.print("both null");
//            searchDate = String.valueOf(LocalDate.now());
//            List<Attendance> listUsers  = service.searchResult(searchDate);
//            model.addAttribute("date", searchDate);
//            model.addAttribute("listUsers", listUsers);
//            return "test";
//
//        }else if (searchCode == null ){
//            System.out.print("code is null, date not");
//            System.out.println("keyword: "+ searchDate);
//            List<Attendance> listUsers = service.searchResult(searchDate);
//            System.out.println("list: "+ listUsers);
//            model.addAttribute("date", searchDate);
//            model.addAttribute("listUsers", listUsers);
//            String result;
//            if (listUsers.isEmpty()) {
//                result = "No record found";
//            } else {
//                result = listUsers.size() + " records";
//            }
//            model.addAttribute("result",result);
//            return "test";
//        } else if (searchDate == null || searchDate.isEmpty()){
//            System.out.print("date is null, code not");
//            System.out.print("searchcode: "+ searchCode);
//            List<Attendance> listUsers = service.searchCode(searchCode);
//            System.out.print("we find: " + listUsers.size());
//            model.addAttribute("date", String.valueOf(LocalDate.now()));
//            model.addAttribute("listUsers", listUsers);
//            return "test";
//        }
//        else  {
//            System.out.print("all not null");
//            System.out.print("search date: "+ searchDate + "    ");
//            List<Attendance> listUsers = service.searchBoth(searchDate,searchCode);
//            System.out.print("when both search we find: " + listUsers.size());
//            model.addAttribute("date", searchDate);
//            model.addAttribute("listUsers", listUsers);
//            return "test";
//        }
//    }

//    @GetMapping(value = "/test/code")
//    public String showAttendanceList2(@Param("searchCode") String searchCode, Model model){
//        if (searchCode == null){
//            System.out.print("searchcode is null    ");
//            List<Attendance> listUsers  = service.listAll();
//            model.addAttribute("listUsers", listUsers);
//            return "redirect:/test";
//        }
//        else {
//            System.out.print("searchcode: "+ searchCode);
//            List<Attendance> listUsers = service.searchCode(searchCode);
//            System.out.print("we find: " + listUsers.size());
//            model.addAttribute("listUsers", listUsers);
//            return "redirect:/test";
//        }
//    }



    @PostMapping(value = "/upload/choose")
    public String uploadFile(@RequestParam("file") MultipartFile file, @Param("date") String date, RedirectAttributes ra) throws IOException {
        CSVReader reader = null;
        try
        {
            service.readLineByLine(file,date);
            Integer count = service.count(file,date);
            String tableName = service.tableName(file,date);
            ra.addFlashAttribute("message", "successfully upload "+count +" records to " + tableName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ra.addFlashAttribute("message", "Date can't be empty! Please Upload again");
        }
        return "redirect:/attendance";
    }






}
