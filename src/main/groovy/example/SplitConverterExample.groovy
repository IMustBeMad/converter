package example

import Source.Source
import defaults.SplitConverter
import groovy.xml.MarkupBuilder

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
    String createXml(File file) {
        return null
    }

    @Override
    void createItems(File file, boolean isd) {

    }

    @Override
    void createItem(MarkupBuilder xml, Source itemSource) {

    }
}
