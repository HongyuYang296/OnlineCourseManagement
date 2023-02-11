package com.example.dataclean.statistics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServer {
    @Autowired
    private StatisticRepo repo;

    public List<Statistic> melStatistics(){
        List<Statistic> list = repo.melStatistics();
        for (Statistic lists : list) {
            System.out.print(lists);
        }
        return repo.melStatistics();
    }


    public List<Statistic> searchDate(String keyword){
        return repo.searchDate(keyword);
    }
    public List<Statistic> searchDateUs(String keyword){
        return repo.searchDateUs(keyword);
    }
    public List<Statistic> searchDateEuro(String keyword){
        return repo.searchDateEuro(keyword);
    }


    public String total(String keyword) {
        return repo.total(keyword);
    }

    public String totalMel(String keyword) {
        return repo.totalMel(keyword);
    }

    public String unknownGeneral(String keyword) {
        return repo.unknown(keyword);
    }

    public String totalSyd(String keyword) {
        return repo.totalSyd(keyword);
    }
//  get attendance result
    public List<Object[]> getResult(){
        return repo.getAttendanceResult();
    }
    public List<Object[]> getNameResult(){
        return repo.getNameResult();
    }

    //  get attendance_euro result
    public List<Object[]> getResultEu(){
        return repo.getAttendanceResultEu();
    }
    public List<Object[]> getNameResultEu(){
        return repo.getNameResultEu();
    }

    //  get attendance_us result
    public List<Object[]> getResultUs(){
        return repo.getAttendanceResultUs();
    }
    public List<Object[]> getNameResultUs(){
        return repo.getNameResultUs();
    }



    public List<Object[]> getOneResult(String keyword){
        return repo.getOneResult(keyword);
    }

    // euro
    public String euroTotal(String keyword) {
        return repo.euroTotal(keyword);
    }

    public String euroTotalMel(String keyword) {
        return repo.euroTotalMel(keyword);
    }

    public String euroTotalSyd(String keyword) {
        return repo.euroTotalSyd(keyword);
    }

    // us
    public String usTotal(String keyword) {
        return repo.usTotal(keyword);
    }

    public String usTotalMel(String keyword) {
        return repo.usTotalMel(keyword);
    }

    public String usTotalSyd(String keyword) {
        return repo.usTotalSyd(keyword);
    }


    public List<Statistic> sdyStatistics(){
        List<Statistic> list = repo.sdyStatistics();
        return repo.sdyStatistics();
    }

    public void updateStatistics(){
        repo.updateStatistics();
    }

    public void deleteStatistics(){
        repo.deleteStatistics();
    }
}
