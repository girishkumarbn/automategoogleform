package demo;

import org.openqa.selenium.By;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.time.Duration;
import org.testng.annotations.Test;
import java.util.logging.Level;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import test.java.demo.wrappers.Wrappers;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
    // public TestCases(ChromeDriver driver){
    //     this.driver = driver;
    // }

     @Test
     public void testCase01() throws InterruptedException {
        String url ="https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform";
        Wrappers.wrapperNavigate(driver, url);
         /* Name field */
        WebElement nameField = driver.findElement(By.xpath("(//div//input[contains(@class,'whsOnd') and @jsname='YPqjbf'])[1]"));
        String nameText ="Crio Learner";
        Wrappers.wrapperEnterText(nameField, nameText);
         

        /* Why Autaomation */
        WebElement practiceAutomationField = driver.findElement(By.xpath("//textarea[contains(@class,'tL9Q4c')]"));
        long epochTime = System.currentTimeMillis();  // getting system time in epoch format
        String finalText = "I want to be the best QA Engineer!" + epochTime; // adding it to the String variable
        Wrappers.wrapperEnterText(practiceAutomationField, finalText);

        /* Selecting Automation experience */
        List<WebElement> yearOfExperience = driver.findElements(By.xpath("//div//div[contains(@class,'wFGF8')]"));
        for (WebElement expRange : yearOfExperience) {
            if (expRange.getText().contains("3 - 5")) {
                System.out.println(expRange.getText());
                Wrappers.wrapperAdvancedClick(expRange);
                //expRange.click();
            }
        }
             
        /* scroll down until element is visisble*/
        WebElement dateFieldHeader = driver.findElement(By.xpath("//div//span[@class='M7eMe' and contains(text(),'Which of the following have you learned in Crio.Do for Automation Testing?')]"));
        Wrappers.wrapperAdvancedScroll(driver, dateFieldHeader);
        
        /* Selecting what is learnt at Crio */
        List<WebElement> skills = driver.findElements(By.xpath("//div[contains(@class,'wFGF8')]//div[@role='checkbox']"));
        for (int i = 0; i < skills.size(); i++) {
            if (i != 2) {
                System.out.println(skills.get(i).getText());
                skills.get(i).click();
            }
        }

        /* How to be Addressed*/
        WebElement howTobeAddressed = driver.findElement(By.xpath("(//div[contains(@class,'MocG8c') and @jsname='wQNmvb'])[1]"));
        howTobeAddressed.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'LMgvRb')]")));
        //Thread.sleep(2000);
        List<WebElement> addressedOption = driver.findElements(By.xpath("//div[contains(@class,'LMgvRb')]"));
        for (WebElement option : addressedOption) {
            if (option.getText().contains("Mr")) {
                Wrappers.wrapperAdvancedClick(option);
            }
        }

        /* Selecting Date */
        String dateFormatted = Wrappers.getFormatedDate();
        WebElement date = driver.findElement(By.xpath("(//div//input[contains(@class,'whsOnd') and @jsname='YPqjbf'])[2]"));
        Wrappers.wrapperEnterText(date, dateFormatted);
        
        /* Selecting Time format */

        String hourValue = Wrappers.getHour();
        WebElement hourPart = driver.findElement(By.xpath("(//input[@class='whsOnd zHQkBf'])[3]"));
        Wrappers.wrapperEnterText(hourPart, hourValue);

        String minuteValue = Wrappers.getHour();
        WebElement minutePart = driver.findElement(By.xpath("(//input[@class='whsOnd zHQkBf'])[4]"));
        Wrappers.wrapperEnterText(minutePart, minuteValue);

        /* Submitting the form */ 
        WebElement submitBtn = driver.findElement(By.xpath("//span[text()='Submit']"));
        Wrappers.wrapperAdvancedClick(submitBtn); 
        Thread.sleep(2000);
        WebElement message = driver.findElement(By.xpath("//div[@class='vHW8K']"));
        System.out.println(message.getText());

     }

    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}