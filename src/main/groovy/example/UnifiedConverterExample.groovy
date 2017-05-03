package example

import source.Source
import category.StandardImpls
import converter.CoreConverter
import defaults.DefaultConverter
import groovy.xml.MarkupBuilder
import groovy.xml.MarkupBuilderHelper
import object.ParseResult

class UnifiedConverterExample extends DefaultConverter implements CoreConverter {
    List<Closure> conditions = [
            { String[] line -> line[0] }
    ]

    Closure exceptionClosure = { String[] line -> line[1] == 'exception' }

    @Override
    ParseResult parse() {
        return null
    }

    @Override
    String createXml(File file) {
        return null
    }

    @Override
    void createItems(File file, boolean isd) {
        StringWriter writer = new StringWriter()
        MarkupBuilder xml = new MarkupBuilder(writer)
        MarkupBuilderHelper helper = new MarkupBuilderHelper(xml)
        helper.xmlDeclaration([version: '1.0', encoding: 'UTF-8'])

        use(StandardImpls) {
            file.toStream('UTF-8')
                .map(this.&toSource)
                .filter(conditions)
                .throwRuntimeExceptionOn(exceptionClosure)
                .forEach({
                    createItem(xml, it)
                    writer.toString()
                })
        }
    }

    @Override
    void createItem(MarkupBuilder xml, Source itemSource) {

    }
}
