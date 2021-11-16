package com.spring.reserva.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.reserva.domain.Reserva;
import com.spring.reserva.domain.enuns.EstadoReserva;

public class ReservaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = "Informe a data de início da reserva!")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@DateTimeFormat
	private LocalDateTime inicioReserva;

	@NotNull(message = "Informe a data do fim da reserva!")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@DateTimeFormat
	private LocalDateTime fimReserva;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@DateTimeFormat
	private LocalDateTime cancelamentoReserva;

	private Integer veiculo;
	private Integer estadoReserva;

	public ReservaDTO() {
	}

	public ReservaDTO(Reserva obj) {
		this.id = obj.getId();
		this.inicioReserva = obj.getInicioReserva();
		this.fimReserva = obj.getFimReserva();
		this.cancelamentoReserva = obj.getCancelamentoReserva();
		this.veiculo = obj.getVeiculo().getId();
		this.estadoReserva = obj.getEstadoReserva().getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getInicioReserva() {
		return inicioReserva;
	}

	public void setInicioReserva(LocalDateTime inicioReserva) {
		this.inicioReserva = inicioReserva;
	}

	public LocalDateTime getFimReserva() {
		return fimReserva;
	}

	public void setFimReserva(LocalDateTime fimReserva) {
		this.fimReserva = fimReserva;
	}

	public LocalDateTime getCancelamentoReserva() {
		return cancelamentoReserva;
	}

	public void setCancelamentoReserva(LocalDateTime cancelamentoReserva) {
		this.cancelamentoReserva = cancelamentoReserva;
	}

	public Integer getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Integer veiculo) {
		this.veiculo = veiculo;
	}

	public EstadoReserva getEstadoReserva() {
		return EstadoReserva.toEnum(this.estadoReserva);
	}

	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva.getCod();
	}

	@Override
	public String toString() {
		return "ReservaDTO [id=" + id + ", inicioReserva=" + inicioReserva + ", fimReserva=" + fimReserva
				+ ", cancelamentoReserva=" + cancelamentoReserva + ", veiculo=" + veiculo + ", estadoReserva="
				+ estadoReserva + "]";
	}
		
}
