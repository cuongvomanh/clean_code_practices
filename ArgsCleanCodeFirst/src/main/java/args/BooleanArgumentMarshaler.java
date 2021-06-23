package args;

public class BooleanArgumentMarshaler extends ArgumentMarshaler {
    private boolean booleanValue;

    public BooleanArgumentMarshaler(boolean booleanValue) {
        super();
        this.booleanValue = booleanValue;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    @Override
    public void set(String value){
        setBooleanValue(true);
    }
}
