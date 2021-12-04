package Current;

public class Current_Store {
    private static String storeName;
    private static String address;

    public static String getStoreName() {
        return storeName;
    }

    public static void setStoreName(String storeName) {
        Current_Store.storeName = storeName;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        Current_Store.address = address;
    }
}
