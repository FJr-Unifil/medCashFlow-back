package medCashFlow.backend.controller;

import lombok.RequiredArgsConstructor;
import medCashFlow.backend.dto.ClinicRegisterDTO;
import medCashFlow.backend.dto.EmployeeLoginDTO;
import medCashFlow.backend.model.Clinic;
import medCashFlow.backend.model.Employee;
import medCashFlow.backend.service.ClinicService;
import medCashFlow.backend.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClinicService clinicService;

    private final EmployeeService employeeService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    public ResponseEntity<String> registerClinic(@RequestBody ClinicRegisterDTO data) {
        Clinic savedClinic = clinicService.createAClinic(data.clinicData());
        Employee savedEmployee = employeeService.createAnEmployee(savedClinic.getId(), data.employeeData());

        return ResponseEntity.ok("Os itens foram criados");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody EmployeeLoginDTO data) {
        boolean isAuthenticated = employeeService.authenticate(data.email(), data.password());

        if (isAuthenticated) {
            return ResponseEntity.ok(employeeService.returnEmployeeByEmail(data.email()));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
