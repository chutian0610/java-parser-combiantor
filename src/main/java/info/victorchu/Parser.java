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
     * parse the given input and return ParsedResult,which contains
     *  1. parsed status
     *  2. message
     *  3. remained input
     *  4. parsed result R
     * @param input warped input, support get location and etc.
     * @return
     */
    ParsedResult<I,R> parse(Input<I> input);

    /**
     * named parser
     * @param name
     * @return
     */
    default Parser<I,R> named(String name){
        return NamedParser.named(name,this);
    }

    /**
     * map function
     * if parser succeed, apply function to result.
     * @param parser
     * @param function
     * @param <U>
     * @return
     */
    default  <U> Parser<I,U> map(Parser<I,R> parser, Function<R,U> function){
        return input -> parser.parse(input).map(function);
    }

    // ======================== stream api style =========================
    default <U> Parser<I,U> bind(Function<R,Parser<I,U>> function){
        return Combinators.bind(this,function);
    }

}