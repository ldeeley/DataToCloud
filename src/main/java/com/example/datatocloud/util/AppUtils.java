package com.example.datatocloud.util;

import com.example.datatocloud.dto.CustomerRequestDTO;
import com.example.datatocloud.dto.CustomerResponseDTO;
import com.example.datatocloud.entity.CustomerEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static CustomerEntity convertCustomerRequestDTOToCustomerEntity(CustomerRequestDTO customerRequestDTO){
        return objectMapper.convertValue(customerRequestDTO, CustomerEntity.class);
    }

    public static CustomerResponseDTO convertCustomerEntityToCustomerResponseDTO(CustomerEntity customerEntity){
        return objectMapper.convertValue(customerEntity, CustomerResponseDTO.class);
    }

    public static String convertObjectToJson(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
