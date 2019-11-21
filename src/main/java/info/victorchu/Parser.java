package info.victorchu;

import info.victorchu.input.Input;
import info.victorchu.result.ParsedResult;

@FunctionalInterface
public interface Parser<I,R> {

    /**
     * parse the given input and return ParsedResult,which contains
     *  1. parsed status
     *  2. message
     *  3. remained input
     *  4. parsed result R
     * @param input warped input, support get location and etc.
     * @return
     */
    ParsedResult<I,R> parse(Input<I> input);

}
