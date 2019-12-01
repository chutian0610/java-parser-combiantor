package info.victorchu;

import info.victorchu.result.Success;

import java.util.function.Function;

/**
 * a collection of parser combinator functions
 */
public abstract class Combinators {

    /**
     * return combinator
     * succeeds immediately without consuming any input, and return supplied value.
     * @param x
     * @param <I>
     * @param <R>
     * @return
     */
    public static <I,R> Parser<I,R> retn(R x){
        return ((Parser<I, R>) input -> new Success<>(input, x)).named("return -> "+x);
    }

    public static <I,R,U> Parser<I,U> bind(Parser<I,R> parser, Function<R,Parser<I,U>> function){

    }

}
