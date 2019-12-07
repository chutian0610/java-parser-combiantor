package info.victorchu;

import info.victorchu.result.MergeResult;
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
     * map function
     * if parser succeed, apply function to result.
     * @param parser
     * @param function
     * @param <I>
     * @param <U>
     * @param <R>
     * @return
     */
    public static <I,U,R> Parser<I,U> map(Parser<I,R> parser, Function<R,U> function){
        return input -> parser.parse(input).map(new Function<ParsedResult<I, R>, ParsedResult<I, U>>() {
            @Override
            public ParsedResult<I, U> apply(ParsedResult<I, R> irParsedResult) {
                if(irParsedResult.successful()){
                    return new Success<>(irParsedResult.next(),function.apply(irParsedResult.getReply()));
                }
                return ((NoSuccess) irParsedResult);
            }
        });
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
                return ((NoSuccess) result).cast(); // return first error
            }
        }).named("bind");
    }

    /**
     * seq combinator
     * seq succeeds if p succeeds and q succeeds on the input left over by p.
     * @param p
     * @param q
     * @param <I>
     * @param <R>
     * @param <U>
     * @return
     */
    public static <I,R,U> Parser<I,MergeResult<R,U>> seq(Parser<I,R> p,Parser<I,U> q){
        return null;
    }

}
