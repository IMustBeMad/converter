package example

import category.StandardImpls
import converter.CoreConverter
import defaults.DefaultConverter
import exception.ParserException
import exception.PipelineException
import groovy.xml.MarkupBuilder
import groovy.xml.MarkupBuilderHelper
import object.ParseResult
import source.Source

class UnifiedConverterExample extends DefaultConverter implements CoreConverter {
    List<Closure> conditions = [
            { Source source -> source.fields[0] }
    ]

    List<Closure> exceptions = [
            { Source source -> source.fields[1] == 'exception' }
    ]

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
            try {
                file.toStream('UTF-8')
                    .map { it.toSource() }
                    .filter { conditions.toPredicate(it) }
                    .forEach { createItem(xml, it) }
            } catch (PipelineException e) {
                throw new ParserException(e)
            }
        }
    }

    @Override
    void createItem(MarkupBuilder xml, Source source) {
        use(StandardImpls) {
            if (source.isExceptionalBy(exceptions)) {
                throw new PipelineException("exception by logic")
            }
        }

        xml.ORDER {
            //fill order
        }
    }
}
