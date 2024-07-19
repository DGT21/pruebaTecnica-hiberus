package org.openapitools.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

/**
 * Modelo de respuesta de contacto
 */
@Entity
@Table(name = "contactos")
public class Contacto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	private String apellidos;
	private String email;

	public Contacto id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * Identificador de contacto
	 * 
	 * @return id
	 */
	@ApiModelProperty(example = "1", value = "Identificador de contacto")

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Contacto nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	/**
	 * Nombre del contacto
	 * 
	 * @return nombre
	 */
	@ApiModelProperty(example = "1", value = "Nombre del contacto")

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Contacto apellidos(String apellidos) {
		this.apellidos = apellidos;
		return this;
	}

	/**
	 * apellidos del contacto
	 * 
	 * @return apellidos
	 */
	@ApiModelProperty(example = "1", value = "apellidos del contacto")

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Contacto email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * direccion de correo electrónico del contacto
	 * 
	 * @return email
	 */
	@ApiModelProperty(example = "1", value = "direccion de correo electrónico del contacto")

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Contacto contacto = (Contacto) o;
		return Objects.equals(this.id, contacto.id) && Objects.equals(this.nombre, contacto.nombre)
				&& Objects.equals(this.apellidos, contacto.apellidos) && Objects.equals(this.email, contacto.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, apellidos, email);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Contacto {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
		sb.append("    apellidos: ").append(toIndentedString(apellidos)).append("\n");
		sb.append("    email: ").append(toIndentedString(email)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
