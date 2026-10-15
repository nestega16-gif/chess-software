package software.ulpgc.chess.adapters;

import software.ulpgc.chess.domain.model.Board;
import software.ulpgc.chess.domain.model.Piece;
import software.ulpgc.chess.domain.model.Rank;
import software.ulpgc.chess.domain.model.Square;
import software.ulpgc.chess.ports.BoardDeserializer;

import java.util.HashMap;
import java.util.Map;

public class FenBoardDeserializer implements BoardDeserializer {
    /// arriba-abajo -> izquierda derecha minúscula = pieza negra; mayúscula=pieza blanca
    @Override
    public Board deserialize(String value) {
        return deserialize(value.split("/"));
    }

    private Board deserialize(String[] boardPieces) {
        Map<Square, Piece> piecesAtBoard = new HashMap<>();
        int rankValue = 8;
        for (String rowPieces : boardPieces)
            placeRank(piecesAtBoard, rowPieces, --rankValue);
        return new Board(piecesAtBoard);
    }

    private void placeRank(Map<Square, Piece> piecesAtBoard, String rowPieces, int rankValue) {
        int fileValue = 0;
        for(char character: rowPieces.toCharArray()) {
            if(Character.isDigit(character)) fileValue += Character.getNumericValue(character);
            else placePiece(piecesAtBoard, character, rankValue, fileValue++);
        }
    }

    private void placePiece(Map<Square, Piece> piecesAtBoard, char piece, int rankValue, int fileValue) {
        piecesAtBoard.put(Square.of(rankValue,fileValue), Piece.of(piece));
    }


}
