package converter

import groovy.xml.MarkupBuilder
import object.IsdResult
import object.LcResult
import source.Source
import trait.CommonXmlFunctionality

trait CoreConverter extends CommonXmlFunctionality {

    LcResult getLcResult() {
        return null
    }

    IsdResult getIsdResult() {
        return null
    }

    abstract String createXml(File file, boolean isd)

    abstract void createItems(MarkupBuilder xml, File file, boolean isd)

    abstract void createItem(MarkupBuilder xml, Source itemSource)
}
