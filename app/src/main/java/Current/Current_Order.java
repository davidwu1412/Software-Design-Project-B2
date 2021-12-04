package Current;

public class Current_Order {

    static String Owner;
    static String OrderToken;
    static String Customer;

    public static String getOwner() {
        return Owner;
    }

    public static void setOwner(String owner) {
        Owner = owner;
    }

    public static String getOrderToken() {
        return OrderToken;
    }

    public static void setOrderToken(String orderToken) {
        OrderToken = orderToken;
    }

    public static String getCustomer() {
        return Customer;
    }

    public static void setCustomer(String customer) {
        Customer = customer;
    }
}
