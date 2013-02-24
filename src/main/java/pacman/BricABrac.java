package pacman;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class BricABrac {
    public static String stripMargin(String input) {
        return input.replaceAll("(^|([\r\n]))\\s*\\|", "$2");
    }

    public static String trimEnding(String text) {
        int index;
        for(index = text.length() - 1; index >= 0; index--) {
            if(text.charAt(index) == ' ' || text.charAt(index) == '\t')
                continue;
            break;
        }
        if(index < text.length())
            return text.substring(0, index+1);
        return text;
    }
}
