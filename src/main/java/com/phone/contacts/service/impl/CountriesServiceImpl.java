package com.phone.contacts.service.impl;

import com.phone.contacts.models.Country;
import com.phone.contacts.repository.CountriesRepository;
import com.phone.contacts.service.CountriesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesServiceImpl implements CountriesService {

    private final CountriesRepository countriesRepository;

    public CountriesServiceImpl(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public List<Country> getCountries() {
        return countriesRepository.findAll();
    }
}
