package beans

import category.StandardImpls
import config.FilterConfig
import exception.ParserException
import exception.PipelineException
import groovy.xml.MarkupBuilder

import java.util.function.Function
import java.util.stream.Collectors

class GreedBlocker {

    static void createItems(File self, MarkupBuilder xml, FilterConfig config, Class sourceClass,
                            Closure<Void> creationMethod, Closure<String> groupingMethod) {
        use(StandardImpls) {
            try {
                self.toStream('UTF-8', config.skipCount)
                    .map { it.toFields() }
                    .filter { config.conditions ? it.toPredicateWith(config.conditions) : it.toPredicateWith(true) }
                    .collect(Collectors.groupingBy(
                        groupingMethod as Function,
                        Collectors.collectingAndThen(Collectors.toList(), { it.toSource(sourceClass) } as Function)
                    ))
                    .forEach { creationMethod(xml, it) }
            } catch (PipelineException e) {
                throw new ParserException(e)
            }
        }
    }

    static Closure withConfig(File self, MarkupBuilder xml, FilterConfig config) {
        return this.&createItems.curry(self, xml, config)
    }

    static Closure convertTo(Closure self, Class sourceClass) {
        return self.curry(sourceClass)
    }

    static Closure createItemsBy(Closure self, Closure creationMethod, Closure groupingMethod) {
        return self(creationMethod, groupingMethod)
    }
}
