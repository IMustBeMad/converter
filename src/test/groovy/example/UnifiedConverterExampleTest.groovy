package example

import groovy.xml.MarkupBuilder


class UnifiedConverterExampleTest extends GroovyTestCase {

    void testCreateItems() {
        def example = new UnifiedConverterExample()
        example.createItems(new MarkupBuilder(), new File('src/test/resources/test.txt'), false)
    }
}
