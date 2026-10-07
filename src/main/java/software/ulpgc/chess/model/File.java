package software.ulpgc.chess.model;

public enum File {
    A('a'), B('b'), C('c'), D('d'), E('e'), F('f'), G('g'), H('h');

    private final char symbol;
    File(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static File fromSymbol(char targetSymbol) {
        for (File file : File.values()) {
            if (file.getSymbol() == targetSymbol) {
                return file;
            }
        }
        return null;
    }
}
