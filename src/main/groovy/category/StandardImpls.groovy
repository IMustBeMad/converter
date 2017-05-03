package category

import Source.SimpleSource
import Source.Source

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.function.Predicate
import java.util.stream.Stream

class StandardImpls {

    static Source toSource(String line, String separator = ',') {
        new SimpleSource().with {
            it.setFields(line.split(separator))

            return it
        }
    }

    static Stream<String> toStream(File self, String charset = 'UTF-8') {
        try {
            return Files.lines(Paths.get(self.getPath()), Charset.forName(charset))
        } catch (IOException e) {
            return null
        }
    }

    static Stream<Source> filter(Stream<Source> self, List<Closure> predicates) {
        predicates.each { it -> self.filter(it as Predicate) }

        return self
    }

    static <T> Stream<T> throwExceptionOn(Stream<T> self, Closure condition, Exception exception) {
        if (self.anyMatch(condition as Predicate)) {
            throw exception
        }

        return self
    }

    static <T> Stream<T> throwRuntimeExceptionOn(Stream<T> self, Closure condition) {
        return throwExceptionOn(self, condition, new RuntimeException('test'))
    }
}