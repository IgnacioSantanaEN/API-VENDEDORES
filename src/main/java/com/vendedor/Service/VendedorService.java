package com.vendedor.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendedor.Dto.VendedorDTO;
import com.vendedor.Model.Vendedor;
import com.vendedor.Repository.VendedorRepository;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository repository;

    public List<VendedorDTO> getAllVendedores(){
        return repository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public Vendedor getVendedorById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Vendedor saveVendedor(Vendedor vendedor) {
        return repository.save(vendedor);
    }

    public Vendedor updateVendedor(Integer id, Vendedor Actualizado) {
        Vendedor existente = repository.findById(id).orElse(null);
        if (existente != null) {
            existente.setIdUsuario(Actualizado.getIdUsuario());
            existente.setNombreCompleto(Actualizado.getNombreCompleto());
            existente.setRut(Actualizado.getRut());
            existente.setAreaVentas(Actualizado.getAreaVentas());
            return repository.save(existente);
        }
        return null;
    }

    public boolean deleteVendedor(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public VendedorDTO toDTO(Vendedor vendedor) {
        VendedorDTO dto = new VendedorDTO();
        dto.setIdVendedor(vendedor.getIdVendedor());

        dto.setLink("http://localhost:8087/api/vendedores/" + vendedor.getIdVendedor());
        return dto;
    }
}
