package my.mapkn3.jbencode.service;

import my.mapkn3.jbencode.model.*;
import my.mapkn3.jbencode.model.item.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class BencodeDecoder {
    private int indent = 0;

    public Item<?> decode(File torrentFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(torrentFile)) {
            BencodeFile bencodeFileData = new BencodeFile(fis.readAllBytes());
            return decodeNext(bencodeFileData);
        }
    }

    private Item<?> decodeNext(BencodeFile bencodeFile) {
        Item<?> result = null;
        if (bencodeFile.hasNextNumber()) {
            result = decodeString(bencodeFile);
        }
        if (bencodeFile.hasNext(NumberItem.PREFIX)) {
            result = decodeInteger(bencodeFile);
        }
        if (bencodeFile.hasNext(ListItem.PREFIX)) {
            result = decodeList(bencodeFile);
        }
        if (bencodeFile.hasNext(DictionaryItem.PREFIX)) {
            result = decodeDictionary(bencodeFile);
        }
        return result;
    }

    private StringItem decodeString(BencodeFile bencodeFile) {
        int stringLength = 0;
        while (bencodeFile.hasNextNumber()) {
            stringLength = stringLength * 10 + bencodeFile.nextNumber();
        }
        bencodeFile.next(':');
        String value = bencodeFile.next(stringLength);
        log("Read string: " + value);
        return new StringItem(value);
    }

    private NumberItem decodeInteger(BencodeFile bencodeFile) {
        bencodeFile.next('i');
        int sign = 1;
        if (bencodeFile.hasNext('-')) {
            bencodeFile.next('-');
            sign = -1;
        }
        int value = 0;
        while (bencodeFile.hasNextNumber()) {
            value = value * 10 + bencodeFile.nextNumber();
        }
        value *= sign;
        bencodeFile.next('e');
        log("Read int: " + value);
        return new NumberItem(value);
    }

    private ListItem decodeList(BencodeFile bencodeFile) {
        log("Begin list");
        indent++;
        bencodeFile.next('l');
        ListItem result = new ListItem(new LinkedList<>());
        while (!bencodeFile.hasNext('e')) {
            Item<?> value = decodeNext(bencodeFile);
            result.add(value);
        }
        bencodeFile.next('e');
        indent--;
        log("End list");
        return result;
    }

    private DictionaryItem decodeDictionary(BencodeFile bencodeFile) {
        log("Begin dictionary");
        indent++;
        bencodeFile.next('d');
        DictionaryItem result = new DictionaryItem(new TreeMap<>(Comparator.comparing(StringItem::getItem)));
        while (!bencodeFile.hasNext('e')) {
            StringItem key = decodeString(bencodeFile);
            Item<?> value = decodeNext(bencodeFile);
            result.put(key, value);
        }
        bencodeFile.next('e');
        indent--;
        log("End dictionary");
        return result;
    }

    private void log(String msg) {
        System.out.println(" ".repeat(indent) + msg);
    }
}