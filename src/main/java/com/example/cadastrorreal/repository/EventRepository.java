package com.example.cadastrorreal.repository;

import com.example.cadastrorreal.model.Evento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository <Evento, Long>{


    static List<Evento> findAll(String nomeEvento) {
        return null;
    }

    Evento findByNomeEvento(String nomeEvento);

    Evento deleteByNomeEvento(String nomeEvento);
}
