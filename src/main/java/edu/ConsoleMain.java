package edu;

import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.IOException;

public class ConsoleMain {
    public static void main(String[] args) throws IOException {
        Console console = System.console();
        if (console == null && !GraphicsEnvironment.isHeadless()) {
            String filename = Main.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toString()
                .substring(6);
            Runtime.getRuntime()
                .exec(new String[] {"cmd", "/c", "start", "cmd", "/k", "java -jar \"" + filename + "\""});
        } else {
            Main.main(new String[0]);
            System.out.println("Программа завершена, напишите 'exit', чтобы закрыть консоль");
        }
    }
}
