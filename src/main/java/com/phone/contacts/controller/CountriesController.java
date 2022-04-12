package com.phone.contacts.controller;

import com.phone.contacts.models.Country;
import com.phone.contacts.service.CountriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("countries")
@CrossOrigin(originPatterns = "*")
public class CountriesController {

    private final CountriesService countriesService;

    public CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries(){
        return ResponseEntity.ok(countriesService.getCountries());
    }
}
