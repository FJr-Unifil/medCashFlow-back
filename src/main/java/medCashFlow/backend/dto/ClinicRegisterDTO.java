package medCashFlow.backend.dto;

// Deveria fazer assim ou uma junção de dois objetos (clinicDTO e employeeDTO)?
public record ClinicRegisterDTO(ClinicRequestDTO clinicData, EmployeeRequestDTO employeeData) {
}
