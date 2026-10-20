package software.ulpgc.chess.domain.game;

import java.util.Arrays;

public enum CastlingRights {
    WhiteKingSide('K'),
    WhiteQueenSide('Q'),
    BlackKingSide('k'),
    BlackQueenSide('q');

    private final char symbol;

    CastlingRights(char symbol) { this.symbol = symbol; }

    public char getSymbol() { return symbol; }

    public static CastlingRights fromSymbol(char symbol) {
        return Arrays.stream(CastlingRights.values()).filter(r -> r.symbol == symbol).findFirst().orElse(null);
    }

}
