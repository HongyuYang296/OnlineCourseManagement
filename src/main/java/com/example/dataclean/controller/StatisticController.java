package com.example.dataclean.controller;

import com.example.dataclean.statistics.D3;
import com.example.dataclean.statistics.Statistic;
import com.example.dataclean.statistics.StatisticServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
public class StatisticController {
    @Autowired
    StatisticServer service;


    @RequestMapping( "/list")
    public List<Object[]> getResult() {
        return service.getResult();
    }


    @RequestMapping("/method")
    @ResponseBody
    public HashMap<String, Object> test(@RequestParam("username")String username, @RequestParam("password")String password){
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("message1","测试");
        map.put("message2",username);
        map.put("message3",password);
        return map;
    }

    @RequestMapping("/method2")
    @ResponseBody
    public List<Object[]> test(){
        List<Object[]> map= service.getResult();
        for (Object[] objects : map){
            String date = objects[0].toString();
            try {
                Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
                objects[0] = date1.getTime();

//                System.out.println(date +"\t"+date1);
//                System.out.println(date1.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.print(objects[0]);
            System.out.print(objects[1]);
        }
        System.out.print(" ");
        System.out.print("test map: ");
        System.out.print(map);
        return map;
    }

    @RequestMapping("/method3")
    @ResponseBody
    public List<Object[]> test2(@Param("searchCode") String searchCode){
        List<Object[]> map= service.getOneResult(searchCode);
        for (Object[] objects : map){
            String date = objects[0].toString();
            try {
                Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
                objects[0] = date1.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.print(objects[0]);
            System.out.print(objects[1]);
        }
        System.out.print(map);
        return map;
    }

    @RequestMapping("/getAllName")
    @ResponseBody
    public List<Object[]> getName(){
        List<Object[]> map= service.getNameResult();
        for (Object[] objects : map){
            String date = objects[0].toString();
            try {
                Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
                objects[0] = date1.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.print(objects[0]);
            System.out.print(objects[1]);
        }
        System.out.print(map);
        return map;
    }



    @GetMapping("/statistics")
    public String showStatisticMel(@Param("searchDate") String searchDate,Model model) {
        service.deleteStatistics();
        service.updateStatistics();

        List<Object[]> res = service.getResult();

//        for (List<String> re: res) {
//            re.get(1) = Integer.parseInt(re.get(1));
//        }

//        Object[] objArray = Arrays.asList("date", "sum").toArray();

        for (Object[] re : res) {
            System.out.print(Arrays.toString(re));
        }
//        System.out.print(res);
        model.addAttribute("data", res);


        if (searchDate == null || searchDate.isEmpty() ){
//            List<Statistic> listUsers2 = service.sdyStatistics();
//            List<Statistic> listUsers = service.melStatistics();
            searchDate = String.valueOf(LocalDate.now());
            List<Statistic> listUsers = service.searchDate(searchDate);
//            List<Statistic> listUsers = service.searchDateMel(searchDate);
            String total = service.total(searchDate);
            String totalMel = service.totalMel(searchDate);
            String totalSyd = service.totalSyd(searchDate);
            String unknown = service.unknownGeneral(searchDate);
            if (unknown == null) {
                unknown = "0";
            }else if (total == null) {
                total = "0";
            }else if (totalMel == null){
                totalMel = "0";
            }else if (totalSyd == null){
                totalSyd = "0";
            }
            Integer totals = Integer.parseInt(total) + Integer.parseInt(unknown);
            model.addAttribute("unknown",unknown);

            model.addAttribute("total", totals);
            model.addAttribute("totalMel", totalMel);
            model.addAttribute("totalSyd", totalSyd);
            model.addAttribute("date", searchDate);
//            model.addAttribute("listUsers", listUsers);
            model.addAttribute("listUsers", listUsers);

        }else {
//            List<Statistic> listUsers2 = service.searchDateSyd(searchDate);
//            List<Statistic> listUsers = service.searchDateMel(searchDate);
            List<Statistic> listUsers = service.searchDate(searchDate);
            String total = service.total(searchDate);
            String totalMel = service.totalMel(searchDate);
            String totalSyd = service.totalSyd(searchDate);
            String unknown = service.unknownGeneral(searchDate);
            System.out.print("unkonwn: " + unknown);
            if (unknown == null) {
                unknown = "0";
            }else if (total == null) {
                total = "0";
            }else if (totalMel == null){
                totalMel = "0";
            }else if (totalSyd == null){
                totalSyd = "0";
            }
            Integer totals = Integer.parseInt(total) + Integer.parseInt(unknown);
            model.addAttribute("unknown",unknown);
            model.addAttribute("total", totals);
            model.addAttribute("totalMel", totalMel);
            model.addAttribute("totalSyd", totalSyd);
            model.addAttribute("date", searchDate);
//          model.addAttribute("listUsers", listUsers);
            model.addAttribute("listUsers", listUsers);
        }
        return "statistics";
    }


    @GetMapping("/statistics_us")
    public String showStatisticUs(@Param("searchDate") String searchDate,Model model) {
        service.deleteStatistics();
        service.updateStatistics();
        if (searchDate == null || searchDate.isEmpty() ){
//            List<Statistic> listUsers2 = service.sdyStatistics();
//            List<Statistic> listUsers = service.melStatistics();
            searchDate = String.valueOf(LocalDate.now());
            List<Statistic> listUsers = service.searchDateUs(searchDate);
//            List<Statistic> listUsers = service.searchDateMel(searchDate);
            String total = service.usTotal(searchDate);
            String totalMel = service.usTotalMel(searchDate);
            String totalSyd = service.usTotalSyd(searchDate);
            String unknown = service.unknownGeneral(searchDate);
            if (unknown == null) {
                unknown = "0";
            }else if (total == null) {
                total = "0";
            }else if (totalMel == null){
                totalMel = "0";
            }else if (totalSyd == null){
                totalSyd = "0";
            }
            Integer totals = Integer.parseInt(total) + Integer.parseInt(unknown);
            model.addAttribute("unknown",unknown);

            model.addAttribute("total", totals);
            model.addAttribute("totalMel", totalMel);
            model.addAttribute("totalSyd", totalSyd);
            model.addAttribute("date", searchDate);
//            model.addAttribute("listUsers", listUsers);
            model.addAttribute("listUsers", listUsers);

        }else {
//            List<Statistic> listUsers2 = service.searchDateSyd(searchDate);
//            List<Statistic> listUsers = service.searchDateMel(searchDate);
            List<Statistic> listUsers = service.searchDateUs(searchDate);
            String total = service.usTotal(searchDate);
            String totalMel = service.usTotalMel(searchDate);
            String totalSyd = service.usTotalSyd(searchDate);

            String unknown = service.unknownGeneral(searchDate);
            if (unknown == null) {
                unknown = "0";
            }else if (total == null) {
                total = "0";
            }else if (totalMel == null){
                totalMel = "0";
            }else if (totalSyd == null){
                totalSyd = "0";
            }
            Integer totals = Integer.parseInt(total) + Integer.parseInt(unknown);
            model.addAttribute("unknown",unknown);

            model.addAttribute("total", totals);
            model.addAttribute("totalMel", totalMel);
            model.addAttribute("totalSyd", totalSyd);
            model.addAttribute("date", searchDate);
//          model.addAttribute("listUsers", listUsers);
            model.addAttribute("listUsers", listUsers);
        }
        return "statistics_us";
    }

    @GetMapping("/statistics_euro")
    public String showStatisticEuro(@Param("searchDate") String searchDate,Model model) {
        service.deleteStatistics();
        service.updateStatistics();


        if (searchDate == null || searchDate.isEmpty() ){
//          List<Statistic> listUsers2 = service.sdyStatistics();
//          List<Statistic> listUsers = service.melStatistics();
            searchDate = String.valueOf(LocalDate.now());
            List<Statistic> listUsers = service.searchDateEuro(searchDate);
//          List<Statistic> listUsers = service.searchDateMel(searchDate);
            String total = service.euroTotal(searchDate);
            String totalMel = service.euroTotalMel(searchDate);
            String totalSyd = service.euroTotalSyd(searchDate);

            String unknown = service.unknownGeneral(searchDate);
            if (unknown == null) {
                unknown = "0";
            }else if (total == null) {
                total = "0";
            }else if (totalMel == null){
                totalMel = "0";
            }else if (totalSyd == null){
                totalSyd = "0";
            }
            Integer totals = Integer.parseInt(total) + Integer.parseInt(unknown);
            model.addAttribute("unknown",unknown);

            model.addAttribute("total", totals);
            model.addAttribute("totalMel", totalMel);
            model.addAttribute("totalSyd", totalSyd);
            model.addAttribute("date", searchDate);
//            model.addAttribute("listUsers", listUsers);
            model.addAttribute("listUsers", listUsers);

        }else {
//            List<Statistic> listUsers2 = service.searchDateSyd(searchDate);
//            List<Statistic> listUsers = service.searchDateMel(searchDate);
            List<Statistic> listUsers = service.searchDateEuro(searchDate);
            String total = service.euroTotal(searchDate);
            String totalMel = service.euroTotalMel(searchDate);
            String totalSyd = service.euroTotalSyd(searchDate);

            String unknown = service.unknownGeneral(searchDate);
            if (unknown == null) {
                unknown = "0";
            }else if (total == null) {
                total = "0";
            }else if (totalMel == null){
                totalMel = "0";
            }else if (totalSyd == null){
                totalSyd = "0";
            }
            Integer totals = Integer.parseInt(total) + Integer.parseInt(unknown);
            model.addAttribute("unknown",unknown);

            model.addAttribute("total", totals);
            model.addAttribute("totalMel", totalMel);
            model.addAttribute("totalSyd", totalSyd);
            model.addAttribute("date", searchDate);
//          model.addAttribute("listUsers", listUsers);
            model.addAttribute("listUsers", listUsers);
        }
        return "statistics_euro";
    }


}
