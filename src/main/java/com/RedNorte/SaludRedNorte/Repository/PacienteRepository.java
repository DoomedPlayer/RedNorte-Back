package com.RedNorte.SaludRedNorte.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RedNorte.SaludRedNorte.Model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {
    Optional<Paciente> findByEmailContacto(String email);
}