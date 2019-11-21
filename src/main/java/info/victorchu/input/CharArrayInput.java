package info.victorchu.input;

public class CharArrayInput extends CharSequenceInput {

    public CharArrayInput(char[] chars, int index) {
        super(new CharArraySequence(chars), index);
    }
}
