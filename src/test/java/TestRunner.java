import org.testng.TestNG;

import com.example.BaseTest;

import java.util.Collections;

public class TestRunner extends BaseTest {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestSuites(Collections.singletonList("src/test/testng.xml"));
        testng.run();
    }
}

//RUN it with either running TestRunner.java or
//using launch.json(mention mainClass and project name by Open Run and Debug (Ctrl + Shift + D).

// Select "Run TestNG Tests" and click Start Debugging.

// 
//  directly by mvn test