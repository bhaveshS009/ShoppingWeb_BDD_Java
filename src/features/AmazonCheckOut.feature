Feature: Functionality testing of Add to Cart feature on Amazon.com Website


#URL verifcation
#Verify URL, Title AMAZON Logo
#@Before Launch_the_Amazon_Url
Scenario: Check functionality of search item on home page
Given Amazon web laucnhed
When User enters some text to search
Then Wild Card search should be executed according to input provided
#@After Close_the browser

#@Before Launch_the_Amazon_Url
#
Given On search box all defined department grouping provided
When User click on All departments
Then list of all defined department appears
#And Refresh Webpage


Given On search box all defined department grouping provided
When User click on All departments
Then able to access first department in list and get selected
And enter a valid text -K to search
And click on item search
And search Sucessfully Executed
 
#And Refresh Webpage
#And also able to select any middle indexd department in list and get selected



Given Amazon icon label visible on page
When click on Amazon icon
Then Web Site should lend on home page



Given On search box all defined department grouping provided

When User click on All departments
Then able to access first department in list and get selected
And list of all defined department appears
And able to select the last department in list and get selected
#And Refresh Webpage
#@After Close_the_browser
#


# Search on invalid items
#Given On search box all defined department grouping provided
#When User click on All departments
#And User enters some invalid text to search
#And click on item search
#Then Observe On webpage no results must appears
#And click on Amazon icon


#Relavent result of search items
Given User enters a valid item to search
When click on item search
Then results are displayed
And Capture details of First Selected Element
And click on first result to check out
Then Verify product page is launched

#Add to cart disabled on undelivered location
Given User enters a valid item to search
When click on item search
Then results are displayed
And click on first result to check out
And Verify Add to Cart Disabled for undelivered location
And Delivery Location Japan
And add item to cart
And Correct items added to cart
And Check Out the item




#@Before Launch_the_Amazon_Url
#Given User search for kindle reader
#When User able to select first ,middle and last index items
#Then on clicking items item detail must be displayed
#And item information displayed must be simillar to as displayed in preview
#
#Scenario: Add_to_cart_functionality_check
#@Before Launch_the_Amazon_Url
#Given Amazon web laucnhed
#When User search for the item which is available and deliverable
#Then  User able to add that item in cart
#And correct item added to cart
#
#Scenario: Add_to_cart_functionality_check
#@Before Launch_the_Amazon_Url
#Given Given Amazon web laucnhed
#When User search for the item undelivered at the location
#Then error should be raised regarding undeliverable
#And User unable to add that item in cart
#
#Scenario: Add_to_cart_functionality_check
#@Before Launch_the_Amazon_Urll
#Given Given Amazon web laucnhed 
#When User search for the item which is not available
#Then error should be raised regarding unavailability
#And User unable to add that item in cart
#
#
