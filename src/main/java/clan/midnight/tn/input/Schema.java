package clan.midnight.tn.input;

import java.util.ArrayList;
import java.util.List;

public class Schema {
    private List<Attribute> attributes;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public Schema() {
        this.attributes = new ArrayList<>();
    }

    public Schema addAttribute(String name, String type) {
        return addAttribute(name, type, "");
    }

    public Schema addAttribute(String name, String type, String description) {
        attributes.add(new Attribute(name, type, description));
        return this;
    }
}
