package info.victorchu;

import info.victorchu.input.Input;
import info.victorchu.result.ParsedResult;

import java.util.function.Function;

/**
 * parser monad
 * @param <I>
 * @param <R>
 */
@FunctionalInterface
public interface Parser<I,R> {

    /**
     * apply the given input and return ParsedResult,which contains
     *  1. parsed status
     *  2. message
     *  3. remained input
     *  4. parsed result R
     * @param input warped input, support get location and etc.
     * @return
     */
    ParsedResult<I,R> apply(Input<I> input);

    /**
     * named parser
     * @param name
     * @return
     */
    default Parser<I,R> named(String name){
        return NamedParser.named(name,this);
    }

}