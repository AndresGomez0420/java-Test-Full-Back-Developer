package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty; // Importa esta anotación
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data; // Necesitas la dependencia Lombok en build.gradle


@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 1. UUID: La DB lo genera, así que le decimos a Hibernate que lo ignore en el INSERT.
    @JsonProperty("uuid") // Acepta "uuid" del JSON
    @Column(insertable = false, updatable = false)
    private String uuid;

    // 2. Mapeo de JSON (snake_case) a Java (camelCase) y Columna DB

    // JSON: "picture_large" -> Java: pictureLarge -> DB Columna: picture_name
    @JsonProperty("picture_large")
    @Column(name = "picture_name")
    private String pictureLarge;

    // JSON: "name_title" -> Java: nameTitle -> DB Columna: name_title
    @JsonProperty("name_title")
    @Column(name = "name_title")
    private String nameTitle;

    // JSON: "first_name" -> Java: firstName -> DB Columna: first_name
    @JsonProperty("first_name")
    @Column(name = "first_name")
    private String firstName;

    // JSON: "last_name" -> Java: lastName -> DB Columna: last_name
    @JsonProperty("last_name")
    @Column(name = "last_name")
    private String lastName;

    // JSON: "phone" -> Java: phone -> DB Columna: num_tel
    @JsonProperty("phone")
    @Column(name = "num_tel")
    private String phone;

    @JsonProperty("gender")
    private String gender; // Columna 'gender' asume que coincide con el campo Java

    // JSON: "email" -> Java: email -> DB Columna: email_address
    @JsonProperty("email")
    @Column(name = "email_address")
    private String email;

    @JsonProperty("state")
    private String state;

    @JsonProperty("city")
    private String city;

    @JsonProperty("postcode")
    private Integer postcode;
}
