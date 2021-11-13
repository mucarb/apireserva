package com.spring.reserva.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.reserva.domain.Reserva;
import com.spring.reserva.domain.Veiculo;
import com.spring.reserva.domain.enuns.Estado;
import com.spring.reserva.domain.enuns.EstadoReserva;
import com.spring.reserva.repositories.ReservaRepository;
import com.spring.reserva.services.exceptions.DataIntegrityViolationException;
import com.spring.reserva.services.exceptions.ObjectNotFoundException;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private VeiculoService veiculoService;

	public Reserva findById(Integer id) {
		Optional<Reserva> obj = reservaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Reserva não encontrada! ID: " + id + ", Tipo: " + Reserva.class.getName()));
	}

	public List<Reserva> listAll() {
		return reservaRepository.findAll();
	}

	public Reserva create(Reserva obj, Integer id_veiculo) {
		obj.setId(null);

		Veiculo veiculo = veiculoService.findById(id_veiculo);

		if (veiculo.getEstado().equals(Estado.MANUTENCAO)) {
			throw new DataIntegrityViolationException(
					"O Veículo não está disponivel: " + veiculo.getModelo() + " Placa: " + veiculo.getPlaca());
		}

		obj.setVeiculo(veiculo);
		obj = reservaRepository.save(obj);

		this.veiculoService.updateSituacao(obj);

		return obj;
	}

	public Reserva update(Reserva obj) {
		findById(obj.getId());
		obj.setVeiculo(this.veiculoService.findById(obj.getId()));
		return this.reservaRepository.save(obj);
	}

	public Reserva cancel(Integer id) {
		Reserva obj = findById(id);

		obj.setCancelamentoReserva(LocalDateTime.now());
		obj.setEstadoReserva(EstadoReserva.CANCELADA);

		this.veiculoService.updateSituacao(obj);

		return update(obj);
	}

}
