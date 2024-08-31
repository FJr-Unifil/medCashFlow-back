package medCashFlow.backend.service;

import lombok.RequiredArgsConstructor;
import medCashFlow.backend.dto.EmployeeRequestDTO;
import medCashFlow.backend.dto.EmployeeResponseDTO;
import medCashFlow.backend.model.Employee;
import medCashFlow.backend.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeResponseDTO returnEmployeeById(UUID clinicId, Long id) {
        var employee = repository.findByIdAndClinicIdAndActiveIsTrue(clinicId, id).orElse(null);

        if (employee == null) return null;

        return new EmployeeResponseDTO(employee.getName(), employee.getUsername(), employee.getCpf(), employee.getEmail(), employee.getPassword(), employee.getRole());
    }

    public Page<Employee> returnAllEmployees(UUID clinicId, Pageable pageable) {
        return repository.findAllByClinicIdAndActiveIsTrue(clinicId, pageable);
    }

    public Employee createAnEmployee(UUID clinicId, EmployeeRequestDTO data) {
        Employee newEmployee = new Employee(data);
        newEmployee.setClinicId(clinicId);
        return repository.save(newEmployee);
    }

    public Employee deleteAnEmployeeById(UUID clinicId, Long id) {
        var employee = repository.findByIdAndClinicIdAndActiveIsTrue(clinicId, id).orElse(null);

        if (employee == null) return null;

        employee.setActive(false);
        return repository.save(employee);
    }
}
