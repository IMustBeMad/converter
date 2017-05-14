package category

import exception.PipelineException
import source.Source

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

class StandardImpls {

    static Stream<String> toStream(File self, String charset = 'UTF-8', int skipCount = 0) {
        try {
            return Files.lines(Paths.get(self.getPath()), Charset.forName(charset)).skip(skipCount)
        } catch (IOException e) {
            throw new PipelineException('failed to create stream from file', e)
        }
    }

    static String[] toFields(String self, Closure splitMethod, String separator = ',') {
        return splitMethod(self, separator)
    }

    static boolean toPredicateWith(String[] self, List<Closure> conditions, boolean any = false) {
        if (!conditions) {
            return false
        }

        return any ? conditions.any { it(self) } : conditions.every { it(self) }
    }

    static boolean skipFilter(String[] self) {
        return true
    }

    static Source toSource(String[] self, Class source) {
        return source.metaClass.invokeConstructor(self) as Source
    }

    static Source toSource(List<String[]> fieldsList, Class source) {
        return source.metaClass.invokeConstructor(fieldsList) as Source
    }
}