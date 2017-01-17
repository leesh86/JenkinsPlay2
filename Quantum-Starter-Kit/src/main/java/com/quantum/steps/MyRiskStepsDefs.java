/**
 * 
 */
package com.quantum.steps;

import static com.qmetry.qaf.automation.step.CommonStep.click;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Lee Shoham
 * @date Nov 21, 2016
 */

@QAFTestStepProvider
public class MyRiskStepsDefs {
	
	// a step that checks for the error popout and disables it if exists, and then asserts login page
	@Given("^I am on the login page$")
	public void validateLoginPage() {
//		PerfectoApplicationSteps.switchNativeContext();
		if (PerfectoApplicationSteps.verifyLocator("analyticsErrorAlert")) {
			System.out.println("FOUND POPUP");
			click("analyricsErrorDismiss");
		}
		PerfectoApplicationSteps.switchWebviewContext();
		PerfectoApplicationSteps.assertLocator("loginUser");
	}
	
	
	@And("^I login with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void loginToApp(String user, String password) {
		PerfectoApplicationSteps.iSet(user, "loginUser");
		PerfectoApplicationSteps.iSet(password, "loginPassword");
		click("loginSignInBtn");
	}
	
	
	


}
