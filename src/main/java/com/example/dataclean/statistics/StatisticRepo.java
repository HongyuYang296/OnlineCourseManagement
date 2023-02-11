package com.example.dataclean.statistics;

import com.example.dataclean.upload.Attendance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepo extends CrudRepository<Statistic, Integer> {

    @Modifying
    @Transactional
    @Query(value =
            "insert into statistic (name, loc, sum, date,type)\n" +
            "select b.name, b.loc, count(*),a.date, a.type from attendance a join code_list b\n" +
            "on a.code = b.code\n" +
            "group by b.name,b.loc,a.date,a.type\n" +
            "order by count(*) desc;",
            nativeQuery = true)
    void updateStatistics();

    @Modifying
    @Transactional
    @Query(value = "delete from statistic;",
            nativeQuery = true)
    void deleteStatistics();

    @Query(value = "select * from statistic\n" +
            "where loc = 'mel'\n" +
            "order by sum desc",
            nativeQuery = true)
    List<Statistic> melStatistics();

    @Query(value = "select count(*) from attendance\n" +
            "where code not in (select code from code_list) \n" +
            "and date = (?1) and type = 'attendance';",
            nativeQuery = true)
    String  unknown(String keyword);


    @Query(value = "select * from statistic\n" +
            "where loc = 'syd'\n" +
            "order by sum desc",
            nativeQuery = true)
    List<Statistic> sdyStatistics();


    @Query(value = "select * from statistic where\n" +
            "date = (?1) and type = 'attendance_euro'\n " +
            "order by sum desc",
            nativeQuery = true)
    public List<Statistic> searchDateEuro(String keyword);

    @Query(value = "select * from statistic where\n" +
            "date = (?1) and type = 'attendance'\n " +
            "order by sum desc",
            nativeQuery = true)
    public List<Statistic> searchDate(String keyword);

    @Query(value = "select * from statistic where\n" +
            "date = (?1) and type = 'attendance_us'\n " +
            "order by sum desc",
            nativeQuery = true)
    public List<Statistic> searchDateUs(String keyword);

    // count num of general table
    @Query(value = "select sum(sum) from statistic where\n" +
            "            date = (?1) and type = 'attendance'",
            nativeQuery = true)
    String total(String keyword);

    @Query(value = "select sum(sum) from statistic where\n" +
            "            date = (?1) and loc = 'mel' and type = 'attendance'",
            nativeQuery = true)
    String totalMel(String keyword);

    @Query(value = "select sum(sum) from statistic where\n" +
            "            date = (?1) and loc = 'syd' and type = 'attendance'",
            nativeQuery = true)
    String totalSyd(String keyword);

// query for euro attendance
    @Query(value = "select sum(sum) from statistic where\n" +
            "            date = (?1) and type = 'attendance_euro'",
            nativeQuery = true)
    String euroTotal(String keyword);

    @Query(value = "select sum(sum) from statistic where\n" +
            "            date = (?1) and loc = 'mel' and type = 'attendance_euro'",
            nativeQuery = true)
    String euroTotalMel(String keyword);

    @Query(value = "select sum(sum) from statistic where\n" +
            "            date = (?1) and loc = 'syd' and type = 'attendance_euro'",
            nativeQuery = true)
    String euroTotalSyd(String keyword);

    // query for Us attendance
    @Query(value = "select sum(sum) from statistic where\n" +
            "            date = (?1) and type = 'attendance_us'",
            nativeQuery = true)
    String usTotal(String keyword);

    @Query(value = "select sum(sum) from statistic where\n" +
            "            date = (?1) and loc = 'mel' and type = 'attendance_us'",
            nativeQuery = true)
    String usTotalMel(String keyword);

    @Query(value = "select sum(sum) from statistic where\n" +
            "            date = (?1) and loc = 'syd' and type = 'attendance_us'",
            nativeQuery = true)
    String usTotalSyd(String keyword);

    @Query(value = "select date, sum(sum) as total from statistic \n" +
            "where type = 'attendance'\n" +
            "group by date\n" +
            "order by date;",
            nativeQuery = true)
    List<Object[]> getAttendanceResult();

    @Query(value = "select date, sum(sum) as total from statistic \n" +
            "where type = 'attendance_euro'\n" +
            "group by date\n" +
            "order by date;",
            nativeQuery = true)
    List<Object[]> getAttendanceResultEu();


    @Query(value = "select date, sum(sum) as total from statistic \n" +
            "where type = 'attendance_us'\n" +
            "group by date\n" +
            "order by date;",
            nativeQuery = true)
    List<Object[]> getAttendanceResultUs();



    @Query(value = "select date, sum(sum) as total from statistic \n" +
            "where name = (?1) and type = 'attendance'\n" +
            "group by date\n" +
            "order by date;",
            nativeQuery = true)
    List<Object[]> getOneResult(String keyword);


    @Query(value = "select date, name, sum(sum) as total from statistic\n" +
            "where type = 'attendance'\n" +
            "group by date, name\n" +
            "order by date, name;",
            nativeQuery = true)
    List<Object[]> getNameResult();

    @Query(value = "select date, name, sum(sum) as total from statistic\n" +
            "where type = 'attendance_euro'\n" +
            "group by date, name\n" +
            "order by date, name;",
            nativeQuery = true)
    List<Object[]> getNameResultEu();
    @Query(value = "select date, name, sum(sum) as total from statistic\n" +
            "where type = 'attendance_us'\n" +
            "group by date, name\n" +
            "order by date, name;",
            nativeQuery = true)
    List<Object[]> getNameResultUs();



}
