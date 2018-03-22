package DemoProject.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.swing.plaf.FileChooserUI;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class OrbitZ {

	static WebDriver driver;
	static Properties prop;
	
	Logger log=Logger.getLogger(Long.class.getName());
	@Parameters("browser")
	@BeforeClass
	public void Initializer(String browser) {
		
	
	 /*prop= new Properties();
    FileInputStream fis= new FileInputStream("C:\\Users\\palash.saxena\\workspace\\Test\\src\\main\\java\\DemoProject\\Test\\configure.properties");

    prop.load(fis);
    
   String Browsername= prop.getProperty("browser");*/
   log.info("Browser Invocation");
   if(browser.equalsIgnoreCase("Chrome")){
	   
	   System.setProperty("webdriver.chrome.driver","C:\\Users\\palash.saxena\\Desktop\\Palash\\chromedriver.exe");
	   
	   driver = new ChromeDriver();
	   System.out.println("CHROME");
	   
   }
   else if (browser.equalsIgnoreCase("Firefox")) {
	   System.setProperty("webdriver.gecko.driver","C:\\Users\\palash.saxena\\Desktop\\Palash\\geckodriver.exe");
	   
	   driver=new FirefoxDriver();
	   System.out.println("Firefox");
}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


}
		
	@Test(timeOut=10000)
	public void Intialize() throws InterruptedException, IOException{
		
		
		/*driver=Initializer();
		
		String URL=prop.getProperty("url");*/
		driver.manage().window().maximize();
		String URL="https://www.orbitz.com/";
		driver.manage().timeouts().pageLoadTimeout(8000,TimeUnit.MILLISECONDS);
		
		try{
		driver.get(URL);
		}
		catch(TimeoutException e){
			System.out.println(e);
			
		}
		
		Thread.sleep(5000);
		
		
		Actions action= new Actions(driver);
		WebElement flight= driver.findElement(By.xpath("//*[@id='tab-flight-tab']/span[1]"));
		action.click(flight).perform();
		
		WebElement oneway=driver.findElement(By.name("type-one-way"));
		action.click(oneway).perform();
		
		WebElement source=driver.findElement(By.id("flight-origin"));
		source.click();
		source.sendKeys("Pune");
		source.sendKeys(Keys.ENTER);
		
		WebElement destination=driver.findElement(By.id("flight-destination"));
		destination.click();
		destination.sendKeys("Indore");
		destination.sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='flight-departing-wrapper']/label/span[2]")).click();
		
		//ScreenShot(driver);
		
		Thread.sleep(5000);
		List<WebElement> element=driver.findElements(By.className("datepicker-day-number"));
		int count=element.size();
		System.out.println(count);
		
		//ScreenShot(driver);
		
		for(int i=0;i<count;i++){
			String value=driver.findElements(By.className("datepicker-cal-date")).get(i).getText();
			System.out.println(value);
			
			if(value.equalsIgnoreCase("20")){
				driver.findElements(By.className("datepicker-day-number")).get(i).click();
				break;
			}
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(@type,'submit')]")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.id("search-button")).click();
		
		
		System.out.println("RESULT FOUND");
			
			
		}
			public static void ScreenShot(WebDriver adriver){
		
			File src=(File) ((TakesScreenshot)adriver).getScreenshotAs(OutputType.FILE);
			try {
				Files.copy(src, new File(prop.getProperty("Screenshot") +System.currentTimeMillis()+"  ScreenShot.png"));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	}

}
