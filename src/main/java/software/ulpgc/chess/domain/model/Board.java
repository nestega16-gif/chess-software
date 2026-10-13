package software.ulpgc.chess.domain.model;

import java.util.HashMap;
import java.util.Map;

import static software.ulpgc.chess.domain.model.Piece.*;
import static software.ulpgc.chess.domain.model.Square.at;

public record Board(Map<Square, Piece> pieces) {

    public Board(Map<Square, Piece> pieces) {
        this.pieces = Map.copyOf(pieces);
    }

    public static Board initialBoard() {
        return new Board(initialMap());
    }

    private static Map<Square, Piece> initialMap() {
        return Map.ofEntries(
                Map.entry(at("a1"), WhiteRook),
                Map.entry(at("b1"), WhiteKnight),
                Map.entry(at("c1"), WhiteBishop),
                Map.entry(at("d1"), WhiteQueen),
                Map.entry(at("e1"), WhiteKing),
                Map.entry(at("f1"), WhiteBishop),
                Map.entry(at("g1"), WhiteKnight),
                Map.entry(at("h1"), WhiteRook),
                Map.entry(at("a2"), WhitePawn),
                Map.entry(at("b2"), WhitePawn),
                Map.entry(at("c2"), WhitePawn),
                Map.entry(at("d2"), WhitePawn),
                Map.entry(at("e2"), WhitePawn),
                Map.entry(at("f2"), WhitePawn),
                Map.entry(at("g2"), WhitePawn),
                Map.entry(at("h2"), WhitePawn),
                Map.entry(at("a7"), BlackPawn),
                Map.entry(at("b7"), BlackPawn),
                Map.entry(at("c7"), BlackPawn),
                Map.entry(at("d7"), BlackPawn),
                Map.entry(at("e7"), BlackPawn),
                Map.entry(at("f7"), BlackPawn),
                Map.entry(at("g7"), BlackPawn),
                Map.entry(at("h7"), BlackPawn),
                Map.entry(at("a8"), BlackRook),
                Map.entry(at("b8"), BlackKnight),
                Map.entry(at("c8"), BlackBishop),
                Map.entry(at("d8"), BlackQueen),
                Map.entry(at("e8"), BlackKing),
                Map.entry(at("f8"), BlackBishop),
                Map.entry(at("g8"), BlackKnight),
                Map.entry(at("h8"), BlackRook)
        );
    }

    Piece pieceAt(File file, Rank rank) {
        return pieceAt(new Square(file, rank));
    }

    Piece pieceAt(String square) {
        return pieceAt(at(square));
    }

    Piece pieceAt(Square square) {
        return pieces.get(square);
    }

    public Board move(Square from, Square to) {
        Map<Square, Piece> updatedPieces = new HashMap<>(pieces);
        Piece piece = updatedPieces.remove(from);
        updatedPieces.put(to, piece);
        return new Board(updatedPieces);
    }

    public Board move(String from, String to) {
        return move(at(from), at(to));
    }
}
