package com.spring.reserva.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.reserva.domain.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
	
	List<Veiculo> findByEstado(Integer situacao);

}
