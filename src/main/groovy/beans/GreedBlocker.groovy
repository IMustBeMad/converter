package beans

import category.StandardImpls
import config.StreamConfig
import exception.ParserException
import exception.PipelineException

import java.util.function.Function
import java.util.stream.Collectors

class GreedBlocker {

    static void createItems(File self, StreamConfig streamConfig, boolean isd, Class sourceClass,
                            Closure<String> groupingMethod, Closure<Void> creationMethod) {
        use(StandardImpls) {
            try {
                self.toStream(streamConfig.charset, streamConfig.skipCount)
                    .map { it.toFields(streamConfig.splitMethod, streamConfig.separator) }
                    .filter { streamConfig.filterConditions ? it.toPredicateWith(streamConfig.filterConditions) : it.skipFilter() }
                    .collect(Collectors.groupingBy(
                        groupingMethod as Function,
                        Collectors.collectingAndThen(Collectors.toList(), { list -> list.toSource(sourceClass) } as Function)
                    ))
                    .each { entry -> creationMethod(streamConfig.xml, entry.value, isd) }
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

    static Closure groupWith(Closure self, Closure groupingMethod) {
        return self.ncurry(2, groupingMethod)
    }

    static Closure createItemsBy(Closure self, Closure creationMethod) {
        return self(creationMethod)
    }
}
