package software.ulpgc.chess.domain.model;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import static software.ulpgc.chess.domain.model.Color.*;

public enum Piece {
    WhitePawn('P'),
    WhiteRook('R'),
    WhiteQueen('Q'),
    WhiteKnight('N'),
    WhiteBishop('B'),
    WhiteKing('K'),
    BlackPawn('p'),
    BlackRook('r'),
    BlackQueen('q'),
    BlackKnight('n'),
    BlackBishop('b'),
    BlackKing('k');

    private final char symbol;

    Piece(char symbol) { this.symbol = symbol; }

    public char getSymbol() { return symbol; }

    public static Piece fromSymbol(char symbol) {
        return EnumSet.allOf(Piece.class).stream().filter(p -> p.symbol == symbol).findFirst().orElse(null);
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
