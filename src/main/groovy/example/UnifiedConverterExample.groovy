package example

import beans.EachLiner
import category.StandardImpls
import converter.CoreConverter
import defaults.DefaultConverter
import exception.PipelineException
import groovy.xml.MarkupBuilder
import object.ParseResult
import org.springframework.stereotype.Component
import source.Source

@Component
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
        viaMarkupBuilder(this.&createItems, file, isd)
    }

    //todo to make three implementations (eachLiner, lazyBlocker, blocker)
    @Override
    void createItems(MarkupBuilder xml, File file, boolean isd) {
        use(EachLiner) {
            file.createItems(xml, this.&createItem, conditions)
        }
    }

    @Override
    void createItem(MarkupBuilder xml, Source source) {
        use(StandardImpls) {
            if (source.isExceptionalBy(exceptions)) {
                throw new PipelineException("exception by logic")
            }
        }
        String[] fields = source.getFields()

        xml.ORDER {
            NUMBER(fields[0])
            TEXT(fields[1])
        }
    }
}
