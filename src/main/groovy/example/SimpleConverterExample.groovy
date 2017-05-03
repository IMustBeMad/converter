package example

import Source.Source
import converter.CoreConverter
import defaults.DefaultConverter
import groovy.xml.MarkupBuilder
import object.IsdResult
import object.ParseResult

class SimpleConverterExample extends DefaultConverter implements CoreConverter {

    @Override
    ParseResult parse() {
        return null
    }

    @Override
    IsdResult getIsdResult() {
        return new IsdResult()
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
