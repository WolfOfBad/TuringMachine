package edu.TuringMachine;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Processor {
    void process(@NotNull Memory memory);
}
