package com.example.dataclean.codelist;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeListInterface extends CrudRepository<CodeList, Integer> {

    @Query(value = "select * from code_list where\n" +
            "match (name, code, loc)\n" +
            "against (?1)",
            nativeQuery = true)
    public List<CodeList> search(String keyword);




}


