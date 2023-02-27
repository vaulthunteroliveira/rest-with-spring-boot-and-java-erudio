package com.example.restwithspringbootandjavaerudio.medicos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
