package info.victorchu;

import info.victorchu.input.Input;
import info.victorchu.result.ParsedResult;

/**
 * construct parser with name for easy debugging
 * @param <I>
 * @param <R>
 */
public class NamedParser<I,R> implements Parser<I,R>{
    public static <I,R> Parser<I,R> named(String name,Parser<I,R> anonymousParser){
        return new NamedParser<>(name,anonymousParser);
    }

    private NamedParser(String name,Parser<I,R> parser){
        this.name = name;
        this.embeddedParser = parser;
    }

    private String name;
    private Parser<I,R> embeddedParser;
    public String getName(){
        return name;
    }

    @Override
    public ParsedResult<I, R> apply(Input<I> input) {
        return embeddedParser.apply(input);
    }
}
