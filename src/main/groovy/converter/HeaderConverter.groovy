package converter

import trait.HeaderXmlFunctionality

trait HeaderConverter implements HeaderXmlFunctionality, CoreConverter {

    String getFieldsValue(String[] fields, String fieldName) {
        return getFieldValueWithTrait(fields, fieldName)
    }
    
    void fillHeaderMap(Map<String, String> map, String[] fields) {
        fillHeaderMapWithTrait(map, fields)
    }
}
