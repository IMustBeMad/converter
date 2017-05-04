package category

import source.SimpleSource
import source.Source

import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.function.Predicate
import java.util.stream.Stream

class StandardImpls {

    static Stream<String> toStream(File self, String charset = 'UTF-8') {
        try {
            return Files.lines(Paths.get(self.getPath()), Charset.forName(charset))
        } catch (IOException e) {
            return null
        }
    }

    static Source toSource(String self, String separator = ',') {
        return new SimpleSource().with {
            it.setFields(self.split(separator))

            return it
        }
    }

    //todo list of closures to one predicate
    static Predicate toPredicate(List<Closure> self) {
        return self.&every as Predicate
    }


    //todo to handle exceptions
//    static Stream<Source> filter(Stream<Source> self, List<Closure> predicates) {
//        predicates.each { it -> self.filter(it as Predicate) }
//
//        return self
//    }

//    static <T> Stream<T> throwExceptionOn(Stream<T> self, Closure condition, Exception exception) {
//        if (self.anyMatch(condition as Predicate)) {
//            throw exception
//        }
//
//        return self
//    }
//
//    static <T> Stream<T> throwRuntimeExceptionOn(Stream<T> self, Closure condition) {
//        return throwExceptionOn(self, condition, new RuntimeException('test'))
//    }
}