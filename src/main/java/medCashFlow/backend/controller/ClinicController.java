package medCashFlow.backend.controller;

import lombok.RequiredArgsConstructor;
import medCashFlow.backend.dto.ClinicResponseDTO;
import medCashFlow.backend.model.Clinic;
import medCashFlow.backend.service.ClinicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/clinics")
@RequiredArgsConstructor
public class ClinicController {

    private final ClinicService service;

    @GetMapping("/{id}")
    public ResponseEntity<ClinicResponseDTO> getAClinicById(@PathVariable UUID id) {
        ClinicResponseDTO clinic = service.returnClinicById(id);

        if (clinic == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clinic);
    }

    @GetMapping
    public ResponseEntity<Page<Clinic>> getAPageOfClinics(Pageable pageable) {
        Page<Clinic> clinicPage = service.returnAllClinics(pageable);

        return ResponseEntity.ok(clinicPage);
    }
}
