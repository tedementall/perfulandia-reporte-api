package com.perfulandia.perfulandia_reporte_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.perfulandia.perfulandia_reporte_api.repository.ReporteRepository;
import com.perfulandia.perfulandia_reporte_api.dto.ReporteDTO;
import com.perfulandia.perfulandia_reporte_api.model.Reporte;

@Service
public class ReporteService {
    @Autowired
    private ReporteRepository Repository;

    public ReporteDTO crearReporte(ReporteDTO reporteDTO) {
        Reporte reporte = toEntity(reporteDTO);
        Reporte savedReporte = Repository.save(reporte);
        return toDto(savedReporte);
    }

    public List<ReporteDTO> obtenerReportes() {
        return Repository.findAll().stream()
            .map(this::toDto)
            .toList();
    }

    public Optional<ReporteDTO> obtenerReportePorId(Integer id) {
        return Repository.findById(id)
            .map(this::toDto);
    }

    public ReporteDTO actualizarReporte(Integer id, ReporteDTO reporteDTO) {
        return Repository.findById(id)
            .map(existingReporte -> {
                existingReporte.setTipo_reporte(reporteDTO.getTipo_reporte());
                existingReporte.setFecha_generacion(java.sql.Date.valueOf(reporteDTO.getFecha_generacion()));
                existingReporte.setDescripcion(reporteDTO.getDescripcion());
                existingReporte.setDatos(reporteDTO.getDatos());
                Reporte updatedReporte = Repository.save(existingReporte);
                return toDto(updatedReporte);
            })
            .orElseThrow(() -> new RuntimeException("Reporte no encontrado con id: " + id));
    }

    public void eliminarReporte(Integer id) {
        if (Repository.existsById(id)) {
            Repository.deleteById(id);
        } else {
            throw new RuntimeException("Reporte no encontrado con id: " + id);
        }
    }

    //metodos auxiliares 
    private ReporteDTO toDto(Reporte reporte) {
        ReporteDTO dto = new ReporteDTO();
        dto.setId_reporte(reporte.getId_reporte());
        dto.setTipo_reporte(reporte.getTipo_reporte());
        dto.setFecha_generacion(reporte.getFecha_generacion().toString());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setDatos(reporte.getDatos());
        return dto;
    }

    private Reporte toEntity(ReporteDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setId_reporte(dto.getId_reporte());
        reporte.setTipo_reporte(dto.getTipo_reporte());
        reporte.setFecha_generacion(java.sql.Date.valueOf(dto.getFecha_generacion()));
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setDatos(dto.getDatos());
        return reporte;
    }
    

    
}
