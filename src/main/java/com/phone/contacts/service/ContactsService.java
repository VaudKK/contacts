package com.phone.contacts.service;

import com.phone.contacts.dto.ContactDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactsService {

    List<ContactDto> getCustomerContacts(Pageable pageable);



}
