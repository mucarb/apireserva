package com.spring.reserva.resources;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.reserva.domain.Reserva;
import com.spring.reserva.services.ReservaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/reservas")
public class ReservaResource {

	@Autowired
	private ReservaService reservaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Reserva> findById(@PathVariable Integer id) {
		Reserva obj = this.reservaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<Reserva>> listAll() {
		List<Reserva> listObj = this.reservaService.listAll();
		return ResponseEntity.ok().body(listObj);
	}

	@PostMapping
	public ResponseEntity<Reserva> create(@Valid @RequestBody Reserva obj,
			@RequestParam(value = "veiculo") Integer id_veiculo) {
		obj = this.reservaService.create(obj, id_veiculo);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/reservas/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/cancel/{id}")
	public ResponseEntity<Reserva> cancel(@PathVariable Integer id) {
		Reserva obj = reservaService.cancel(id);
		return ResponseEntity.ok().body(obj);
	}

}
