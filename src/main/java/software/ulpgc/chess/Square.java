package software.ulpgc.chess;

public record Square(File file, Rank rank) {

    public static Square at(String square) {
        File file = toFile(square.charAt(0));
        Rank rank = toRank(square.charAt(1));
        return new Square(file, rank);
    }

    private static File toFile(char symbol) {
        return File.fromSymbol(symbol);
    }

    private static Rank toRank(int value) {
        return Rank.fromValue(value);
    }
}
