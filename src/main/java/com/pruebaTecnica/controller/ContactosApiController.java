package com.pruebaTecnica.controller;

import org.openapitools.api.ContactosApi;
import org.openapitools.model.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaTecnica.dto.ContactoResponse;
import com.pruebaTecnica.service.ContactoService;

@RestController
@RequestMapping("/contactos")
public class ContactosApiController implements ContactosApi {

	@Autowired
	private ContactoService contactoService;

	@GetMapping
	public ResponseEntity<ContactoResponse> getContactos(@RequestParam(defaultValue = "0") int paginaActual,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(value = "orderBy", required = false) String orderBy) {
		Page<Contacto> contactos = contactoService.getContactos(paginaActual, size, orderBy);
		ContactoResponse response = new ContactoResponse();
		response.setContactos(contactos.getContent());
		response.setTotalElementos((int) contactos.getTotalElements());
		response.setPaginasTotales(contactos.getTotalPages());
		response.setElementosPagina(contactos.getSize());
		response.setPaginaActual(contactos.getNumber() + 1);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Contacto> createContacto(@RequestBody Contacto contacto) {
		Contacto created = contactoService.createContacto(contacto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Contacto> updateContacto(@PathVariable Integer id, @RequestBody Contacto contacto) {
		Contacto updated = contactoService.updateContacto(id, contacto);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteContacto(@PathVariable Integer id) {
		contactoService.deleteContacto(id);
		return ResponseEntity.noContent().build();
	}
}
