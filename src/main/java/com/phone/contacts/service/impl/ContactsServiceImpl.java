package com.phone.contacts.service.impl;

import com.phone.contacts.dto.ContactDto;
import com.phone.contacts.models.Country;
import com.phone.contacts.repository.CountriesRepository;
import com.phone.contacts.repository.CustomerRepository;
import com.phone.contacts.service.ContactsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ContactsServiceImpl implements ContactsService {

    private final CustomerRepository customerRepository;
    private final CountriesRepository countriesRepository;

    public ContactsServiceImpl(CustomerRepository customerRepository, CountriesRepository countriesRepository) {
        this.customerRepository = customerRepository;
        this.countriesRepository = countriesRepository;
    }

    @Override
    public Page<ContactDto> getCustomerContacts(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(customer -> new ContactDto(customer.getName(),customer.getPhone()));
    }

    @Override
    public Page<ContactDto> filterByCountry(Integer countryId, Pageable pageable) {
        String pattern =  countriesRepository.findById(countryId)
                .map(Country::getContactRegex)
                .orElse(null);

        if(pattern != null){
            var contacts =  customerRepository.findAll();

            var filteredList = contacts.stream()
                    .filter(contact -> contact.getPhone().trim().matches(pattern.trim()))
                    .map(customer -> new ContactDto(customer.getName(),customer.getPhone()))
                    .collect(Collectors.toList());

            return new PageImpl<>(filteredList,
                    pageable,
                    filteredList.size()
            );
        }else{
            return Page.empty();
        }
    }
}
