package clan.midnight.tn;

import clan.midnight.tn.generate.TabularDataGeneration;
import clan.midnight.tn.generate.TabularDataGenerationConfiguration;
import clan.midnight.tn.input.Attribute;
import clan.midnight.tn.input.Schema;
import clan.midnight.tn.output.Property;
import clan.midnight.tn.output.TabularOutputFormat;
import clan.midnight.tn.output.Record;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataGeneration {
    public static void generateTabularData(Schema schema, TabularOutputFormat format, OutputStream outputStream, TabularDataGenerationConfiguration configuration) {
        if (configuration == null) {
            configuration = new TabularDataGenerationConfiguration();
        }
        Stream<Record> records = TabularDataGeneration.generate(schema, configuration);
        if (format == TabularOutputFormat.CSV) {
            try (
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                    BufferedWriter writer = new BufferedWriter(outputStreamWriter)
            ) {
                String header = schema.getAttributes().stream().map(Attribute::name).collect(Collectors.joining(","));
                writer.write(header);
                writer.newLine();
                records.forEach(record -> {
                    try {
                        Iterator<Property> iterator = record.properties().iterator();
                        while (iterator.hasNext()) {
                            Property property = iterator.next();
                            if (property.value() instanceof String value) {
                                if (value.contains(",") || value.contains("\"") || value.contains("\n") || value.contains("\r")) {
                                    value = "\"" + value.replaceAll("\"", "\"\"") + "\"";
                                }
                                writer.write(value);
                            } else {
                                writer.write(property.value().toString());
                            }
                            if (iterator.hasNext()) {
                                writer.write(",");
                            }
                        }
                        writer.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException("Cannot write to output stream.", e);
                    }
                });
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException("Cannot write to output stream.", e);
            }
        } else if (format == TabularOutputFormat.JSON) {

        } else {
            throw new RuntimeException("Unsupported format");
        }
    }

    public static void generateTabularData(Schema schema, TabularOutputFormat format, OutputStream outputStream) {
        generateTabularData(schema, format, outputStream, null);
    }
}
