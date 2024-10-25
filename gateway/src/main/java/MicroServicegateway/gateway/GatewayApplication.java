package MicroServicegateway.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("products_route", r -> r
						.path("/products/all")
						.uri("http://localhost:8080"))
				.route("products_route", r -> r
						.path("/products/id/{id}")
						.uri("http://localhost:8080"))
				.route("cart_route", r -> r
						.path("/cart/delete")
						.uri("http://localhost:9090"))
				.route("cart_route", r -> r
						.path("/cart/add")
						.uri("http://localhost:9090"))
				.route("cart_route", r -> r
						.path("cart/deletecart/{cartId}")
						.uri("http://localhost:9090"))
				.route("cart_route", r -> r
						.path("/cart/fullcart")
						.uri("http://localhost:9090"))
				.route("user_route", r -> r
						.path("/user/register")
						.uri("http://localhost:7070"))
				.route("user_route", r -> r
						.path("/user/login")
						.uri("http://localhost:7070"))
				.route("order_route", r -> r
						.path("/order/order")
						.uri("http://localhost:9595"))
				.build();
	}
}
