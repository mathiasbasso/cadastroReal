package com.example.cadastrorreal.controller;

import com.example.cadastrorreal.model.Evento;
import com.example.cadastrorreal.repository.EventRepository;
import com.example.cadastrorreal.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;

import java.util.Date;
import java.util.List;

import static com.example.cadastrorreal.repository.EventRepository.findAll;

@RestController
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/eventoAdd")
    private String eventoAdd(@RequestParam("nomeEvento") String nomeEvento,
                             @RequestParam("dataEvento") Date dataEvento,
                             @RequestParam("localEvento") String localEvento,
                             @RequestParam("action") String action, Model model) {
        Evento newEvento = new Evento();

        if ("adicionar".equals(action)) {
            if (eventRepository.findByNomeEvento(nomeEvento) == null) {
                newEvento.setNomeEvento(nomeEvento);
                newEvento.setDataEvento(dataEvento);
                newEvento.setLocalEvento(localEvento);
                eventRepository.save(newEvento);
                model.addAttribute("message", "Novo evento registrado!");
            }
        } else {
            model.addAttribute("message", "Este evento ja existe.");
        }
        return "";
    }

    @DeleteMapping("/eventoDelete")
    private void eventoDelete(@RequestParam String nomeEvento) {
        eventRepository.deleteByNomeEvento(nomeEvento);
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<Evento>> getAll() {

        return new ResponseEntity<>(EventoService.getAll(), HttpStatus.OK);
    }
    @PutMapping("/updateEvento")
    private ResponseEntity<Object> updateEvento(@RequestParam ("nomeEvento") String nomeEvento,
                                                @RequestParam ("dataEvento") Date dataEvento,
                                                @RequestParam ("localEvento") String localEvento,
                                                @RequestBody Evento updateEvento) {
        Evento checkEvento = eventRepository.findByNomeEvento(nomeEvento);

        if (!checkEvento.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        checkEvento.setNomeEvento(updateEvento.getNomeEvento());
        checkEvento.setDataEvento(updateEvento.getDataEvento());
        checkEvento.setLocalEvento(updateEvento.getLocalEvento());


        Evento updated = eventRepository.save(checkEvento);
        return ResponseEntity.ok(updated);
    }
}
