package software.ulpgc.chess.domain.game;

import software.ulpgc.chess.domain.model.Square;

public record EnPassantRights(EnPassantStatus status, Square square) {

}
