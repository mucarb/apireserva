package com.spring.reserva.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.reserva.domain.Reserva;
import com.spring.reserva.domain.Veiculo;
import com.spring.reserva.domain.enuns.Estado;
import com.spring.reserva.domain.enuns.EstadoReserva;
import com.spring.reserva.repositories.ReservaRepository;
import com.spring.reserva.repositories.VeiculoRepository;

@Service
public class DbService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	public void dbInstance() throws Exception {

		Veiculo v1 = new Veiculo(null, "Ford Focus", "ICE2973", "2013", Estado.MANUTENCAO);
		Veiculo v2 = new Veiculo(null, "Volkswagen Golf", "NNS4646", "2015", null);
		Veiculo v3 = new Veiculo(null, "Volkswagen Fox", "BFQ8663", "2013", null);
		Veiculo v4 = new Veiculo(null, "Renault Sandero", "ABC1234", "2014", null);
		Veiculo v5 = new Veiculo(null, "Citroen C4 Lounge", "HMG0248", "2015", null);
		Veiculo v6 = new Veiculo(null, "Honda WR-V", "BBU3321", "2017", null);

		this.veiculoRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		Reserva r1 = new Reserva(null, LocalDateTime.parse("10/11/2021 09:30", formatter),
				LocalDateTime.parse("11/11/2021 09:30", formatter), v2, EstadoReserva.CONFIRMADA);
		Reserva r2 = new Reserva(null, LocalDateTime.parse("12/11/2021 10:30", formatter),
				LocalDateTime.parse("14/11/2021 10:30", formatter), v3, EstadoReserva.PROVISORIA);
		Reserva r3 = new Reserva(null, LocalDateTime.parse("16/11/2021 12:00", formatter),
				LocalDateTime.parse("18/11/2021 10:30", formatter), v4, EstadoReserva.CONFIRMADA);
		Reserva r4 = new Reserva(null, LocalDateTime.parse("20/11/2021 09:30", formatter),
				LocalDateTime.parse("20/11/2021 18:00", formatter), v5, EstadoReserva.CANCELADA);

		this.reservaRepository.saveAll(Arrays.asList(r1, r2, r3, r4));

	}

}
