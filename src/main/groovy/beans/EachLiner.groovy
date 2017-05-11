package beans

import category.StandardImpls
import config.FilterConfig
import exception.ParserException
import exception.PipelineException
import groovy.xml.MarkupBuilder

class EachLiner {

    static void createItems(File self, MarkupBuilder xml, FilterConfig config, Class sourceClass,
                            Closure<Void> creationMethod) {
        use(StandardImpls) {
            try {
                self.toStream('UTF-8', config.skipCount)
                    .map { it.toFields() }
                    .filter { config.conditions ? it.toPredicateWith(config.conditions) : it.toPredicateWith(true) }
                    .map { it.toSource(sourceClass) }
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

    static Closure createItemsBy(Closure self, Closure creationMethod) {
        return self(creationMethod)
    }
}
