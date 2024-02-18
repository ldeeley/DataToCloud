package com.example.datatocloud.service;

import com.example.datatocloud.dao.CustomerEntityDAO;
import com.example.datatocloud.dto.CustomerRequestDTO;
import com.example.datatocloud.dto.CustomerResponseDTO;
import com.example.datatocloud.entity.CustomerEntity;
import com.example.datatocloud.util.AppUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service(value = "CustomerService")
public class CustomerService {

    @Autowired
    CustomerEntityDAO customerEntityDAO;

    Logger log = LoggerFactory.getLogger(CustomerService.class);

    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO) {
//        convertDTO to DAO
        log.info("CustomerService::addCustomer execution started");

            CustomerEntity customerEntity = AppUtils.convertCustomerRequestDTOToCustomerEntity(customerRequestDTO);
            CustomerEntity entity = customerEntityDAO.save(customerEntity);
            log.debug("Customer entity response from Database {}",AppUtils.convertObjectToJson(entity));
            CustomerResponseDTO customerResponseDTO = AppUtils.convertCustomerEntityToCustomerResponseDTO(entity);
            log.debug("Customer response for Controller {}",AppUtils.convertObjectToJson(customerResponseDTO));
            log.info("CustomerService::addCustomer execution ended");
            return customerResponseDTO;

    }

    public CustomerResponseDTO updateCustomerDTO(Integer customerDTOId, CustomerRequestDTO customerRequestDTO)  {
        log.info("CustomerService::updateCustomerDTO execution started");

            log.debug("CustomerService::updateCustomer request payload {} & id {}",AppUtils.convertObjectToJson(customerRequestDTO),customerDTOId);
            CustomerEntity existingCustomerDTO = customerEntityDAO.findById(customerDTOId)
                    .orElseThrow(null);
            log.debug("CustomerService::updateCustomer existingCustomerDTO {}",AppUtils.convertObjectToJson(existingCustomerDTO));
            existingCustomerDTO.setCustomerId(customerRequestDTO.getCustomerId());
            existingCustomerDTO.setGrp(customerRequestDTO.getG_group());
            existingCustomerDTO.setTipe(customerRequestDTO.getType());
            existingCustomerDTO.setPVal(customerRequestDTO.getPVal());
            existingCustomerDTO.setCreateDateTime(customerRequestDTO.getCreateDateTime());
            existingCustomerDTO.setBand(customerRequestDTO.getBand());
            existingCustomerDTO.setName(customerRequestDTO.getName());
            existingCustomerDTO.setMiddleName(customerRequestDTO.getMiddleName());
            existingCustomerDTO.setState(customerRequestDTO.getState());
            existingCustomerDTO.setZip(customerRequestDTO.getZip());
            existingCustomerDTO.setDepartment(customerRequestDTO.getDepartment());
            existingCustomerDTO.setSector(customerRequestDTO.getSector());
            existingCustomerDTO.setCode(customerRequestDTO.getCode());
            existingCustomerDTO.setCode(customerRequestDTO.getCode());
            existingCustomerDTO.setArea(customerRequestDTO.getArea());
            existingCustomerDTO.setMobile(customerRequestDTO.getMobile());
            existingCustomerDTO.setMisc(customerRequestDTO.getMisc());
            existingCustomerDTO.setCategory(customerRequestDTO.getCategory());

            CustomerEntity updatedCustomerEntity = customerEntityDAO.save(existingCustomerDTO);

            CustomerResponseDTO customerResponseDTO = AppUtils.convertCustomerEntityToCustomerResponseDTO(updatedCustomerEntity);
            log.debug("CustomerService::updateCustomerDTO customerResponseDTO is {}",customerResponseDTO);
            log.info("CustomerService::updateCustomerDTO execution ended");
            return customerResponseDTO;


    }

    public void deleteCustomer(Integer customerRequestDTOIdentifier) {
        log.info("CustomerService::deleteCustomer execution started");
        customerEntityDAO.deleteById(customerRequestDTOIdentifier);
        log.info("CustomerService::deleteCustomer execution ended");
    }

    public CustomerResponseDTO findCustomerByIdentifier(Integer customerRequestDTOIdentifier) {
        log.info("CustomerService::findCustomerByIdentifier execution started");
        CustomerEntity customerEntity = customerEntityDAO.findById(customerRequestDTOIdentifier).orElseThrow(()->new RuntimeException(""));
        log.info("CustomerService::findCustomerByIdentifier execution ended");
        return AppUtils.convertCustomerEntityToCustomerResponseDTO(customerEntity);

    }

    public List<CustomerResponseDTO> getAllCustomers() {
        log.info("CustomerService::getAllCustomers execution started");
        Iterable<CustomerEntity> customerEntities = customerEntityDAO.findAll();
        log.info("CustomerService::getAllCustomers execution ended");
        return StreamSupport.stream(customerEntities.spliterator(),false)
                .map(AppUtils::convertCustomerEntityToCustomerResponseDTO)
                .toList();
    }
}
