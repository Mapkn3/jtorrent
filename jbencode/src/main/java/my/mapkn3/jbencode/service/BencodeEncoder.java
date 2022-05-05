package my.mapkn3.jbencode.service;

import my.mapkn3.jbencode.model.item.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BencodeEncoder {
    public String encode(Item<?> item) {
        return encodeNext(item);
    }

    private String encodeNext(Item<?> item) {
        String result = "";
        if (item instanceof StringItem) {
            result = encodeString((StringItem) item);
        }
        if (item instanceof NumberItem) {
            result = encodeNumber((NumberItem) item);
        }
        if (item instanceof ListItem) {
            result = encodeList((ListItem) item);
        }
        if (item instanceof DictionaryItem) {
            result = encodeDictionary((DictionaryItem) item);
        }
        return result;
    }

    private String encodeString(StringItem stringItem) {
        String value = stringItem.getItem();
        return value.length() + ":" + value;
    }

    private String encodeNumber(NumberItem numberItem) {
        int value = numberItem.getItem();
        return "i" + value + "e";
    }

    private String encodeList(ListItem listItem) {
        List<Item<?>> value = listItem.getItem();
        StringBuilder stringBuilder = new StringBuilder("l");
        for (Item<?> item : value) {
            stringBuilder.append(encodeNext(item));
        }
        stringBuilder.append("e");
        return stringBuilder.toString();
    }

    private String encodeDictionary(DictionaryItem dictionaryItem) {
        Map<StringItem, Item<?>> value = dictionaryItem.getItem();
        StringBuilder stringBuilder = new StringBuilder("d");
        for (Map.Entry<StringItem, Item<?>> item : value.entrySet()) {
            stringBuilder.append(encodeString(item.getKey()));
            stringBuilder.append(encodeNext(item.getValue()));
        }
        stringBuilder.append("e");
        return stringBuilder.toString();
    }
}