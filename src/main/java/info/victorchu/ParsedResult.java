package info.victorchu;

import java.util.function.Function;

/**
 * parseFunction 输出.
 *
 * @param <IN> 输入类型
 * @param <OUT> 输出类型
 */
public interface ParsedResult<IN,OUT> {

    /**
     * return the input after parser parsed
     * @return
     */
    Input<IN> next();

    /**
     * true, if an successful apply action
     * @return
     */
    boolean successful();

    /**
     * return the embedded result
     * @return
     */
    OUT getReply();

    /**
     * use function to cast reply type from OUT to R
     * @param function
     * @param <R>
     * @return
     */
    <R> ParsedResult<IN,R> map(Function<ParsedResult<IN,OUT>,ParsedResult<IN,R>> function);
}
