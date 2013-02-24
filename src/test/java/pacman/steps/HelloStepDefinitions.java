package pacman.steps;

import cucumber.api.java.en.Given;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class HelloStepDefinitions {

    @Given("^I have (\\d+) cukes in my belly$")
    public void given_n_cukes(int nbCukes) {
        System.out.println("HelloStepDefinitions.given_n_cukes(" + nbCukes + ")");
    }
}
