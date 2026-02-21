package dev.wproglk.rulez.engine;

import java.io.IOException;

public interface FileWriterPort {
    void write(String htmlContent) throws IOException;
}
