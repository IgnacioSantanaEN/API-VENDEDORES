package com.vendedor.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendedor.Model.Vendedor;
import com.vendedor.Service.VendedorService;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("api/vendedores")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/")
    public ResponseEntity<?> listarVendedores(){
        List<Vendedor> vendedores = vendedorService.getALLVendedores();

        if (vendedores.isEmpty()) {
            return ResponseEntity.ok(new Mensaje("No hay vendedores registrados"));
        }
        return ResponseEntity.ok(vendedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> encontrarVendedor(@PathVariable Integer id){
        Vendedor vendedor = vendedorService.getVendedorById(id);
        if(vendedor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("Vendedor no encontrado"));
        }
        return ResponseEntity.ok(vendedor);
    }

    @PostMapping("/")
    public ResponseEntity<?> registrarVendedor(@RequestBody Vendedor vendedor){
        Vendedor nuevoVendedor = vendedorService.saveVendedor(vendedor);
        if(nuevoVendedor == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Mensaje("No fue posible registrar al vendedor"));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new Mensaje("Vendedor registrado: " + nuevoVendedor.getIdVendedor()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVendedor(@PathVariable Integer id, @RequestBody Vendedor vendedor){
        Vendedor actualizado = vendedorService.updateVendedor(id, vendedor);
        if(actualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("El vendedor a actualizar no fue encontrado"));
        }
        return ResponseEntity
            .ok(new Mensaje("El vendedor fue actualizado: " + actualizado.getIdVendedor()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVendedor(@PathVariable Integer id){
        boolean eliminado = vendedorService.deleteVendedor(id);
        if(!eliminado){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Mensaje("El vendedor no fue encontrado"));
        }

        return ResponseEntity.ok(new Mensaje("Vendedor eliminado con exito"));
    }

    @Data
    @AllArgsConstructor
    public static class Mensaje{
        private String mensaje;
    }
}
