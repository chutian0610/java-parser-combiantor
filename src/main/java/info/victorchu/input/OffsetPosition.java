package info.victorchu.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Supplier;

public class OffsetPosition implements Position {
    // threadlocal cache map
    private static ThreadLocal<Map<CharSequence, List<Integer>>> lineCacheTL =
            ThreadLocal.withInitial(new Supplier<Map<CharSequence, List<Integer>>>() {
                @Override
                public Map<CharSequence, List<Integer>> get() {
                    return new WeakHashMap<>();
                }
            });

    private final CharSequence charSequence;
    private final int offset;

    private List<Integer> getLineStarts() {
        if (lineCacheTL.get().containsKey(charSequence)) {
            return lineCacheTL.get().get(charSequence);
        } else {
            List<Integer> starts = generateLineStarts(charSequence);
            lineCacheTL.get().put(charSequence, starts);
            return starts;
        }
    }

    public OffsetPosition(CharSequence charSequence, int offset) {
        this.charSequence = charSequence;
        this.offset = offset;
    }

    /**
     * return line Number
     *
     * @return
     */
    @Override
    public int line() {
        int location = 0;
        List<Integer> indexs = getLineStarts();
        int end = indexs.size() - 1;
        while (location + 1 < end) {
            int middile = (end + location) / 2;
            if (offset < indexs.get(middile)) {
                end = middile;
            } else {
                location = middile;
            }
        }
        return location + 1;
    }

    /**
     * return column Number
     *
     * @return
     */
    @Override
    public int column() {
        List<Integer> indexs = getLineStarts();
        return offset - indexs.get(line() - 1) + 1;
    }

    public String lineContents() {
        List<Integer> indexs = getLineStarts();
        int line = line();
        int lineStart = indexs.get(line - 1);
        int lineEnd = indexs.get(line);
        int endIndex = 0;

        if (lineStart < lineEnd - 1
                && charSequence.charAt(lineEnd - 2) == '\r'
                && charSequence.charAt(lineEnd - 1) == '\n') {
            endIndex = lineEnd - 2;
        } else if (lineStart < lineEnd &&
                (charSequence.charAt(lineEnd - 1) == '\r' || charSequence.charAt(lineEnd - 1) == '\n')) {
            endIndex = lineEnd - 1;
        } else {
            endIndex = lineEnd;
        }
        return charSequence.subSequence(lineStart,lineEnd).toString();
    }

    /**
     * return position string
     *
     * @return
     */
    @Override
    public String toString() {
        return line() + "." + column();
    }

    /**
     * is this position before that one
     *
     * @param that
     * @return
     */
    @Override
    public boolean before(Position that) {
        if (that instanceof OffsetPosition) {
            return this.offset < ((OffsetPosition) that).offset;
        }
        return this.line() < that.line()
                || (this.line() == that.line() && this.column() < that.column());
    }


    /**
     * get index list of line start character
     * <p>
     * x0 ..........
     * x1 ..........
     * x2 ..........
     * x3
     *
     * @param charSequence
     * @return
     */
    public static List<Integer> generateLineStarts(CharSequence charSequence) {
        List<Integer> starts = new ArrayList<>();
        starts.add(0); // add first line start position
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (charSequence.charAt(i) == '\n'
                    || (charSequence.charAt(i) == '\r'
                    && (i == (charSequence.length() - 1) || charSequence.charAt(i + 1) != '\n')
            )) {
                starts.add(i + 1);
            }
        }
        starts.add(charSequence.length());
        return starts;
    }

}
