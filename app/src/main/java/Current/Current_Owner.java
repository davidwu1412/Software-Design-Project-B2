package Current;

public class Current_Owner {

    private static String ownerUsername;

    public static String getOwnerUsername() {
        return ownerUsername;
    }

    public static void setOwnerUsername(String ownerUsername) {
        Current_Owner.ownerUsername = ownerUsername;
    }
}
