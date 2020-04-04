package com.formacionbdi.microservicios.app.usuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.microservicios.app.usuarios.models.entity.Alumno;
import com.formacionbdi.microservicios.app.usuarios.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Override
	@Transactional(readOnly = true) // De spring framework
	public List<Alumno> findAll() {
		return (List<Alumno>) alumnoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long id) {
		return alumnoRepository.findById(id);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return alumnoRepository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		alumnoRepository.deleteById(id);
	}

}