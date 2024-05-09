package clan.midnight.tn.generate;

import clan.midnight.tn.input.Attribute;
import clan.midnight.tn.output.Property;

public interface PropertyGenerator {
    Property generate(Attribute attribute);
}
