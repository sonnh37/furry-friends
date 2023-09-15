/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author hoang
 */
public class AboardProgram {
    private String Id;
    private String name;
    private String time;
    private String FromRegistrationDate;
    private String EndRegistrationDate;
    private String days;
    private String location;
    private int cost;
    private String content;

    public AboardProgram() {
    }

    public AboardProgram(String Id, String name, String time, String FromRegistrationDate, String EndRegistrationDate, String days, String location, int cost, String content) {
        this.Id = Id;
        this.name = name;
        this.time = time;
        this.FromRegistrationDate = FromRegistrationDate;
        this.EndRegistrationDate = EndRegistrationDate;
        this.days = days;
        this.location = location;
        this.cost = cost;
        this.content = content;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFromRegistrationDate() {
        return FromRegistrationDate;
    }

    public void setFromRegistrationDate(String FromRegistrationDate) {
        this.FromRegistrationDate = FromRegistrationDate;
    }

    public String getEndRegistrationDate() {
        return EndRegistrationDate;
    }

    public void setEndRegistrationDate(String EndRegistrationDate) {
        this.EndRegistrationDate = EndRegistrationDate;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
