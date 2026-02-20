import dev.wproglk.rulez.interpreter.Interpreter;
import dev.wproglk.rulez.rules.Rule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InterpreterTest {

    @Test
    public void extractFirstname(){
        // arrange
        final String json = """
                {
                    "firstname": "Foo"
                }
                """;
        final Rule rule = new Rule();

        // act
        String result = Interpreter.execute(rule, json);

        // assert
        assertThat(result).isEqualTo("Foo");
    }
}
