package integrationTest.api.config;

import com.automobilefleet.AutomobileFleetApplication;
import com.github.javafaker.Faker;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static integrationTest.api.utils.RequestSpecUtils.onPort;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(classes = AutomobileFleetApplication.class, webEnvironment = RANDOM_PORT)
public abstract class AbstractWebIntegrationTest {

    @LocalServerPort
    private int port;

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14"));

    protected static final Faker faker = Faker.instance();

    static {
        postgres.start();
    }

    @BeforeEach
    void setUp() {
        onPort(port);
    }

    @Test
    void shouldDatabaseBeRunning() {
        then(postgres.isCreated()).isTrue();
        then(postgres.isRunning()).isTrue();
    }

}
