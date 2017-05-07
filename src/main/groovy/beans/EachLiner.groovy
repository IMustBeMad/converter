package beans

import category.StandardImpls
import exception.ParserException
import exception.PipelineException
import groovy.xml.MarkupBuilder
import org.springframework.stereotype.Component

@Component
@Category(File)
class EachLiner {

    void createItems(MarkupBuilder xml, Closure creationMethod, List<Closure> conditions = [], int skipCount = 0) {
        use(StandardImpls) {
            try {
                this.toStream('UTF-8', skipCount)
                    .map { it.toSource() }
                    .filter { it.toPredicateWith(conditions) }
                    .forEach { creationMethod(xml, it) }
            } catch (PipelineException e) {
                throw new ParserException(e)
            }
        }
    }
}
