
package com.quantum.steps;

import static com.qmetry.qaf.automation.step.CommonStep.click; 
import static com.quantum.steps.PerfectoApplicationSteps.assertLocator;
import static com.quantum.steps.PerfectoApplicationSteps.iSet;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Lee Shoham
 * @date Jan 10, 2017
 */

@QAFTestStepProvider
public class RecipesStespsDefs {
	
	private QAFExtendedWebDriver driver = new WebDriverTestBase().getDriver();
	
	@Given("^I am on the home page$")
	public void validateHomePage() {

		assertLocator("homePage");
	}
	
	
	@And("^I navigate to add another recipe$")
	public void clickAddRecipe() {
		click("addBtn");
		Assert.assertEquals(driver.findElement("title").getText(), "Add Recipe");
	}
	
	
	@And("^I add a new recipe name \"([^\"]*)\"$")
	public void insertRecipeName(String name) {
		iSet(name, "recipeNameInput");
		click("saveBtn");
		Assert.assertEquals(driver.findElement("title").getText(), name);
	}
	
	
	@When("^I navigate to edit recipe$")
	public void goToEditRecipe() {
		click("editRecipeBtn");
	}
	
	@And("^I choose category$")
	public void chooseCategory() {
		click("chooseCategory");
		click("dessertCategory");
		click("doneBtn");
	}
	
	@And("^I add ingredients$")
	public void addIngredients() {
		click("addIngredients");
		click("dessertCategory");
		click("doneBtn");
	}
	
	@And("^I add ingredients with the following data$")
	public void addIngredients(Map<Object,Object> table) {
		//Initialize data table 
	      System.out.println(table.get(1)); 

	}

	
	

	
	
	


}
