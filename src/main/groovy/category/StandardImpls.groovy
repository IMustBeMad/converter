package category

import exception.PipelineException
import source.SimpleSource
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

    static Source toSource(String self, String separator = ',') {
        return new SimpleSource().with {
            it.setFields(self.split(separator))

            return it
        }
    }

    static boolean toPredicate(List<Closure> self, Source source) {
        return self.every { it(source) }
    }

    static boolean isExceptionalBy(Source self, List<Closure> exceptions) {
        return exceptions.any { it(self) }
    }
}