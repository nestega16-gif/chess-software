package software.ulpgc.chess;

public class Rank {
    private final int value;

    private Rank(int value){
        this.value = value;
    }

    public static final Rank R1 = new Rank(1);

}
