package medCashFlow.backend.service;

import lombok.RequiredArgsConstructor;
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

    public ClinicResponseDTO returnClinicById(UUID id) {
        var clinic = repository.findByIdAndActiveIsTrue(id).orElse(null);

        if (clinic == null) return null;

        return new ClinicResponseDTO(clinic.getName(), clinic.getCnpj(), clinic.getPhone());
    }

    public Page<Clinic> returnAllClinics(Pageable pageable) {
        return repository.findAllByActiveIsTrue(pageable);
    }
}
