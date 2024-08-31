package medCashFlow.backend.service;

import lombok.RequiredArgsConstructor;
import medCashFlow.backend.dto.ClinicRequestDTO;
import medCashFlow.backend.dto.ClinicResponseDTO;
import medCashFlow.backend.model.Clinic;
import medCashFlow.backend.repository.ClinicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClinicService {

    private final ClinicRepository repository;

    public Clinic createAClinic(ClinicRequestDTO data) {
        Clinic newClinic = new Clinic(data);

        return repository.save(newClinic);
    }

    public ClinicResponseDTO returnClinicById(UUID id) {
        var clinic = repository.findByIdAndActiveIsTrue(id).orElse(null);

        if (clinic == null) return null;

        return new ClinicResponseDTO(clinic.getName(), clinic.getCnpj(), clinic.getPhone());
    }

    public Page<Clinic> returnAllClinics(Pageable pageable) {
        return repository.findAllByActiveIsTrue(pageable);
    }

    public ClinicResponseDTO deleteAClinicById(UUID id) {
        var clinic = repository.findByIdAndActiveIsTrue(id).orElse(null);

        if (clinic == null) return null;

        clinic.setActive(false);
        repository.save(clinic);

        return new ClinicResponseDTO(clinic.getName(), clinic.getCnpj(), clinic.getPhone());
    }
}
