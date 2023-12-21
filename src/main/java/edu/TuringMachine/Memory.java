package edu.TuringMachine;

import org.jetbrains.annotations.NotNull;

public class Memory {
    private String memory;
    private int index;
    private final char emptySymbol;

    public Memory(@NotNull String memory, char emptySymbol, int index) {
        this.memory = memory;
        this.emptySymbol = emptySymbol;
        this.index = index;
    }

    public void goLeft() {
        if (index == 0) {
            memory = emptySymbol + memory;
        } else {
            index--;
        }
    }

    public void goRight() {
        index++;
        if (index == memory.length()) {
            memory += emptySymbol;
        }
    }

    public String getMemory() {
        return memory;
    }

    public char getChar() {
        return memory.charAt(index);
    }

    public void replaceChar(char newChar) {
        char[] chars = memory.toCharArray();
        chars[index] = newChar;
        memory = new String(chars);
    }
}
