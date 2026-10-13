package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import software.ulpgc.chess.adapters.FenBoardDeserializer;
import software.ulpgc.chess.domain.model.Board;
import software.ulpgc.chess.domain.model.File;
import software.ulpgc.chess.domain.model.Piece;
import software.ulpgc.chess.domain.model.Rank;
import software.ulpgc.chess.domain.model.Square;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para FenBoardDeserializer")
public class FenBoardDeserializerTest {

    private FenBoardDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = new FenBoardDeserializer();
    }

    private static Square square(File file, Rank rank) {
        return new Square(file, rank);
    }

    private static Square square(char fileChar, int rankNum) {
        File file = switch (Character.toLowerCase(fileChar)) {
            case 'a' -> File.A;
            case 'b' -> File.B;
            case 'c' -> File.C;
            case 'd' -> File.D;
            case 'e' -> File.E;
            case 'f' -> File.F;
            case 'g' -> File.G;
            case 'h' -> File.H;
            default -> throw new IllegalArgumentException("Columna inválida: " + fileChar);
        };
        Rank rank = switch (rankNum) {
            case 1 -> Rank.R1;
            case 2 -> Rank.R2;
            case 3 -> Rank.R3;
            case 4 -> Rank.R4;
            case 5 -> Rank.R5;
            case 6 -> Rank.R6;
            case 7 -> Rank.R7;
            case 8 -> Rank.R8;
            default -> throw new IllegalArgumentException("Fila inválida: " + rankNum);
        };
        return new Square(file, rank);
    }

    @Nested
    @DisplayName("Tableros vacíos y básicos")
    class BasicBoards {

        @Test
        @DisplayName("Debe deserializar un tablero completamente vacío (8/8/8/8/8/8/8/8)")
        void should_deserialize_empty_board() {
            String fen = "8/8/8/8/8/8/8/8";
            Board board = deserializer.deserialize(fen);

            assertNotNull(board, "El tablero deserializado no debe ser nulo");
            assertNotNull(board.pieces(), "El mapa de piezas no debe ser nulo");
            assertTrue(board.pieces().isEmpty(), "Un tablero vacío no debe tener piezas");
        }

        @Test
        @DisplayName("Debe deserializar un tablero con una sola pieza en una casilla específica")
        void should_deserialize_single_piece() {
            // Rey blanco en e1
            String fen = "8/8/8/8/8/8/8/4K3";
            Board board = deserializer.deserialize(fen);

            assertNotNull(board);
            assertEquals(1, board.pieces().size(), "Debe haber exactamente 1 pieza en el tablero");
            assertEquals(Piece.WhiteKing, board.pieces().get(square(File.E, Rank.R1)));
        }

        @Test
        @DisplayName("Debe posicionar correctamente en las cuatro esquinas (a8, h8, a1, h1)")
        void should_correctly_place_pieces_in_corners() {
            String fen = "r6r/8/8/8/8/8/8/R6R";
            Board board = deserializer.deserialize(fen);

            assertNotNull(board);
            assertEquals(4, board.pieces().size());

            assertEquals(Piece.BlackRook, board.pieces().get(square('a', 8)));
            assertEquals(Piece.BlackRook, board.pieces().get(square('h', 8)));
            assertEquals(Piece.WhiteRook, board.pieces().get(square('a', 1)));
            assertEquals(Piece.WhiteRook, board.pieces().get(square('h', 1)));
        }
    }

    @Nested
    @DisplayName("Posición inicial estándar")
    class InitialPosition {

        private static final String INITIAL_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        private static final String FULL_INITIAL_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

        @Test
        @DisplayName("Debe deserializar la posición inicial con exactamente 32 piezas")
        void should_deserialize_initial_board_piece_count() {
            Board board = deserializer.deserialize(INITIAL_FEN);

            assertNotNull(board);
            assertEquals(32, board.pieces().size(), "El tablero inicial debe contener 32 piezas");
        }

        @Test
        @DisplayName("Debe colocar correctamente las piezas mayores blancas en la fila 1")
        void should_deserialize_white_major_pieces() {
            Board board = deserializer.deserialize(INITIAL_FEN);

            assertNotNull(board);
            Map<Square, Piece> pieces = board.pieces();

            assertEquals(Piece.WhiteRook, pieces.get(square('a', 1)));
            assertEquals(Piece.WhiteKnight, pieces.get(square('b', 1)));
            assertEquals(Piece.WhiteBishop, pieces.get(square('c', 1)));
            assertEquals(Piece.WhiteQueen, pieces.get(square('d', 1)));
            assertEquals(Piece.WhiteKing, pieces.get(square('e', 1)));
            assertEquals(Piece.WhiteBishop, pieces.get(square('f', 1)));
            assertEquals(Piece.WhiteKnight, pieces.get(square('g', 1)));
            assertEquals(Piece.WhiteRook, pieces.get(square('h', 1)));
        }

        @Test
        @DisplayName("Debe colocar correctamente los peones blancos en la fila 2")
        void should_deserialize_white_pawns() {
            Board board = deserializer.deserialize(INITIAL_FEN);

            assertNotNull(board);
            Map<Square, Piece> pieces = board.pieces();

            for (char file = 'a'; file <= 'h'; file++) {
                assertEquals(Piece.WhitePawn, pieces.get(square(file, 2)),
                        "Debe haber un peón blanco en " + file + "2");
            }
        }

        @Test
        @DisplayName("Debe colocar correctamente las piezas mayores negras en la fila 8")
        void should_deserialize_black_major_pieces() {
            Board board = deserializer.deserialize(INITIAL_FEN);

            assertNotNull(board);
            Map<Square, Piece> pieces = board.pieces();

            assertEquals(Piece.BlackRook, pieces.get(square('a', 8)));
            assertEquals(Piece.BlackKnight, pieces.get(square('b', 8)));
            assertEquals(Piece.BlackBishop, pieces.get(square('c', 8)));
            assertEquals(Piece.BlackQueen, pieces.get(square('d', 8)));
            assertEquals(Piece.BlackKing, pieces.get(square('e', 8)));
            assertEquals(Piece.BlackBishop, pieces.get(square('f', 8)));
            assertEquals(Piece.BlackKnight, pieces.get(square('g', 8)));
            assertEquals(Piece.BlackRook, pieces.get(square('h', 8)));
        }

        @Test
        @DisplayName("Debe colocar correctamente los peones negros en la fila 7")
        void should_deserialize_black_pawns() {
            Board board = deserializer.deserialize(INITIAL_FEN);

            assertNotNull(board);
            Map<Square, Piece> pieces = board.pieces();

            for (char file = 'a'; file <= 'h'; file++) {
                assertEquals(Piece.BlackPawn, pieces.get(square(file, 7)),
                        "Debe haber un peón negro en " + file + "7");
            }
        }

        @Test
        @DisplayName("Debe asegurar que las filas intermedias (3, 4, 5, 6) no contengan piezas")
        void should_have_empty_intermediate_ranks() {
            Board board = deserializer.deserialize(INITIAL_FEN);

            assertNotNull(board);
            Map<Square, Piece> pieces = board.pieces();

            for (int rank = 3; rank <= 6; rank++) {
                for (char file = 'a'; file <= 'h'; file++) {
                    assertNull(pieces.get(square(file, rank)),
                            "La casilla " + file + rank + " debe estar vacía");
                }
            }
        }

        @Test
        @DisplayName("Debe deserializar correctamente un FEN completo con campos adicionales (turno, enroque, etc.)")
        void should_deserialize_full_fen_string_with_extra_fields() {
            Board board = deserializer.deserialize(FULL_INITIAL_FEN);

            assertNotNull(board);
            assertEquals(32, board.pieces().size());
            assertEquals(Piece.WhiteKing, board.pieces().get(square('e', 1)));
            assertEquals(Piece.BlackKing, board.pieces().get(square('e', 8)));
        }
    }

    @Nested
    @DisplayName("Identificación y mapeo de cada tipo de pieza")
    class PieceTypeMapping {

        @ParameterizedTest(name = "Símbolo FEN ''{0}'' debe mapear a la pieza {1}")
        @CsvSource({
                "P, WhitePawn",
                "R, WhiteRook",
                "N, WhiteKnight",
                "B, WhiteBishop",
                "Q, WhiteQueen",
                "K, WhiteKing",
                "p, BlackPawn",
                "r, BlackRook",
                "n, BlackKnight",
                "b, BlackBishop",
                "q, BlackQueen",
                "k, BlackKing"
        })
        @DisplayName("Debe mapear correctamente cada símbolo de pieza individual en FEN")
        void should_map_each_piece_symbol_correctly(char symbol, Piece expectedPiece) {
            String fen = symbol + "7/8/8/8/8/8/8/8";
            Board board = deserializer.deserialize(fen);

            assertNotNull(board);
            assertEquals(1, board.pieces().size());
            assertEquals(expectedPiece, board.pieces().get(square('a', 8)));
        }

        @Test
        @DisplayName("Debe deserializar una fila que contiene todos los tipos de piezas blancas")
        void should_deserialize_all_white_piece_types() {
            String fen = "8/8/8/8/8/8/8/PRNBQK2";
            Board board = deserializer.deserialize(fen);

            assertNotNull(board);
            assertEquals(6, board.pieces().size());
            assertEquals(Piece.WhitePawn, board.pieces().get(square('a', 1)));
            assertEquals(Piece.WhiteRook, board.pieces().get(square('b', 1)));
            assertEquals(Piece.WhiteKnight, board.pieces().get(square('c', 1)));
            assertEquals(Piece.WhiteBishop, board.pieces().get(square('d', 1)));
            assertEquals(Piece.WhiteQueen, board.pieces().get(square('e', 1)));
            assertEquals(Piece.WhiteKing, board.pieces().get(square('f', 1)));
        }

        @Test
        @DisplayName("Debe deserializar una fila que contiene todos los tipos de piezas negras")
        void should_deserialize_all_black_piece_types() {
            String fen = "prnbqk2/8/8/8/8/8/8/8";
            Board board = deserializer.deserialize(fen);

            assertNotNull(board);
            assertEquals(6, board.pieces().size());
            assertEquals(Piece.BlackPawn, board.pieces().get(square('a', 8)));
            assertEquals(Piece.BlackRook, board.pieces().get(square('b', 8)));
            assertEquals(Piece.BlackKnight, board.pieces().get(square('c', 8)));
            assertEquals(Piece.BlackBishop, board.pieces().get(square('d', 8)));
            assertEquals(Piece.BlackQueen, board.pieces().get(square('e', 8)));
            assertEquals(Piece.BlackKing, board.pieces().get(square('f', 8)));
        }
    }

    @Nested
    @DisplayName("Posiciones de medio juego y casillas consecutivas")
    class MidgameAndComplexPositions {

        @Test
        @DisplayName("Debe deserializar posición tras la jugada 1. e4 (Defensa Siciliana u otras)")
        void should_deserialize_position_after_e4() {
            // Posición tras 1. e4
            String fen = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR";
            Board board = deserializer.deserialize(fen);

            assertNotNull(board);
            assertEquals(32, board.pieces().size());

            // e4 tiene un peón blanco y e2 está vacío
            assertEquals(Piece.WhitePawn, board.pieces().get(square('e', 4)));
            assertNull(board.pieces().get(square('e', 2)));
        }

        @Test
        @DisplayName("Debe deserializar correctamente una posición compleja de medio juego con espacios vacíos intercalados")
        void should_deserialize_complex_midgame_position() {
            // Posición: 1. e4 c5 2. Nf3 d6 3. d4 cxd4 4. Nxd4
            String fen = "rnbqkbnr/pp2pppp/3p4/8/3NP3/8/PPP2PPP/RNBQKB1R";
            Board board = deserializer.deserialize(fen);

            assertNotNull(board);

            // d4 tiene caballo blanco
            assertEquals(Piece.WhiteKnight, board.pieces().get(square('d', 4)));
            // e4 tiene peón blanco
            assertEquals(Piece.WhitePawn, board.pieces().get(square('e', 4)));
            // d6 tiene peón negro
            assertEquals(Piece.BlackPawn, board.pieces().get(square('d', 6)));
            // c5 está vacío
            assertNull(board.pieces().get(square('c', 5)));
            // g1 está vacío (el caballo se movió)
            assertNull(board.pieces().get(square('g', 1)));
        }

        @Test
        @DisplayName("Debe manejar casillas vacías consecutivas en distintas filas correctamente")
        void should_handle_consecutive_empty_squares() {
            // FEN con números alternados: "1p1p1p1p/p1p1p1p1/8/8/8/8/1P1P1P1P/P1P1P1P1"
            String fen = "1p1p1p1p/p1p1p1p1/8/8/8/8/1P1P1P1P/P1P1P1P1";
            Board board = deserializer.deserialize(fen);

            assertNotNull(board);
            assertEquals(16, board.pieces().size());

            // Fila 8: casillas pares tienen peones negros
            assertNull(board.pieces().get(square('a', 8)));
            assertEquals(Piece.BlackPawn, board.pieces().get(square('b', 8)));
            assertNull(board.pieces().get(square('c', 8)));
            assertEquals(Piece.BlackPawn, board.pieces().get(square('d', 8)));

            // Fila 1: casillas impares tienen peones blancos
            assertEquals(Piece.WhitePawn, board.pieces().get(square('a', 1)));
            assertNull(board.pieces().get(square('b', 1)));
            assertEquals(Piece.WhitePawn, board.pieces().get(square('c', 1)));
            assertNull(board.pieces().get(square('d', 1)));
        }
    }
}
