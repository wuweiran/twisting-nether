package clan.midnight.tn.generate;

import clan.midnight.tn.input.Attribute;
import clan.midnight.tn.output.Property;

import java.util.Random;
import java.util.stream.Collectors;

public class NameStringPropertyGenerator implements PropertyGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private final Random random;
    public NameStringPropertyGenerator() {
        this.random = new Random();
    }

    @Override
    public Property generate(Attribute attribute) {
        int length = random.nextInt(5, 20);
        return new Property(attribute.name(), generateRandomString(length - 1));
    }

    private String generateRandomString(int length) {
        return random.ints(length, 0, CHARACTERS.length())
                .mapToObj(CHARACTERS::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
