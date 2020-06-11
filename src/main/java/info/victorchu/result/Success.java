package info.victorchu.result;

import info.victorchu.ParsedResult;
import info.victorchu.Input;
import java.util.function.Function;

public class Success<I,R> implements ParsedResult<I,R> {
    private final Input<I> next;
    private final R reply;
    public Success(Input<I> next, R reply) {
        this.next = next;
        this.reply = reply;
    }

    @Override
    public Input<I> next() {
        return next;
    }

    @Override
    public boolean successful() {
        return true;
    }

    @Override
    public R getReply() {
        return reply;
    }

    @Override
    public <U> ParsedResult<I, U> map(Function<ParsedResult<I,R>, ParsedResult<I,U>> function) {
        return new Success<>(this.next,function.apply(this).getReply());
    }

    @Override
    public String toString() {
        return "["+ next.position()+"] parsed: "+ reply;
    }
}
