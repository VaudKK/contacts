package com.phone.contacts.service;

import com.phone.contacts.dto.ContactDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactsService {

    /**
     * Gets contacts from customer data
     * @param pageable {@link Pageable}
     * @return Page of ContactDto
     */
    Page<ContactDto> getCustomerContacts(Pageable pageable);

    /**
     * Filters contact data by country
     * @param countryId the country Id
     * @param pageable {@link Pageable}
     * @return Page of ContactDto
     */
    Page<ContactDto> filterByCountry(Integer countryId,Pageable pageable);

}
