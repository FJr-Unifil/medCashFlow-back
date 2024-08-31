package medCashFlow.backend.service;

import lombok.RequiredArgsConstructor;
import medCashFlow.backend.dto.ClinicResponseDTO;
import medCashFlow.backend.dto.EmployeeRequestDTO;
import medCashFlow.backend.dto.EmployeeResponseDTO;
import medCashFlow.backend.model.Clinic;
import medCashFlow.backend.model.Employee;
import medCashFlow.backend.repository.ClinicRepository;
import medCashFlow.backend.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    private final ClinicRepository clinicRepository;

    public EmployeeResponseDTO returnEmployeeById(UUID clinicId, Long id) {
        var employee = repository.findByClinic_IdAndIdAndActiveIsTrue(clinicId, id).orElse(null);

        if (employee == null) return null;

        return new EmployeeResponseDTO(employee.getName(), employee.getUsername(), employee.getCpf(), employee.getEmail(), employee.getPassword(), employee.getRole());
    }

    public Employee returnEmployeeByEmail(String email) {
        var employee = repository.findByEmailAndActiveIsTrue(email).orElse(null);

        return employee;
    }

    public Page<Employee> returnAllEmployees(UUID clinicId, Pageable pageable) {
        return repository.findAllByClinic_IdAndActiveIsTrue(clinicId, pageable);
    }

    public Employee createAnEmployee(UUID clinicId, EmployeeRequestDTO data) {
        Employee newEmployee = new Employee(data);
        var clinic = clinicRepository.findById(clinicId).orElse(null);

        if (clinic == null) return null;

        newEmployee.setClinic(clinic);
        return repository.save(newEmployee);
    }

    public Employee deleteAnEmployeeById(UUID clinicId, Long id) {
        var employee = repository.findByClinic_IdAndIdAndActiveIsTrue(clinicId, id).orElse(null);

        if (employee == null) return null;

        employee.setActive(false);
        return repository.save(employee);
    }

    public boolean authenticate(String email, String password) {
        var employee = repository.findByEmailAndActiveIsTrue(email).orElse(null);

        if (employee == null) {
            return false;
        }

        return employee.getPassword().equals(password);
    }
}
