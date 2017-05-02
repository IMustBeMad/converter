package object;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class IsdResult {
    private File isdFile;
    private String autoProcess;
}
