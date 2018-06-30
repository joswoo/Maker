package com.example.qhdud.holo_final;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Member extends RealmObject//
{
    @PrimaryKey
    private int id;
    @Required //반드시 입력해야 한다면
    private String name;
    private boolean visable1 = true;
    private int age;
    @Required
    private String email;
    @Required
    private String password;
    private String writeText;
    private String currentTime;
    //time set
    private String time = "1";

    public Boolean getVisable1(){
        return visable1;
    }

    public void setVisable1(Boolean visable1)
    {
        this.visable1 = visable1;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriteText() {
        return writeText;
    }

    public void setWriteText(String writeText){
        this.writeText = writeText;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime){
        this.currentTime = currentTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}