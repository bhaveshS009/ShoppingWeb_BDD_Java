package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

import io.cucumber.java.en.And;

public class StepDefinition {
	
	public static WebDriver driver;
	private String[] PreDefinedList;
	private  List<WebElement> ListAllDepartments;
	private List<String> itemsAttributes;
	private Screenshot Shot_Item_Image;
	
	@When("User enters some text to search")
	public void user_enters_some_text_to_search() {
	    WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
	    searchTextBox.click();
	    //Single Text Search Check k i n D L
	    
	    searchTextBox.sendKeys("K");
	    System.out.println("when executed");
	  
	   
	    
	}

	@Then("Wild Card search should be executed according to input provided")
	public void wild_card_search_should_be_executed_according_to_input_provided() throws InterruptedException {
		Thread.sleep(5000); 
		List <WebElement> suggestedList = driver.findElements(By.xpath("//div[@class= 's-suggestion-container']/div"));
		 char character = 'k';   
		 System.out.println("Total number of suggested items for text entry - " + suggestedList.size());
		 for(int i =0;i<suggestedList.size();i++)
		    {
			 
			 String suggestedName = suggestedList.get(i).getText();
		    	if (suggestedList.get(i).getText().charAt(0) == character)
		    		{ 
		    		System.out.println("Contains the text "+ suggestedName);		
		    		}
		    	else
		    		{
		    		System.out.println("else");
		    			System.out.println("Test is failed");
		    		}
		    	
		    }
	    
	    
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
						AllDepartmeterFiltter.click();
	    
	}

	@Then("list of all defined department appears")
	public List<WebElement> list_of_all_defined_department_appears() {
		//ListAllDepartments = new WebElement[]driver.findElements(By.xpath("//select[@id='searchDropdownBox']/option"));
		ListAllDepartments = driver.findElements(By.xpath("//select[@id='searchDropdownBox']/option"));
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
	    				+ ListAllDepartments.get(k).getText()
	    				
	    				);
	    	}
	    		    	
	    }	
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
		if(elementList.get(1).isEnabled())
		{
			//Click Operation
			elementList.get(1).click();
		}
		else
		{
			System.out.println("Test is Failed");
			
		}
		
	    
	}


	@And("able to select the last department in list and get selected")
	public void able_to_select_the_last_department_in_list_and_get_selected() throws InterruptedException {
		List <WebElement> elementList = this.list_of_all_defined_department_appears();
		
		int lastIndex = elementList.size()-1;//Array Indexing  for last element
		System.out.println("Last Element Name - " + elementList.get(lastIndex).getText());
		
		
//      To mimic Scroll Down Operation - not working Stale element error
//		Actions actions = new Actions(driver);
//		//Mouse hover
//		actions.moveToElement(elementList.get(5)).contextClick();
//		actions.scrollByAmount(0, 200);
//		System.out.println("Scrolled final");
//		driver.wait(10000);
//		elementList.get(lastIndex).click();
		
					
//		To mimic Scroll Down Operation - Execution using java script - not working Stale element error
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementList.get(lastIndex));
//		driver.wait(2);
//		System.out.println("Scrolled");
				

		if(elementList.get(lastIndex).isDisplayed())
		{

			System.out.println("Element not displayed on current view");
//	      //To mimic Scroll Down Operation - not working
//			Actions actions = new Actions(driver);
//			//Mouse hover
//			actions.moveToElement(elementList.get(0)).build().perform(); //Error - element not interactable: [object HTMLOptionElement] has no size and location
//			actions.scrollByAmount(0, 200);
//			System.out.println("Scrolled final");
//			driver.wait(10000);
			
		}
			
			
			
		else if(elementList.get(lastIndex).isSelected())
		{
			//Click Operation
			elementList.get(lastIndex).click();
		}
		else
		{
			System.out.println("Test is Failed");
			
		}
		
	    
	}

	
	@Then("Refresh Webpage")
	public void Refresh_Webpage() {
		driver.navigate().refresh();
		
	}



	@Given("Amazon web laucnhed")
	public void given_amazon_web_laucnhed() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		String actual_URL = driver.getCurrentUrl();//https://www.amazon.com/
		Thread.sleep(20000);
		driver.navigate().refresh();
		//thread.sleep(10000);
		String webTittle = driver.getTitle();//Amazon.com. Spend less. Smile more.
		System.out.println(actual_URL + webTittle);
		
	    
	    
	}


	@Then("enter a valid text -K to search")
	public void enter_a_valid_text_k_to_search() {
		WebElement searchText = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		//Text to search
		String TextToType = "k";
		searchText.sendKeys(TextToType);
		if (searchText.getAttribute("value") == TextToType )
		{
			System.out.println("Entered text is visible");
		}
		else
		{
			System.out.println("Test Fail");
		}
	}
	
	@Then("click on item search")
	public void click_on_item_search() {
		driver.findElement(By.id("nav-search-submit-button")).click();
	    
	}
	
	@Then("search Sucessfully Executed")
	public void search_sucessfully_executed() {
	    //Verification of Search executed
		driver.getCurrentUrl().contains("K");
		driver.findElement(By.xpath("//div[@class='s-no-outline']/span")).getText().toLowerCase().contains("results");
	}

	
	
	@Given("Amazon icon label visible on page")
	public void amazon_icon_label_visible_on_page() {
	   driver.findElement(By.cssSelector("#nav-logo-sprites")).isDisplayed();
	   
	}
	
	@When("click on Amazon icon")
	public void click_on_amazon_icon() {
		driver.findElement(By.cssSelector("#nav-logo-sprites")).click();
	}
	
	@Then("Web Site should lend on home page")
	public void web_site_should_lend_on_home_page() {
	    driver.getCurrentUrl().equals("https://www.amazon.com/ref=nav_logo");
	    
	    
	}
	
	
	@When("User enters some invalid text to search")
	public void user_enters_some_invalid_text_to_search() {
		    WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		    searchTextBox.click();
		    
		    searchTextBox.sendKeys("2kjdojas09duq32ejoawjdosijd093ueoiasjdksjadh");
		    System.out.println("Invalid Search executed");

	}
	
	
	@Then("Observe On webpage no results must appears")
	public void Observe_On_webpage_no_results_must_appears() {
		WebElement SearchedResult = driver.findElement(By.xpath("(//div[@class = 's-no-outline' and @tabindex = 0]/div)[1]"));
		System.out.println(SearchedResult.getText());		
		SearchedResult.getText().contains("No results for");
		
		
	}

	
	@Given("User enters a valid item to search")
	public void user_enters_a_valid_item_to_search() {
		WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
	    searchTextBox.click();
	    
	    searchTextBox.sendKeys("Kindle");
	    System.out.println("Valid Search executed"); 
	}
	
	@Then("results are displayed")
	public void results_are_displayed() {
		WebElement SearchedResult = driver.findElement(By.xpath("//div[@class = 's-no-outline' and @tabindex = 0]"));
		System.out.println(SearchedResult.getText());		
		SearchedResult.getText().equals("Results");
		
		
		

	    
	}
	
	
	
	@And("click on first result to check out")
	public void  click_on_first_result_to_check_out() throws InterruptedException {
		
		//Capture item properties in to list
		//Intended url
		Thread.sleep(2000);
		WebElement firstElemnetInfo = driver.findElement(By.xpath(
				"//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'][1]"
				));
		System.out.println(firstElemnetInfo);
		
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		WebElement firstElemnetInfo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'][1]")));
//		firstElemnetInfo.click();

		
		
		firstElemnetInfo.click();	
		//firstElemnetInfo.clear();
	}
	/**
	 * This method returns a list of properties of first element <URL> <Description> <Price>.
	 * 
	 * @return A list of items - URL Description Price.
	 * @throws IOException 
	 */
	
	@And("Capture details of First Selected Element")
	public void  Capture_details_of_First_Selected_Element() throws IOException {
		
		//Capture item properties in to list
		//Intended url
		WebElement firstElemnetInfo = driver.findElement(By.xpath(
				"//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'][1]"
				));
		//Item URL
		String firstItemURL = firstElemnetInfo.getAttribute("href");
		System.out.println("First Item URL--"+firstItemURL);
		//Item Description
		String firstItem_Description = firstElemnetInfo.getText();
		System.out.println("First Item Description--"+firstItem_Description);
		//Item Price
		//String firstItem_Price = driver.findElement(By.xpath("//span[@class='a-offscreen'][1]")).getText();
		// Create a JavascriptExecutor object
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Execute the JavaScript code to get the text content of the span element
		String script = "var spanElement = document.querySelector('span.a-offscreen');"
		              + "var textContent = spanElement.textContent;"
		              + "return textContent;";
		String itemPrice = (String) js.executeScript(script);

		//Selected item price		
		System.out.println("First Item Price-- "+itemPrice);
		
		itemsAttributes = new ArrayList<>(); // Initialize the class-level field
		itemsAttributes.add(firstItemURL);
		itemsAttributes.add(firstItem_Description);
		itemsAttributes.add(itemPrice);
		//Details of Element
		System.out.println(itemsAttributes.get(0));
		System.out.println(itemsAttributes.get(1));
		System.out.println(itemsAttributes.get(2));
		
		//return itemsAttributes;
		
				
	}

	
	@Then("Verify product page is launched")
	public void Verify_product_page_is_launched() throws InterruptedException, IOException {
		//Page loading
		Thread.sleep(2000);
		 //List <String> itemAttributes = this.Capture_details_of_First_Selected_Element();
		itemsAttributes.get(0).equals(driver.getCurrentUrl());
		 System.out.println("First Item URL-- "+itemsAttributes.get(0));
		 itemsAttributes.get(1).equals(driver.findElement(By.xpath("//span[@id='productTitle']")).getText());
		 System.out.println("First Item Description-- "+itemsAttributes.get(1));
		 itemsAttributes.get(2).equals(driver.findElement(By.xpath("//table[@class='a-lineitem a-align-top']/tbody/tr/td[2]")).getText());
		 System.out.println("First Item Price-- "+itemsAttributes.get(2));

//		 //Capture and place screenshot initially
//			WebElement selectedItemImage = driver.findElement(By.xpath("//img[@class = 'a-dynamic-image a-stretch-vertical' and @id = 'landingImage']"));
//			Shot_Item_Image = new AShot().takeScreenshot(driver,selectedItemImage);
//			ImageIO.write(Shot_Item_Image.getImage(),"png",new File("./ImageComparison/001addedItemImage.png"));
			
			//BufferedImage ItemImage = Shot_Item_Image.getImage();

	
	
	}
		
	
	@Then("Verify Add to Cart Disabled for undelivered location")
	public void verify_add_to_cart_disabled_for_undelivered_location() throws InterruptedException {
		WebElement AddToCart = driver.findElement(By.xpath("//Span[@id='exportsUndeliverable-cart-announce']"));
		if(!AddToCart.isSelected())
		{
		System.out.println("Element not enabled");
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
		}
	}
	
	@Then("Delivery Location Japan")
	public void delivery_location_japan() throws InterruptedException {
	   WebElement Location = driver.findElement(By.xpath("//span[contains(text(),'Deliver to')]"));
	   Location.click();
	   Thread.sleep(2000);	
	   //Select Japan
	   //radiogroup
	 
	   WebElement ChooseLocation = driver.findElement(By.xpath("//span[@class='a-button-text a-declarative' and @role='radiogroup']/span"));
	   ChooseLocation.click();
	   Thread.sleep(2000);
	   //Select Japan - delivered location from list box
	   List<WebElement> Countries = driver.findElements(By.xpath("//ul[@class='a-nostyle a-list-link' and @role ='listbox']/li/a"));
	   //Select Japan
	   for(int i = 0;i<Countries.size();i++)
	   {
		   
		   if(Countries.get(i).getText().equals("Japan"))
		   {
			   Countries.get(i).click();
			   break;
			   
		   }
	   }
	   Thread.sleep(1000);	
	   driver.findElement(By.xpath("//button[@name='glowDoneButton']")).click();
	   
	}

	@Then("add item to cart")
	public void add_item_to_cart() throws InterruptedException {
		Thread.sleep(4000);	
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();			
	}


	@Then("Correct items added to cart")
	public void Correct_items_added_to_cart() throws IOException {
		driver.getCurrentUrl().startsWith("https://www.amazon.com/cart/");
		WebElement AddtoCartInfo = driver.findElement(By.xpath("//h1[contains(text(),'Added to Cart')]"));
		AddtoCartInfo.getText().strip().equals("Added to Cart");
		//Cart SubTotal Check
		//span[@class='a-price sw-subtotal-amount']/span[1]
		//WebElement CartSubTotal = driver.findElement(By.xpath("//span[@class='a-price sw-subtotal-amount']/span[1]"));
		
		// Create a JavascriptExecutor object
				JavascriptExecutor js = (JavascriptExecutor) driver;

				// Execute the JavaScript code to get the text content of the span element
				String script = "var spanElement = document.querySelector('span.a-offscreen');"
				              + "var textContent = spanElement.textContent;"
				              + "return textContent;";
				String itemPrice = (String) js.executeScript(script);
		
		
		
		System.out.println("Sub Total of Cart" + itemPrice);
		//Cart Item quantity Check
		//div[@class='sc-without-multicart']
		WebElement ItemQuantity = driver.findElement(By.xpath("//div[@class='sc-without-multicart']"));
		System.out.println("Item Quantity - " + ItemQuantity.getText());
		
		//Cart Count on icon Check
		//span[@id='nav-cart-count]
		WebElement iconCartCount = driver.findElement(By.xpath("//div[@id='nav-cart-count-container']/span[1]"));
		System.out.println("Item Count on Icon - " + iconCartCount.getText());
		
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
	Thread.sleep(1000);
	
			
	if (!driver.getCurrentUrl().toLowerCase().startsWith("https://www.amazon.com/cart/", 0)){
	    
	} else {
	    System.out.println("Test Fail, Website stille on current page");
	}
	
			
	}
	
	

}
