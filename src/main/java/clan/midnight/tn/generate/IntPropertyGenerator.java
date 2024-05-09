package clan.midnight.tn.generate;

import clan.midnight.tn.input.Attribute;
import clan.midnight.tn.output.Property;

import java.util.Random;

public class IntPropertyGenerator implements PropertyGenerator {
    private final Random random;
    private final long bound;

    public IntPropertyGenerator(int bits) {
        this.random = new Random();
        this.bound = 1L << (bits - 1) - 1;
    }

    @Override
    public Property generate(Attribute attribute) {
        if (bound > Integer.MAX_VALUE) {
            long a = random.nextLong(bound);
            return new Property(attribute.name(), random.nextLong(bound));
        } else {
            return new Property(attribute.name(), random.nextInt((int) bound));
        }
    }
}
