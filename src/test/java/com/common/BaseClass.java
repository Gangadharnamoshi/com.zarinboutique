package com.common;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    // ThreadLocal WebDriver for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static Properties prop = new Properties();
    public static Properties loc = new Properties();
    public static FileReader fr;
    public static FileReader fr1;

    // Get driver instance from ThreadLocal
    public WebDriver getdriver() {
        return driver.get();
    }

    // Set up browser and properties
    public void setup(String browserName) throws IOException {
        if (getdriver() == null) {

            System.out.println("Project path: " + System.getProperty("user.dir"));

            fr = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config files/configuration.properties");
            prop.load(fr);

            WebDriver localDriver = null;

            if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                localDriver = new ChromeDriver();

            } else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                localDriver = new EdgeDriver();
            }

            driver.set(localDriver); // set to ThreadLocal

            getdriver().manage().window().maximize();
            getdriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            getdriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            getdriver().get(prop.getProperty("testurl"));
        }
    }

    // Close browser and clean up thread-local
    public void teardown() {
        if (getdriver() != null) {
            getdriver().close();
            getdriver().quit();
            driver.remove(); // very important to avoid memory leaks
            System.out.println("Teardown successful");
        }
    }

    // Highlight an element
    public void highLightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getdriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }
}
