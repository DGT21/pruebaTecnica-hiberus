package com.pruebaTecnica.contact_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.Contacto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.pruebaTecnica.exception.ResourceNotFoundException;
import com.pruebaTecnica.repository.ContactoRepository;
import com.pruebaTecnica.service.ContactoService;

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

		Contacto contacto1 = new Contacto().id(1).nombre("prueba1").apellidos("prueba1").email("prueba1@prueba1.com");
		Contacto contacto2 = new Contacto().id(2).nombre("prueba2").apellidos("prueba2").email("prueba2@prueba2.com");
		List<Contacto> contactoList = new ArrayList<>();
		contactoList.add(contacto1);
		contactoList.add(contacto2);
		Page<Contacto> pagina = new PageImpl<>(contactoList, PageRequest.of(0, 5), 2);

		when(contactoRepository
				.findAll(PageRequest.of(0, 5, Sort.by(Sort.Order.asc("nombre"), Sort.Order.asc("apellidos")))))
				.thenReturn(pagina);

		// Call the service method
		Page<Contacto> result = contactoService.getContactos(0, 5, null);

		// Perform assertions
		assertEquals(2, result.getTotalElements());
		assertEquals(2, result.getContent().size());
		assertEquals("prueba1", result.getContent().get(0).getNombre());
		assertEquals("prueba2", result.getContent().get(1).getNombre());

		// Verify that the repository method was called with the correct parameters
		Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("nombre"), Sort.Order.asc("apellidos")));
		verify(contactoRepository).findAll(pageable);
	}

	@Test
	public void testCreateContacto() {
		Contacto contacto = new Contacto().id(1).nombre("prueba1").apellidos("prueba1").email("prueba1.prueba1@prueba.com");
		when(contactoRepository.save(any(Contacto.class))).thenReturn(contacto);
		Contacto createdContacto = contactoService.createContacto(contacto);
		assertEquals("prueba1", createdContacto.getNombre());
		verify(contactoRepository, times(1)).save(contacto);
	}

	@Test
	public void testUpdateContacto() {
		Contacto existingContacto = new Contacto().id(1).nombre("prueba1").apellidos("prueba1").email("prueba1.prueba1@prueba.com");
		Contacto updatedContacto = new Contacto().id(1).nombre("prueba2").apellidos("prueba1").email("prueba2.prueba1@prueba.com");

		when(contactoRepository.findById(anyInt())).thenReturn(Optional.of(existingContacto));
		when(contactoRepository.save(any(Contacto.class))).thenReturn(updatedContacto);

		Contacto result = contactoService.updateContacto(1, updatedContacto);
		assertEquals("prueba2", result.getNombre());
		verify(contactoRepository, times(1)).findById(1);
		verify(contactoRepository, times(1)).save(existingContacto);
	}

	@Test
	public void testDeleteContacto() {
		Contacto existingContacto = new Contacto().id(1).nombre("prueba1").apellidos("prueba1").email("prueba1.prueba1@prueba.com");

		when(contactoRepository.findById(anyInt())).thenReturn(Optional.of(existingContacto));
		doNothing().when(contactoRepository).delete(existingContacto);

		contactoService.deleteContacto(1);
		verify(contactoRepository, times(1)).findById(1);
		verify(contactoRepository, times(1)).delete(existingContacto);
	}

	@Test
	public void actualizarContactoNotFoundTest() {
		Contacto updatedContacto = new Contacto().id(1).nombre("prueba2").apellidos("prueba1").email("prueba2.prueba1@prueba.com");

		when(contactoRepository.findById(anyInt())).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			contactoService.updateContacto(1, updatedContacto);
		});

		verify(contactoRepository, times(1)).findById(1);
		verify(contactoRepository, times(0)).save(any(Contacto.class));
	}

	@Test
	public void borrarContactoNotFoundTest() {
		when(contactoRepository.findById(anyInt())).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			contactoService.deleteContacto(1);
		});

		verify(contactoRepository, times(1)).findById(1);
		verify(contactoRepository, times(0)).delete(any(Contacto.class));
	}
}