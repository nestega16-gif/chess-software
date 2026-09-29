package software.ulpgc.chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Board() {
    private final Map<Square, Piece> pieces;

    public Board(Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public static Board initial() {
        Map<Square, Piece> Initial = Map.ofEntries(
                Map.entry(at("a1"), WhiteRock),

        );
    }

    public Board move(Square from, Square to) {
        /// TODO -> crea nuevo board modificado
    }

    Piece pieceAt(File file, Rank rank) {
        return pieceAt(new Square(file, rank));
    }

    Piece pieceAt(String square) {
        /// TODO
        return null;
    }

    private Piece pieceAt(Square square) {
        return pieces.get(square);
    }
}
