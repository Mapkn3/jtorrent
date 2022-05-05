package my.mapkn3.jbencode.model.item;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class NumberItem implements Item<Integer> {
    public static final char PREFIX = 'i';

    private final Integer item;

    @Override
    public Integer getItem() {
        return item;
    }
}