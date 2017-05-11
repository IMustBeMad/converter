package trait

import groovy.xml.MarkupBuilder
import groovy.xml.MarkupBuilderHelper


trait CommonXmlFunctionality {
    void toXml() {
        //
    }

    String toFredDate() {
        return 'fredDate'
    }

    String viaMarkupBuilder(Closure creationMethod, File file, boolean isd) {
        StringWriter writer = new StringWriter()
        MarkupBuilder xml = new MarkupBuilder(writer)
        MarkupBuilderHelper helper = new MarkupBuilderHelper(xml)
        helper.xmlDeclaration([version: '1.0', encoding: 'UTF-8'])

        xml.ORDER_FILE {
            creationMethod(xml, file, isd)
        }

        return writer.toString()
    }
}
