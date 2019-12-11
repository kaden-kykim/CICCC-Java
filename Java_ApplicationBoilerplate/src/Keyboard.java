import java.util.Scanner;

public class Keyboard {

    Scanner scanner = new Scanner(System.in);

    // Reads a boolean value from the user
    public boolean nextBoolean() {
        return scanner.nextBoolean();
    }

    // Reads a byte value from the user
    public byte nextByte() {
        return scanner.nextByte();
    }

    // Reads a double value from the user
    public double nextDouble() {
        return scanner.nextDouble();
    }

    // Reads a float value from the user
    public float nextFloat() {
        return scanner.nextFloat();
    }

    // Reads an integer value from the user
    public int nextInt() {
        return scanner.nextInt();
    }

    // Reads a String value from the user
    public String nextLine() {
        return scanner.nextLine();
    }

    // Reads a long value from the user
    public long nextLong() {
        return scanner.nextLong();
    }

    // Reads a short value from the user
    public short nextShort() {
        return scanner.nextShort();
    }

    // returns if there is input waiting
    public boolean hasNext() {
        return scanner.hasNext();
    }
}
