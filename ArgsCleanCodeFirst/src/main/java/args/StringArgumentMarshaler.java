package args;

public class StringArgumentMarshaler extends ArgumentMarshaler {

    private String stringValue;

    public StringArgumentMarshaler(String stringValue) {
        super();
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public void set(String value){
        stringValue = value;
    }
}
