package software.ulpgc.chess.domain.model;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import static software.ulpgc.chess.domain.model.Color.*;

public enum Piece {
    WhitePawn,
    WhiteRook,
    WhiteQueen,
    WhiteKnight,
    WhiteBishop,
    WhiteKing,
    BlackPawn,
    BlackRook,
    BlackQueen,
    BlackKnight,
    BlackBishop,
    BlackKing;


    public static Piece of(char piece) {
        return switch (piece) {
            case 'P' -> WhitePawn;
            case 'R' -> WhiteRook;
            case 'Q' -> WhiteQueen;
            case 'K' -> WhiteKing;
            case 'B' -> WhiteBishop;
            case 'N' -> WhiteKnight;
            case 'p' -> BlackPawn;
            case 'r' -> BlackRook;
            case 'q' -> BlackQueen;
            case 'k' -> BlackKing;
            case 'b' -> BlackBishop;
            case 'n' -> BlackKnight;
            default -> null;
        };
    }

    Color color() {
        return isWhite() ? White : Black ;
    }

    boolean isPawn() {
        return this == WhitePawn || this == BlackPawn;
    }

    boolean isRook() {
        return this == WhiteRook || this == BlackRook;
    }

    boolean isKnight() {
        return this == WhiteKnight || this == BlackKnight;
    }

    boolean isBishop() {
        return this == WhiteBishop || this == BlackBishop;
    }

    boolean isQueen() {
        return this == WhiteQueen || this == BlackQueen;
    }

    boolean isKing() {
        return this == WhiteKing || this == BlackKing;
    }

    private static final Set<Piece> WHITE_PIECES = Collections.unmodifiableSet(EnumSet.of(WhitePawn, WhiteBishop, WhiteRook, WhiteKing, WhiteQueen, WhiteKnight));
    private boolean isWhite() {
        return WHITE_PIECES.contains(this);
    }
}
