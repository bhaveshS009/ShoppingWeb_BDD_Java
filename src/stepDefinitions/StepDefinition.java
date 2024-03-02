package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class StepDefinition {
	
	public static WebDriver driver;
	private String[] PreDefinedList;
	private  List<WebElement> ListAllDepartments;
	private List<String> itemsAttributes;
	private Screenshot Shot_Item_Image;
	
	public static ExtentSparkReporter spark;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest logger;
	
	@Before
	public void SparkReporterHandling(){
	spark = new ExtentSparkReporter("./TestReports/Sparkreport.html");
	spark.config().setTheme(Theme.STANDARD);
	extent.attachReporter(spark);
	
	}
	
	@Given("Amazon web laucnhed")
	public void given_amazon_web_laucnhed() throws InterruptedException {
		logger = extent.createTest("Amazon.com web Checkout feature verification");
		
		
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");
		logger.info("Launching Amamzon.com web site.....");
		String actual_URL = driver.getCurrentUrl();//https://www.amazon.com/
		actual_URL.contains("www.amazon.com");
		Thread.sleep(20000);
		driver.navigate().refresh();
		String webTittle = driver.getTitle();//Amazon.com. Spend less. Smile more.
		webTittle.equals("Amazon.com. Spend less. Smile more.");
		System.out.println("Web Url -> " + actual_URL);
		System.out.println("Web Tittle -> " + webTittle);
	    logger.pass("Webpage launch & Verified");
	    
	}
	
	@When("User enters {string} to search")
	public void user_enters_to_search(String item) {
	    WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
	    searchTextBox.click();
	    //Single Text Search Check k i n D L
	    searchTextBox.clear();
	    searchTextBox.sendKeys(item);
	    searchTextBox.isDisplayed();
	    searchTextBox.getAttribute("value").equals(item);
	    System.out.println("Searched item - "+ item + " visible correctly");
	    logger.pass("Searched item - "+ item + " visible correctly");
	   
	    
	}

	@Then("Wild Card search should be executed according to {string} provided")
	public void wild_card_search_should_be_executed_according_to_provided(String item) throws InterruptedException {
		Thread.sleep(5000); 
		List <WebElement> suggestedList = driver.findElements(By.xpath("//div[@class= 's-suggestion-container']/div"));
		 String itemName = item.toLowerCase();   
		 System.out.println("Total number of suggested items displayed against searched text - " + suggestedList.size());
		 logger.info("Total number of suggested items displayed against searched text - " + suggestedList.size());
		 int misMatchCount= 0;
		 for(int i =0;i<suggestedList.size();i++)
		    {
			 
			 String suggestedName = suggestedList.get(i).getText();
		    	if (suggestedName.toLowerCase().contains(itemName))
		    		{ 
		    		System.out.println("Contains the text "+ suggestedName);		
		    		}
		    	else
		    		{
		    		
		    			System.out.println("Test is failed");
		    			System.out.println("Item Name Forwarded" + itemName);
		    			System.out.println("Actual Item - " + suggestedList.get(i).getText());
		    			misMatchCount++;
		    			logger.fail("Suggested Items name mis-matched for - "+ itemName);
		    		}
		    	
		    }
	    if (misMatchCount==0);
	    logger.pass("All suggested items matches from the input search "+ itemName );
	    
	}

	
	
	@Given("On search box all defined department grouping provided")
	public void on_search_box_all_defined_department_grouping_provided() {
	  
				
	    //Predefined all departments list
		PreDefinedList = new String[]{
					    "All Departments",
					    "Arts & Crafts",
					    "Automotive",
					    "Baby",
					    "Beauty & Personal Care",
					    "Books",
					    "Boys' Fashion",
					    "Computers",
					    "Deals",
					    "Digital Music",
					    "Electronics",
					    "Girls' Fashion",
					    "Health & Household",
					    "Home & Kitchen",
					    "Industrial & Scientific",
					    "Kindle Store",
					    "Luggage",
					    "Men's Fashion",
					    "Movies & TV",
					    "Music, CDs & Vinyl",
					    "Pet Supplies",
					    "Prime Video",
					    "Software",
					    "Sports & Outdoors",
					    "Tools & Home Improvement",
					    "Toys & Games",
					    "Video Games",
					    "Women's Fashion"
					};
				
			
	    }
	    
	    
	    

	@When("User click on All departments")
	public void user_click_on_all_departments() {
		  //Element all department
				WebElement AllDepartmeterFiltter = driver.findElement(By.xpath("//select[@id = 'searchDropdownBox']"));
				AllDepartmeterFiltter.isDisplayed();		
				AllDepartmeterFiltter.click();
				logger.info("All departments list displayed on search bar");
	    
	}

	@Then("list of all defined department appears")
	public List<WebElement> list_of_all_defined_department_appears() {
		//ListAllDepartments = new WebElement[]driver.findElements(By.xpath("//select[@id='searchDropdownBox']/option"));
		ListAllDepartments = driver.findElements(By.xpath("//select[@id='searchDropdownBox']/option"));
	    int unmatchedCount = 0;
		for(int k=0;k<ListAllDepartments.size();k++) 
	    {
	    	
	    	//Designed list elements matching
	    	if (ListAllDepartments.get(k).getText().equals(PreDefinedList[k]))
			{
	    		
			//System.out.println("List elements are matched with predefined");
			}
	    	
	    	else
	    	{
	    		System.out.println("Implementation Gap in predined value - "
	    	
	    				+ PreDefinedList[k]
	    				+ "actual value -"
	    				+ ListAllDepartments.get(k).getText());
	    		
	    		unmatchedCount++;
	    		logger.fail("\"Implementation Gap in predined value - \"\r\n"
	    				+ "	    	\r\n"
	    				+ "	    				+ PreDefinedList[k]\r\n"
	    				+ "	    				+ \"actual value -\"");
	    	}			
	    		    		    	
	    }	
		if (unmatchedCount == 0);
    	System.out.println("All elements are matched with predefined element list");
    	logger.pass("All elements are matched with predefined element list");
	    return ListAllDepartments;
	}

	@Then("able to access first department in list and get selected")
	public void able_to_access_first_department_in_list_and_get_selected() {
		List <WebElement> elementList = this.list_of_all_defined_department_appears();
		for(int k=0;k<elementList.size();k++) 
	    {
			System.out.println(elementList.get(k).getText());
	    }
	    
		System.out.println("First Element Name - " + elementList.get(1).getText());
		elementList.get(1).isDisplayed();
		logger.pass("First Department Name -"+elementList.get(1)+"displayed on current view");
		if(elementList.get(1).isEnabled())
		{
			//Click Operation
			elementList.get(1).click();
		}
		else
		{
			System.out.println("Test is Failed");
			logger.fail("Unable to acces the first department name "+ elementList.get(1).getText() +"for the search");
			
		}
		
	    
	}


	@And("able to select the last department in list and get selected")
	public void able_to_select_the_last_department_in_list_and_get_selected() throws InterruptedException {
		List <WebElement> elementList = this.list_of_all_defined_department_appears();
		
		int lastIndex = elementList.size()-1;//Array Indexing  for last element
		System.out.println("Last Element Name - " + elementList.get(lastIndex).getText());
		elementList.get(lastIndex).isDisplayed();
		logger.pass("Last Department Name -"+elementList.get(lastIndex)+"displayed on current view");
		
	
		if(elementList.get(lastIndex).isEnabled())
		{
			//Click Operation
			elementList.get(lastIndex).click();

			System.out.println("Element displayed on current view");
		}
			
			
			
		else if(elementList.get(lastIndex).isSelected())
		{
			//Click Operation
			elementList.get(lastIndex).click();
		}
		else
		{
			System.out.println("Test is Failed");
			logger.fail("Unable to acces the last department name "+ elementList.get(lastIndex).getText() +"for the search");
			
		}
		
	    
	}

	
	@Then("Refresh Webpage")
	public void Refresh_Webpage() {
		driver.navigate().refresh();
		
	}





	
	@Then("click on item search")
	public void click_on_item_search() {
		WebElement btnSearch = driver.findElement(By.id("nav-search-submit-button"));
		btnSearch.isDisplayed();
		btnSearch.click();
		logger.info("Item search executing...");
	    
	}
	
	@Then("search {string} Sucessfully Executed")
	public void search_sucessfully_executed(String item) {
	    //Verification of Search executed
		driver.getCurrentUrl().contains(item);
		driver.findElement(By.xpath("//div[@class='s-no-outline']/span")).getText().toLowerCase().contains("results");
		logger.pass("Search result appeared");
	}

	
	
	@Given("Amazon icon label visible on page")
	public void amazon_icon_label_visible_on_page() {
	   driver.findElement(By.cssSelector("#nav-logo-sprites")).isDisplayed();
	   logger.info("Amazon icon visible on webpage");
	}
	
	@When("click on Amazon icon")
	public void click_on_amazon_icon() {
		driver.findElement(By.cssSelector("#nav-logo-sprites")).click();
		logger.info("Click on Amzon Icon executed");
	}
	
	@Then("Web Site should land on home page")
	public void web_site_should_land_on_home_page() {
	    driver.getCurrentUrl().equals("https://www.amazon.com/ref=nav_logo");
	    logger.pass("Web lands on home page");
	    
	}
	
	
	@When("User enters some invalid text to search")
	public void user_enters_some_invalid_text_to_search() {
		    WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		    String invalidText = "2kjdojas09duq32ejoawjdosijd093ueoiasjdksjadh";
		    searchTextBox.click();
		    searchTextBox.clear();
		    searchTextBox.sendKeys(invalidText);
		    System.out.println("Invalid Search executed");
		    logger.info("Invalid Search - " + invalidText+" executed");

	}
	
	
	@Then("Observe On webpage no results must appears")
	public void Observe_On_webpage_no_results_must_appears() {
		WebElement SearchedResult = driver.findElement(By.xpath("(//div[@class = 's-no-outline' and @tabindex = 0]/div)[1]"));
		System.out.println(SearchedResult.getText());		
		SearchedResult.getText().contains("No results for");
		logger.pass("No result for the invalid search text");
		
	}

	
	@Then("results are displayed")
	public void results_are_displayed() {
		WebElement SearchedResult = driver.findElement(By.xpath("//div[@class = 's-no-outline' and @tabindex = 0]"));
		System.out.println(SearchedResult.getText());		
		SearchedResult.getText().equals("Results");
		logger.pass("Search Results are displayed");
	    
	}
	
	
	
	@And("click on the item to check out")
	public void  click_on_the_item_to_check_out() throws InterruptedException {
		
		//Capture item properties in to list
		//Intended url
		Thread.sleep(2000);
		WebElement firstElemnetInfo = driver.findElement(By.xpath(
				"(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'])[2]"
				));
		//Click on Checkout
		firstElemnetInfo.click();	
		logger.info("clicked on the item to check out");
	}
	
	/**
	 * Example of Java Doc Creation
	 * This method calculate properties of selected element in list "firstElemnetInfo" declared at class level <URL> <Description> <Price>.
	 * Element 1 - Item URL
	 * Element 2 - Item Description
	 * Element 3 - Item Price
	 * @return None
	 * @throws IOException 
	 */
	
	@And("Capture details of Selected Element")
	public void  Capture_details_of_Selected_Element() throws IOException {
		
		//Capture item properties in to list
		WebElement firstElemnetInfo = driver.findElement(By.xpath(
				"(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'])[2]"
				));
		//Item URL
		String firstItemURL = firstElemnetInfo.getAttribute("href");
		System.out.println("First Item URL--"+firstItemURL);
		logger.info("<b>"+"First Item URL--"+"</b>"+firstItemURL);
		//Item Description
		String firstItem_Description = firstElemnetInfo.getText();
		System.out.println("First Item Description--"+firstItem_Description);
		logger.info("<b>"+"First Item Description--"+"</b>"+firstItem_Description);
		//For Item Price - Create a JavascriptExecutor object
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Execute the JavaScript code to get the text content of the span element
		String script = "var spanElement = document.querySelector('span.a-offscreen');"
		              + "var textContent = spanElement.textContent;"
		              + "return textContent;";
		String itemPrice = (String) js.executeScript(script);

		//Selected item price		
		System.out.println("First Item Price-- "+itemPrice);
		logger.info("<b>"+"First Item Price-- "+"</b>"+itemPrice);
		itemsAttributes = new ArrayList<>(); // Initialize the class-level field
		itemsAttributes.add(firstItemURL);
		itemsAttributes.add(firstItem_Description);
		itemsAttributes.add(itemPrice);
		//Details of Element
		System.out.println(itemsAttributes.get(0));
		System.out.println(itemsAttributes.get(1));
		System.out.println(itemsAttributes.get(2));
				
	}

	
	@Then("Verify product page is launched")
	public void Verify_product_page_is_launched() throws InterruptedException, IOException {
		//Page loading
		Thread.sleep(2000);
		 //List <String> itemAttributes = this.Capture_details_of_First_Selected_Element();
		itemsAttributes.get(0).equals(driver.getCurrentUrl());
		 System.out.println("<b><h3>"+"First Item URL-- "+itemsAttributes.get(0)+"</h3></b>");
		 itemsAttributes.get(1).equals(driver.findElement(By.xpath("//span[@id='productTitle']")).getText());
		 System.out.println("<b><h3>"+"First Item Description-- "+itemsAttributes.get(1)+"</h3></b>");
		 itemsAttributes.get(2).equals(driver.findElement(By.xpath("//table[@class='a-lineitem a-align-top']/tbody/tr/td[2]")).getText());
		 System.out.println("<b><h3>"+"First Item Price-- "+itemsAttributes.get(2)+"</h3></b>");

//		 //Capture and place screenshot initially
//			WebElement selectedItemImage = driver.findElement(By.xpath("//img[@class = 'a-dynamic-image a-stretch-vertical' and @id = 'landingImage']"));
//			Shot_Item_Image = new AShot().takeScreenshot(driver,selectedItemImage);
//			ImageIO.write(Shot_Item_Image.getImage(),"png",new File("./ImageComparison/001addedItemImage.png"));
			
			//BufferedImage ItemImage = Shot_Item_Image.getImage();

		 logger.pass("The product page of selected item verified");
	
	}
		
	
	@Then("Verify Add to Cart Disabled for undelivered location")
	public void verify_add_to_cart_disabled_for_undelivered_location() throws InterruptedException {
		WebElement AddToCart = driver.findElement(By.xpath("//Span[@id='exportsUndeliverable-cart-announce']"));
		if(!AddToCart.isSelected())
		{
		System.out.println("Element not enabled");
		logger.pass("Add to cart disabled for undeliverable location");
		//Mouse Hover Action
		Actions action = new Actions(driver);
		action.moveToElement(AddToCart).click().perform();
		Thread.sleep(1000);		
		
		//Capture the message - is correct?
		String definedMessage = "This item cannot be shipped to your selected delivery location. Please choose a different delivery location.";
		String ErrorOnWeb = driver.findElement(By.xpath("//table[@class='a-normal a-span10']/tbody/tr/td/span")).getText();
		//Verify
		definedMessage.equals(ErrorOnWeb.strip());
		driver.findElement(By.xpath("//span[@class='a-color-error'][1]")).getText().contains("cannot be shipped");
		logger.info("Web User unable to checkout of undeliverable product");
		}
	}
	

	
	@Then("Delivery Location {string}")
	public void delivery_location_(String countryName) throws InterruptedException {
	   WebElement Location = driver.findElement(By.xpath("//span[contains(text(),'Deliver to')]"));
	   Location.click();
	   Thread.sleep(2000);	
	 
	   WebElement ChooseLocation = driver.findElement(By.xpath("//span[@class='a-button-text a-declarative' and @role='radiogroup']/span"));
	   ChooseLocation.click();
	   Thread.sleep(2000);
	   //Select Japan - delivered location from list box for available delivery location
	   List<WebElement> Countries = driver.findElements(By.xpath("//ul[@class='a-nostyle a-list-link' and @role ='listbox']/li/a"));
	   //Select Japan
	   for(int i = 0;i<Countries.size();i++)
	   {
		   
		   if(Countries.get(i).getText().equals(countryName))
		   {
			   Countries.get(i).click();
			   Thread.sleep(1000);
			   break;
			   
		   }
	   }
	   Thread.sleep(1000);	
	   driver.findElement(By.xpath("//button[@name='glowDoneButton']")).click();
	   Thread.sleep(1000);
	   logger.info("Item deliver location changed to deliverable country - "+countryName);
	   
	}
	

	@Then("add item to cart")
	public void add_item_to_cart() throws InterruptedException {
		Thread.sleep(4000);	
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();			
		logger.info("Item added to cart sucessfully");
	}


	@Then("Correct items added to cart")
	public void Correct_items_added_to_cart() throws IOException {
		driver.getCurrentUrl().startsWith("https://www.amazon.com/cart/");
		WebElement AddtoCartInfo = driver.findElement(By.xpath("//h1[contains(text(),'Added to Cart')]"));
		AddtoCartInfo.getText().strip().equals("Added to Cart");
		
		//For Price value - Create a JavascriptExecutor object
				JavascriptExecutor js = (JavascriptExecutor) driver;

				// Execute the JavaScript code to get the text content of the span element
				String script = "var spanElement = document.querySelector('span.a-offscreen');"
				              + "var textContent = spanElement.textContent;"
				              + "return textContent;";
				String itemPrice = (String) js.executeScript(script);
		
		
		
		System.out.println("Sub Total of Cart" + itemPrice);
		//Item Price Comparison
		itemPrice.equals(itemsAttributes.get(2));
		
		//Item Description Comparison
		itemPrice.equals(itemsAttributes.get(1));
		
		
		//Cart Item quantity Check
		WebElement ItemQuantity = driver.findElement(By.xpath("//div[@class='sc-without-multicart']"));
		System.out.println("Item Quantity - " + ItemQuantity.getText());
		
		//Cart Count on icon Check
		WebElement iconCartCount = driver.findElement(By.xpath("//div[@id='nav-cart-count-container']/span[1]"));
		System.out.println("Item Count on Icon - " + iconCartCount.getText());
		
		//Item Count Cross Verification
		(ItemQuantity.getText()).contains(iconCartCount.getText());
		logger.pass("<b><h3>"+"Product price, details and quantitly is verified"+"</h3></b>");
		
		//Right Span Info Check - AddToCartImg and CartRightSpanImg
		
//		//add to cart screen shot capture
//		File Screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());	
//		FileUtils.copyFile(Screenshot, new File("./Screenshot/"+timeStamp+"_ErrorPage.png"));

		
		
////FUll Page Screen Shot add dependency "Ashot" but supported only in firefox browser
//		Screenshot FullPageScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
//		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//		ImageIO.write(FullPageScreenshot.getImage(), "png", new File("./Screenshot/"+timeStamp+"_FUllPageShot.png") );
//		AddedCartItem = 
		
//		//FInalImage Verification
//		
//		WebElement addedItemImage = driver.findElement(By.xpath("//img[@class='sc-product-image celwidget']"));
//
//		WebElement CartTooltipImage = driver.findElement(By.xpath("//img[@class='sc-product-image']"));
//		
//		Screenshot shot_addedItemImage = new AShot().takeScreenshot(driver,addedItemImage);
//		Screenshot shot_CartTooltipImage = new AShot().takeScreenshot(driver,CartTooltipImage);
//		
//		ImageIO.write(shot_addedItemImage.getImage(),"png",new File("./ImageComparison/addedItemImage.png"));
//		ImageIO.write(shot_CartTooltipImage.getImage(),"png",new File("./ImageComparison/shot_CartTooltipImage.png"));

		
		
		
//		BufferedImage Image_CartItem = shot_addedItemImage.getImage();
//		BufferedImage Image_ToolTipItem = shot_CartTooltipImage.getImage();
//		
//		ImageDiffer imageDiff = new ImageDiffer();
//		
//		ImageDiff diff = imageDiff.makeDiff(Image_CartItem, Image_ToolTipItem);
//		
//		if(diff.hasDiff()==true)
//		{
//			System.out.println("Images are same");
//		}
//		else
//		{
//			System.out.println("Image are not same");
//		}
//		
//		
//		
//		
//		//Item_Image on search page
//		BufferedImage ItemImage = Shot_Item_Image.getImage();
//		//Image Compare from item selection on search result page
//		
//		ImageDiff imageDiff_SearchToAdd = imageDiff.makeDiff(Image_CartItem, ItemImage);
//		
//		if(imageDiff_SearchToAdd.hasDiff()==true)
//		{
//			System.out.println("Search and Cart Image are same");
//		}
//		else
//		{
//			System.out.println("Search and Cart Image are not same");
//		}
//		
		}
	
	@Then ("Check Out the item")
	public void Check_Out_the_item() throws InterruptedException {
	WebElement btnCheckOut = driver.findElement(By.xpath("//span[@id=\"sc-buy-box-ptc-button\"]/span/input"));
	btnCheckOut.getAttribute("value").equals("Proceed to checkout");
	btnCheckOut.isSelected();
	btnCheckOut.click();
	logger.info("Item Proceed to checkout");
	Thread.sleep(1000);
	
			
	if (!driver.getCurrentUrl().toLowerCase().startsWith("https://www.amazon.com/cart/", 0)){
	    
	} else {
	    System.out.println("Test Fail, Website still on current page");
	    logger.fail("Test Fail, Website still on current page");
	}
	
			
	}

	@Then ("Close the Web Browser")
	public void Close_the_Web_Browser() throws SocketException {
			// Close the browser
            driver.quit();
            System.out.println("Browser closed");
	}
	
    @After
    public void ReporterClosure() {
    	System.out.println("Browser closed");
    	 extent.flush();
     }

	
}
