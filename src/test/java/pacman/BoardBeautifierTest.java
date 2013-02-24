package pacman;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class BoardBeautifierTest {

    @Test
    public void top_left_corner() {
        assertThat(beautify("##\n# ")).isEqualTo("┌─\n" +
                "│ ");
    }

    @Test
    public void top_right_corner() {
        assertThat(beautify("##\n #")).isEqualTo("─┐\n" +
                " │");
    }

    @Test
    public void bottom_left_corner() {
        assertThat(beautify("# \n##")).isEqualTo("│ \n" +
                "└─");
    }

    @Test
    public void bottom_right_corner() {
        assertThat(beautify(" #\n##")).isEqualTo(" │\n" +
                "─┘");
    }

    @Test
    public void flat_square() {
        assertThat(beautify("##\n##")).isEqualTo("┌┐\n" +
                "└┘");
    }

    @Test
    public void flat_rectangle() {
        assertThat(beautify("###\n###")).isEqualTo("┌─┐\n" +
                "└─┘");
    }

    @Test
    public void flat_long_horizontal_rectangle() {
        assertThat(beautify("#####\n#####")).isEqualTo("┌───┐\n" +
                "└───┘");
    }

    @Test
    public void flat_long_vertical_rectangle() {
        assertThat(beautify("##\n##\n##\n##\n##")).isEqualTo("┌┐\n" +
                "││\n" +
                "││\n" +
                "││\n" +
                "└┘");
    }

    @Test
    public void non_flat_rectangle() {
        assertThat(beautify("####\n#  #\n#  #\n#  #\n####")).isEqualTo("┌──┐\n" +
                "│  │\n" +
                "│  │\n" +
                "│  │\n" +
                "└──┘");
    }

    @Test
    public void two_squares() {
        String raw = //
                "######\n" +//
                        "# ## #\n" +//
                        "######";
        String expected = //
                "┌─┐┌─┐\n" +
                        "│ ││ │\n" +
                        "└─┘└─┘";
        assertThat(beautify(raw)).isEqualTo(expected);
    }

    @Test
    public void four_squares() {
        String raw = //
                "######\n" +//
                        "# ## #\n" +//
                        "# ## #\n" +//
                        "######\n" +//
                        "######\n" +//
                        "# ## #\n" +//
                        "######";
        String expected = //
                "┌─┐┌─┐\n" +//
                        "│ ││ │\n" +//
                        "│ ││ │\n" +//
                        "└─┘└─┘\n" +//
                        "┌─┐┌─┐\n" +//
                        "│ ││ │\n" +//
                        "└─┘└─┘";
        assertThat(beautify(raw)).isEqualTo(expected);
    }


    private String beautify(String input) {
        return new BoardBeautifier().process(input);
    }
}
