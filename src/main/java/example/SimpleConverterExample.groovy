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
