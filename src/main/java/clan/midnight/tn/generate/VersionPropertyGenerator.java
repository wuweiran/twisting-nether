package clan.midnight.tn.generate;

import clan.midnight.tn.input.Attribute;
import clan.midnight.tn.output.Property;

import java.util.Random;

public class VersionPropertyGenerator implements PropertyGenerator {
    private final Random random = new Random();

    @Override
    public Property generate(Attribute attribute) {
        return new Property(attribute.name(), random.nextInt(10) + "." + random.nextInt(16) + "." + random.nextInt(72));
    }
}
