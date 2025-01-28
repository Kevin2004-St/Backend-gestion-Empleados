package com.gestion.empleados.Service.Impl;

import com.gestion.empleados.Entity.Empleados;
import com.gestion.empleados.Repository.EmpleadoRepository;
import com.gestion.empleados.Service.EmpleadosService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadosService {

    private final EmpleadoRepository repository;

    public EmpleadoServiceImpl(EmpleadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Empleados> findAll() {
        return repository.findAll();
    }

    @Override
    public Empleados save(Empleados empleado) {
        return repository.save(empleado);

    }

    @Override
    public Optional<Empleados> findByCedula(Long cedula) {
        return repository.findByCedula(cedula);
    }

    @Override
    public Empleados update(Long cedula, Empleados empleado) {
        return repository.findByCedula(cedula)
                .map(exist ->{
                    exist.setNombre(empleado.getNombre());
                    exist.setApellido(empleado.getApellido());
                    exist.setEmail(empleado.getEmail());
                    return repository.save(exist);
                }).orElse(null);
    }
}
