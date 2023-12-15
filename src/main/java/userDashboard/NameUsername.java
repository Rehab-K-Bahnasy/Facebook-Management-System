package userDashboard;
/**
 * Represents a combination of a user's name and username. Provides methods to
 * initialize the instance and retrieve the name. The maximum length of the names
 * is tracked across instances.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class NameUsername {
    public String name;
    public String username;
    private static int max_length;

    /**
     * Constructs a new NameUsername instance with the specified name and username.
     * Updates the maximum length of names if the current name length exceeds it.
     *
     * @param name     The user's name.
     * @param username The user's username.
     */
    public NameUsername(String name, String username) {
        this.username = username;
        this.name = name;
        max_length = Math.max(max_length, name.length());
    }

    /**
     * Returns the string representation of the user's name.
     *
     * @return The user's name.
     */
    @Override
    public String toString() {
        return name;
    }
}
