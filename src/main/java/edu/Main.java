package edu;

import edu.TuringMachine.Command;
import edu.TuringMachine.Memory;
import edu.TuringMachine.TuringMachine;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private Main() {
    }

    public static void main(String[] args) throws IOException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Path.of("./src/main/resources/task1.txt"));

        //System.out.println("Введите алфавит МТ:");
        Set<Character> alphabet = Arrays.stream(
                scanner.nextLine().split(" "))
            .map(ch -> ch.charAt(0))
            .collect(Collectors.toSet());

        //System.out.println("\nВведите количество команд:");
        int commandsSize = Integer.parseInt(scanner.nextLine());

        //System.out.println("\nВведите команды:");
        Map<Integer, List<Command>> commands = new HashMap<>();
        for (int i = 0; i < commandsSize; i++) {
            Command command = Command.parseCommand(scanner.nextLine());
            if (!commands.containsKey(command.commandIndex())) {
                commands.put(command.commandIndex(), new ArrayList<>());
            }
            commands.get(command.commandIndex()).add(command);
        }

        //System.out.println("\nВведите пустой символ");
        char empty = scanner.nextLine().charAt(0);

        //System.out.println("\nВведите номер начального состояния");
        int start = Integer.parseInt(scanner.nextLine());
        //System.out.println("\nВведите номер конечного состояния");
        int end = Integer.parseInt(scanner.nextLine());

        //System.out.println("\nВведите часть ленты памяти, с которой будет работать МТ");
        String memoryString = scanner.nextLine();
        //System.out.println("\nВведите начальный индекс из введенной ленты:");
        int index = Integer.parseInt(scanner.nextLine());
        Memory memory = new Memory(memoryString, empty, index);

        TuringMachine mt = new TuringMachine(alphabet, commands, start, end);
        mt.process(memory);

        System.out.println("\nПамять перед работой машины Тьюринга:");
        System.out.println(memoryString);

        System.out.println("\nРезультат работы машины Тьюринга:");
        System.out.println(memory.getMemory());
    }
}
