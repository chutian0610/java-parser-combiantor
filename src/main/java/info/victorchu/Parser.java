package info.victorchu;

import info.victorchu.input.Input;
import info.victorchu.result.ParsedResult;

/**
 * parser monad
 * @param <I>
 * @param <R>
 */
public abstract class Parser<I,R> {

    /**
     * parser name
     */
    protected String name = "";

    public String name(){
        return name;
    }

    public void named(String n){
        name = n;
    }

    /**
     * parse the given input and return ParsedResult,which contains
     *  1. parsed status
     *  2. message
     *  3. remained input
     *  4. parsed result R
     * @param input warped input, support get location and etc.
     * @return
     */
    public abstract ParsedResult<I,R> parse(Input<I> input);


}