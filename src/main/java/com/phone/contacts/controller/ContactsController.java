package com.phone.contacts.controller;

import com.phone.contacts.dto.ContactDto;
import com.phone.contacts.service.ContactsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contacts")
@CrossOrigin(originPatterns = "*")
public class ContactsController {

    private final ContactsService contactsService;


    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @GetMapping
    public ResponseEntity<Page<ContactDto>> getAllContacts(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(contactsService.getCustomerContacts(pageable));
    }
}
