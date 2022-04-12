package com.phone.contacts.repository;

import com.phone.contacts.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepository extends JpaRepository<Country,Integer> {
}
