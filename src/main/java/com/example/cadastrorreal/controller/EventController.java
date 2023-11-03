package com.example.cadastrorreal.controller;

import com.example.cadastrorreal.model.Evento;
import com.example.cadastrorreal.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Evento getEventoByNome(@RequestParam String nomeEvento) {
        return eventRepository.findByNomeEvento(nomeEvento);
    }
    @PutMapping("/eventoAtualiza")
    private String eventoAtualizaUsuario> updateUsuario(@RequestParam ("nomeEvento") String nomeEvento,
                                                        @RequestParam ("dataEvento") Date dataEvento,
                                                        @RequestParam ("localEvento") String localEvento,
                                                        @RequestBody Evento updateEvento) {
        List <Evento> optionalEvento = eventRepository.findByNomeEvento(nomeEvento);

        if (!optionalUsuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Usuario existingUsuario = optionalUsuario.get();
        existingUsuario.setNome(updatedUsuario.getNome());
        existingUsuario.setEmail(updatedUsuario.getEmail());

        Usuario updated = usuarioRepository.save(existingUsuario);
        return ResponseEntity.ok(updated);
    }
}
