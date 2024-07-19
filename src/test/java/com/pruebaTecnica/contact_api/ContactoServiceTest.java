package com.pruebaTecnica.contact_api;

import com.pruebaTecnica.exception.ResourceNotFoundException;
import com.pruebaTecnica.repository.ContactoRepository;
import com.pruebaTecnica.service.ContactoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.Contacto;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ContactoServiceTest {

    @Mock
    private ContactoRepository contactoRepository;

    @InjectMocks
    private ContactoService contactoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllContactos() {
        when(contactoRepository.findAll()).thenReturn(Arrays.asList(new Contacto().id(1).nombre("John").apellidos("Doe").email("john.doe@example.com")));
        List<Contacto> contactos = contactoService.getAllContactos(null);
        assertEquals(1, contactos.size());
        assertEquals("John", contactos.get(0).getNombre());

        when(contactoRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"))).thenReturn(Arrays.asList(new Contacto().id(2).nombre("Alice").apellidos("Smith").email("alice.smith@example.com")));
        contactos = contactoService.getAllContactos("nombre");
        assertEquals(1, contactos.size());
        assertEquals("Alice", contactos.get(0).getNombre());
    }

    @Test
    public void testCreateContacto() {
        Contacto contacto = new Contacto().id(1).nombre("John").apellidos("Doe").email("john.doe@example.com");
        when(contactoRepository.save(any(Contacto.class))).thenReturn(contacto);
        Contacto createdContacto = contactoService.createContacto(contacto);
        assertEquals("John", createdContacto.getNombre());
        verify(contactoRepository, times(1)).save(contacto);
    }

    @Test
    public void testUpdateContacto() {
        Contacto existingContacto = new Contacto().id(1).nombre("John").apellidos("Doe").email("john.doe@example.com");
        Contacto updatedContacto = new Contacto().id(1).nombre("Jane").apellidos("Doe").email("jane.doe@example.com");

        when(contactoRepository.findById(anyInt())).thenReturn(Optional.of(existingContacto));
        when(contactoRepository.save(any(Contacto.class))).thenReturn(updatedContacto);

        Contacto result = contactoService.updateContacto(1, updatedContacto);
        assertEquals("Jane", result.getNombre());
        verify(contactoRepository, times(1)).findById(1);
        verify(contactoRepository, times(1)).save(existingContacto);
    }

    @Test
    public void testDeleteContacto() {
        Contacto existingContacto = new Contacto().id(1).nombre("John").apellidos("Doe").email("john.doe@example.com");

        when(contactoRepository.findById(anyInt())).thenReturn(Optional.of(existingContacto));
        doNothing().when(contactoRepository).delete(existingContacto);

        contactoService.deleteContacto(1);
        verify(contactoRepository, times(1)).findById(1);
        verify(contactoRepository, times(1)).delete(existingContacto);
    }

    @Test
    public void testUpdateContactoNotFound() {
        Contacto updatedContacto = new Contacto().id(1).nombre("Jane").apellidos("Doe").email("jane.doe@example.com");

        when(contactoRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            contactoService.updateContacto(1, updatedContacto);
        });

        verify(contactoRepository, times(1)).findById(1);
        verify(contactoRepository, times(0)).save(any(Contacto.class));
    }

    @Test
    public void testDeleteContactoNotFound() {
        when(contactoRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            contactoService.deleteContacto(1);
        });

        verify(contactoRepository, times(1)).findById(1);
        verify(contactoRepository, times(0)).delete(any(Contacto.class));
    }
}