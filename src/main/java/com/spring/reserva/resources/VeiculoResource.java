package com.spring.reserva.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.reserva.domain.Veiculo;
import com.spring.reserva.dto.VeiculoDTO;
import com.spring.reserva.services.VeiculoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoResource {

	@Autowired
	private VeiculoService veiculoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<VeiculoDTO> findById(@PathVariable Integer id) {
		Veiculo obj = this.veiculoService.findById(id);
		VeiculoDTO objDto = new VeiculoDTO(obj);
		return ResponseEntity.ok().body(objDto);
	}

	@GetMapping
	public ResponseEntity<List<VeiculoDTO>> listAll() {
		List<VeiculoDTO> listDto = this.veiculoService.listAll().stream().map(obj -> new VeiculoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/fetch")
	public ResponseEntity<List<VeiculoDTO>> findAllDisponivel(
			@RequestParam("datainicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
			@RequestParam("datafim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
		List<Veiculo> listObj = this.veiculoService.findAllDisponivel(dataInicio, dataFim);
		List<VeiculoDTO> listDto = listObj.stream().map(obj -> new VeiculoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<Veiculo> create(@Valid @RequestBody Veiculo obj) {
		obj = this.veiculoService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Veiculo> update(@Valid @RequestBody Veiculo obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = veiculoService.update(obj, id);
		return ResponseEntity.ok().body(obj);
	}

}
