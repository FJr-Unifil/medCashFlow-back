package medCashFlow.backend.service;

import lombok.RequiredArgsConstructor;
import medCashFlow.backend.dto.ParticipantRequestDTO;
import medCashFlow.backend.dto.ParticipantResponseDTO;
import medCashFlow.backend.model.Participant;
import medCashFlow.backend.repository.ParticipantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository repository;

    public ParticipantResponseDTO returnParticipantById(UUID clinicId, Long id) {
        var participant = repository.findByClinic_IdAndIdAndActiveIsTrue(clinicId, id).orElse(null);

        if (participant == null) return null;

        return new ParticipantResponseDTO(participant.getName(), participant.getIdentity(), participant.getEmail(), participant.getPhone());
    }

    public Page<Participant> returnAllParticipants(UUID clnicId, Pageable pageable) {
        return repository.findAllByClinic_IdAndActiveIsTrue(clnicId,pageable);
    }

    public Participant createAParticipant(UUID clinicId, ParticipantRequestDTO data) {
        Participant newParticipant = new Participant(data);
        newParticipant.setClinicId(clinicId);
        return repository.save(newParticipant);
    }

    public Participant deleteAParticipantById(UUID clinicId, Long id) {
        var participant = repository.findByClinic_IdAndIdAndActiveIsTrue(clinicId, id).orElse(null);

        if (participant == null) return null;

        participant.setActive(false);
        return repository.save(participant);
    }
}
