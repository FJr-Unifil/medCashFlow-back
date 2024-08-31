package medCashFlow.backend.controller;

import lombok.RequiredArgsConstructor;
import medCashFlow.backend.dto.ParticipantRequestDTO;
import medCashFlow.backend.dto.ParticipantResponseDTO;
import medCashFlow.backend.model.Participant;
import medCashFlow.backend.service.ParticipantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clinics/{clinicId}/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService service;

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantResponseDTO> getAParticipantById(@PathVariable UUID clinicId, @PathVariable Long id) {
        ParticipantResponseDTO participant = service.returnParticipantById(clinicId, id);

        if (participant == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(participant);
    }

    @GetMapping
    public ResponseEntity<Page<Participant>> getAPageOfParticipants(@PathVariable UUID clinicId, Pageable pageable) {
        Page<Participant> participantPage = service.returnAllParticipants(clinicId, pageable);

        return ResponseEntity.ok(participantPage);
    }

    @PostMapping
    public ResponseEntity<Participant> createAParticipant(@PathVariable UUID clinicId, @RequestBody ParticipantRequestDTO data) {
        Participant newParticipant = service.createAParticipant(clinicId, data);

        return ResponseEntity.ok(newParticipant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAParticipantById(@PathVariable UUID clinicId, @PathVariable Long id) {
        Participant participantToBeDeleted = service.deleteAParticipantById(clinicId, id);

        if (participantToBeDeleted == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}