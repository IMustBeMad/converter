package example

import org.springframework.stereotype.Component
import source.Source
import defaults.SplitConverter
import groovy.xml.MarkupBuilder

@Component
class SplitConverterExample extends SplitConverter implements converter.SplitConverter {

    @Override
    List<File> split(File file) {
        return null
    }

    @Override
    List<File> splitIntoFiles(File file) {
        return null
    }

    @Override
    File createSeparateFile(String fileName) {
        return null
    }

    @Override
    String createXml(File file, boolean isd) {
        return null
    }

    @Override
    void createItems(MarkupBuilder xml, File file, boolean isd) {

    }

    @Override
    void createItem(MarkupBuilder xml, Source itemSource) {

    }
}
