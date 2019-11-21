package info.victorchu.result;

import info.victorchu.input.Input;

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
    public String print() {
        return "["+ next.position()+"] parsed: "+ reply;
    }
}
