package example


class UnifiedConverterExampleTest extends GroovyTestCase {

    void testCreateItems() {
        def example = new UnifiedConverterExample()
        example.createItems(new File('test.txt'), false)
    }
}
