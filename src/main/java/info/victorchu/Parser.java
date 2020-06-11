package info.victorchu;

/**
 * 表示一个parse monad
 * <pre>
 *    parser = IN -> Result(IN,OUT)
 * </pre>
 *
 * @param <IN>  输入类型
 * @param <OUT>  输出类型
 * @author <a href="mailto:victorchu0610@outlook.com">victor.chu</a>
 * @version 1.0.0
 */
@FunctionalInterface
public interface Parser<IN,OUT> {

    /**
     * parse monad 的主要逻辑
     * @param input 输入
     * @return
     */
    ParsedResult<IN,OUT> apply(Input<IN> input);
}