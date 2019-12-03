package info.victorchu;

import info.victorchu.result.NoSuccess;
import info.victorchu.result.ParsedResult;
import info.victorchu.result.Success;

import java.util.function.Function;

/**
 * a collection of parser combinator functions
 */
public abstract class Combinators {

    /**
     * return combinator
     * succeeds immediately without consuming any input, and return supplied value.
     * @param x      supplied value
     * @param <I>    input type
     * @param <R>    result type
     * @return
     */
    public static <I,R> Parser<I,R> retn(R x){
        return ((Parser<I, R>) input -> new Success<>(input, x)).named("return");
    }


    /**
     * bind combinator
     * if first parser p succeed, the result is determinded by the second parser in bind function
     * otherwise, return the result of parser p
     * @param parser    first parser p
     * @param function  bind function
     * @param <I>       input type
     * @param <R>       result type of first parser p
     * @param <U>       result type of second parser
     * @return
     */
    public static <I,R,U> Parser<I,U> bind(Parser<I,R> parser, Function<R,Parser<I,U>> function){
        return ((Parser<I, U>)input -> {
            ParsedResult<I,R> result = parser.parse(input);
            if(result.successful()){ // first parser succeed
                return function.apply(result.getReply()).parse(result.next());
            }else {
                return ((NoSuccess) result); // return first error
            }
        }).named("bind");
    }

}
