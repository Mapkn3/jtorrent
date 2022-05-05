package my.mapkn3.jbencode.model.item;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Map;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class DictionaryItem implements Item<Map<StringItem, Item<?>>> {
    public static final char PREFIX = 'd';

    private final Map<StringItem, Item<?>> item;

    @Override
    public Map<StringItem, Item<?>> getItem() {
        return item;
    }

    public Item<?> put(StringItem key, Item<?> value) {
        return item.put(key, value);
    }
}