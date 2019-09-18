import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import org.approvaltests.Approvals;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class Examples {

    @Test
    public void exampleOne(){
        String roomResponse = given()
                                  .get("https://automationintesting.online/room/")
                                  .body()
                                  .prettyPrint();

        Approvals.verify(roomResponse);
    }

    @Test
    public void exampleTwo(){
        String roomResponse = given()
                .get("https://automationintesting.online/room/")
                .body()
                .prettyPrint();

        roomResponse = roomResponse.replaceAll("\".*\"", "\"===SCRUBBED===\"");

        Approvals.verify(roomResponse);
    }

    @Test
    public void exampleThree(){
        // Open a Chrome browser.
        WebDriver driver = new ChromeDriver();

        Eyes eyes = new Eyes();
        eyes.setApiKey("gaDp2dDF7TxdarQ6X2100mSQq8ywwUIVtdVI5LJ8rZ1PA110");

        try{
            eyes.open(driver, "Applitools Demo", "TestBash Test Example",
                    new RectangleSize(800, 600));

            driver.get("https://applitools.com/helloworld?diff1");

            eyes.checkWindow("Hello!");

            driver.findElement(By.tagName("button")).click();

            eyes.checkWindow("Click!");

            eyes.close();
        } finally {
            // Close the browser.
            driver.quit();

            // If the test was aborted before eyes.close was called, ends the test as aborted.
            eyes.abortIfNotClosed();
        }
    }

}
