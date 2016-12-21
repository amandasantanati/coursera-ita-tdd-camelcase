import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

public class StringConverter {
    private static final String REPLACEMENT = " ";

    public static List<String> converterCamelCase(String original) {
        return splitWords(original);
    }

    private static String splitCamelCase(String original) {
        return original.replaceAll(
                String.format("%s|%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[a-z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[0-9])",
                        "(?<=[0-9])(?=[A-Za-z])"
                ),
                REPLACEMENT
        );
    }

    private static List<String> splitWords(String original) {
        String[] words = splitCamelCase(original).split(REPLACEMENT);

        return makeListAtLowercase(words);
    }

    private static List<String> makeListAtLowercase(String[] words) {
        List<String> lowerWords = new ArrayList<String>();

        for (String word : words) {
            addToResultList(lowerWords, word);
        }

        return lowerWords;
    }

    private static void addToResultList(List<String> lowerWords, String word) {
        if(isAllUpper(word)) {
            lowerWords.add(word);
        } else {
            lowerWords.add(word.toLowerCase());
        }
    }

    private static boolean isAllUpper(String word) {
        for(char letter : word.toCharArray()) {
            if(Character.isLowerCase(letter)) {
                return false;
            }
        }
        return true;
    }
}
