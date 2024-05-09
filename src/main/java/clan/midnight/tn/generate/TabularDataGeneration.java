package clan.midnight.tn.generate;

import clan.midnight.tn.input.Attribute;
import clan.midnight.tn.input.Schema;
import clan.midnight.tn.output.Property;
import clan.midnight.tn.output.Record;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TabularDataGeneration {
    public static Stream<Record> generate(Schema schema, TabularDataGenerationConfiguration configuration) {
        Map<String, PropertyGenerator> generatorMap = schema.getAttributes().stream().collect(Collectors.toMap(Attribute::name, attribute -> {
            PropertyGenerator generator = configuration.findGenerator(attribute);
            if (generator == null) {
                throw new RuntimeException("Cannot find generator for attribute: " + attribute);
            }
            return generator;
        }));
        return IntStream.range(0, configuration.getSize()).mapToObj(a -> {
            List<Property> properties = schema.getAttributes().stream().map(attribute -> {
                PropertyGenerator generator = generatorMap.get(attribute.name());
                return generator.generate(attribute);
            }).toList();
            return new Record(properties);
        });
    }
}
