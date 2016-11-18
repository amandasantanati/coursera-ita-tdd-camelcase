import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringConverter {
    private static final int INITIAL_LETTER = 0;
    private static int position = 0;

    public static List<String> converterCamelCase(String original) {
        if(retriveWhereCapitalLetterIs(original) != INITIAL_LETTER) {
            return breakWords(original);
        };

        return Collections.singletonList(original.toLowerCase());
    }

    private static int retriveWhereCapitalLetterIs(String original) {
        for(int i = 0; i < original.length(); i++) {
            if(Character.isUpperCase(original.charAt(i))) {
                position = i;
                return i;
            }
        }

        return 0;
    }

    private static List<String> breakWords(String original) {
        return Arrays.asList(
                original.substring(0, position).toLowerCase(),
                original.substring(position).toLowerCase()
        );
    }
}
