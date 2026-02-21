package dev.wproglk.rulez.engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriterAdapter implements FileWriterPort {

    @Override
    public void write(final String htmlContent) throws IOException {
        Path path = Path.of("target/documentation.html");
        Files.createDirectories(path.getParent());

        Files.writeString(path, htmlContent);
    }
}
