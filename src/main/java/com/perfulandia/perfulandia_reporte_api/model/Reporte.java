package com.perfulandia.perfulandia_reporte_api.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reportes")
@Data

public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reporte;

    private String tipo_reporte;
    private Date fecha_generacion;
    private String descripcion;
    private String Datos;
}
