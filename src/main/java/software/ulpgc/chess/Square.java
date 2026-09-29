package software.ulpgc.chess;

public record Square(File file, Rank rank) {
    ///Factory method.
    public static Square from(String square) {
        return new Square();
    }

    /// Factory method at


}
