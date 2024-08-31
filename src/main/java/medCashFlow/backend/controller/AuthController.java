package medCashFlow.backend.controller;

import lombok.RequiredArgsConstructor;
import medCashFlow.backend.dto.ClinicRegisterDTO;
import medCashFlow.backend.dto.EmployeeLoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<String> registerClinic(@RequestBody ClinicRegisterDTO data) {

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody EmployeeLoginDTO data) {

    }
}
