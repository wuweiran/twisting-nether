package clan.midnight.tn.generate;

import clan.midnight.tn.input.Attribute;
import clan.midnight.tn.output.Property;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DateTimePropertyGenerator implements PropertyGenerator {
    private final long now;
    private final Random random;
    private final DateFormat dateFormat;
    private final long fluctuation;

    public DateTimePropertyGenerator() {
        this.now = System.currentTimeMillis();
        this.random = new Random();
        this.fluctuation = TimeUnit.DAYS.toMillis(365);
        this.dateFormat = new SimpleDateFormat();
    }

    @Override
    public Property generate(Attribute attribute) {
        Date date = new Date(now + random.nextLong(-fluctuation, fluctuation));
        return new Property(attribute.name(), dateFormat.format(date));
    }
}
