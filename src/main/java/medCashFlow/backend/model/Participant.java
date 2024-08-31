package medCashFlow.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import medCashFlow.backend.dto.ParticipantRequestDTO;

import java.util.UUID;

    @Entity
    @Table(name = "participants")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class Participant{

        @Id
        @GeneratedValue
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false, unique = true)
        private String identity;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String phone;

        @Column(nullable = false)
        private boolean active = true;

        @ManyToOne
        @JoinColumn(name = "clinic_id")
        private Clinic clinic;

        public void setClinicId(UUID clinicId) {
            this.clinic.setId(clinicId);
        }

        public Participant(ParticipantRequestDTO data) {
            this.name = data.name();
            this.identity = data.identity();
            this.email = data.email();
            this.phone = data.phone();
        }
}
