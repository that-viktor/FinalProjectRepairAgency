package entities;

/**
 * Role class is a class that describes the DB entity - Role
 * 
 * @author Viktor
 *
 */
public class Role {
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
