package caffeinateme.model;

import caffeinateme.model.CoffeeShop;
import caffeinateme.model.Order;

public class Customer {
    private int distanceInMetres;
    private Order order;
    private String name;

    public Customer(String name){
        this.name = name;
    }

    public void setDistanceFromShop(int distanceInMetres) {
        this.distanceInMetres = distanceInMetres;
    }
   /* public void placeAnOrder(Order order) {
        this.order = order;
    }*/

    public CustomerOrderBuilder placeAnOrderFor(Order order) {
        return new CustomerOrderBuilder(order, distanceInMetres);

    }

    public static class CustomerOrderBuilder {
        private Order order;
        private final int distanceInMetres;

        public CustomerOrderBuilder(Order order, int distanceInMetres) {
            this.order = order;
            this.distanceInMetres = distanceInMetres;
        }

        public void at(CoffeeShop coffeeShop) {
            coffeeShop.placeOrder(order, distanceInMetres);
        }

    }
}
