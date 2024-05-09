package clan.midnight.tn.generate;

import clan.midnight.tn.input.Attribute;
import clan.midnight.tn.output.Property;

import java.util.Random;

public class DefaultStringPropertyGenerator implements PropertyGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int maxLength;
    private final Random random;

    public DefaultStringPropertyGenerator(int maxLength) {
        this.maxLength = maxLength;
        this.random = new Random();
    }

    public DefaultStringPropertyGenerator() {
        this(16);
    }

    @Override
    public Property generate(Attribute attribute) {
        int length = random.nextInt(1, maxLength);
        return new Property(attribute.name(), generateRandomString(length));
    }

    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
