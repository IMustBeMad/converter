package converter;

import java.io.File;
import java.util.List;

public interface SplitConverter extends CoreConverter {
    List<File> splitIntoFiles(File file);

    File createSeparateFile(String fileName);
}
