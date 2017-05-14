package config

import groovy.transform.Canonical
import groovy.xml.MarkupBuilder

@Canonical
class StreamConfig {
    MarkupBuilder xml
    List<Closure> filterConditions

    int skipCount = 0
    String charset = 'UTF-8'

    String separator = ','
    Closure<String[]> splitMethod = { String line, String separator -> line.split(separator) }
}
