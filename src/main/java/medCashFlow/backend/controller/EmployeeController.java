package medCashFlow.backend.controller;

import lombok.RequiredArgsConstructor;
import medCashFlow.backend.dto.EmployeeRequestDTO;
import medCashFlow.backend.dto.EmployeeResponseDTO;
import medCashFlow.backend.model.Employee;
import medCashFlow.backend.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clinics/{clinicId}/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getAnEmployeeById(@PathVariable UUID clinicId, @PathVariable Long id) {
        EmployeeResponseDTO employee = service.returnEmployeeById(clinicId, id);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getAPageOfEmployees(@PathVariable UUID clinicId, Pageable pageable) {
        Page<Employee> employeePage = service.returnAllEmployees(clinicId, pageable);

        return ResponseEntity.ok(employeePage);
    }

    @PostMapping
    public ResponseEntity<Employee> createAnEmployee(@PathVariable UUID clinicId, @RequestBody EmployeeRequestDTO data) {
        Employee newEmployee = service.createAnEmployee(clinicId, data);

        return ResponseEntity.ok(newEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnEmployeeById(@PathVariable UUID clinicId, @PathVariable Long id) {
        Employee employeeToBeDeleted = service.deleteAnEmployeeById(clinicId, id);

        if (employeeToBeDeleted == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
