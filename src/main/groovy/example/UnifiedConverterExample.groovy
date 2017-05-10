package example

import beans.GreedBlocker
import category.StandardImpls
import config.FilterConfig
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
        return viaMarkupBuilder(this.&createItems, file, isd)
    }

    //todo to make three implementations (eachLiner, lazyBlocker, blocker)
    @Override
    void createItems(MarkupBuilder xml, File file, boolean isd) {
        FilterConfig filterConfig = new FilterConfig(conditions: conditions, skipCount: 0)

//        use(EachLiner) {
//            file.withConfig(xml, filterConfig)
//                .call(this.&createItem)
//        }

        use(GreedBlocker) {
            file.withConfig(xml, filterConfig)
                .call(this.&createItem, this.&groupingMethod)
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

    private static String groupingMethod(Source source) {
        return source.getFields()[1]
    }
}
