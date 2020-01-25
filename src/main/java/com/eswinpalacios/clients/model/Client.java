package com.eswinpalacios.clients.model;

import com.eswinpalacios.clients.dto.AdditionalInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "name is empty")
    private String name;

    @JsonProperty("last_name")
    @NotEmpty(message = "lastname is empty")
    private String lastName;

    @JsonProperty("date_of_birth")
    @NotNull(message = "date of birth is null")
    private Date dateOfBirth;

    private int age;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AdditionalInfo additionalInfo;

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
