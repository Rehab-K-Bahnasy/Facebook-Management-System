package userDashaboard;

public class NameUsername {
    public String name;
    public String username;
    private static int max_length;

    public NameUsername(String name, String username) {
        this.username = username;
        this.name = name;
        max_length = Math.max(max_length, name.length());
    }

    @Override
    public String toString() {
        return name;
    }
}
