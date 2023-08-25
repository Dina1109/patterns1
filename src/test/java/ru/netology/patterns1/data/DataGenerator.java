package ru.netology.patterns1.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) { //метод генерации даты
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        //метод генерации города без faker,
        //потому что faker не умеет генерировать города, являющиеся администр центрами
        var cities = new String[]{"Москва, Самара, Оренбург, Казань, Тюмень, Екатеринбург, Ярославль"};
        return cities[new Random().nextInt(cities.length)]; //выбор случайного элемента из массива
    }

    public static String generateName(String locale) { //метод генерации имени и фамилии
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) { //метод генерации номера телефона
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo { //data-классы
        String city;
        String name;
        String phone;
    }
}
