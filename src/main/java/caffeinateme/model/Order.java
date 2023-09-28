package caffeinateme.model;

public class Order {

    private Customer customer;
    private OrderItem item;
    private OrderStatus status;
    private String comments;

    public Order(Customer customer,OrderItem item){
        this.customer = customer;
        this.item = item;
    }

    public Customer getCustomer() {
        return customer;
    }

    public static OrderBuilder of(int quantity, String orderedProduct) {
        return new OrderBuilder(quantity,orderedProduct);
    }


    public OrderStatus getStatus() {
        return status;
    }

    public Order withStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public Order withComments(String comments) {
        this.comments = comments;
        return this;
    }

    public static class OrderBuilder {
        private final int quantity;
        private final String orderedProduct;

        public OrderBuilder(int quantity, String orderedProduct){
            this.quantity =quantity;
            this.orderedProduct = orderedProduct;
        }

        public Order forCustomer(Customer customer) {
            return new Order(customer,new OrderItem(orderedProduct,quantity));
        }

    }
}
