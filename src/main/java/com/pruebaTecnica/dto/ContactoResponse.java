package com.pruebaTecnica.dto;

import java.util.List;

import org.openapitools.model.Contacto;

public class ContactoResponse {
	  private List<Contacto> contactos;
	    private int totalElementos;
	    private int paginasTotales;
	    private int elementosPagina;
	    private int paginaActual;
		public List<Contacto> getContactos() {
			return contactos;
		}
		public void setContactos(List<Contacto> contactos) {
			this.contactos = contactos;
		}
		public int getTotalElementos() {
			return totalElementos;
		}
		public void setTotalElementos(int totalElementos) {
			this.totalElementos = totalElementos;
		}
		public int getPaginasTotales() {
			return paginasTotales;
		}
		public void setPaginasTotales(int paginasTotales) {
			this.paginasTotales = paginasTotales;
		}
		public int getElementosPagina() {
			return elementosPagina;
		}
		public void setElementosPagina(int elementosPagina) {
			this.elementosPagina = elementosPagina;
		}
		public int getPaginaActual() {
			return paginaActual;
		}
		public void setPaginaActual(int paginaActual) {
			this.paginaActual = paginaActual;
		}

	    
	    
}
