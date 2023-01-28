package com.example.dataclean.upload;

import com.example.dataclean.user.User;
import com.example.dataclean.user.UserNotFoundException;
import com.example.dataclean.user.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class UploadService {

    @Autowired
    private UploadRepository repo;

    public List<Attendance> listAll() {
        return (List<Attendance>) repo.findAll();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
    public List<String> listCode(){return (List<String >) repo.searchCodes();}
    public void updateDatabase() {
        repo.updateDatabase();
        System.out.println("updated");
    }

    public List<Attendance> findError(){
        System.out.println("find");
        List<Attendance> ac = repo.findError();
        System.out.print("size:  ");
        System.out.print(ac.size());
        return (List<Attendance>) repo.findError();
    };

    public void updateAttendance(){
        repo.updateAttendance();
    }

    public List<String> findDiffer(){
        System.out.print("sizezzz: ");
        List<String> ac = repo.findDiffer();
        System.out.print("size:  ");
        System.out.print(ac.size());
        return (List<String>) repo.findDiffer();
    }


    public List<Attendance> searchGeneral(String keyword){
        return repo.searchGeneral(keyword);
    }
    public List<Attendance> searchEuro(String keyword){
        return repo.searchEuro(keyword);
    }
    public List<Attendance> searchUs(String keyword){
        return repo.searchUs(keyword);
    }

    public List<Attendance> searchAll(String keyword){
        return repo.searchAll(keyword);
    }


    public  void readLineByLine(MultipartFile file, String date) throws Exception {
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-mm-dd");
        Date date1=formatter1.parse(date);
        List<String[]> list = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
            if (Arrays.toString(list.get(0)).contains("欧盘")) {
                String table = "attendance_euro";
                List<String> statements = readFiles(list,date,table);
                saveToDb(statements);

            }else if(Arrays.toString(list.get(0)).contains("美盘")) {
                String table = "attendance_us";
                List<String> statements = readFiles(list,date,table);
                saveToDb(statements);

            } else {
                String table = "attendance";
                List<String> statements = readFiles(list,date,table);
                saveToDb(statements);
            }

        }catch (Exception E) {
            System.out.print("errors");
        }
//        String text = Arrays.toString(list.get(0));
//        System.out.print("test: "+ text);
//        if (text.contains("欧盘")) {
//            text = "欧盘测试成功";
//            System.out.print("test2: "+ text);
//        }
//        list.subList(0, 7).clear();
//        final String template = "INSERT INTO attendance(%s,%s,%s,%s,%s,%s,%s) VALUES ('%s','%s','%s','%s','%s','%s','%s');";
//        List<String> statements = new ArrayList<>();
//        for (String[] line : list) {
//            ArrayList<String> scripts = new ArrayList<String>();
//            for (String token:line){
//                token = token.replaceAll(",", "");
//                scripts.add(token);
//            }
//            if (Objects.equals(scripts.get(0), "Yes")){
//                statements.add(String.format(template, "first_name","last_name","email", "join_left_time","time_session","code","date",
//                        scripts.get(3),scripts.get(2),scripts.get(4),scripts.get(6),scripts.get(7), scripts.get(8), date));
//            }
//        }
       //Registering the Driver
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection
//        String mysqlUrl = "jdbc:mysql://localhost:3306/usersdb";
//        Connection con = DriverManager.getConnection(mysqlUrl, "root", "Qpmz970106");
//        System.out.println("Connection established......");
//        //Initialize the script runner
//        ScriptRunner sr = new ScriptRunner(con);
//        //Creating a reader object
//        //Running the script
//        StringBuilder buffer = new StringBuilder();
//        for(String current : statements) {
//            buffer.append(current).append("\n");
//        }
//        BufferedReader br1 = new BufferedReader(new StringReader(buffer.toString()));
//        sr.runScript(br1);
    }

    public void saveToDb(List<String> statements) throws SQLException {
        String mysqlUrl = "jdbc:mysql://localhost:3306/usersdb";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "Qpmz970106");
        System.out.println("Connection established......");
        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        //Running the script
        StringBuilder buffer = new StringBuilder();
        for(String current : statements) {
            buffer.append(current).append("\n");
        }
        BufferedReader br1 = new BufferedReader(new StringReader(buffer.toString()));
        sr.runScript(br1);
    }

    public List<String> readFiles(List<String[]> list, String date, String table){
        list.subList(0, 7).clear();
        final String template = "INSERT INTO attendance (%s,%s,%s,%s,%s,%s,%s,%s) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s');";
        List<String> statements = new ArrayList<>();
        for (String[] line : list) {
            ArrayList<String> scripts = new ArrayList<String>();
            for (String token:line){
                token = token.replaceAll(",", "");
                scripts.add(token);
            }
            if (Objects.equals(scripts.get(0), "Yes")){
                statements.add(String.format(template, "first_name","last_name","email", "join_left_time","time_session","code","date","type",
                        scripts.get(3),scripts.get(2),scripts.get(4),scripts.get(6),scripts.get(7), scripts.get(8), date,table));
            }
        }
        return statements;
    }



    public Integer count(MultipartFile file, String date) throws Exception {
        List<String[]> list = new ArrayList<>();
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
            }
            String table = "all";
            List<String> statements = readFiles(list,date,table);
            return statements.size();
        }catch (Exception e) {
            return 0;
        }
    }

    public String tableName(MultipartFile file, String date) throws IOException {
        List<String[]> list = new ArrayList<>();
        String tableName;
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    list.add(line);
                }
                if (Arrays.toString(list.get(0)).contains("欧盘")) {
                    tableName = "Euro";

                }else if(Arrays.toString(list.get(0)).contains("美盘")) {
                    tableName = "Us";

                } else {
                    tableName = "General";

                }
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }
        }
        return tableName;
    }



    public List<Attendance> searchError() {
        return repo.searchError();
    }

    public void update(Attendance user) {
        repo.save(user);
    }

    public Attendance get(Integer id) throws UserNotFoundException {
        Optional<Attendance> result = repo.findById(id);
        if (result.isPresent()) {
            System.out.print("id is: ");
            System.out.print(id);

            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID "+ id);
    }

    public Optional<Attendance> getOne(Integer id) {
        System.out.print("id is:  ");
        System.out.print(id);
        return repo.findById(id);
    }

}
