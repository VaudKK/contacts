package com.phone.contacts.service.impl;

import com.phone.contacts.dto.ContactDto;
import com.phone.contacts.repository.CustomerRepository;
import com.phone.contacts.service.ContactsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsServiceImpl implements ContactsService {

    private final CustomerRepository customerRepository;

    public ContactsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<ContactDto> getCustomerContacts(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(customer -> new ContactDto(customer.getName(),customer.getPhone()))
                .toList();
    }
}
