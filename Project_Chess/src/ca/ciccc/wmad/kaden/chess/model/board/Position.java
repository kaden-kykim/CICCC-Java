package ca.ciccc.wmad.kaden.chess.model.board;

import java.util.Arrays;

public enum Position {

    A8(0), B8(1), C8(2), D8(3), E8(4), F8(5), G8(6), H8(7),
    A7(10), B7(11), C7(12), D7(13), E7(14), F7(15), G7(16), H7(17),
    A6(20), B6(21), C6(22), D6(23), E6(24), F6(25), G6(26), H6(27),
    A5(30), B5(31), C5(32), D5(33), E5(34), F5(35), G5(36), H5(37),
    A4(40), B4(41), C4(42), D4(43), E4(44), F4(45), G4(46), H4(47),
    A3(50), B3(51), C3(52), D3(53), E3(54), F3(55), G3(56), H3(57),
    A2(60), B2(61), C2(62), D2(63), E2(64), F2(65), G2(66), H2(67),
    A1(70), B1(71), C1(72), D1(73), E1(74), F1(75), G1(76), H1(77);

    public static final int ROW_INDEX = 0, COL_INDEX = 1;

    private int id;

    Position(int id) {
        this.id = id;
    }

    public static Position convertInputToPosition(String inputString) throws IllegalArgumentException {
        if (inputString.charAt(0) >= '0' && inputString.charAt(0) <= '8') {
            inputString = "" + inputString.charAt(1) + inputString.charAt(0);
        }
        return Position.valueOf(inputString.toUpperCase());
    }

    public static Position convertIndexesToPosition(int[] indexes) throws IllegalArgumentException {
        return Position.valueOf("" + (indexes[COL_INDEX] + 'A') + (8 - indexes[ROW_INDEX] + '0'));
    }

    public static Position convertIndexesToPosition(int row, int col) throws IllegalArgumentException {
        return Position.valueOf("" + (col + 'A') + (8 - row + '0'));
    }

    public Position getPositionByDirection(Direction direction) throws IllegalArgumentException {
        int move = 0;
        String directName = direction.name();
        if (directName.contains("E")) {
            move += 1;
        }
        if (directName.contains("W")) {
            move -= 1;
        }
        if (directName.contains("N")) {
            move -= 10;
        }
        if (directName.contains("S")) {
            move += 10;
        }

        return getPositionById(id + move);
    }

    public int[] convertPositionToIndexes() {
        return new int[]{id / 10, id % 10};
    }

    private Position getPositionById(int id) throws IllegalArgumentException {
        return convertIndexesToPosition(id / 10, id % 10);
    }
}
