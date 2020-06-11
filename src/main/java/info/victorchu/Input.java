package info.victorchu;


import java.util.List;

/**
 * parseFunction 输入.
 *
 * @param <IN> 输入元素类型
 * @author <a href="mailto:victorchu0610@outlook.com">victor.chu</a>
 * @version 1.0.0
 */
public interface Input<IN> {


    /**
     * return Input consisting of all elements except the first
     * @return
     */
    Input<IN> rest();

    /**
     * return Input consisting of all elements except the first n
     * @return
     */
    Input<IN> rest(int n);

    /**
     * return the first element
     * @return
     */
    IN first();

    /**
     * return the first n elements
     * @param n
     * @return
     */
    List<IN> first(int n);
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
