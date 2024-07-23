package com.pruebaTecnica.contact_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.pruebaTecnica.controller.ContactosApiController;
import com.pruebaTecnica.dto.ContactoResponse;
import com.pruebaTecnica.service.ContactoService;

public class ContactosApiControllerTest {

    @Mock
    private ContactoService contactoService;
    
    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private ContactosApiController contactosApiController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getContactosTest() throws Exception {
		Contacto contacto1 = new Contacto().id(1).nombre("prueba1").apellidos("prueba1").email("prueba1@prueba1.com");
		Contacto contacto2 = new Contacto().id(2).nombre("prueba2").apellidos("prueba2").email("prueba2@prueba2.com");
		List<Contacto> contactoList = new ArrayList<>();
		contactoList.add(contacto1);
		contactoList.add(contacto2);
		Page<Contacto> pagina = new PageImpl<>(contactoList, PageRequest.of(0, 5), 2);
    	    when(contactoService.getContactos(0, 5, null)).thenReturn(pagina);

            // Invoke the controller method
            ResponseEntity<ContactoResponse> responseEntity = contactosApiController.getContactos(0, 5, null);
            ContactoResponse response = responseEntity.getBody();

            // Perform assertions
            assertEquals(200, responseEntity.getStatusCodeValue());
            assertEquals(2, response.getTotalElementos());
            assertEquals(1, response.getPaginasTotales());
            assertEquals(5, response.getElementosPagina());
            assertEquals(1, response.getPaginaActual());
            assertEquals(1, response.getContactos().get(0).getId());
            assertEquals("prueba1", response.getContactos().get(0).getNombre());
            assertEquals("prueba1@prueba1.com", response.getContactos().get(0).getEmail());

            // Verify that the service method was called with the correct parameters
            verify(contactoService).getContactos(0, 5, null);
    }

    @Test
    public void createContactoTest() {
        Contacto contacto = new Contacto().id(1).nombre("prueba").apellidos("prueba").email("prueba.prueba@prueba.com");
        when(contactoService.createContacto(any(Contacto.class))).thenReturn(contacto);

        ResponseEntity<Contacto> response = contactosApiController.createContacto(contacto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("prueba", response.getBody().getNombre());

        verify(contactoService, times(1)).createContacto(contacto);
    }

    @Test
    public void updateContactoTest() {
        Contacto contacto = new Contacto().id(1).nombre("prueba").apellidos("prueba").email("prueba.prueba@prueba.com");
        when(contactoService.updateContacto(anyInt(), any(Contacto.class))).thenReturn(contacto);

        ResponseEntity<Contacto> response = contactosApiController.updateContacto(1, contacto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("prueba", response.getBody().getNombre());

        verify(contactoService, times(1)).updateContacto(1, contacto);
    }

    @Test
    public void deleteContactoTest() {
        doNothing().when(contactoService).deleteContacto(anyInt());

        ResponseEntity<Void> response = contactosApiController.deleteContacto(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(contactoService, times(1)).deleteContacto(1);
    }
}