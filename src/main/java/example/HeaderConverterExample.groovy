package example

import Source.Source
import converter.HeaderConverter
import defaults.DefaultConverter
import groovy.xml.MarkupBuilder
import object.IsdResult
import object.LcResult
import object.ParseResult

class HeaderConverterExample extends DefaultConverter implements HeaderConverter {

    @Override
    ParseResult parse() {
        return null
    }

    @Override
    String getFieldsValue(String[] fields, String fieldName) {
        return super.getFieldsValue(fields, fieldName)
    }

    @Override
    void fillHeaderMap(Map<String, String> map, String[] fields) {
        super.fillHeaderMap(map, fields)
    }

    @Override
    LcResult getLcResult() {
        return super.getLcResult()
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
