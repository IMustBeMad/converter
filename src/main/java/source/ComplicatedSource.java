package source;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplicatedSource extends LineSource {
    private String measurement;
    private String unitMeasurement;
    private String cbm;
    private String ldm;
}
