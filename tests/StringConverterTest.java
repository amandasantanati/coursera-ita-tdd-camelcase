import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StringConverterTest {
    @Test
    public void shouldReturnOneLowercaseWordGivenOneLowercaseWord() {
        List<String> expectedValue = Collections.singletonList("nome");
        assertThat(StringConverter.converterCamelCase("nome"), is(expectedValue));
    }

    @Test
    public void shouldReturnOneLowercaseWordGivenOneUppercaseWord() {
        List<String> expectedValue = Collections.singletonList("composto");
        assertThat(StringConverter.converterCamelCase("Composto"), is(expectedValue));
    }

    @Test
    public void shouldReturnTwoLowercaseWordsGivenOneLowercaseWordAndOneUppercaseWord() {
        List<String> expectedValue = Arrays.asList("nome","composto");
        assertThat(StringConverter.converterCamelCase("nomeComposto"), is(expectedValue));
    }

    @Test
    public void shouldReturnTwoLowercaseWordsGivenTwoUppercaseWords() {
        List<String> expectedValue = Arrays.asList("nome","composto");
        assertThat(StringConverter.converterCamelCase("NomeComposto"), is(expectedValue));
    }

    @Test
    public void shouldReturnAnyLowercaseWordsGivenAnyWords() {
        List<String> expectedValue = Arrays.asList("nome","composto","completo");
        assertThat(StringConverter.converterCamelCase("nomeCompostoCompleto"), is(expectedValue));
        assertThat(StringConverter.converterCamelCase("NomeCompostoCompleto"), is(expectedValue));
    }

    
}