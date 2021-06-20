package entities;
/**
 * Status class is a class that describes the DB entity - Status
 * 
 * @author Viktor
 *
 */
public class Status {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
