package edu.TuringMachine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public record Command(
    int commandIndex,
    char replacing,
    Processor processor,
    int nextCommandIndex
) {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^q(\\d+) ([^ ]) -> ([^ ]) ([RLH]) q(\\d+)$");

    public static Command parseCommand(@NotNull String command) {
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (!matcher.find()) {
            throw new RuntimeException("Wrong command format");
        }

        int commandIndex = Integer.parseInt(matcher.group(1));
        char replacing = matcher.group(2).charAt(0);
        Processor processor = (Memory memory) -> {
            memory.replaceChar(matcher.group(3).charAt(0));
            switch (matcher.group(4).charAt(0)) {
                case 'R' -> memory.goRight();
                case 'L' -> memory.goLeft();
            }
        };
        int newCommandIndex = Integer.parseInt(matcher.group(5));

        return new Command(
            commandIndex,
            replacing,
            processor,
            newCommandIndex
        );
    }
}
