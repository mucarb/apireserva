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
import com.spring.reserva.dto.ReservaDTO;
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

	public Reserva create(ReservaDTO objDto) {
		return fromDto(objDto);
	}

	public Reserva update(Reserva obj) {
		findById(obj.getId());
		obj.setVeiculo(this.veiculoService.findById(obj.getId()));
		return this.reservaRepository.save(obj);
	}
	
	private Reserva fromDto(ReservaDTO objDto) {
		Reserva newObj = new Reserva();

		newObj.setId(objDto.getId());
		newObj.setInicioReserva(objDto.getInicioReserva());
		newObj.setFimReserva(objDto.getFimReserva());
		newObj.setEstadoReserva(EstadoReserva.toEnum(objDto.getEstadoReserva().getCod()));

		Veiculo v = veiculoService.findById(objDto.getVeiculo());

		newObj.setVeiculo(v);

		if (v.getEstado().equals(Estado.MANUTENCAO)) {
			throw new DataIntegrityViolationException(
					"O Veículo não está disponivel: " + v.getModelo() + " Placa: " + v.getPlaca());
		}

		System.out.println(objDto.toString());
		
		return reservaRepository.save(newObj);
	}

	public Reserva cancel(Integer id) {
		Reserva obj = findById(id);

		obj.setCancelamentoReserva(LocalDateTime.now());
		
		if(!obj.getEstadoReserva().equals(EstadoReserva.CANCELADA)) {
			obj.setEstadoReserva(EstadoReserva.CANCELADA);			
		} else {
			throw new DataIntegrityViolationException("Essa Reserva já está cancelada!");			
		}

		this.veiculoService.updateSituacao(obj);

		return update(obj);
	}

}
