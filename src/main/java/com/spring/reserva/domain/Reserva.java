package com.spring.reserva.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.reserva.domain.enuns.EstadoReserva;

@Entity
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToOne
	@JoinColumn(name = "veiculo_id")
	private Veiculo veiculo;

	private Integer estadoReserva;

	public Reserva() {
	}

	public Reserva(Integer id, LocalDateTime inicioReserva, LocalDateTime fimReserva, Veiculo veiculo, EstadoReserva estadoReserva) {
		this.id = id;
		this.inicioReserva = inicioReserva;
		this.fimReserva = fimReserva;
		this.veiculo = veiculo;
		this.estadoReserva = (estadoReserva == null) ? 0 : estadoReserva.getCod();
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

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public EstadoReserva getEstadoReserva() {
		return EstadoReserva.toEnum(this.estadoReserva);
	}

	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva.getCod();
	}

}
