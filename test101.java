import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.Keys;

public class test101 {

    public static void main(String[] args) throws InterruptedException {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.Sdriver",
                "C:\\Users\\sellina.mthembu\\Projects\\SeleniumAutomation\\chromedriver-win64");

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

            // submit the form without filling all the required fields
            nameField.sendKeys("");
            Thread.sleep(1000);
            surnameField.sendKeys("");
            Thread.sleep(1000);
            phoneField.sendKeys("");
            Thread.sleep(1000);
            emailField.sendKeys("");
            Thread.sleep(1000);

            submitButton.click();
            WebElement nameError = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id(":r0:-form-item-message")));

            WebElement surnameError = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id(":r1:-form-item-message")));
            WebElement mobileError = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id(":r2:-form-item-message")));
            WebElement EmailError = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id(":r3:-form-item-message")));

            if (nameError.isDisplayed() && surnameError.isDisplayed() && mobileError.isDisplayed()
                    && EmailError.isDisplayed()) {
                System.out.println("nameError message displayed as expected.step Passed");
            } else {
                System.out.println("nameError message not displayed.Step failed");
            }

            /// Enter more than 9 digits or fewer that 9 digits into the mobile number field

            phoneField.clear();
            phoneField.sendKeys("123");
            submitButton.click();
            Thread.sleep(1000);

            String errorMessage = mobileError.getText();

            String expectedErrorMessage = "Mobile number must be exactly 9 digits.";

            if (errorMessage.equals(expectedErrorMessage)) {
                phoneField.clear();
                System.out.println("Test Passed: Correct error message displayed for fewer than 9 digits.");
            } else {
                System.out.println("Test Failed: Expected error message '" + expectedErrorMessage + "', but got '"
                        + errorMessage + "'.");
            }

            emailField.clear();
            emailField.sendKeys("selinaemail");
            Thread.sleep(1000);

            String errorMessageEmail = EmailError.getText();
            String expectedErrorMessageEmail = "Invalid email address";

            if (errorMessageEmail.equals(expectedErrorMessageEmail)) {
                System.out.println("Test Passed error message displayed");
                Thread.sleep(1000);

            } else {
                System.out.println("Test Failed:");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            // Close the browser
            // driver.quit();
        }
    }
}