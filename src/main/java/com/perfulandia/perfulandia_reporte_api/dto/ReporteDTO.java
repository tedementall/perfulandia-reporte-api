package com.perfulandia.perfulandia_reporte_api.dto;

import lombok.Data;

@Data
public class ReporteDTO {
    private Integer id_reporte;
    private String tipo_reporte;
    private String fecha_generacion;
    private String descripcion;
    private String datos;
}
