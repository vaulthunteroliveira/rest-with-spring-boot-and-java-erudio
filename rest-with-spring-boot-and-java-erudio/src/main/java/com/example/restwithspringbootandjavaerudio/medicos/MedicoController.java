package com.example.restwithspringbootandjavaerudio.medicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private MedicoService service;

    @Autowired
    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @PostMapping
    protected Medico cadastrar(@RequestBody Medico medico) {
        return service.save(medico);
    }
}
