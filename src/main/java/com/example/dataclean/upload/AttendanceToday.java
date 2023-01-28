package com.example.dataclean.upload;
import jakarta.persistence.*;

@Entity
@Table (name = "attendance_today")

public class AttendanceToday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, name = "last_name")
    private String lastName;

    @Column(length = 45, name = "first_name")
    private String firstName;

    @Column(length = 45, name = "email")
    private String email;

    @Column(name = "join_left_time")
    private String joinLeftTime;

    @Column(name="time_session")
    private String timeSession;
    @Column(name="code")
    private String code;

    @Column(name="date")
    private String date;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJoinLeftTime() {
        return joinLeftTime;
    }

    public void setJoinLeftTime(String joinLeftTime) {
        this.joinLeftTime = joinLeftTime;
    }

    public String getTimeSession() {
        return timeSession;
    }

    public void setTimeSession(String timeSession) {
        this.timeSession = timeSession;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
