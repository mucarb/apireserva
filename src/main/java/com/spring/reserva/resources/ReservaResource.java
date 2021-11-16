package com.spring.reserva.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.reserva.domain.Reserva;
import com.spring.reserva.dto.ReservaDTO;
import com.spring.reserva.services.ReservaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/reservas")
public class ReservaResource {

	@Autowired
	private ReservaService reservaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ReservaDTO> findById(@PathVariable Integer id) {
		ReservaDTO objDto = new ReservaDTO(reservaService.findById(id));
		return ResponseEntity.ok().body(objDto);
	}

	@GetMapping
	public ResponseEntity<List<ReservaDTO>> listAll() {
		List<ReservaDTO> listDto = this.reservaService.listAll().stream().map(obj -> new ReservaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<ReservaDTO> create(@Valid @RequestBody ReservaDTO obj) {
		obj = new ReservaDTO(this.reservaService.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/cancel/{id}")
	public ResponseEntity<Reserva> cancel(@PathVariable Integer id) {
		Reserva obj = reservaService.cancel(id);
		return ResponseEntity.ok().body(obj);
	}

}
