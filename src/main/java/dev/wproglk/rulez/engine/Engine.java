package dev.wproglk.rulez.engine;

import dev.wproglk.rulez.generator.Generator;
import dev.wproglk.rulez.rules.Rule;

import java.io.IOException;

public class Engine {
    private final FileWriterPort fileWriter;
    private final Rule rule;

    public Engine(final FileWriterPort fileWriterPort, final Rule rule) {
        this.fileWriter = fileWriterPort;
        this.rule = rule;
    }

    public Engine(final Rule rule) {
        this(new FileWriterAdapter(), rule);
    }

    static void main() throws IOException {
        final Engine engine = new Engine(new Rule("$.firstname"));
        engine.generateDocumentation();
    }

    public void generateDocumentation() throws IOException {
        String html = Generator.generateHTML(rule);
        this.fileWriter.write(html);
    }

}
