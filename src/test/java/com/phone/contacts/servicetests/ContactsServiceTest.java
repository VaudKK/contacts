package com.phone.contacts.servicetests;

import com.phone.contacts.models.Country;
import com.phone.contacts.models.Customer;
import com.phone.contacts.repository.CountriesRepository;
import com.phone.contacts.repository.CustomerRepository;
import com.phone.contacts.service.impl.ContactsServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public class ContactsServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CountriesRepository countriesRepository;


    private ContactsServiceImpl contactsService;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        contactsService = new ContactsServiceImpl(customerRepository,countriesRepository);
    }

    @Test
    public void getContactsShouldReturnPageOfContacts(){
        Pageable testPageable = Pageable.ofSize(2);
        Page<Customer> testPage = new PageImpl<>(List.of(new Customer(),new Customer()),testPageable,2);
        Mockito.when(customerRepository.findAll(testPageable)).thenReturn(testPage);

        Assert.assertEquals(2L,contactsService.getCustomerContacts(testPageable).getTotalElements());
    }

    @Test
    public void filterByCountryShouldReturnPageOfFilteredContacts(){
        Country testCountry = createTestCountry("\\d{5}");
        Mockito.when(countriesRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(testCountry));

        List<Customer> customers = List.of(createTestCustomer("12345"),createTestCustomer("123"),createTestCustomer("90987"));
        Mockito.when(customerRepository.findAll()).thenReturn(customers);

        Assert.assertEquals(2L,contactsService.filterByCountry(ArgumentMatchers.anyInt(),Pageable.ofSize(10)).getTotalElements());
    }

    @Test
    public void filterByNonExistingCountryShouldReturnEmptyPage(){
        Mockito.when(countriesRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
        Assert.assertEquals(0L,contactsService.filterByCountry(ArgumentMatchers.anyInt(),Pageable.ofSize(10)).getTotalElements());
    }



    private Country createTestCountry(String pattern){
        Country country = new Country();
        country.setCountry("Test");
        country.setContactRegex(pattern);
        return country;
    }

    private Customer createTestCustomer(String phone){
        Customer customer = new Customer();
        customer.setPhone(phone);
        return customer;
    }

}
