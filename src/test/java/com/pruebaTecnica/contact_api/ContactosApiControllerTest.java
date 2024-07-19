package com.pruebaTecnica.contact_api;

import com.pruebaTecnica.controller.ContactosApiController;
import com.pruebaTecnica.service.ContactoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.Contacto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ContactosApiControllerTest {

    @Mock
    private ContactoService contactoService;

    @InjectMocks
    private ContactosApiController contactosApiController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetContactos() {
        List<Contacto> contactos = Arrays.asList(new Contacto().id(1).nombre("John").apellidos("prueba").email("john.doe@example.com"));
        when(contactoService.getAllContactos(any())).thenReturn(contactos);

        ResponseEntity<List<Contacto>> response = contactosApiController.getContactos(null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("John", response.getBody().get(0).getNombre());

        verify(contactoService, times(1)).getAllContactos(null);
    }

    @Test
    public void testCreateContacto() {
        Contacto contacto = new Contacto().id(1).nombre("John").apellidos("prueba").email("john.doe@example.com");
        when(contactoService.createContacto(any(Contacto.class))).thenReturn(contacto);

        ResponseEntity<Contacto> response = contactosApiController.createContacto(contacto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("John", response.getBody().getNombre());

        verify(contactoService, times(1)).createContacto(contacto);
    }

    @Test
    public void testUpdateContacto() {
        Contacto contacto = new Contacto().id(1).nombre("John").apellidos("prueba").email("john.doe@example.com");
        when(contactoService.updateContacto(anyInt(), any(Contacto.class))).thenReturn(contacto);

        ResponseEntity<Contacto> response = contactosApiController.updateContacto(1, contacto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getNombre());

        verify(contactoService, times(1)).updateContacto(1, contacto);
    }

    @Test
    public void testDeleteContacto() {
        doNothing().when(contactoService).deleteContacto(anyInt());

        ResponseEntity<Void> response = contactosApiController.deleteContacto(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(contactoService, times(1)).deleteContacto(1);
    }
}