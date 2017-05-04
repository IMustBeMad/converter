package converter

interface SplitConverter extends CoreConverter {
    List<File> splitIntoFiles(File file)

    File createSeparateFile(String fileName)
}
