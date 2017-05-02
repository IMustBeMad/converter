package Source;

import lombok.Setter;

@Setter
public class SimpleSource implements Source {
    private String[] fields;

    @Override
    public String[] getFields() {
        return fields;
    }
}
