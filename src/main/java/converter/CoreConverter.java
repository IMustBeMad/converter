package converter;

import source.Source;
import groovy.xml.MarkupBuilder;
import object.IsdResult;
import object.LcResult;
import trait.CommonXmlFunctionality;

import java.io.File;

public interface CoreConverter extends CommonXmlFunctionality {

    default LcResult getLcResult() {
        return null;
    }

    default IsdResult getIsdResult() {
        return null;
    }

    String createXml(File file);

    void createItems(File file, boolean isd);

    void createItem(MarkupBuilder xml, Source itemSource);
}
