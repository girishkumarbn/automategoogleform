package test.java.demo.wrappers;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
	
	public static boolean wrapperNavigate(ChromeDriver driver, String url) {
		try{
			if(driver.getCurrentUrl().equals(url)) {
				return true;
			}
		else {
                driver.get(url);
                return driver.getCurrentUrl() ==url;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean wrapperEnterText(WebElement textBox, String inputText) throws InterruptedException {
		try {
			textBox.clear();
			textBox.sendKeys(inputText);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean wrapperAdvancedClick(WebElement button) throws InterruptedException{
		try{
			button.click();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean wrapperAdvancedScroll(ChromeDriver driver, WebElement element) throws InterruptedException {
		try {
			 	JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].scrollIntoView();", element);
				//js.executeScript("window.scrollBy(0,230)");
		        return true;
		}catch(Exception e) {
			return false;
		}
	}

	public static String getFormatedDate () {
		Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date previousDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = dateFormat.format(previousDate);
		return formatedDate;
	}

	public static String getHour(){

		LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);

        int hour = currentTime.getHour() - 12;
        String hourValue = Integer.toString(hour);
        return hourValue;
	}

	public static String getMinute(){
		LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);
        int minute = currentTime.getMinute();
        String minuteValue = Integer.toString(minute);
        String am_pm = formattedTime.substring(formattedTime.length() - 2);

		return minuteValue;

	}
}