package info.victorchu.input;


import info.victorchu.util.Printable;

public interface Position extends Printable {
    /**
     * return line of position
     * @return
     */
    int line();

    /**
     * return column of position
     * @return
     */
    int column();

    /**
     * is this position before that one
     * @param that
     * @return
     */
    default boolean before(Position that) {
        return this.line() < that.line()
                || (this.line() == that.line() && this.column() < that.column());
    }

    /**
     * The contents of the line at this position. (must not contain a new-line character)
      * @return
     */
    String lineContents();

    /**
     * returns a more ``visual'' representation of this position.
     * the resulting string consists of two lines:
     *    1. the line in the document referred to by this position
     *    2. a caret indicating the column
     * Example:
     *
     *  List(this, is, a, line, from, the, document)
     *                 ^
     * @return
     */
    default String visualString(){
        String lineContents = lineContents();
        String secondLineContents = lineContents.substring(0,column()-1);
        char[] chars = secondLineContents.toCharArray();
        for (int i =0;i<chars.length;i++) {
            if(chars[i] != '\t'){
                chars[i] = ' ';
            }
        }
        return lineContents+"\n"+new String(chars)+"^";
    }
}
