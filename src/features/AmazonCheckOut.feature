Feature: Functionality testing of Add to Cart feature on Amazon.com Website
@FunctionalTest
Scenario Outline: Check the Wild Cart Search should function according to input provided
Given Amazon web laucnhed
When User enters "<item>" to search
Then Wild Card search should be executed according to "<item>" provided
Examples:
|item		|
|Mobile	|
|Kindle	|
|Shirt	|
|Shoe		|

@SearchItem
Scenario: On Search box departments list should be displayed according to pre defined departments
Given On search box all defined department grouping provided
When User click on All departments
Then list of all defined department appears

@SearchItem
Scenario: Checksearch feature with first department list name
Given On search box all defined department grouping provided
When User click on All departments
Then able to access first department in list and get selected
And User enters "Paint" to search
And click on item search
And search "Paint" Sucessfully Executed


Scenario: Checksearch feature with last department list name
Given On search box all defined department grouping provided
When User click on All departments
And able to select the last department in list and get selected
And User enters "makeup" to search
And click on item search
And search "makeup" Sucessfully Executed


Scenario: Check webpage landing via Amazon icon provided
Given Amazon icon label visible on page
When click on Amazon icon
Then Web Site should land on home page


Scenario: Check after product selection product page is launched
Given User enters "Kindle" to search
When click on item search
And search "Kindle" Sucessfully Executed
Then results are displayed
And Capture details of Selected Element
And click on the item to check out
Then Verify product page is launched


Scenario: Check that product unable to check out for undelivered location
Given User enters "Kindle" to search
When click on item search
And search "Kindle" Sucessfully Executed
Then results are displayed
And Capture details of Selected Element
And click on the item to check out
Then Verify product page is launched
And Verify Add to Cart Disabled for undelivered location


Scenario: Check that product unable to check out for undelivered location
Given User enters "Kindle" to search
When click on item search
And search "Kindle" Sucessfully Executed
Then results are displayed
And Capture details of Selected Element
And click on the item to check out
Then Verify product page is launched
And Verify Add to Cart Disabled for undelivered location
And Delivery Location "Japan"
And add item to cart
And Correct items added to cart
And Check Out the item
And Close the Web Browser

