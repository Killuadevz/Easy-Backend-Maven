package com.EasyContacts.demo.controller;

import java.util.*;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.EasyContacts.demo.repository.EasyConRepository;
import com.EasyContacts.demo.dto.*;
import com.EasyContacts.demo.model.*;
import com.EasyContacts.demo.service.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class EasyConController {

    @Autowired
    EasyConRepository repo;

    @Autowired
    EasyConService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        var contacts = repo.findAll();
        if (contacts.isEmpty()) {
            return ResponseEntity.status(404).body("Nenhum contato encontrado.");
        }
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable @Valid UUID id) {
        Optional<EasyConModel> contact = repo.findById(id);
        if (contact.isEmpty()) {
            return ResponseEntity.status(404).body("Contato não encontrado para o ID fornecido: " + id);
        }
        return ResponseEntity.ok(contact);
    }

    @PostMapping("new")
    public ResponseEntity<?> newContact(@RequestBody @Valid EasyConDto data) {
        try {
            EasyConModel newContact = new EasyConModel(data);
            repo.save(newContact);
            return ResponseEntity.status(201).body(Map.of("message", "Contato criado com sucesso."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Erro ao criar contato: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable UUID id, @RequestBody @Valid EasyConDto dto) {
        Optional<EasyConModel> exists = repo.findById(id);
        if (exists.isPresent()) {
            EasyConModel update = exists.get();
            update.setNome(dto.nome());
            update.setNumero(dto.numero());
            update.setDescricao(dto.descricao());
            update.setLink(dto.link());
            try {
                repo.save(update);
                Map<String, String> response = Map.of(
                        "message", "Contato atualizado com sucesso.",
                        "id", id.toString()
                );
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                Map<String, String> response = Map.of(
                        "error", "Erro ao atualizar contato: " + e.getMessage()
                );
                return ResponseEntity.status(500).body(response);
            }
        } else {
            Map<String, String> response = Map.of(
                    "error", "Contato não encontrado para o ID fornecido: " + id
            );
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("dell/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable @Valid UUID id) {
        Optional<EasyConModel> exists = repo.findById(id);
        if (exists.isPresent()) {
            try {
                repo.deleteById(id);
                return ResponseEntity.ok("Contato deletado com sucesso.");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Erro ao deletar contato: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(404).body("Contato não encontrado para o ID fornecido: " + id);
        }
    }


    @GetMapping("view")
    public ResponseEntity<?> getContatosCom11() {
        try {
            String total = service.getTotalContatosCom11();
            return ResponseEntity.ok(Map.of("total_contatos_com_11", total));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Erro ao obter total de contatos com 11: " + e.getMessage()));
        }
    }

    @GetMapping("procedure")
    public ResponseEntity<?> getTotalContatos() {
        try {
            String total = service.contarTotalContatos();
            return ResponseEntity.ok(Map.of("total_contatos", total));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Erro ao obter total de contatos: " + e.getMessage()));
        }
    }
}
