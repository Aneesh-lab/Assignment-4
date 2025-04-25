import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.Arrays;

public class ParallelTestRunner {

    public static class Test1 {
        @Test
        public void testOne() {
            System.out.println("Test1 is running on thread: " + Thread.currentThread().getId());
        }
    }

    public static class Test2 {
        @Test
        public void testTwo() {
            System.out.println("Test2 is running on thread: " + Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) {
        XmlSuite suite = new XmlSuite();
        suite.setName("MyTestSuite");
        suite.setParallel(XmlSuite.ParallelMode.CLASSES);
        suite.setThreadCount(2);

        XmlTest test = new XmlTest(suite);
        test.setName("MyParallelTest");
        test.setXmlClasses(Arrays.asList(
                new XmlClass(Test1.class),
                new XmlClass(Test2.class)
        ));

        TestNG testng = new TestNG();
        testng.setXmlSuites(Arrays.asList(suite));
        testng.run();
    }
}
