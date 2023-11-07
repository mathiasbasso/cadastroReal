package com.example.cadastrorreal.service;

import com.example.cadastrorreal.model.Evento;
import com.example.cadastrorreal.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {
    List<Evento> eventos = new ArrayList<>();

    public static List<Evento> getAll() {
        return null;
    }

    public List<Evento> getAllEventos() {
        return EventRepository.findAll(eventos.toString());


    }
}
