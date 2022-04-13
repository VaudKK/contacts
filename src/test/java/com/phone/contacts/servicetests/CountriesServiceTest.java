package com.phone.contacts.servicetests;

import com.phone.contacts.models.Country;
import com.phone.contacts.repository.CountriesRepository;
import com.phone.contacts.service.impl.CountriesServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class CountriesServiceTest {

    @Mock
    private CountriesRepository countriesRepository;

    private CountriesServiceImpl countriesService;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        countriesService = new CountriesServiceImpl(countriesRepository);
    }


    @Test
    public void getCountriesShouldReturnListOfCountries(){
        Mockito.when(countriesRepository.findAll()).thenReturn(List.of(new Country(),new Country(),new Country()));
        Assert.assertEquals(3,countriesService.getCountries().size());
    }

}
