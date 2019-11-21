package info.victorchu.input;

import java.util.Arrays;

public class CharArraySequence implements CharSequence {
    private final char[] chars;

    public CharArraySequence(char[] chars) {
        this.chars = chars;
    }

    @Override
    public int length() {
        return chars.length;
    }

    @Override
    public char charAt(int index) {
        return chars[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        // to match conditions when super method throw exception
        if(start<0 || end <0){throw new IndexOutOfBoundsException("start or end must not negative!");}
        if(start>end){throw new IndexOutOfBoundsException("start must not greater than end !");}
        if(end >chars.length){ throw new IndexOutOfBoundsException("end must not greater than length() !");}

        return new CharArraySequence(Arrays.copyOfRange(chars,start,end));
    }
}
