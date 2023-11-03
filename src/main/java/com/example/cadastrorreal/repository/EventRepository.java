package com.example.cadastrorreal.repository;

import com.example.cadastrorreal.model.Evento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository <Evento, Long>{

    Evento findByNomeEvento(String nomeEvento);

    Evento deleteByNomeEvento(String nomeEvento);
}
