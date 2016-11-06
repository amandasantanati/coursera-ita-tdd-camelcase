import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StringConverterTest {
    @Test
    public void shouldReturnOneTinyWordGivenOneTinyWord() {
        List<String> expectedValue = Collections.singletonList("nome");
        assertThat(StringConverter.converterCamelCase("nome"), is(expectedValue));
    }

}