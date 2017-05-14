package beans

import category.StandardImpls
import config.StreamConfig
import exception.ParserException
import exception.PipelineException

class EachLiner {

    static void createItems(File self, StreamConfig streamConfig, boolean isd, Class sourceClass,
                            Closure<Void> creationMethod) {
        use(StandardImpls) {
            try {
                self.toStream(streamConfig.charset, streamConfig.skipCount)
                    .map { it.toFields(streamConfig.splitMethod, streamConfig.separator) }
                    .filter { streamConfig.filterConditions ? it.toPredicateWith(streamConfig.filterConditions) : it.skipFilter() }
                    .map { it.toSource(sourceClass) }
                    .each { creationMethod(streamConfig.xml, it, isd) }
            } catch (PipelineException e) {
                throw new ParserException(e)
            }
        }
    }

    static Closure withStreamConfig(File self, StreamConfig streamConfig) {
        return this.&createItems.curry(self, streamConfig)
    }

    static Closure convertTo(Closure self, Class sourceClass, boolean isd = false) {
        return self.ncurry(1, sourceClass).ncurry(0, isd)
    }

    static Closure createItemsBy(Closure self, Closure creationMethod) {
        return self(creationMethod)
    }
}
