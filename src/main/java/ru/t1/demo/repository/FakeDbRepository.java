package ru.t1.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import net.datafaker.Faker;
import ru.t1.demo.aspect.CatchDbExecution;
import ru.t1.demo.aspect.CountDbExecution;
import ru.t1.demo.aspect.LogDbException;
import ru.t1.demo.aspect.LogDbExecution;
import ru.t1.demo.domain.User;

@Component
public class FakeDbRepository {

    private final List<User> users;
    private final Random random;
    private final Faker faker;
    private Integer counter;

    public FakeDbRepository() {
        counter = 0;
        random = new Random();
        users = new ArrayList<>();
        faker = new Faker();
    }

    @LogDbExecution
    @CountDbExecution
    public List<User> getUsers() {
        return users;
    }

    @LogDbExecution
    @CountDbExecution
    public void insertRows(int capacity) {
        IntStream.range(0, capacity)
                .forEach(i -> {
                    User user = User.builder()
                            .id(counter++)
                            .firstName(faker.name().firstName())
                            .lastName(faker.name().lastName())
                            .age(random.nextInt(100) + 1)
                            .address(faker.address().fullAddress())
                            .build();
                    users.add(user);
                });
    }

    @LogDbExecution
    @LogDbException
    @CountDbExecution
    public User getUserUnsafe(int id) {
        return users.get(id);
    }

    @LogDbExecution
    @CountDbExecution
    @CatchDbExecution
    public User getUserSafe(int id) {
        return users.get(id);
    }

}
