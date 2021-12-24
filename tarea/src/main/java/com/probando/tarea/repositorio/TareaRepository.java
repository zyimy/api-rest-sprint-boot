package com.probando.tarea.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.probando.tarea.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea,Long> {

}
