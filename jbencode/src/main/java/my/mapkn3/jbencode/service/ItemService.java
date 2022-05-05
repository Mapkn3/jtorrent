package my.mapkn3.jbencode.service;

import my.mapkn3.jbencode.model.item.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ItemService {
    public static Item<?> get(DictionaryItem dictionaryItem, String key) {
        return dictionaryItem.getItem().get(new StringItem(key));
    }
}