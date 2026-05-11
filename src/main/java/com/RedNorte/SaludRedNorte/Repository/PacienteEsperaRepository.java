package com.RedNorte.SaludRedNorte.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RedNorte.SaludRedNorte.Model.PacienteEspera;

import java.util.List;

@Repository
public interface PacienteEsperaRepository extends JpaRepository<PacienteEspera, Long> {
    // Puedes agregar métodos personalizados fácilmente solo nombrándolos bien
    List<PacienteEspera> findByEspecialidadRequerida(String especialidad);
}