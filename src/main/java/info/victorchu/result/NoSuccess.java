package info.victorchu.result;

import info.victorchu.input.Input;

import java.util.function.Function;

public abstract class NoSuccess<I,R> implements ParsedResult<I,R>  {
    protected final Input<I> next;
    protected final String message;
    public NoSuccess(Input<I> next, String message) {
        this.next = next;
        this.message = message;
    }

    @Override
    public Input<I> next() {
        return next;
    }

    @Override
    public boolean successful() {
        return false;
    }

    @Override
    public R getReply() {
        throw new IllegalStateException("No result when parsing failed!");
    }

    /**
     * for fast cast type
     * @param <U>
     * @return
     */
    public <U>  ParsedResult<I, U> cast() {
        return (NoSuccess<I, U>)this;
    }


}
