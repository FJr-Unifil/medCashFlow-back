package medCashFlow.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import medCashFlow.backend.dto.EmployeeRequestDTO;

import java.util.UUID;

@Entity
@Table(name = "employees")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    public Employee(EmployeeRequestDTO data) {
        this.name = data.name();
        this.username = data.username();
        this.cpf = data.cpf();
        this.email = data.email();
        this.password = data.password();
        this.role = data.role();
    }

}
