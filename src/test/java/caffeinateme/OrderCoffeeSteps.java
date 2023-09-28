package caffeinateme;

import caffeinateme.model.CoffeeShop;
import caffeinateme.model.Customer;
import caffeinateme.model.Order;
import caffeinateme.model.OrderStatus;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderCoffeeSteps {
    CoffeeShop coffeeShop = new CoffeeShop();
    Order order;
    Customer customer;

    @Given("{} is a CaffeinateMe customer")
    public void a_caffeinate_me_customer_named(String customerName) {
        customer = coffeeShop.registerNewCustomer(customerName);
    }
    @Given("Cathy is {int} meters from the coffee shop")
    public void cathy_is_n_meters_from_the_coffee_shop(int distanceInMeters) {
        customer.setDistanceFromShop(distanceInMeters);
    }
    @When("Cathy orders a {string}")
    public void cathy_orders_a(String orderedProduct) {
        this.order=Order.of(1,orderedProduct).forCustomer(customer);
        customer.placeAnOrderFor(order).at(coffeeShop);
    }

    @When("Cathy orders a {string} with a comment {string}")
    public void cathy_orders_a_with_a_comment(String orderedProduct, String comment) {
        this.order=Order.of(1,orderedProduct).forCustomer(customer).withComments(comment);
        customer.placeAnOrderFor(order).at(coffeeShop);

    }

    @Then("Barry should receive the order")
    public void barry_should_receive_the_order() {
        assertThat(coffeeShop.getPendingOrders()).contains(order);
    }
    @Then("^Barry should know that the coffee is (.*)")
    public void barry_should_know_that_the_coffee_is_urgent(OrderStatus expectedStatus) {
        Order order = coffeeShop.getOrderFor(customer)
                .orElseThrow(() -> new AssertionError("No order found!"));
        assertThat(order.getStatus()).isEqualTo(expectedStatus);
    }

    @Then("the order should have the comment {string}")
    public void the_order_should_have_the_comment(String comment) {
        Order order = coffeeShop.getOrderFor(customer).get();
        assertThat(order.getComments()).isEqualTo(comment);

    }

}
