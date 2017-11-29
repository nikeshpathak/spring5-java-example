package com.example.demo;

/* Copyright 2017 Nikesh Pathak
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. */

import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.server.ServerResponse.status;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.UUID;

@Component
public class CustomerHandler {

    CustomerRepo customerRepo;

    public CustomerHandler(CustomerRepo customerRepo)
    {
        this.customerRepo = customerRepo;
    }

    public Mono<ServerResponse> add(ServerRequest request)
    {
        Customer customerLocal = request.bodyToMono(Customer.class).block();
        customerLocal.setCustId(UUID.randomUUID().toString());
        return status(HttpStatus.CREATED).body(customerRepo.save(customerLocal),Customer.class);
    }

    public Mono<ServerResponse> get(ServerRequest request)
    {
        Mono<Customer> customerMono = customerRepo.findByCustId(request.pathVariable("id"));
        return ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> delete(ServerRequest request)
    {
        customerRepo.deleteByCustId(request.pathVariable("id"));
        return ok().build();
    }

    public Mono<ServerResponse> getAll(ServerRequest request)
    {
        //TODO
      return null;
    }
}
