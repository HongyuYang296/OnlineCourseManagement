package com.example.dataclean.upload;

import com.example.dataclean.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UploadRepository")
public interface UploadRepository extends CrudRepository<Attendance, Integer> {

    @Query(value = "select * from attendance where\n" +
            "date = (?1) and type = 'attendance' order by code",
            nativeQuery = true)
    public List<Attendance> searchGeneral(String keyword);

    @Query(value = "select * from attendance where\n" +
            "date = (?1) and type = 'attendance_euro' order by code",
            nativeQuery = true)
    public List<Attendance> searchEuro(String keyword);


    @Query(value = "select * from attendance where\n" +
            "date = (?1) and type = 'attendance_us' order by code",
            nativeQuery = true)
    public List<Attendance> searchUs(String keyword);

    @Query(value = "select * from attendance where\n" +
            "date = (?1) order by code",
            nativeQuery = true)
    public List<Attendance> searchAll(String keyword);





    @Modifying
    @Transactional
    @Query (value = "INSERT INTO users (first_name, last_name, email, code) \n" +
            "SELECT distinct first_name, last_name, email, code FROM attendance a \n" +
            "WHERE NOT EXISTS (SELECT email FROM users u WHERE u.email = a.email) \n" +
            "and EXISTS(select code from code_list c where c.code = a.code);",
            nativeQuery = true)
    public void updateDatabase();

    @Query (value = "SELECT * FROM attendance a \n" +
            "WHERE NOT EXISTS (SELECT email FROM users u WHERE u.email = a.email)",
            nativeQuery = true)
    public List<Attendance> findError();

    @Query (value = "SELECT distinct email FROM attendance a \n" +
            "WHERE NOT EXISTS (SELECT email FROM users u WHERE u.email = a.email) \n" +
            "and EXISTS(select code from code_list c where c.code = a.code);",
            nativeQuery = true)
    public List<String> findDiffer();

    @Modifying
    @Transactional
    @Query (value = "UPDATE attendance T1 SET\n" +
            "\tCODE = (SELECT T2.CODE FROM users T2\n" +
            "        WHERE T1.email = T2.email)\n" +
            "WHERE EXISTS (SELECT 1 FROM users T2 WHERE T1.email = T2.email);",
            nativeQuery = true)
    public void updateAttendance();

    @Query (value = "select distinct * from attendance \n" +
            "where LENGTH(code) > 6 or code is NULL or email not in(select email from users) or code not in (select code from code_list);",
            nativeQuery = true)
    List<Attendance> searchError();

    @Query(value = "select code from code_list ",
            nativeQuery = true)
    public List<String> searchCodes();



}
