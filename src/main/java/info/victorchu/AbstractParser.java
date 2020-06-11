package info.victorchu;

/**
 * parser 的抽象类,扩展了 lambda形式parser的功能.
 *
 * 1. 强制增加名称
 *
 * @param <IN> 输入类型
 * @param <OUT> 输出类型
 */
public abstract class AbstractParser<IN,OUT> implements Parser<IN,OUT> {

    private static final String PREFIX = "Parser(";
    private static final String STUFFIX = ")";
    public static final String ANONYMITY = "Anonymity";

    public String getName() {
        return name;
    }

    /**
     * 名称是immutable 不可变属性.默认名称是匿名
     */
    private String name= ANONYMITY;

    /**
     * AbstractParser 工厂方法
     * @param lambda parser 逻辑
     * @param name   parser 名字
     * @param <IN>   输入类型
     * @param <OUT>  输出类型
     * @return AbstractParser
     */
    public static <IN,OUT> AbstractParser<IN,OUT> ofParser( Parser<IN,OUT> lambda,String name){
        return new AbstractParser<IN, OUT>(name) {
            @Override
            public ParsedResult<IN, OUT> apply(Input<IN> input) {
                return lambda.apply(input);
            }
        };
    }

    private AbstractParser(String name) {
        this.name = name;
    }

    /**
     * 复写toString方法.输出parser name
     * @return
     */
    @Override
    public String toString() {
        return PREFIX+name+STUFFIX;
    }
}
