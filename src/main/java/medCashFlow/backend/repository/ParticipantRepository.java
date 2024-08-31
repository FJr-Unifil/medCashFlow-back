package medCashFlow.backend.repository;

import medCashFlow.backend.model.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Optional<Participant> findByClinic_IdAndIdAndActiveIsTrue(UUID clinicId, Long id);

    Page<Participant> findAllByClinic_IdAndActiveIsTrue(UUID clinicId, Pageable pageable);
}
