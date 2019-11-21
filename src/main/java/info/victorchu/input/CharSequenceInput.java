package info.victorchu.input;

import java.util.ArrayList;
import java.util.List;

public class CharSequenceInput implements CharInput {
    protected final CharSequence charSequence;
    protected final int index;

    public CharSequenceInput(CharSequence charSequence, int index) {
        this.charSequence = charSequence;
        this.index = index;
    }

    @Override
    public Input<Character> rest() {
        if(index<charSequence.length()){
            return new CharSequenceInput(charSequence,index+1);
        }else {
            return this;
        }
    }

    @Override
    public Input<Character> rest(int n) {
        return new CharSequenceInput(charSequence,Math.min(index+n,charSequence.length()));
    }

    @Override
    public Character first() {
        return charSequence.charAt(index);
    }

    @Override
    public List<Character> first(int n) {
        int count = 0;
        List<Character> characters = new ArrayList<>();
        while (count<n){
            int cursor = index + count;
            if(cursor<charSequence.length()){
                characters.add(charSequence.charAt(cursor));
            }else {
                break;
            }
            count++;
        }
        return characters;
    }

    @Override
    public Position position() {
        return new OffsetPosition(charSequence, index);
    }

    @Override
    public boolean atEnd() {
        return index>=charSequence.length();
    }
}
