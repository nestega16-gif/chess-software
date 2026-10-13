package software.ulpgc.chess.model;

import java.util.Arrays;

public enum File {
    A('a'), B('b'), C('c'), D('d'), E('e'), F('f'), G('g'), H('h');

    private final char symbol;
    File(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    static File fromSymbol(char targetSymbol) {
        return Arrays.stream(File.values()).filter(file -> file.getSymbol() == targetSymbol).findFirst().orElse(null);
    }
}
