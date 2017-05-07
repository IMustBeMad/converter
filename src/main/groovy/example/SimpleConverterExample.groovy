package example

import org.springframework.stereotype.Component
import source.Source
import converter.CoreConverter
import defaults.DefaultConverter
import groovy.xml.MarkupBuilder
import object.IsdResult
import object.ParseResult

@Component
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
