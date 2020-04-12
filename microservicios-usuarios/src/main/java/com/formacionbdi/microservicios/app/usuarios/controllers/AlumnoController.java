package com.formacionbdi.microservicios.app.usuarios.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.usuarios.models.entity.Alumno;
import com.formacionbdi.microservicios.app.usuarios.services.AlumnoService;

@RestController
@RequestMapping
public class AlumnoController {

	private static Logger LOG = LoggerFactory.getLogger(AlumnoController.class);

	@Autowired
	private AlumnoService alumnoService;

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(alumnoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id) {
		Optional<Alumno> o = alumnoService.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(o.get());
	}

	// Eliminar
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		alumnoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno) {
		Alumno alumnboDB = alumnoService.save(alumno);

		return ResponseEntity.status(HttpStatus.CREATED).body(alumnboDB);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Alumno> o = alumnoService.findById(id);

		if (o.isEmpty()) {
			ResponseEntity.notFound().build();
		}

		Alumno alumnoDB = o.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.save(alumnoDB));
	}

}