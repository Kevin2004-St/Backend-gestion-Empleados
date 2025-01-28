package com.gestion.empleados.Repository;

import com.gestion.empleados.Entity.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository <Empleados,Long> {

    Optional<Empleados> findByCedula(Long cedula);
}
