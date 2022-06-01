import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        /*Otići na http://www.strela.co.rs/, kliknuti na dugme Prodavnica u headeru,
        izabrati opciju Lukovi - Bows iz leve navigacije, potom kliknuti na luk koji se zove Samick Sage,
        i onda proveriti da ime tog luka na njegovoj stranici zaista sadrži reč Samick.*/

        System.setProperty("webdriver.chrome.driver","C:\\Users\\Acer\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //1. maksimizirati prozor
        driver.manage().window().maximize();

        //2. posjetiti stranicu
        driver.navigate().to("http://www.strela.co.rs/");

        //3. pronaci dugme Prodavnica u headeru i kliknuti na njega
        WebElement prodavnica = driver.findElement(By.xpath("//*[@id=\"ctl00_RadMenu1\"]/ul/li[2]/a/span/strong"));
        prodavnica.click();

        //4. pronaci opciju Lukovi - Bows i kliknuti na nju
        WebElement lukovi = driver.findElement(By.className("rtIn"));
        lukovi.click();

        //5. sacekati dok trazeni element ne bude vidljiv na stranici
        WebDriverWait driverWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ctl00_MainContent_ListView1_ctrl35_Panel1 > figure > a > img")));

        //6. kliknuti na vidljivi element
        WebElement luk = driver.findElement(By.cssSelector("#ctl00_MainContent_ListView1_ctrl35_Panel1 > figure > a > img"));
        luk.click();

        //7. provjera ocekivanog i stvarnog naslova na stranici trazenog elementa
        String actualTitle = driver.getTitle();

        if(actualTitle.contains("Samick")){
            System.out.println("TC valid. Title contains word Samick.");
        }else{
            System.out.println("TC invalid. Title does not contain word Samick.");
        }

        //8. zatvoriti pretrazivac
        driver.close();

    }
}
