package example

import Source.Source
import defaults.SplitConverter
import groovy.xml.MarkupBuilder
import object.IsdResult


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
    IsdResult getIsdResult() {
        return super.getIsdResult()
    }

    @Override
    String createXml(File file) {
        return null
    }

    @Override
    void createItems(MarkupBuilder xml, boolean isd) {

    }

    @Override
    void createItem(MarkupBuilder xml, Source itemSource) {

    }
}
