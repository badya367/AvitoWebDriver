package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Avito {
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\BrowserDriver\\chromedriver.exe");

        WebDriver chrdriver = new ChromeDriver();
        chrdriver.manage().window().maximize();
        chrdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        chrdriver.get("https://www.avito.ru");

        Select categorySelect = new Select(chrdriver.findElement(By.xpath("//select[@id='category']")));
        categorySelect.selectByVisibleText("Оргтехника и расходники");


        chrdriver.findElement(By.xpath("//input[@id='search']")).sendKeys("Принтер");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        chrdriver.findElement(By.xpath("//div[@class='main-select-2pf7p main-location-3j9by']")).click();
        chrdriver.findElement(By.xpath("//input[@class='suggest-input-3p8yi']"))
                .sendKeys("Владивосток");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chrdriver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement checkBox = chrdriver.findElement(By.xpath("//div/label[contains(@data-marker, 'delivery-filter')]"));
        if (!checkBox.getAttribute("class").contains("checkbox-checked")) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkBox.click();
        }

        chrdriver.findElement(By.xpath("//button[@class='button-button-2Fo5k button-size-s-3-rn6 button-primary-1RhOG width-width-12-2VZLz']")).click();
        chrdriver.findElement(By.xpath("//option[text()='Дороже']")).click();


        List<WebElement> name = chrdriver.findElements(By.xpath("//div[@class='snippet-title-row']"));
        List<WebElement> price = chrdriver.findElements(By.xpath("//div[@class='snippet-price-row']"));
        for (int i = 0; i <= 2; i++) {
            System.out.println("Принтер: "+ name.get(i).findElement(By.xpath(".//h3/a[@class='snippet-link']")).getText()
                    +" \n" + "Цена: "+ price.get(i).findElement(By.xpath(".//span[@class='snippet-price ']")).getText());
        }
        chrdriver.quit();
    }
}