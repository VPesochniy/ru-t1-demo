package ru.t1.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import ru.t1.demo.service.MainService;

@ComponentScan
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        try {

            MainService mainService = context.getBean(MainService.class);

            // Отступ для наглядности
            System.out.println();
            System.out.println();

            /*
             * Логи будут показывать 1 выполненный запрос, хотя под капотом происходит
             * добавление в цикле.
             * Понятное дело, что лучше использовать батчи, либо же дергать функцию из
             * другой функции в цикле.
             * Еще можно повесить аннотацию на поле счетчика.
             */
            mainService.initDbWithRandomRecords(10);

            /*
             * Здесь будет выведен лог "ru.t1.demo.repository.FakeDbRepository.getUsers()",
             * так как под капотом используется именно он.
             */
            mainService.listAllUsers();

            // Отступ для наглядности
            System.out.println();
            System.out.println();

            // Проверка отработки лога-счетчика
            mainService.getAllUsers();
            mainService.getAllUsers();
            mainService.getAllUsers();

            // Отступ для наглядности
            System.out.println();
            System.out.println();

            /*
             * Лог выведет информацию об ошибке и не прервет выполнение программы, счетчик
             * не отработает (исключение)
             */
            mainService.getUserByIdOrLogException(10);

            // Отступ для наглядности
            for (int i = 0; i < 10; i++) {
                System.out.println();
            }

            /*
             * Лог выведет информацию об ошибке и прервет выполнение программы, счетчик не
             * отработает (исключение)
             */
            mainService.getUserByIdOrThrowException(10);

        } finally {
            context.close();
        }

    }
}