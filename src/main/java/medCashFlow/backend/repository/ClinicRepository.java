package medCashFlow.backend.repository;

import medCashFlow.backend.model.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClinicRepository extends JpaRepository<Clinic, UUID> {

    Optional<Clinic> findByIdAndActiveIsTrue(UUID id);

    Page<Clinic> findAllByActiveIsTrue(Pageable pageable);
}
