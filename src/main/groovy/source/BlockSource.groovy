package source

import groovy.transform.Canonical

@Canonical
class BlockSource implements Source {
    List<String[]> block

    @Override
    String[] getFields() {
        return null
    }

    @Override
    boolean isExceptionalBy(List<Closure> closureList) {
        return false
    }

    @Override
    boolean isExceptionalBy(Closure closure) {
        return false
    }
}
