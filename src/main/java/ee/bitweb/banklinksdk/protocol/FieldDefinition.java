package ee.bitweb.banklinksdk.protocol;

/**
 * Created by tobre on 18/03/2017.
 */
public class FieldDefinition {

    protected String name;
    protected Integer length;

    public FieldDefinition(String name, Integer length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
