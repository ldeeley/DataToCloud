package com.example.datatocloud.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
public class CustomerResponseDTO {

    private Integer recordId;
    private String customerId;
    private String g_group;
    private String type;
    private String pVal;
    private String createDateTime;
    private String band;
    private String region;
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
    private String createdBy;
    private Date createdDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
}
