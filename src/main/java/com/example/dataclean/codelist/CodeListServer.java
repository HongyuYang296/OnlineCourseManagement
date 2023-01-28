package com.example.dataclean.codelist;

import com.example.dataclean.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodeListServer {
    @Autowired
    private CodeListInterface repo;


    public List<CodeList> listAll() {
        return (List<CodeList>) repo.findAll();
    }

    public List<CodeList> searchResult(String keyword){
        return repo.search(keyword);
    }

    public void update(CodeList user) {
        repo.save(user);
    }

    public CodeList get(Integer id) throws UserNotFoundException {
        Optional<CodeList> result = repo.findById(id);
        if (result.isPresent()) {
            System.out.print("id id: " + id);
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID "+ id);
    }

    public Optional<CodeList> getOne(Integer id) {
        return repo.findById(id);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }


    public void save(CodeList user) {
        repo.save(user);
    }






}
