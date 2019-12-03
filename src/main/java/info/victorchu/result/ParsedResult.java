package info.victorchu.result;

import info.victorchu.input.Input;
import info.victorchu.util.Printable;

import java.util.function.Function;

public interface ParsedResult<I,R> extends Printable {

    /**
     * return the input after parser parsed
     * @return
     */
    Input<I> next();

    /**
     * true, if an successful parse action
     * @return
     */
    boolean successful();

    /**
     * return the embedded result
     * @return
     */
    R getReply();
}
