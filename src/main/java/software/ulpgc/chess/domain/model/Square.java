package software.ulpgc.chess.domain.model;

public record Square(File file, Rank rank) {

    public static Square at(String square) {
        return new Square(toFile(square.charAt(0)), toRank(square.charAt(1)));
    }

    public static Square of(int i, int j) {
        return new Square(File.values()[j], Rank.values()[i]);
    }

    private static File toFile(char symbol) {
        return File.fromSymbol(symbol);
    }

    private static Rank toRank(int value) {
        return Rank.fromValue(value);
    }
}
