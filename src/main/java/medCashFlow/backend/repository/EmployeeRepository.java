package medCashFlow.backend.repository;

import medCashFlow.backend.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByClinic_IdAndIdAndActiveIsTrue(UUID clinicId, Long id);

    Optional<Employee>  findByEmailAndActiveIsTrue(String email);

    Page<Employee> findAllByClinic_IdAndActiveIsTrue(UUID clinicId, Pageable pageable);
}
