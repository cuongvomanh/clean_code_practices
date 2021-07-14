
public class StringB implements StringBInteface {
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public StringBInteface append(String str) {
        stringBuilder.append(str);
        return this;
    }
}
