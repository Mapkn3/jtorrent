package my.mapkn3.jbencode.model.item;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class StringItem implements Item<String> {
    private final String item;

    public String getItem() {
        return item;
    }
}