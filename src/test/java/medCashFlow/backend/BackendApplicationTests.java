package medCashFlow.backend;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BackendApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

	@Test
	void shouldReturnAClinic() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/clinics/5e387c7d-74d4-43f8-8cf7-010e2ed2e0b0", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());

		String name = documentContext.read("$.name");
		assertThat(name).isEqualTo("Clinic A");
	}

	@Test
	void shouldNotReturnAClinicThatDoNotExists() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/clinics/5e387c7d-74d4-43f8-8cf7-010e2ed2e0b1", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

	@Test
	void shouldNotReturnAClinicThatIsNotActive() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/clinics/dfa21d3e-e1dc-475f-832d-f6c9eac172ff", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

	@Test
	void shouldReturnAPageOfClinics() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/clinics?page=0&size=3", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number totalElements = documentContext.read("$.totalElements");
		assertThat(totalElements).isEqualTo(7);

		Number numberOfClinics = documentContext.read("$.numberOfElements");
		assertThat(numberOfClinics).isEqualTo(3);

		Number pageSize = documentContext.read("$.pageable.pageNumber");
		assertThat(pageSize).isEqualTo(0);

		Number pageNumber = documentContext.read("$.pageable.pageSize");
		assertThat(pageNumber).isEqualTo(3);
	}

}
