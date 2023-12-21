package edu.TuringMachine;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public record TuringMachine(
    @NotNull Set<Character> alphabet,
    @NotNull Map<Integer, List<Command>> commands,
    int startCommandIndex,
    int endCommandIndex
) implements Processor {
    @Override
    public void process(@NotNull Memory memory) {
        int commandIndex = startCommandIndex;

        while (commandIndex != endCommandIndex) {
            char currentCharacter = memory.getChar();

            Command currentCommand = null;
            for (Command command : commands.get(commandIndex)) {
                if (command.replacing() == currentCharacter) {
                    currentCommand = command;
                }
            }
            if (currentCommand == null) {
                throw new RuntimeException("There is no command for this character: " + currentCharacter);
            }
            if (!alphabet.contains(currentCommand.replacing())) {
                throw new RuntimeException("Alphabet does not contain character that command is trying to replace: "
                    + currentCommand.replacing());
            }

            currentCommand.processor().process(memory);

            commandIndex = currentCommand.nextCommandIndex();
        }
    }
}
