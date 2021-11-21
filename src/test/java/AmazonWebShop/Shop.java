package AmazonWebShop;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Shop {
	
	 WebDriver driver;
	 
		
		String prodname="Aakriti® Transparent Glass Flower Pot Long Stem Flower Vase 60cm for Table Decor Centerpiece Living Room";
		double doubleVal=749.00;
		String strval="749.00";
		
		By productlist =By.xpath("//a[normalize-space(@class)='navSecondaryLink']");
		
		By hitsearch=By.xpath("//input[@id='nav-search-submit-button']");

		//By prod=By.id ("//span[@class='celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1']"+ "//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-4']");
		 By prodtitle= By.xpath("//a[contains(@href,'/Aakriti-Transparent-Flower-Centerpiece-Living')and @class = 'a-link-normal a-text-normal']");
		
		By prodName=By.xpath("//span[@id='productTitle']");

		By addtocart=By.id("add-to-cart-button");
		
		By cartbtn=By.xpath("//a[ @id='hlb-view-cart-announce']");
		
		By quantityboxtext= By.xpath("//input[@name='quantityBox']");
		
		//in case of find elements
		//div[@class='a-popover-inner a-lgtbox-vertical-scroll']/ul/li
		
		By qty10= By.id("quantity_10");
		
		By currency=By.xpath("//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']");
			
				
		By updatebtn= By.xpath("//a[@data-action='update']");
		
		By searchtoolbar= By.id("twotabsearchtextbox");
		
		By prodnameincart=By.xpath("//span[@class='a-truncate a-size-medium']");
		
		By qtyzerostate=By.xpath("//span[@class='a-button-text a-declarative']");
		
		
		public Shop() {
			PageFactory.initElements(driver, this);
			}

	@BeforeClass
	public void setUp() throws InterruptedException  {
		driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	

	@Test(priority=1)
	public void Searchprod() {
	driver.findElement(By.id("twotabsearchtextbox")).click();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(prodname);
		driver.findElement(hitsearch).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		driver.findElement(prodtitle).click();
		 String oldTab = driver.getWindowHandle();
		 ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		    newTab.remove(oldTab);
		    // change focus to new tab
		    driver.switchTo().window(newTab.get(0));
    
	}

	@Test(priority=2)
	public void AddProductToCart() {
		driver.findElement(addtocart).click();
		driver.findElement(cartbtn).click();
		Assert.assertEquals(driver.findElement(prodnameincart).getText(),prodname);
		
	}
	
	@Test(priority=3)
	public void qutyincrease() {
		driver.findElement(qtyzerostate).click();
		driver.findElement(qty10).click();
		driver.findElement(quantityboxtext).clear();
		driver.findElement(quantityboxtext).clear();
		driver.findElement(quantityboxtext).sendKeys("5");
		driver.findElement(updatebtn).click();
		Assert.assertEquals(Double.parseDouble(driver.findElement(currency).getText().trim()),doubleVal*5);
}
	
	@Test(priority=4)
	public void qutydecrease() {
		driver.findElement(quantityboxtext).clear();
		driver.findElement(quantityboxtext).sendKeys("2");
		driver.findElement(updatebtn).click();
		Assert.assertEquals(Integer.parseInt(driver.findElement(currency).getText()),749.00*2);
	}
	
	

@AfterClass
	public void tearDown() {
		driver.quit();
	}


}
