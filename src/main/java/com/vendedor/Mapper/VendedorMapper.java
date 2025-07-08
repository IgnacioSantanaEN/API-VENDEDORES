package com.vendedor.Mapper;

import com.vendedor.Dto.VendedorDTO;
import com.vendedor.Model.Vendedor;

public class VendedorMapper {
    public static VendedorDTO toDTO(Vendedor vendedor) {
        String link = "http/localhost:8087/api/vendedores/" + vendedor.getIdVendedor();
        return new VendedorDTO(vendedor.getIdVendedor(), link);
    }
}
