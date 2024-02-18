package com.example.datatocloud.dao;

import com.example.datatocloud.dto.CustomerRequestDTO;
import com.example.datatocloud.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

//don't add @Repository here
//public interface CustomerDAO extends RevisionRepository<CustomerEntity,Integer,Integer>, JpaRepository<CustomerEntity,Integer>
public interface CustomerEntityDAO extends RevisionRepository<CustomerEntity,Integer,Integer>,JpaRepository<CustomerEntity,Integer> {
}
