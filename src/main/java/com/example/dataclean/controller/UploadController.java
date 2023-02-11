package com.example.dataclean.controller;

import com.example.dataclean.codelist.CodeList;
import com.example.dataclean.codelist.CodeListServer;
import com.example.dataclean.upload.Attendance;
import com.example.dataclean.upload.UploadService;
import com.example.dataclean.user.User;
import com.example.dataclean.user.UserNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class UploadController {
    @Autowired
    private UploadService service;


//    @GetMapping(value = "/attendance")
//    public String showAttendanceList(@Param("searchDate") String searchDate,@Param("searchCode") String searchCode, Model model, RedirectAttributes ra){
//        updateDifferFromDatabase(ra);
//        if (searchDate == null || searchDate.isEmpty() && searchCode == null ){
//            System.out.print("both null");
//            searchDate = String.valueOf(LocalDate.now());
//            List<Attendance> listUsers  = service.searchResult(searchDate);
//            model.addAttribute("date", searchDate);
//            model.addAttribute("listUsers", listUsers);
//            return "attendance";
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
//            return "attendance";
//        } else if (searchDate == null || searchDate.isEmpty()){
//            System.out.print("date is null, code not");
//            System.out.print("searchcode: "+ searchCode);
//            List<Attendance> listUsers = service.searchCode(searchCode);
//            System.out.print("we find: " + listUsers.size());
//            model.addAttribute("date", String.valueOf(LocalDate.now()));
//            model.addAttribute("listUsers", listUsers);
//            return "attendance";
//        }
//        else  {
//            System.out.print("all not null");
//            System.out.print("search date: "+ searchDate + "    ");
//            List<Attendance> listUsers = service.searchBoth(searchDate,searchCode);
//            System.out.print("when both search we find: " + listUsers.size());
//            model.addAttribute("date", searchDate);
//            model.addAttribute("listUsers", listUsers);
//            return "attendance";
//        }
//    }
@GetMapping(value = "/attendance")
public String showAttendanceList(@Param("searchDate") String searchDate,Model model, RedirectAttributes ra){
    updateDifferFromDatabase(ra);


    if (searchDate == null || searchDate.isEmpty()) {
        searchDate = String.valueOf(LocalDate.now());
        List<Attendance> listUsers = service.searchGeneral(searchDate);
        model.addAttribute("date", searchDate);
        model.addAttribute("listUsers", listUsers);
    }
    else  {
        List<Attendance> listUsers = service.searchGeneral(searchDate);
        model.addAttribute("date", searchDate);
        model.addAttribute("listUsers", listUsers);
    }
    return "attendance";
}
    @GetMapping(value = "/attendance_euro")
    public String showAttendanceListEuro(@Param("searchDate") String searchDate,Model model, RedirectAttributes ra){
        updateDifferFromDatabase(ra);
        if (searchDate == null || searchDate.isEmpty()) {
            searchDate = String.valueOf(LocalDate.now());
            List<Attendance> listUsers = service.searchEuro(searchDate);
            model.addAttribute("date", searchDate);
            model.addAttribute("listUsers", listUsers);
        }
        else  {
            List<Attendance> listUsers = service.searchEuro(searchDate);
            model.addAttribute("date", searchDate);
            model.addAttribute("listUsers", listUsers);
        }
        return "attendance_euro";
    }

    @GetMapping(value = "/attendance_us")
    public String showAttendanceListUs(@Param("searchDate") String searchDate,Model model, RedirectAttributes ra){
        updateDifferFromDatabase(ra);
        if (searchDate == null || searchDate.isEmpty()) {
            searchDate = String.valueOf(LocalDate.now());
            List<Attendance> listUsers = service.searchUs(searchDate);
            model.addAttribute("date", searchDate);
            model.addAttribute("listUsers", listUsers);
        }
        else  {
            List<Attendance> listUsers = service.searchUs(searchDate);
            model.addAttribute("date", searchDate);
            model.addAttribute("listUsers", listUsers);
        }
        return "attendance_us";
    }

    @GetMapping(value = "/attendance_total")
    public String showAttendanceTotal(@Param("searchDate") String searchDate,Model model, RedirectAttributes ra){
        updateDifferFromDatabase(ra);
        if (searchDate == null || searchDate.isEmpty()) {
            searchDate = String.valueOf(LocalDate.now());
            List<Attendance> listUsers = service.searchAll(searchDate);
            model.addAttribute("date", searchDate);
            model.addAttribute("listUsers", listUsers);
        }
        else  {
            List<Attendance> listUsers = service.searchAll(searchDate);
            model.addAttribute("records", new Attendance());
            model.addAttribute("date", searchDate);
            model.addAttribute("listUsers", listUsers);
        }
        return "attendance_total";
    }

    @RequestMapping("/{deleteDate}/delete")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(RedirectAttributes redirectAttributes, @PathVariable String deleteDate) {
        redirectAttributes.addFlashAttribute("css", "Success");
        redirectAttributes.addFlashAttribute("msg", "The user is deleted");
        service.deleteByDate(deleteDate);

        System.out.print("successfully delete "+ deleteDate);
        return "redirect:/attendance_total";
    }






    @GetMapping(value = "/attendance/searchError")
    public String getResult(@Param("keyword") String keyword, Model model,RedirectAttributes ra){
//        model.addAttribute("user", new Attendance());
//        if (keyword == null){
//            List<Attendance> listUsers = service.searchError();
//            model.addAttribute("listUsers", listUsers);
//        }
//        else {
//            String key = "\""+keyword+"\"";;
//            List<Attendance> listUsers = service.searchResult(key);
//            model.addAttribute("listUsers", listUsers);
//        }
        updateDifferFromDatabase(ra);
        model.addAttribute("user", new Attendance());
        try {
            List<Attendance> listUsers = service.searchError();
            model.addAttribute("listUsers", listUsers);
        } catch (Exception e) {
            ra.addFlashAttribute("message", "some error");
        }
        return "update_attendance";
    }


    @GetMapping(value = "/attendance/searchError/result")
    public String searchError(Model model, RedirectAttributes ra) {
//        updateDifferFromDatabase(ra);
//        model.addAttribute("user", new Attendance());
        try {
            List<Attendance> listUsers = service.searchError();
            if (listUsers.isEmpty()){
                ra.addFlashAttribute("message", "no error found");
            }else {
                ra.addFlashAttribute("message", listUsers.size() + " mistake found");
            }
            return "redirect:/attendance/searchError";

        } catch (Exception e) {
            ra.addFlashAttribute("message", "some error");
            return "redirect:/attendance/searchError";
        }

    }

    @PostMapping(value = "/attendance/update")
    public String updateUser(RedirectAttributes ra) {
//        service.update(user);
        try {
            List<String> error = service.findDiffer();
            Integer num = error.size();
            System.out.print("num of user: ");
            System.out.print(num + " ");
            ra.addFlashAttribute("message",  num + " new user has been add to database");
            service.updateDatabase();
            return "redirect:/attendance";

        } catch (Exception e) {
            ra.addFlashAttribute("message", "some error");
            return "redirect:/attendance";
        }

    }

//    @PostMapping(value = "/attendance/updateDiffer")
//    public String updateDiffer(RedirectAttributes ra) {
//        return updateDiiferFromDatabase(ra);
//
//    }

    @RequestMapping(value = "/attendance/searchError/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateAttendance(Integer id, Attendance user, Model model, RedirectAttributes ra) {
        return updateUsers(id, user, model, ra);
    }


    private String updateUsers(Integer id, Attendance user, Model model, RedirectAttributes ra) {
            List<String> codeList = service.listCode();
        try {
            model.addAttribute("user", user);
            String firstName = service.get(id).getFirstName();
            String lastName = service.get(id).getLastName();
            String code = service.get(id).getCode();
            System.out.print("code:  " + code);
//            List<String> codeList = CodeListServer.searchCode();
//            for (String code:codeList){
//                System.out.print(code);
//            }
            ra.addFlashAttribute("message", firstName+" "+lastName+"  has been update successfully");
            service.update(user);
            String code2 = service.get(id).getCode();
            if (code2.length() > 6 || !codeList.contains(code2) || code2.isEmpty()) {
                ra.addFlashAttribute("message", code2 + " is not correct code!");
                List<Attendance> listUsers = service.searchError();
                ra.addFlashAttribute("message2", listUsers.size() + " mistake left");
            } else {
                service.updateDatabase();
                service.updateAttendance();
                List<Attendance> listUsers = service.searchError();
                ra.addFlashAttribute("message2", listUsers.size() + " mistake left");
            }
            return "redirect:/attendance/searchError";
        } catch (Exception e) {
            ra.addFlashAttribute("message", "code is not correct!");
            return "redirect:/attendance/searchError";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "cant find");
            return "redirect:/attendance/searchError";
        }
    }

    @RequestMapping("attendance/searchError/getOne")
    @ResponseBody
    public Optional<Attendance> getOneSearch(Integer id, Model model) {
        Optional<Attendance> user = service.getOne(id);
        model.addAttribute("user", user);
        return service.getOne(id);
    }

    @RequestMapping (value = "/attendance/searchError/delete",  method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteError(Integer id, RedirectAttributes ra) {
        try{
            ra.addFlashAttribute("message", "The user "+ service.get(id).getFirstName()+ " " +service.get(id).getLastName()+" has been deleted");
            service.delete(id);
            return "redirect:/attendance/searchError";
        }catch (Exception e){
            System.out.println("error");
            ra.addFlashAttribute("message", "some error");
            return "redirect:/attendance/searchError";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "cant find users");
            return "redirect:/attendance/searchError";
        }

    }




    private void updateDifferFromDatabase(RedirectAttributes ra) {
        try {
//            System.out.print("num of differ user: ");
//            List<String> differ = service.findDiffer();
//            System.out.print("num of differ user:");
//            System.out.print(differ.size());
//            ra.addFlashAttribute("message",  differ.size()+" user's code has been updated");
            service.updateDatabase();
            service.updateAttendance();

        } catch (Exception e) {
            ra.addFlashAttribute("message", "some error1");
        }
    }


}
