package com.spring.reserva.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.spring.reserva.domain.Veiculo;
import com.spring.reserva.domain.enuns.Estado;

public class VeiculoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Informe o modelo do veículo!")
	private String modelo;

	@NotEmpty(message = "Informe a numeração da placa!")
	private String placa;

	@NotEmpty(message = "Informe o ano de fabricação!")
	private String ano;

	private Integer estado;

	
	
	public VeiculoDTO() {
	}

	public VeiculoDTO(Veiculo obj) {
		this.id = obj.getId();
		this.modelo = obj.getModelo();
		this.placa = obj.getPlaca();
		this.ano = obj.getAno();
		this.estado = obj.getEstado().getCod();
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

}
