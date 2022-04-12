package com.phone.contacts.service;

import com.phone.contacts.dto.ContactDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactsService {

    Page<ContactDto> getCustomerContacts(Pageable pageable);

    Page<ContactDto> filterByCountry(Integer countryId,Pageable pageable);

}
