package info.victorchu.result;

import info.victorchu.input.Input;

import java.util.function.Function;

public interface ParsedResult<I,R> {

    /**
     * return the input after parser parsed
     * @return
     */
    Input<I> next();

    /**
     * true, if an successful apply action
     * @return
     */
    boolean successful();

    /**
     * return the embedded result
     * @return
     */
    R getReply();

    /**
     * use function to cast reply type from R to U
     * @param function
     * @param <U>
     * @return
     */
    <U> ParsedResult<I,U> map(Function<ParsedResult<I,R>,ParsedResult<I,U>> function);
}
