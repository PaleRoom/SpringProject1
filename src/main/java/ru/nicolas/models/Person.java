package ru.nicolas.models;

//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

/**
 * @author Neil Alishev
 */
public class Person {
    private int id;


    //Страна, Город, Индекс (6 цифр)
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address should be in this format:" +
            "Country, City, ZIP code")
    private  String address;

    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "NAME must contain FIO")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    private int born;

//    @NotEmpty(message = "Email should not be empty")
//   // @Email(message = "Email should be valid")
//    private String email;

    public Person() {

    }


    public Person(int id, String name, int born) {
        this.id = id;
        this.name = name;
        this.born = born;
//        this.email = email;
//        this.address = address;
    }

    public int getBorn() {
        return born;
    }

//    public String getEmail() {
//        return email;
//    }

    public void setBorn(int born) {
        this.born = born;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setAddress(String address) {
//        this.address = address;
//    }

//    public String getAddress() {
//        return address;
//    }
}
