package my.mapkn3.jbencode.model.item;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class ListItem implements Item<List<Item<?>>> {
    public static final char PREFIX = 'l';

    private final List<Item<?>> item;

    @Override
    public List<Item<?>> getItem() {
        return item;
    }

    public boolean add(Item<?> value) {
        return item.add(value);
    }
}