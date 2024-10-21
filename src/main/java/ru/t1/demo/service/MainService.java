package ru.t1.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.t1.demo.domain.User;
import ru.t1.demo.repository.FakeDbRepository;

@Service
public class MainService {
    private final FakeDbRepository fakeDbRepository;

    public MainService(FakeDbRepository fakeDbRepository) {
        this.fakeDbRepository = fakeDbRepository;
    }

    public void initDbWithRandomRecords(int capacity) {
        fakeDbRepository.insertRows(capacity);
    }

    public User getUserByIdOrThrowException(int id) {
        /*
         * Здесь должна быть проверка на существование объекта в списке, но мы проверяем
         * отработку AOP
         */
        return fakeDbRepository.getUserUnsafe(id);
    }

    public List<User> getAllUsers() {
        return fakeDbRepository.getUsers();
    }

    public void listAllUsers() {
        List<User> allUsers = getAllUsers();
        System.out.println();
        allUsers.forEach(System.out::println);
        System.out.println();
    }

    public User getUserByIdOrLogException(int id) {
        /*
         * Здесь должна быть проверка на существование объекта в списке, но мы проверяем
         * отработку AOP
         */
        return fakeDbRepository.getUserSafe(id);
    }

}
