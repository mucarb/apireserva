package com.spring.reserva.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.reserva.domain.enuns.Estado;

@Entity
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Informe o modelo do veículo!")
	private String modelo;

	@NotEmpty(message = "Informe a numeração da placa!")
	private String placa;

	@NotEmpty(message = "Informe o ano de fabricação!")
	private String ano;
	
	private Integer estado;
	
	@JsonIgnore
	@OneToMany(mappedBy = "veiculo")
	private List<Reserva> reserva;

	public Veiculo() {
	}

	public Veiculo(Integer id, String modelo, String placa, String ano, Estado estado) {
		this.id = id;
		this.modelo = modelo;
		this.placa = placa;
		this.ano = ano;
		this.estado = (estado == null) ? 0 : estado.getCod();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Estado getEstado() {
		return Estado.toEnum(this.estado);
	}

	public void setEstado(Estado estado) {
		this.estado = estado.getCod();
	}
	
	public List<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Modelo: " + modelo + " - Placa: " + placa;
	}
	
}
