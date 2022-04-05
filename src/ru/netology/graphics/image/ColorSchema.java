package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema {
    private char[] s;

    public ColorSchema(char c1, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        char[] s = new char[]{c1, c2, c3, c4, c5, c6, c7, c8};
        this.s = s;
    }

    @Override

    public char convert(int color) {
        char c = this.s[(int) (Math.ceil((color + 1) / (256 / s.length)))];
        return c;
    }
}
