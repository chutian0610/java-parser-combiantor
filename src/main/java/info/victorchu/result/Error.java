package info.victorchu.result;

import info.victorchu.input.Input;

public class Error<I,R> extends NoSuccess<I,R> {
    public Error(Input<I> next, String message) {
        super(next, message);
    }

    @Override
    public String toString() {
        return "["+next.position()+"] error: "
                +message+"\n\n"+next.position().visualString();
    }
}
