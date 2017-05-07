package beans

import category.StandardImpls
import exception.ParserException
import exception.PipelineException
import filter.FilterConfig
import groovy.xml.MarkupBuilder

@Category(File)
class EachLiner {

    void createItems(MarkupBuilder xml, Closure creationMethod, FilterConfig config) {
        use(StandardImpls) {
            try {
                this.toStream('UTF-8', config.skipCount)
                    .map { it.toSource() }
                    .filter { config.conditions ? it.toPredicateWith(config.conditions) : it.toPredicateWith(true) }
                    .forEach { creationMethod(xml, it) }
            } catch (PipelineException e) {
                throw new ParserException(e)
            }
        }
    }
}
