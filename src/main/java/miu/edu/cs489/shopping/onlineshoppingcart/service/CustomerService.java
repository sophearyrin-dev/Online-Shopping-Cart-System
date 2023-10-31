package miu.edu.cs489.shopping.onlineshoppingcart.service;

import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerRequest;
import miu.edu.cs489.shopping.onlineshoppingcart.dto.customer.CustomerResponse;
import miu.edu.cs489.shopping.onlineshoppingcart.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> getAllCustomers();

    CustomerResponse addNewCustomer(CustomerRequest newCustomer);

    CustomerResponse getCustomerById(int patientId) throws CustomerNotFoundException;

    CustomerResponse updateCustomerById(CustomerRequest customerRequest, int customerId) throws CustomerNotFoundException;

    void deleteCustomerById(int customerId) throws CustomerNotFoundException;
}
