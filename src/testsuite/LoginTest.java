package testsuite;
/**
 * Write down the following test into ‘LoginTest’ class
 * 1. userShouldNavigateToLoginPageSuccessfully *  click on the ‘Sign In’ link
 * * Verify the text ‘Welcome Back!’
 * 2. verifyTheErrorMessage
 * * click on the ‘Sign In’ link
 * * Enter invalid username
 * * Enter invalid password
 * * Click on Login button
 * * Verify the error message ‘Invalid email  or password.’
 */

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    static String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldNavigateToLoginPageSuccessfully(){
        driver.findElement(By.xpath("//a[@href=\"/users/sign_in\"='Sign In']")).click();
        String expectedMessage = "Welcome Back!";
        String actualMessage = driver.findElement(By.xpath("//h2[@class='page__heading']")).getText();
        Assert.assertEquals(expectedMessage, actualMessage);

    }
    @Test
     public void verifyTheErrorMessage(){
        driver.findElement(By.xpath("//a[@href=\"/users/sign_in\"='Sign In']")).click();
        driver.findElement(By.id("user[email]")).sendKeys("Xyz@gmail.com");
        driver.findElement(By.name("user[password]")).sendKeys("qwerty12");
        driver.findElement(By.xpath("//button[@class='button button-primary g-recaptcha']")).click();

        driver.findElement(By.xpath("//button[@id='recaptcha-verify-button']")).click();

        String expectedMessage = "Invalid email or password";
        String actualMessage = driver.findElement(By.xpath("//li[@class='form-error__list-item']")).getText();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}
