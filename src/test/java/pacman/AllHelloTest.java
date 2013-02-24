package pacman;

import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
        format = {"pretty","html:target/cucumber"},
        /*glue = {"classpath:pacman/steps/HelloStepDefinitions"}*/
        features = {"classpath:features/hello"}
)
public class AllHelloTest {
}
