package com.example.datatocloud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.hibernate.envers.Audited;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER_TBL")
@EntityListeners(AuditingEntityListener.class)
@Audited
public class CustomerEntity {
    //    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    @Column(name = "record_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int    recordId;

    private String customerId;
    private String grp;
    private String tipe;
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
    private String identifier;

    @CreatedBy
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;
    @LastModifiedBy
    private String lastModifiedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastModifiedDate;




}
