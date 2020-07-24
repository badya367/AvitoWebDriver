import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AvitoTest {
    public static WebDriver chrdriver;
    public static boolean checkFlag = false;
    public static String city;
    @Before
    public void prepareBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\BrowserDriver\\chromedriver.exe");
        chrdriver = new ChromeDriver();
        chrdriver.manage().window().maximize();
        chrdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Пусть("открыт ресурс авито")
    public static void openSite() {
        chrdriver.get("https://www.avito.ru");
    }

    @ParameterType(".*")
    public Category myCategory(String categ) {return Category.valueOf(categ);
    }
    @И("в выпадающем списке категорий выбрана {myCategory}")
    public void selectCategory(Category orgtech) {
        Select categorySelect = new Select(chrdriver.findElement(By.xpath("//select[@id='category']")));
        categorySelect.selectByValue(orgtech.numb);
    }

    @И("в поле поиска введено значение {string}")
    public void searchPrinter(String printer) {
        chrdriver.findElement(By.xpath("//input[@id='search']")).sendKeys(printer);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Тогда("кликнуть по выпадающему списку региона")
    public void allCity(){
        chrdriver.findElement(By.xpath("//div[@class='main-select-2pf7p main-location-3j9by']")).click();
    }

    @Тогда("в поле регион введено значение {string}")
    public void concrectCity(String myCity){
        chrdriver.findElement(By.xpath("//input[@class='suggest-input-3p8yi']"))
                .sendKeys(myCity);
        city = myCity;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @И("нажата кнопка показать объявления")
    public void buttonShow(){
        chrdriver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Тогда("открылась страница результаты по запросу {string}")
    public void assertPage(String string){

        WebElement check = chrdriver.findElement(By.xpath("//h1[@class='page-title-text-WxwN3 page-title-inline-2v2CW']"));

        if (check.getText().contains(string) && check.getText().contains("Владивостоке")) {

            checkFlag = true;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(checkFlag, "Не открылась нужная страница");
    }

    @И("активирован чекбокс только с фотографией")
    public void checkbox(){
        WebElement checkBox = chrdriver.findElement(By.xpath("//div/label[contains(@data-marker, 'delivery-filter')]"));
        if (!checkBox.getAttribute("class").contains("checkbox-checked")) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkBox.click();
        }
    }

    @ParameterType(".*")
    public Price price(String prices) { return Price.valueOf(prices);
    }
    @И("в выпадающем списке сортировка выбрано значение {price}")
    public void checkTopPrice(Price topPrice){
        chrdriver.findElement(By.xpath("//button[@class='button-button-2Fo5k button-size-s-3-rn6 button-primary-1RhOG width-width-12-2VZLz']")).click();
        WebElement top = chrdriver.findElement(By.xpath(topPrice.prices));
        top.click();
        //chrdriver.findElement(By.xpath("//option[text()=\""+ topPrice.prices + "\"]")).click();
    }

    @И("в консоль выведено значение названия и цены {int} первых товаров")
    public void topPrinters(int quantity){
        List<WebElement> name = chrdriver.findElements(By.xpath("//div[@class='snippet-title-row']"));
        List<WebElement> price = chrdriver.findElements(By.xpath("//div[@class='snippet-price-row']"));
        for (int i = 0; i <= quantity; i++) {
            System.out.println("Принтер: "+ name.get(i).findElement(By.xpath(".//h3/a[@class='snippet-link']")).getText()
                    +" \n" + "Цена: "+ price.get(i).findElement(By.xpath(".//span[@class='snippet-price ']")).getText());
        }
    }

    @After
    public void exit() {
        chrdriver.quit();
    }
}
