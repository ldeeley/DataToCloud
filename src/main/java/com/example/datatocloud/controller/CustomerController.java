package com.example.datatocloud.controller;

import com.example.datatocloud.dto.CustomerRequestDTO;
import com.example.datatocloud.dto.CustomerResponseDTO;
import com.example.datatocloud.dto.ServiceResponse;
import com.example.datatocloud.service.BatchService;
import com.example.datatocloud.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController()
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {


    private final CustomerService customerService;
    private final BatchService batchService;
    private final Job job;

    @Autowired
    private JobLauncher jobLauncher;
    private final Logger log = LoggerFactory.getLogger(CustomerController.class);


    @GetMapping("/")
    public void home(){
        System.out.println("hello world");
    }

    @PostMapping("/addCustomer")
    public ServiceResponse<CustomerResponseDTO> addCustomer(@RequestBody @Valid CustomerRequestDTO customerRequestDTO)  {
        CustomerResponseDTO customerResponseDTO = customerService.addCustomer(customerRequestDTO);
        ServiceResponse<CustomerResponseDTO> serviceResponse = new ServiceResponse<>();
        serviceResponse.setStatus(HttpStatus.OK);
        serviceResponse.setResponse(customerResponseDTO);
        return serviceResponse;
    }

//    public void importCustomers(){
//        batchService.importCustomers();
//    }

    @PutMapping("/updateCustomer/{customerIdentifier}")
    public ServiceResponse<CustomerResponseDTO> updateCustomer(@PathVariable Integer customerIdentifier, @RequestBody @Valid CustomerRequestDTO customerRequestDTO)  {
        CustomerResponseDTO customerResponseDTO = customerService.updateCustomerDTO(customerIdentifier,customerRequestDTO);
        ServiceResponse<CustomerResponseDTO> serviceResponse = new ServiceResponse<>();
        serviceResponse.setStatus(HttpStatus.OK);
        serviceResponse.setResponse(customerResponseDTO);
        return serviceResponse;
    }

    @DeleteMapping("/deleteCustomer/{customerIdentifier}")
    public ServiceResponse<CustomerResponseDTO> deleteCustomer(@PathVariable Integer customerIdentifier){
        customerService.deleteCustomer(customerIdentifier);
        ServiceResponse<CustomerResponseDTO> serviceResponse = new ServiceResponse<>();
        serviceResponse.setStatus(HttpStatus.NO_CONTENT);
        return serviceResponse;
    }

    @GetMapping("/customers")
    public ServiceResponse<List<CustomerResponseDTO>> allCustomers(){
        List<CustomerResponseDTO> customerResponseDTOList = customerService.getAllCustomers();
        ServiceResponse<List<CustomerResponseDTO>> serviceResponse = new ServiceResponse<>();
        serviceResponse.setStatus(HttpStatus.OK);
        serviceResponse.setResponse(customerResponseDTOList);
        return serviceResponse;
    }

    @GetMapping("/{customerIdentifier}")
    public ServiceResponse<CustomerResponseDTO> findCustomer(@PathVariable Integer customerIdentifier){
        CustomerResponseDTO customerResponseDTO = customerService.findCustomerByIdentifier(customerIdentifier);
        ServiceResponse<CustomerResponseDTO> serviceResponse = new ServiceResponse<>();
        serviceResponse.setStatus(HttpStatus.OK);
        serviceResponse.setResponse(customerResponseDTO);
        return serviceResponse;
    }

}
