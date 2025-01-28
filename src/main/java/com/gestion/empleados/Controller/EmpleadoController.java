package com.gestion.empleados.Controller;

import com.gestion.empleados.Entity.Empleados;
import com.gestion.empleados.Excepciones.ResourceNoFoundException;
import com.gestion.empleados.Service.EmpleadosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:4200/")
public class EmpleadoController {

    private final EmpleadosService service;

    public EmpleadoController(EmpleadosService service) {
        this.service = service;
    }

    @GetMapping("/empleados")
    public ResponseEntity<?> findAll(){
        List<Empleados> empleados = service.findAll();

        if(empleados.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No empleados");
        }
        return ResponseEntity.ok().body(empleados);
    }

    @GetMapping("/empleados/{cedula}")
    public ResponseEntity<Empleados> findById(@PathVariable Long cedula){
        return  service.findByCedula(cedula)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNoFoundException("No existe el empleado con la cedula: "+ cedula ));

    }

    @PostMapping("/empleados")
    public ResponseEntity<?> save(@RequestBody Empleados empleado){
         return ResponseEntity.status(HttpStatus.CREATED).body(service.save(empleado));
    }

    @PutMapping("/empleados/{cedula}")
    public ResponseEntity<?> update(@PathVariable Long cedula, @RequestBody Empleados empleado) {
        return service.findByCedula(cedula)
                .map(existingEmpleado -> {
                    Empleados empleadoUpdate = service.update(cedula, empleado);
                    return ResponseEntity.ok(empleadoUpdate);
                })
                .orElseThrow(() -> new ResourceNoFoundException("No existe el empleado con la cedula: " + cedula));
    }


}
