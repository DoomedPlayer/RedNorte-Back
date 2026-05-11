package com.RedNorte.SaludRedNorte.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RedNorte.SaludRedNorte.Model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByRutPaciente(String rutPaciente);

    List<Cita> findByEstado(String estado);
}