package com.userManagement.springcrud.model.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UserDto {

    private Integer id;

    private String appKey;

    private String appName;

    private String name;

}
