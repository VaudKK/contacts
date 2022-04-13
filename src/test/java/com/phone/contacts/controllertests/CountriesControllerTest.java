package com.phone.contacts.controllertests;

import com.phone.contacts.controller.CountriesController;
import com.phone.contacts.models.Country;
import com.phone.contacts.service.CountriesService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

@WebMvcTest(CountriesController.class)
public class CountriesControllerTest {

    @Mock
    private CountriesService countriesService;

    private CountriesController countriesController;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        countriesController = new CountriesController(countriesService);
        mockMvc = MockMvcBuilders.standaloneSetup(countriesController).build();
    }

    @Test
    public void getAllCountriesShouldReturnListOfCountries() throws Exception {
        given(countriesService.getCountries()).willReturn(List.of(new Country(),new Country()));

        mockMvc.perform(MockMvcRequestBuilders.get("/countries"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(2)))
                .andReturn();
    }

}
