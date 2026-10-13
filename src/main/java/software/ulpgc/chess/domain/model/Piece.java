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
        switch (piece) {
            case 'P': return WhitePawn;
            case 'R': return WhiteRook;
            case 'Q': return WhiteQueen;
            case 'K': return WhiteKing;
            case 'B': return WhiteBishop;
            case 'N': return WhiteKnight;
            case 'p': return BlackPawn;
            case 'r': return BlackRook;
            case 'q': return BlackQueen;
            case 'k': return BlackKing;
            case 'b': return BlackBishop;
            case 'n': return BlackKnight;
        }
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
