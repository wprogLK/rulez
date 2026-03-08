package dev.wproglk.rulez.engine;

import dev.wproglk.rulez.generator.Generator;
import dev.wproglk.rulez.interpreter.Interpreter;
import dev.wproglk.rulez.rules.Ruleset;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class Engine<T> {
    private final FileWriterPort fileWriter;
    private final Function<ResultCache, T> mapper;
    private List<Ruleset> rulesets;

    public Engine(Function<ResultCache, T> mapper) {
        this(new FileWriterAdapter(), mapper);
    }

    public Engine(final FileWriterPort fileWriter) {
        this(fileWriter, null);
    }

    public Engine(final FileWriterPort fileWriter, Function<ResultCache, T> mapper) {
        this.fileWriter = fileWriter;
        this.mapper = mapper;
    }

    public void generateDocumentation() throws IOException {
        String html = Generator.generateHTML(rulesets);
        this.fileWriter.write(html);
    }

    public void setRulesets(Ruleset... rulesets) {
        this.rulesets = List.of(rulesets);
    }

    public void setRulesets(List<Ruleset> rulesets) {
        this.rulesets = rulesets;
    }

    public T executeRuleset(String source) {
        ResultCache resultCache = Interpreter.execute(rulesets, source);
        return mapper.apply(resultCache);
    }
}
