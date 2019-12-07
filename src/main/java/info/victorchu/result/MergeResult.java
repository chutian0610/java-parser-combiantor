package info.victorchu.result;

public class MergeResult<R,U> {
    protected R r;
    protected U u;
    public MergeResult(R r, U u) {
        this.r = r;
        this.u = u;
    }

    @Override
    public String toString() {
        return "merge<"+r+","+u+">";
    }
}
