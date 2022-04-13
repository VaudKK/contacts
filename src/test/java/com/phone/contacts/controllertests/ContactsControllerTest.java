package com.phone.contacts.controllertests;

import com.phone.contacts.controller.ContactsController;
import com.phone.contacts.dto.ContactDto;
import com.phone.contacts.service.ContactsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

@WebMvcTest(ContactsController.class)
public class ContactsControllerTest {

    private ContactsController controller;

    @Mock
    private ContactsService contactsService;

    private MockMvc mockMvc;


    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        controller = new ContactsController(contactsService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }


    @Test
    public void getAllContactsShouldReturnPageOfContacts() throws Exception {

        Page<ContactDto> contactDtoPage = new PageImpl<>(List.of(new ContactDto(),new ContactDto(),new ContactDto()), Pageable.ofSize(10),3);
        given(contactsService.getCustomerContacts(ArgumentMatchers.any(Pageable.class))).willReturn(contactDtoPage);


        mockMvc.perform(MockMvcRequestBuilders.get("/contacts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",hasSize(3)))
                .andReturn();
    }

    @Test
    public void filterContactsShouldReturnPageOfContacts() throws Exception {
        Page<ContactDto> contactDtoPage = new PageImpl<>(List.of(new ContactDto(),new ContactDto()), Pageable.ofSize(10),2);
        given(contactsService.filterByCountry(ArgumentMatchers.anyInt(),ArgumentMatchers.any(Pageable.class))).willReturn(contactDtoPage);


        mockMvc.perform(MockMvcRequestBuilders.get("/contacts/filter/100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(2))
                .andReturn();
    }

    @Test
    public void filterContactsWithoutParamShouldReturnError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contacts/filter/"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

}
