package source

import groovy.transform.Canonical

@Canonical
class LineSource implements Source {
    String[] fields

    @Override
    List<String[]> getBlock() {
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
