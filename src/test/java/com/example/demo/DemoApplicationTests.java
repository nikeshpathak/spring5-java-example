package com.example.demo;

import com.example.demo.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	WebTestClient webTestClient;

	@Autowired
	RouterFunction<ServerResponse> routerFunction;

	@Before
	void setUp()
	{
		webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
	}

	@Test
	void getCustomer()
	{
		FluxExchangeResult<Customer> result = webTestClient.get().uri("/customer").exchange().expectStatus().isOk().returnResult(Customer.class);
		List<Customer> customerList = result.getResponseBody().toStream().collect(Collectors.toList());
		Assert.assertNotNull(customerList);
		Assert.assertTrue(customerList.size()>0);
	}

	@Test
	void addCustomer()
	{
		//Mono<Customer> customerMono = Mono.just()
	}
}
