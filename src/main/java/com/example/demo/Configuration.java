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

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    RouterFunction<ServerResponse> router(CustomerHandler customerHandler)
    {
        return route(GET("customer/{id}"),customerHandler::get)
                .andRoute(POST("/customer"),customerHandler::add)
                .andRoute(DELETE("/customer/{id}"),customerHandler::delete)
                .andRoute(GET("/customer").and(accept(MediaType.TEXT_EVENT_STREAM)),customerHandler::getAll);
    }
}
