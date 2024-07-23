package com.pruebaTecnica.service;

import org.openapitools.model.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pruebaTecnica.exception.ResourceNotFoundException;
import com.pruebaTecnica.repository.ContactoRepository;

@Service
public class ContactoService {

	@Autowired
	private ContactoRepository contactoRepository;

    public Page<Contacto> getContactos(int paginaActual, int size, String orderBy) {
    	
    	 // Define default sort if no valid orderBy is provided
        Sort sort = Sort.by(Sort.Order.asc("nombre")).and(Sort.by(Sort.Order.asc("apellidos")));

        // Parse the orderBy parameter and apply sorting
        if (orderBy != null && !orderBy.isEmpty()) {
            String[] sortParams = orderBy.split(",");
            for (String param : sortParams) {
                String[] sortOrder = param.trim().split(" ");
                if (sortOrder.length == 2) {
                    sort = sort.and(Sort.by(new Sort.Order(Sort.Direction.fromString(sortOrder[1]), sortOrder[0])));
                } else {
                    sort = sort.and(Sort.by(new Sort.Order(Sort.Direction.ASC, sortOrder[0])));
                }
            }
        }

        Pageable pageable = PageRequest.of(paginaActual, size, sort);
        return contactoRepository.findAll(pageable);
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
