package ru.gb.lessons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
/**    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");     говорим библиотеке webdriver где хранится chromdriver, который запустит браузер
*      WebDriver webDriver = new ChromeDriver();                                                 создаем класс с которым будем работать
*/
        WebDriver webDriver = WebDriverManager.chromedriver().create();                         //safaridriver().create(); упрощаем создание веб драйва добавляя в мавин депенденси <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->//<dependency>//    <groupId>io.github.bonigarcia</groupId>//    <artifactId>webdrivermanager</artifactId>//    <version>5.1.0</version>//</dependency>

        webDriver.get("https://www.shatura.com/");                                              //в сценарии пишем страницу куда нужно перейти
        webDriver.manage().window().setSize(new Dimension(1500, 1100));             //настроить размеры окна браузера
        webDriver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);                    //неявное ожидание - по дефолту каждого действия, ждать элемент
        //webDriver.findElement(By.xpath(""));                                                  //метод для поиска элемента (findElements  - элементов)
        //webDriver.findElement(By.id(""));                                                     //метод для поиска элемента по id
        webDriver.findElement(By.xpath                                                          //clear - почистить;.click-кликнуть по элементу;.sendKeys-вписать текст в элемент;.getText -получить текст;.getLocation- получить локацию; .findElement - получить другой элемент из элемента; .getAttribute - получить атрибут;
                ("//div[@class='signin-link']//span[@class='signin-link__title']"))
                .click();
        WebElement modal__login = webDriver.findElement(By.xpath                               //выносим авторизацию в отдельную переменную, что бы проще было искать элементы
                ("//div[@class='modal__login']"));                                            //WebElement modal__login = webDriver.findElement(By.xpath).var

        //By authModal__Login_Locator = By.xpath("//div[@class='modal__login']");               //создали патч что бы вставить в следующую строку
        //new WebDriverWait(webDriver,5).until(                                                 //остановка в случае ошибки, для возможности открытия (до нажатия клика). тайм аут 5 сек в течении которых идут попытки дождаться действия через каждые 5 милисек. дальше указываем элемент который нужно обнаружить. ExpectedConditions. elementToBeClickable -ждять пока элемент станет кликабельным, yextToBe - пока не появится текст,
        //      ExpectedConditions.presenceOfElementLocated(authModal__Login_Locator));         //эта конструкция называется - явное ожидание
        //new WebDriverWait(webDriver,5,500).until(                                             //ожидание , когда элемент станет видимым (именно элемент, а не как в предидущей строке - локатор)
        //      ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//div[@class='modal__login']"))));
        modal__login.findElement(By.name("email")).sendKeys("laserjet007@rambler.ru");//ищем внутри созданной переменной, найти элемент по имени. sendKeys -ввод текста
        modal__login.findElement(By.name("password")).sendKeys("999999999");       //то же самое с вводом пароля
        modal__login.findElement(By.className("btn__txt")).click();
        webDriver.findElement(By.xpath("//div[@class='signin-link']//span[@class='signin-link__title']")).click();
        //new WebDriverWait(webDriver,5).until(                                                 //подождать когда исчезнет элемент  в случае если он висит
        //        ExpectedConditions.invisibilityOf(modal__login));
        webDriver.findElement(By.xpath("//div[@class='dropdown-menu show']//a[text()='Выйти']")).click();

        //Thread.sleep(5000);
        //TODO:переделать в Selenide, костыль не нужен
        //try {
        //   modal__login.findElement(By.cssSelector("div.header_user")).click();
        //}catch (StaleElementReferenceException e){
        //   modal__login.findElement(By.cssSelector("div.header_user")).click();
        //}

        //modal__login.findElement(By.cssSelector("div.header_user")).click();                  //поиск по css селектору
               // Thread.sleep(36000);                                                          //остановка на 10 секунд проверить откроется ли страница
        webDriver.quit();                                                                       //закрываем webdriver

    }
}