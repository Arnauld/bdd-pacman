package pacman;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class BricABracTest {
    @Test
    public void stripMargin_single_line_no_trailing_whitespace() {
        assertThat(BricABrac.stripMargin("|ehoh")).isEqualTo("ehoh");
    }

    @Test
    public void stripMargin_single_line_trailing_whitespaces() {
        assertThat(BricABrac.stripMargin("    |ehoh")).isEqualTo("ehoh");
    }

    @Test
    public void stripMargin_multilines_trailing_whitespaces() {
        assertThat(BricABrac.stripMargin("    |ehoh\n    |yuk")).isEqualTo("ehoh\nyuk");
    }

    @Test
    public void stripMargin_multilines_only_replace_trailing_margin() {
        assertThat(BricABrac.stripMargin("    |ehoh\n    |yuk   |yuk")).isEqualTo("ehoh\nyuk   |yuk");
    }

    @Test
    public void trimEnding_usecases() {
        assertThat(BricABrac.trimEnding("    yuk   ")).isEqualTo("    yuk");
        assertThat(BricABrac.trimEnding("yuk")).isEqualTo("yuk");
    }

}
