package clan.midnight.tn.generate;

import clan.midnight.tn.input.Attribute;
import clan.midnight.tn.output.Property;

import java.util.Random;

public class UuidStringPropertyGenerator implements PropertyGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final Random random;
    public UuidStringPropertyGenerator() {
        this.random = new Random();
    }
    @Override
    public Property generate(Attribute attribute) {
        String uuid = generateRandomString(8) + "-" + generateRandomString(4) + "-" + generateRandomString(4) + generateRandomString(12);
        return new Property(attribute.name(), uuid);
    }

    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
