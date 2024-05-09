package clan.midnight.tn.generate;

import clan.midnight.tn.input.Attribute;

public class TabularDataGenerationConfiguration {
    public int getSize() {
        return 1024;
    }

    public PropertyGenerator findGenerator(Attribute attribute) {
        switch (attribute.type().toLowerCase()) {
            case "string" -> {
                String name = attribute.name().toLowerCase();
                if (name.endsWith("name")) {
                    return new NameStringPropertyGenerator();
                }
                if (name.endsWith("version")) {
                    return new VersionPropertyGenerator();
                }
                if (name.endsWith("id")) {
                    return new UuidStringPropertyGenerator();
                }
                return new DefaultStringPropertyGenerator();
            }
            case "int", "int32" -> {
                return new IntPropertyGenerator(32);
            }
            case "int64" -> {
                return new IntPropertyGenerator(64);
            }
            case "datetime" -> {
                return new DateTimePropertyGenerator();
            }
            default -> {
                return null;
            }
        }
    }
}
