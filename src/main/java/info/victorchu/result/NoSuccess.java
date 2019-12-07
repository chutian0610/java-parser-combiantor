package info.victorchu.result;

import info.victorchu.input.Input;
import info.victorchu.util.Printable;

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
     * no success result has no reply, so no need to call the function
     * and the unchecked type cast is safe
     * @param function
     * @param <U>
     * @return
     */
    @Override
    public <U> ParsedResult<I, U> map(Function<R, U> function) {
        return (NoSuccess<I, U>) this;
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
