import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringConverter {
    private static final String REPLACEMENT = " ";

    public static List<String> converterCamelCase(String original) {
        if(isBeginWithNUmber(original)) {
            throw new IllegalArgumentException("Não pode começar com número");
        }

        if(containsIllegals(original)) {
            throw new IllegalArgumentException("Não pode conter caracteres inválidos");
        }

        return splitWords(original);
    }

    private static boolean containsIllegals(String original) {
        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
        Matcher matcher = pattern.matcher(original);
        return matcher.find();
    }

    private static boolean isBeginWithNUmber(String original) {
        return Character.isDigit(original.charAt(0));
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
