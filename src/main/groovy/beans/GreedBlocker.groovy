package beans

import category.StandardImpls
import exception.ParserException
import exception.PipelineException
import filter.FilterConfig
import groovy.xml.MarkupBuilder

import java.util.function.Function
import java.util.stream.Collectors

@Category(File)
class GreedBlocker {

    void createItems(MarkupBuilder xml, Closure creationMethod, Closure groupMethod, FilterConfig config) {
        use(StandardImpls) {
            try {
                this.toStream('UTF-8', config.skipCount)
                    .map { it.toSource() }
                    .filter { config.conditions ? it.toPredicateWith(config.conditions) : it.toPredicateWith(true) }
                    .collect(Collectors.groupingBy(groupMethod as Function))
                    .forEach { creationMethod(xml, it) }
            } catch (PipelineException e) {
                throw new ParserException(e)
            }
        }
    }
}
