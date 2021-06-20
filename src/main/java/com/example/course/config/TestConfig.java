package com.example.course.config;

import java.time.Instant;
import java.util.Arrays;

import com.example.course.entities.Category;
import com.example.course.entities.Order;
import com.example.course.entities.OrderItem;
import com.example.course.entities.Payment;
import com.example.course.entities.Product;
import com.example.course.entities.User;
import com.example.course.entities.enums.OrderStatus;
import com.example.course.repositories.CategoryRepository;
import com.example.course.repositories.OrderItemRepository;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.ProductRepository;
import com.example.course.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub

        Category cat1 = new Category(null, "Eletronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");


        User user1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User user2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, user1);
        Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT,user2);
        Order order3 = new Order(null, Instant.parse("2019-06-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT,user1);

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Loremm", 2500.99, "");
        Product p3 = new Product(null, "MacBook Pro", "Lorem", 8000.00, "");
        Product p4 = new Product(null, "PC GAMER", "lorem", 4500.78, "");
        Product p5 = new Product(null, "Rails for dummies", "lorem", 78.00, "");




        userRepository.saveAll(Arrays.asList(user1, user2));
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
        
        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat2);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);
        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

        OrderItem oi1 = new OrderItem(order1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(order1, p3, 1, p4.getPrice());
        OrderItem oi3 = new OrderItem(order2, p3, 2, p1.getPrice());
        OrderItem oi4 = new OrderItem(order3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));

        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), order1);
        order1.setPayment(pay1);
        orderRepository.save(order1);
    }
}
