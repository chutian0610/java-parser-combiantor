package info.victorchu.input;


import java.util.List;

public interface Input<I> {


    /**
     * return Input consisting of all elements except the first
     * @return
     */
    Input<I> rest();

    /**
     * return Input consisting of all elements except the first n
     * @return
     */
    Input<I> rest(int n);

    /**
     * return the first element
     * @return
     */
    I first();

    /**
     * return the first n elements
     * @param n
     * @return
     */
    List<I> first(int n);
    /**
     * position of the first element in Input
     * @return
     */
    Position position();

    /**
     * true,if no more elements found
     * @return
     */
    boolean atEnd();
}
