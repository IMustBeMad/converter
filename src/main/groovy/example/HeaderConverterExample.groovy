package example

import source.Source
import converter.HeaderConverter
import defaults.DefaultConverter
import groovy.xml.MarkupBuilder
import object.ParseResult

class HeaderConverterExample extends DefaultConverter implements HeaderConverter {

    @Override
    ParseResult parse() {
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
