package medCashFlow.backend.dto;

import medCashFlow.backend.model.Role;

public record EmployeeResponseDTO(String name, String username, String cpf, String email, String password, Role role) {
}
