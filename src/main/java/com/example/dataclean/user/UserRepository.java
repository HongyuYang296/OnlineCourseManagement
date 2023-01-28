package com.example.dataclean.user;


import com.example.dataclean.upload.Attendance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.dataclean.user.User;

import java.util.List;


@Repository("UserRepository")
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "select * from users where\n" +
            "match (code, email, first_name, last_name)\n" +
            "against (?1)",
            nativeQuery = true)
    public List<User> search(String keyword);


    @Query (value = "select * from users \n" +
            "where LENGTH(code) > 6 or code is NULL;",
            nativeQuery = true)
    List<User> searchError();


//    @Query(value = "static/other/testOut.sql")
//    public void uploadData();
}
