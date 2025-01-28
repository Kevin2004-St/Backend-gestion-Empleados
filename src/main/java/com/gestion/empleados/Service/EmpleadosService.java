package com.gestion.empleados.Service;

import com.gestion.empleados.Entity.Empleados;

import java.util.List;
import java.util.Optional;

public interface EmpleadosService {

    List<Empleados> findAll();

    Empleados save(Empleados empleado);

    Optional<Empleados> findByCedula(Long cedula);

    Empleados update(Long cedula, Empleados empleado);

    void delete(Long cedula);

}
