package my.mapkn3.jbencode.model;

import java.util.Arrays;

public class BencodeFile {
    private final byte[] data;
    private final int length;
    private int position;

    public BencodeFile(byte[] data) {
        this.data = data;
        this.length = data.length;
        this.position = -1;
    }

    public boolean hasNext(char symbol) {
        int next = position + 1;
        if (next >= length) {
            return false;
        }

        return data[next] == symbol;
    }

    public boolean hasNextNumber() {
        int next = position + 1;
        if (next >= length) {
            return false;
        }

        return Character.isDigit(data[next]);
    }

    public String next(int count) {
        int next = position + count;
        if (next >= length) {
            throw new IllegalStateException();
        }

        String value = new String(Arrays.copyOfRange(data, position + 1, next + 1));
        position = next;
        return value;
    }

    public String next(char symbol) {
        if (!hasNext(symbol)) {
            throw new IllegalStateException();
        }

        return next(1);
    }

    public int nextNumber() {
        return Integer.parseInt(next(1));
    }
}