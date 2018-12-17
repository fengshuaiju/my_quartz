package com.feng.test;

import com.feng.Application;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class DemoApplicationTests {

    @Test
    public void contextLoads() {

        List<Address> addresses1 = Arrays.asList(
                new Address("country1", "city1"),
                new Address("country2", "city2"),
                new Address("country3", "city3")
        );

        List<Address> addresses2 = Arrays.asList(
                new Address("countryA", "cityA"),
                new Address("countryB", "cityB"),
                new Address("countryC", "cityC")
        );

        User user1 = new User("username1","sex2", addresses1);
        User user2 = new User("usernameA","sexB", addresses2);


        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        Stream<Address> addressStream = userList.stream().flatMap(user -> user.addressList.stream());

        addressStream.forEach(System.err::println);
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    class User {
        private String username;
        private String sex;
        List<Address> addressList;
    }


    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    class Address {
        private String country;
        private String city;
    }


    @Test
    public void test(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        Map<Integer, List<Integer>> collect = integers.stream().collect(Collectors.groupingBy(s -> s % 2));

        collect.forEach((k,v) -> {
            System.err.println("k: " + k);
            v.stream().forEach(System.err::println);
        });

    }


    @Test
    public void completableFutureTest() throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "fengshuaiju");

        String s = completableFuture.get();

        System.err.println(s);

    }

}
