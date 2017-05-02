package converter;

import trait.HeaderXmlFunctionality;

import java.util.Map;

public interface HeaderConverter extends HeaderXmlFunctionality, CoreConverter {

    default String getFieldsValue(String[] fields, String fieldName) {
        return getFieldValueWithTrait(fields, fieldName);
    }
    
    default void fillHeaderMap(Map<String, String> map, String[] fields) {
        fillHeaderMapWithTrait(map, fields);
    }
}
