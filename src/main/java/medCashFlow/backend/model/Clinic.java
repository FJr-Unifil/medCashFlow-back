package medCashFlow.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import medCashFlow.backend.dto.ClinicRequestDTO;

import java.util.UUID;

@Entity
@Table(name = "clinics")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    public Clinic(ClinicRequestDTO data) {
        this.name = data.clinicName();
        this.cnpj = data.cnpj();
        this.phone = data.phone();
    }
}
