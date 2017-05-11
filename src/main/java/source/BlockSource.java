package source;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlockSource implements Source {
    private List<String[]> fieldsList;

    @Override
    public String[] getFields() {
        return fieldsList.get(0);
    }
}
