package com.example.beproject22.model;


import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class User {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private Integer roleId;
    private Boolean isDeleted;
}
