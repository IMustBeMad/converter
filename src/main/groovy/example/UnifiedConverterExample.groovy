package example

import beans.GreedBlocker
import config.StreamConfig
import converter.CoreConverter
import defaults.DefaultConverter
import exception.PipelineException
import groovy.xml.MarkupBuilder
import object.ParseResult
import org.springframework.stereotype.Component
import source.BlockSource
import source.Source

@Component
class UnifiedConverterExample extends DefaultConverter implements CoreConverter {
    List<Closure> conditions = [
            { String[] fields -> fields[0] }
    ]

//    List<Closure> exceptions = [
//            { LineSource source -> source.fields[1] == 'exception' }
//    ]

    List<Closure> exceptions = [
            { BlockSource source -> source.block.any { it[0] == 'exception' } }
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
        StreamConfig streamConfig = new StreamConfig(xml: xml, filterConditions: conditions, separator: ',')

//        use(EachLiner) {
//            file.withStreamConfig(streamConfig)
//                .convertTo(LineSource.class, isd)
//                .createItemsBy(this.&createItem)
//        }

        use(GreedBlocker) {
            file.withStreamConfig(streamConfig)
                .groupWith(this.&groupingMethod)
                .convertTo(BlockSource.class, isd)
                .createItemsBy(this.&createItem)
        }
    }

    @Override
    void createItem(MarkupBuilder xml, Source source, boolean isd) {
        if (source.isExceptionalBy(exceptions)) {
            throw new PipelineException("exception by logic")
        }

//        String[] fields = source.fields
//
//        xml.ORDER {
//            NUMBER(fields[0])
//            TEXT(fields[1])
//        }

        source.block.each { fields ->
            xml.ORDER {
                NUMBER(fields[0])
                TEXT(fields[1])
            }
        }
    }

    private static String groupingMethod(String[] fields) {
        return fields[0]
    }
}
