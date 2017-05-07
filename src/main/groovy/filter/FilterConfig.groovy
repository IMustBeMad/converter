package filter

import groovy.transform.Canonical

@Canonical
class FilterConfig {
    int skipCount = 0
    List<Closure> conditions = []
}
