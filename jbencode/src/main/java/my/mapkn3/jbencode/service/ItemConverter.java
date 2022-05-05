package my.mapkn3.jbencode.service;

import my.mapkn3.jbencode.model.item.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ItemConverter {
    public static int convert(NumberItem numberItem) {
        return numberItem.getItem();
    }

    public static String convert(StringItem stringItem) {
        return stringItem.getItem();
    }

    public static List<Object> convert(ListItem listItem) {
        List<Object> result = new LinkedList<>();

        for (Item<?> item : listItem.getItem()) {
            result.add(convert(item));
        }

        return result;
    }

    public static Map<String, Object> convert(DictionaryItem dictionaryItem) {
        Map<String, Object> result = new HashMap<>();

        for (Map.Entry<StringItem, Item<?>> item : dictionaryItem.getItem().entrySet()) {
            result.put(convert(item.getKey()), convert(item.getValue()));
        }

        return result;
    }

    private static Object convert(Item<?> item) {
        Object result = null;

        if (item instanceof StringItem) {
            result = convert((StringItem) item);
        }
        if (item instanceof NumberItem) {
            result = convert((NumberItem) item);
        }
        if (item instanceof ListItem) {
            result = convert((ListItem) item);
        }
        if (item instanceof DictionaryItem) {
            result = convert((DictionaryItem) item);
        }

        return result;
    }
}