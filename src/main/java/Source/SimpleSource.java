package Source;

public class SimpleSource implements Source {
    private String[] fields;

    @Override
    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }
}
