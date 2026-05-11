package com.RedNorte.SaludRedNorte.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RedNorte.SaludRedNorte.Model.RegistroReasignacion;

@Repository
public interface ReasignacionRepository extends JpaRepository<RegistroReasignacion, Long> {
}
