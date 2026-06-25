package heck.jokenponline;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class JokenponlineApplicationTests {

    @Test
    void contextLoads() {
    }


    ApplicationModules modules = ApplicationModules.of(JokenponlineApplication.class);

    @Test
    void verifyArchitecture() {
        modules.verify();
    }

}