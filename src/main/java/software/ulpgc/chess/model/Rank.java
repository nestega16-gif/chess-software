package software.ulpgc.chess.model;

public enum Rank {
    R1(1),R2(2),R3(3),R4(4),R5(5),R6(6),R7(7),R8(8);


    private final int value;
    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static Rank fromValue(int targetValue) {
        for (Rank rank : Rank.values()) {
            if (rank.getValue() == targetValue) {
                return rank;
            }
        }
        return null;
    }
}

