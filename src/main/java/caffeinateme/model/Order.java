package caffeinateme.model;
import java.util.List;

public class Order {

    private Customer customer;
    private OrderItem item;
    private List<OrderItem> itemList;
    private OrderStatus status;
    private String comments;

    public Order(Customer customer,List<OrderItem> item){
        this.customer = customer;
        //this.item = item;
        this.itemList=item;
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

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public static class OrderBuilder {
        private final int quantity;
        private final String orderedProduct;

        public OrderBuilder(int quantity, String orderedProduct){
            this.quantity =quantity;
            this.orderedProduct = orderedProduct;
        }

        public Order forCustomer(Customer customer) {
            return new Order(customer,List.of(new OrderItem(orderedProduct,quantity)));
        }

    }
}
