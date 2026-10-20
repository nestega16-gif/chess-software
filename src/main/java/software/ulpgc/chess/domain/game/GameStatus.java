package software.ulpgc.chess.domain.game;

import software.ulpgc.chess.domain.model.Color;

public record GameStatus(Color activeColor, int halfmoveClock, int Turn, CastlingRights castlingRights, EnPassantStatus enPassantStatus) {

}
