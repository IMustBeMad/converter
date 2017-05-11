package source

interface Source {
    String[] getFields()

    List<String[]> getBlock()

    boolean isExceptionalBy(List<Closure> closureList)

    boolean isExceptionalBy(Closure closure)
}
