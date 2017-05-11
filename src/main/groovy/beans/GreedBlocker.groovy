package beans

import category.StandardImpls
import exception.ParserException
import exception.PipelineException
import config.FilterConfig
import groovy.xml.MarkupBuilder

import java.util.function.Function
import java.util.stream.Collectors

class GreedBlocker {

    static void createItems(File self, MarkupBuilder xml, FilterConfig config, Closure creationMethod, Closure groupMethod) {
        use(StandardImpls) {
            try {
                self.toStream('UTF-8', config.skipCount)
                    .map { it.toSimpleSource() }
                    .filter { config.conditions ? it.toPredicateWith(config.conditions) : it.toPredicateWith(true) }
                    .collect(Collectors.groupingBy(groupMethod as Function))
                    .forEach { creationMethod(xml, it) }
            } catch (PipelineException e) {
                throw new ParserException(e)
            }
        }
    }

    static Closure withConfig(File self, MarkupBuilder xml, FilterConfig config) {
        return this.&createItems.curry(self, xml, config)
    }
}
