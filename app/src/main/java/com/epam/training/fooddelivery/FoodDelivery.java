package com.epam.training.fooddelivery;

import com.epam.training.fooddelivery.domain.Customer;
import com.epam.training.fooddelivery.domain.Food;
import com.epam.training.fooddelivery.domain.Order;
import com.epam.training.fooddelivery.domain.User;
import com.epam.training.fooddelivery.repository.CustomerRepository;
import com.epam.training.fooddelivery.repository.FoodRepository;
import com.epam.training.fooddelivery.repository.OrderItemRepository;
import com.epam.training.fooddelivery.repository.OrderRepository;
import com.epam.training.fooddelivery.service.*;
import com.epam.training.fooddelivery.view.CLIView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FoodDelivery implements CommandLineRunner {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {


        CustomerService customerService = new DefaultCustomerService(customerRepository);
        CartService cartService;
        FoodService foodService;
        OrderService orderService;


        //FileDataStore dataStore = new FileDataStore(BaseDirPath.getBaseDirPath());
        //dataStore.init();
        //FoodDeliveryService service = new DefaultFoodDeliveryService(dataStore);
        CLIView view = new CLIView(customerRepository);


        foodService = new DefaultFoodService(foodRepository);
        cartService = new DefaultCartService();
        orderService = new DefaultOrderService(orderRepository, orderItemRepository, customerRepository);


        User user = view.readCredentials();

        Customer customer = customerService.authenticate(user);

        view.printWelcomeMessage(customer);

        Order order = new Order();

        Food food;
        int pieces;


        do {
            view.printAllFoods(foodService.listAllFood());

            food = view.selectFood(foodService.listAllFood());

            pieces = view.readPieces();

            cartService.updateCart(customer, food, pieces);

            view.printAddedToCart(food, pieces);

            view.printCart(customer.getCart());

            System.out.print("\nAre you finished with your order? (y/n): ");

        } while (view.readDecision());


        try {
            order = orderService.createOrder(customer);
        } catch (LowBalanceException e) {
            view.printErrorMessage(e.getMessage());
        }

        view.printConfirmOrder(order);
    }
}