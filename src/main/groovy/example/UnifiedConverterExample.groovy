package example

import category.StandardImpls
import converter.CoreConverter
import defaults.DefaultConverter
import groovy.xml.MarkupBuilder
import groovy.xml.MarkupBuilderHelper
import object.ParseResult
import source.Source

import java.util.stream.Collectors

class UnifiedConverterExample extends DefaultConverter implements CoreConverter {
    List<Closure> conditions = [
            { Source source -> source.getFields()[0] }
    ]

    Closure exceptionClosure = { String[] line -> line[1] == 'exception' }

    @Override
    ParseResult parse() {
        return null
    }

    @Override
    String createXml(File file, boolean isd) {
        StringWriter writer = new StringWriter()
        MarkupBuilder xml = new MarkupBuilder(writer)
        MarkupBuilderHelper helper = new MarkupBuilderHelper(xml)
        helper.xmlDeclaration([version: '1.0', encoding: 'UTF-8'])

        xml.ORDER_FILE {
            createItems(xml, file, isd)
        }

        return writer.toString()
    }

    //todo to make three implementations (eachLiner, lazyBlocker, blocker)
    @Override
    void createItems(MarkupBuilder xml, File file, boolean isd) {

        use(StandardImpls) {
            file.toStream('UTF-8')
                .map { it.toSource() }
                .filter { conditions.toPredicate(it) }
                .collect(Collectors.toList())
//                .throwRuntimeExceptionOn(exceptionClosure)
//                .forEach({
//                    createItem(xml, it)
//                })
        }
    }

    @Override
    void createItem(MarkupBuilder xml, Source itemSource) {

    }
}
