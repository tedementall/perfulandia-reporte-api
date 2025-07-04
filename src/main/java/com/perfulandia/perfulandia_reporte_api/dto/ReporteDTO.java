package com.perfulandia.perfulandia_reporte_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO extends RepresentationModel<ReporteDTO> {
    private Integer id_reporte;
    private String tipo_reporte;
    private String fecha_generacion;
    private String descripcion;
    private String datos;

    public Integer getId() {
    return id_reporte;
}

public void setId(Integer id) {
    this.id_reporte = id;
}
}
