import java.util.ArrayList;
import java.util.List;

public class StringConverter {
    private static final String REPLACEMENT = " ";

    public static List<String> converterCamelCase(String original) {
        return breakWords(original);
    }

    private static String splitCamelCase(String original) {
        return original.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                REPLACEMENT
        );
    }

    private static List<String> breakWords(String original) {
        String[] words = splitCamelCase(original).split(REPLACEMENT);

        return makeListAtLowercase(words);
    }

    private static List<String> makeListAtLowercase(String[] words) {
        List<String> lowerWords = new ArrayList<String>();

        for (String word : words) {
            lowerWords.add(word.toLowerCase());
        }

        return lowerWords;
    }
}
