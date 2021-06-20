package argsrefactor;

public abstract class BaseArg {
    private char vc;
    public BaseArg(char vc){
        this.vc = vc;
    }
    public abstract Object getValue();

    public char getVc() {
        return vc;
    }

    public void setVc(char vc) {
        this.vc = vc;
    }
}
