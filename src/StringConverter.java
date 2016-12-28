import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringConverter {
    private static final String REPLACEMENT = " ";
    private static final String MESSAGE_INVALID_NUMBER_START = "Não pode começar com número";
    private static final String MSG_INVALID_CARACTERES = "Não pode conter caracteres inválidos";
    private static final String REGEX_INVALID_CARACTERES = "[~#@*+%{}<>\\[\\]|\"\\_^]";
    private static final String UPPERCASE_BEFORE_LOWER_OR_UPPERCASE = "(?<=[A-Z])(?=[A-Z][a-z])";
    private static final String LOWERCASE_BEFORE_UPPERCASE = "(?<=[a-z])(?=[A-Z])";
    private static final String UPPER_AND_LOWERCASE_BEFORE_DIGITS = "(?<=[A-Za-z])(?=[0-9])";
    private static final String DIGITS_BEFORE_LOWER_AND_UPPERCASE = "(?<=[0-9])(?=[A-Za-z])";

    public static List<String> converterCamelCase(String original) {
        if(isBeginWithNUmber(original)) {
            throw new IllegalArgumentException(MESSAGE_INVALID_NUMBER_START);
        }

        if(containsIllegals(original)) {
            throw new IllegalArgumentException(MSG_INVALID_CARACTERES);
        }

        return splitWords(original);
    }

    private static boolean containsIllegals(String original) {
        Pattern pattern = Pattern.compile(REGEX_INVALID_CARACTERES);
        Matcher matcher = pattern.matcher(original);
        return matcher.find();
    }

    private static boolean isBeginWithNUmber(String original) {
        return Character.isDigit(original.charAt(0));
    }

    private static String splitCamelCase(String original) {
        return original.replaceAll(
                String.format("%s|%s|%s|%s",
                        UPPERCASE_BEFORE_LOWER_OR_UPPERCASE,
                        LOWERCASE_BEFORE_UPPERCASE,
                        UPPER_AND_LOWERCASE_BEFORE_DIGITS,
                        DIGITS_BEFORE_LOWER_AND_UPPERCASE
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
