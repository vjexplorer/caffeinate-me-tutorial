package caffeinateme;

import caffeinateme.model.*;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ParameterType("\"[^\"]*\"")
    public Order order(String orderedProduct)  {
            return Order.of(1,orderedProduct).forCustomer(customer);
    }

    @When("Cathy orders a {order}")
    public void cathy_orders_a( Order order) {
        this.order=order;
        customer.placeAnOrderFor(order).at(coffeeShop);
    }

    @When("Cathy orders a {order} with a comment {string}")
    public void cathy_orders_a_with_a_comment(Order order, String comment) {
        this.order =order.withComments(comment);
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

    @DataTableType
    public OrderItem orderitem(Map<String,String> row){
        return new OrderItem(row.get("Product"),Integer.parseInt(row.get("Quantity")));
    }

    @When("Cathy places an order for the following items:")
    public void cathyPlacesAnOrderForTheFollowingItems(List<OrderItem> items) {
        this.order = new Order(customer,items);
        customer.placeAnOrderFor(order).at(coffeeShop);
    }

    @And("the order should contain {int} line items")
    public void theOrderShouldContainLineItems(int expectedNumberOfLineItems) {
        Order order = coffeeShop.getOrderFor(customer).get();
        assertThat(order.getItemList().size()).isEqualTo(expectedNumberOfLineItems);
    }

    @And("the order should contain the following products:")
    public void theOrderShouldContainTheFollowingProducts(List<String> expectedProducts) {
        Order order = coffeeShop.getOrderFor(customer).get();
        List<String> productItems = order.getItemList().stream()
                .map(item -> item.product()).toList();
        assertThat(productItems).hasSameElementsAs(expectedProducts);


    }
}
