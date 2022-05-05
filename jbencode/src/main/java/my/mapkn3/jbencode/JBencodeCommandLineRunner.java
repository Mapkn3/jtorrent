package my.mapkn3.jbencode;

import lombok.RequiredArgsConstructor;
import my.mapkn3.jbencode.model.item.DictionaryItem;
import my.mapkn3.jbencode.service.BencodeDecoder;
import my.mapkn3.jbencode.service.BencodeEncoder;
import my.mapkn3.jbencode.service.ItemConverter;
import my.mapkn3.jbencode.service.ItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JBencodeCommandLineRunner implements CommandLineRunner {
    private final BencodeDecoder bencodeDecoder;
    private final BencodeEncoder bencodeEncoder;

    @Override
    public void run(String... args) throws Exception {
        File file = new File("C:\\Users\\Mapkn3\\IdeaProjects\\jtorrent\\eternals.torrent");
        System.out.println(new String(new FileInputStream(file).readAllBytes()));

        DictionaryItem item = (DictionaryItem) bencodeDecoder.decode(file);
        Map<String, Object> torrentFileData = ItemConverter.convert(item);
        System.out.println(torrentFileData);

        MessageDigest instance = MessageDigest.getInstance("SHA-1");
        String infoBencode = bencodeEncoder.encode(ItemService.get(item, "info"));
        System.out.println(new BigInteger(instance.digest(infoBencode.getBytes(StandardCharsets.UTF_8))).toString(16));
    }
}