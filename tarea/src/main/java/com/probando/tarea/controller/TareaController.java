package com.probando.tarea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.probando.tarea.model.Tarea;
import com.probando.tarea.repositorio.TareaRepository;

@RestController
@RequestMapping("/tareas")
public class TareaController {
	
	@Autowired
	private TareaRepository tr;
	
	@GetMapping
	List<Tarea> index(){
		
		return tr.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Tarea create(@RequestBody Tarea tarea) {
		return tr.save(tarea);
	}
	
	@PutMapping(value = "{id}")
	public Tarea update(@PathVariable Long id, @RequestBody Tarea tarea) {
		
		Tarea fromDbTarea = tr
				.findById(id)
				.orElseThrow(RuntimeException::new);
		
		fromDbTarea.setNombre(tarea.getNombre());
		fromDbTarea.setCompletado(tarea.isCompletado());
		
		return tr.save(fromDbTarea);
		
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable Long id) {
		Tarea tarea = tr
				.findById(id)
				.orElseThrow(RuntimeException::new);
		
		tr.delete(tarea);
	}

}
