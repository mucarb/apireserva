package com.spring.reserva.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.reserva.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

	@Query("SELECT r FROM Reserva r WHERE (r.estadoReserva <> 2) AND (r.inicioReserva <= :data_ini) AND (r.fimReserva >= :data_fim)")
	List<Reserva> findAllReservas(@Param(value = "data_ini") LocalDateTime data_ini, @Param(value = "data_fim") LocalDateTime data_fim);
	
}