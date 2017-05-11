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

    static String[] toFields(String self, String separator = ',') {
        return self.split(separator)
    }

    static boolean toPredicateWith(String[] self, List<Closure> conditions, boolean any = false) {
        return any ? conditions.any { it(self) } : conditions.every { it(self) }
    }

    static boolean toPredicateWith(String[] self, boolean skip) {
        return skip
    }

    static Source toSource(String[] self, Class source) {
        return source.metaClass.invokeConstructor(self) as Source
    }

    static Source toSource(List<String[]> fieldsList, Class source) {
        return source.metaClass.invokeConstructor(fieldsList) as Source
    }

//    static boolean toPredicateWith(Source self, List<Closure> conditions, boolean any = false) {
//        return any ? conditions.any { it(self) } : conditions.every { it(self) }
//    }
//
//    static boolean toPredicateWith(Source self, boolean skip) {
//        return skip
//    }

//    static boolean isExceptionalBy(String[] self, List<Closure> exceptions, boolean any = true) {
//        return any ? exceptions.any { it(self) } : exceptions.every { it(self) }
//    }

//    static boolean isExceptionalBy(Source self, List<Closure> exceptions, boolean any = true) {
//        return any ? self.any { source -> exceptions.any { it(source) } }
//                   : self.any { source -> exceptions.every { it(source) } }
//    }

    static boolean isExceptionalBy(Source self, List<Closure> exceptions, boolean any = true) {
        return any ? exceptions.any { it(self) } : exceptions.every { it(self) }
    }
//
//    static boolean isExceptionalBy(List<Source> self, List<Closure> exceptions, boolean any = true) {
//        return any ? self.any { source -> exceptions.any { it(source) } }
//                   : self.any { source -> exceptions.every { it(source) } }
//    }
}