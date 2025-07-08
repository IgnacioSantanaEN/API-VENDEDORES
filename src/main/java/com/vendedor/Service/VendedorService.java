package com.vendedor.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendedor.Model.Vendedor;
import com.vendedor.Repository.VendedorRepository;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    public List<Vendedor> getALLVendedores(){
        return vendedorRepository.findAll();
    }

     public Vendedor getVendedorById(Integer id) {
        return vendedorRepository.findById(id).orElse(null);
    }

    public Vendedor saveVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Vendedor updateVendedor(Integer id, Vendedor Actualizado) {
        Vendedor existente = vendedorRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setIdUsuario(Actualizado.getIdUsuario());
            existente.setNombreCompleto(Actualizado.getNombreCompleto());
            existente.setRut(Actualizado.getRut());
            existente.setAreaVentas(Actualizado.getAreaVentas());
            return vendedorRepository.save(existente);
        }
        return null;
    }

    public boolean deleteVendedor(Integer id) {
        if (vendedorRepository.existsById(id)) {
            vendedorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
