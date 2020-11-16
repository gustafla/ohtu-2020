package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        Random r = new Random();
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");

        System.out.println(driver.getPageSource());

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        element.submit();

        System.out.println(driver.getPageSource());

        // Epäonnistunut kirjautuminen
        driver.get("http://localhost:4567");

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkeo");
        element = driver.findElement(By.name("login"));

        element.submit();

        System.out.println(driver.getPageSource());

        // Käyttäjätunnuksen luominen
        driver.get("http://localhost:4567");

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.linkText("register new user"));
        element.click();

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka" + r.nextInt(10000000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("asdf1234");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("asdf1234");
        element = driver.findElement(By.name("signup"));

        element.submit();

        System.out.println(driver.getPageSource());

        // ulkoskirjautuminen

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        System.out.println(driver.getPageSource());

        element = driver.findElement(By.linkText("logout"));
        element.click();

        System.out.println(driver.getPageSource());


        driver.quit();
    }

    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
