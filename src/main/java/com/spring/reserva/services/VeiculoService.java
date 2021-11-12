package com.spring.reserva.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.reserva.domain.Reserva;
import com.spring.reserva.domain.Veiculo;
import com.spring.reserva.domain.enuns.EstadoReserva;
import com.spring.reserva.domain.enuns.Estado;
import com.spring.reserva.repositories.ReservaRepository;
import com.spring.reserva.repositories.VeiculoRepository;
import com.spring.reserva.services.exceptions.ObjectNotFoundException;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private ReservaRepository reservaRepository;
	
	public Veiculo findById(Integer id) {
		Optional<Veiculo> veiculo = this.veiculoRepository.findById(id);
		return veiculo.orElseThrow(() -> new ObjectNotFoundException(
				"Veículo não encontrado! ID: " + id + ", Tipo: " + Veiculo.class.getName()));
	}
	
	public List<Veiculo> listAll() {		
		return this.veiculoRepository.findAll();
	}

	public List<Veiculo> findAllDisponivel(LocalDateTime dataInicio, LocalDateTime dataFim) {
		// reserva nao canceladas no periodo informado
		List<Reserva> listReservas = this.reservaRepository.findAllReservas(dataInicio, dataFim);
		
		List<Veiculo> listVeiculos = this.veiculoRepository.findByEstado(0);
				
		for (Veiculo veiculo : listVeiculos) {
			for (Reserva reserva : listReservas) {
				if(veiculo.equals(reserva.getVeiculo())) {
					listVeiculos.remove(reserva.getVeiculo());
				}
			}
		}
		
		return listVeiculos;
	}

	public Veiculo create(Veiculo obj) {
		obj.setId(null);
		return veiculoRepository.save(obj);
	}

	public Veiculo update(Veiculo obj, Integer id) {
		Veiculo newObj = findById(obj.getId());
		updateData(newObj, obj);
		return veiculoRepository.save(obj);
	}

	private void updateData(Veiculo newObj, Veiculo obj) {
		newObj.setModelo(obj.getModelo());
		newObj.setPlaca(obj.getPlaca());
		newObj.setAno(obj.getAno());
		newObj.setEstado(obj.getEstado());
	}

	public void updateSituacao(Reserva obj) {
		if (obj.getEstadoReserva().equals(EstadoReserva.CANCELADA)) {
			obj.getVeiculo().setEstado(Estado.DISPONIVEL);
		}

		update(obj.getVeiculo(), obj.getId());
	}
	
}
