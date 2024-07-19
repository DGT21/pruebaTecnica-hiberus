package com.pruebaTecnica.service;

import java.util.List;

import org.openapitools.model.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pruebaTecnica.exception.ResourceNotFoundException;
import com.pruebaTecnica.repository.ContactoRepository;

@Service
public class ContactoService {

	@Autowired
	private ContactoRepository contactoRepository;

    public List<Contacto> getAllContactos(String orderBy) {
        if ("nombre".equalsIgnoreCase(orderBy)) {
            return contactoRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
        } else if ("apellidos".equalsIgnoreCase(orderBy)) {
            return contactoRepository.findAll(Sort.by(Sort.Direction.ASC, "apellidos"));
        }
        return contactoRepository.findAll();
    }

    public Contacto createContacto(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    public Contacto updateContacto(Integer id, Contacto contacto) {
        Contacto existingContacto = contactoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contacto not found with id " + id));
        existingContacto.setNombre(contacto.getNombre());
        existingContacto.setApellidos(contacto.getApellidos());
        existingContacto.setEmail(contacto.getEmail());
        return contactoRepository.save(existingContacto);
    }

    public void deleteContacto(Integer id) {
        Contacto existingContacto = contactoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contacto not found with id " + id));
        contactoRepository.delete(existingContacto);
    }

}
