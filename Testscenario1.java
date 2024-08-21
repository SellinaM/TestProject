import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testscenario1 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\sellina.mthembu\\Projects\\SeleniumAutomation\\chromedriver-win64\\chromedriver.exe");

        // Initialize a ChromeDriver instance
        WebDriver driver = new ChromeDriver();

        try {
            // Open a website
            driver.get("https://deploy-preview-1--safercitywebapp.netlify.app/crime");

            // Add an explicit wait for elements to be present
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Find the form fields by their IDs
            WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
            WebElement surnameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("surname")));
            WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("phone")));
            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));

            // Find the submit button by its tag name or other attributes
            WebElement submitButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Next')]")));

            nameField.sendKeys("sellina");
            Thread.sleep(1000);
            surnameField.sendKeys("gina");
            Thread.sleep(1000);
            phoneField.sendKeys("793795878");
            Thread.sleep(1000);
            emailField.sendKeys("test@gmail.com");
            Thread.sleep(1000);

            submitButton.click();
            // Second page

            // Wait until the loader disappears
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader-container")));

            // Find the map element (You need to find the correct element representing the
            // map)
            WebElement mapElement = driver.findElement(By.className("map-container"));

            // Create an Actions object to simulate user interactions
            Actions actions = new Actions(driver);

            // Click on the map at a specific location (x, y coordinates within the map)
            // Adjust the coordinates as needed for your use case
            actions.moveToElement(mapElement, 100, 100).click().perform();
            Thread.sleep(9000);

            // Locate the dropdown button
            WebElement dropdownButton = driver.findElement(By.id(":r4:-form-item"));
            // Click to open the dropdown
            dropdownButton.click();

            // Wait for the dropdown options to load
            Thread.sleep(1000);

            // Find and click the "Common robbery" option
            WebElement selectOption = driver.findElement(By.xpath("//option[@value='Common robbery']"));
            selectOption.click();

            // Use JavaScript to replace the text of the span
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelector('span.text-primary').innerText = 'Common robbery';");

            // Optional: Pause to see the action
            Thread.sleep(1000);

            dropdownButton.click();

            Thread.sleep(1000);

            // Locate the textarea element by its id
            WebElement descriptionBox = driver.findElement(By.id("crime-description"));

            // Enter text into the textarea
            descriptionBox.sendKeys("This is a description of the common robbery.");

            Thread.sleep(1000);

            // Locate the input element by its id
            WebElement locationInput = driver.findElement(By.id("crimeLocation"));

            // Enter the location into the input field
            locationInput.sendKeys("1645 Ndlovu street, Ivory park");

            Thread.sleep(1000);

            // Click the button to open the date picker
            WebElement dateButton = driver.findElement(By.id(":r8:-form-item"));
            dateButton.click();

            WebElement datePicker = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//div[contains(@class, 'date-picker-class')]")));

            // Check if the date picker is displayed
            if (datePicker.isDisplayed()) {
                // Select the desired date
                WebElement desiredDate = driver.findElement(By.xpath("//div[contains(text(),'August 20th, 2024')]"));
                if (desiredDate.isDisplayed()) {
                    desiredDate.click();
                } else {
                    System.out.println("Desired date not found");
                }
            } else {
                System.out.println("Date picker not found");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            // Close the browser
            driver.quit();
        }

    }
}
