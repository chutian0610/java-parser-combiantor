package info.victorchu.result;

import info.victorchu.input.Input;

public class Failure<I,R> extends NoSuccess<I,R> {

    public Failure(Input<I> next, String message) {
        super(next, message);
    }

    @Override
    public String toString() {
        return "["+next.position()+"] failure: "
                +message+"\n\n"+next.position().visualString();
    }
}
