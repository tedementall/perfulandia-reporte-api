package com.perfulandia.perfulandia_reporte_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.perfulandia.perfulandia_reporte_api.services.ReporteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.perfulandia.perfulandia_reporte_api.dto.ReporteDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
    @Autowired
    private ReporteService Service;

    @PostMapping
    public ResponseEntity<ReporteDTO> crearReporte(@RequestBody ReporteDTO reporteDTO) {
        ReporteDTO createdReporte = Service.crearReporte(reporteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReporte);
    }

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> obtenerReportes() {
        List<ReporteDTO> reportes = Service.obtenerReportes();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> obtenerReportePorId(@RequestParam Integer id) {
        return Service.obtenerReportePorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> actualizarReporte(@PathVariable Integer id, @RequestBody ReporteDTO reporteDTO) {
        ReporteDTO actualizado = Service.actualizarReporte(id, reporteDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Integer id) {
        try {
            Service.eliminarReporte(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/hateoas/{id}")
    public ResponseEntity<ReporteDTO> obtenerHATEOAS(@PathVariable Integer id) {
        return Service.obtenerReportePorId(id)
            .map(dto -> {
                dto.add(Link.of("http://localhost:8888/api/proxy/reporte/" + dto.getId()).withSelfRel());
                dto.add(Link.of("http://localhost:8888/api/proxy/reporte/" + dto.getId()).withRel("Modificar HATEOAS").withType("PUT"));
                dto.add(Link.of("http://localhost:8888/api/proxy/reporte/" + dto.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));
                return ResponseEntity.ok(dto);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hateoas")
    public List<ReporteDTO> obtenerTodosHATEOAS() {
        List<ReporteDTO> lista = Service.obtenerReportes();

        for (ReporteDTO dto : lista) {
            
            dto.add(Link.of("http://localhost:8888/api/proxy/reporte").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/reporte/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }
    
    

}
