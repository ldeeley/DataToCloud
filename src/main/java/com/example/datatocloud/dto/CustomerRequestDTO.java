package com.example.datatocloud.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerRequestDTO {

    private Integer recordId;
    @NotBlank(message = "customerId must be provided")
    private String customerId;
    private String g_group;
    private String type;
    private String pVal;
    private String createDateTime;
    private String band;
    private String region;
    @NotBlank(message = "Please supply name")
    private String name;
    private String middleName;
    private String state;
    private String zip;
    private String department;
    private String sector;
    private String code;
    private String area;
    private String xcode;
    private String mobile;
    private String land;
    private String misc;
    private String category;
    private String email;
}
