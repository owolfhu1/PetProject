package com.catalyte.OrionsPets;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class AppRunnerTest {
    private AppRunner classToTest;


    @Test//(expected = Exception.class)
    public void appRunnerSadPath() {
        classToTest.main(new String[0]);
    }
}
