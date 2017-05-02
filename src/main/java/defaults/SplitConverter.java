package defaults;

import java.io.File;
import java.util.List;

public abstract class SplitConverter {
    public abstract List<File> split(File file);
}
