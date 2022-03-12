package ru.gb.lessons;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gb.lessons.lesson_4.Colour;
import ru.gb.lessons.lesson_4.Triangle;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParametrizedTriangleTest {
    public static Stream<Arguments> triangles() {
        return Stream.of(Arguments.of(new Triangle(3,4,5), 12), //метод состоит из аргументов. в аргумент Arguments.of() пишем параметры, которые хотели проверить
                Arguments.of(new Triangle(3,4,6), 13),                 //для других тестов тоже самое
                Arguments.of(new Triangle(3,3,3), 9)
        );
    }

    @ParameterizedTest(name = "Периметр треугольника: позитивный сценарий, периметр треугольника {0} == {1}")//добавляем имя, равное 0 = 1
    @DisplayName("Тест с прокидыванием параметров")                                  //название теста
    @MethodSource("triangles")                                                       //способов прокидывать много, вот один из них (берутся аргументы из методов-создаем выше метод triangles) .https:junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests
    void countPerimeterPositiveTest(Triangle triangle, int expectedResult) {//прокидываем параметры: тестовое тело будет оставаться одним и тем же с TriangleTest, но параметры будут разные. int expectedResult - ожидаемый результат. int perimeter - периметр треугольника {0} == {1}

        int perimeter = triangle.countPerimeter();                                  //следующая часть - Act:
        assertEquals(expectedResult,perimeter);                                     //третья часть - Assert:
    }
    public static Stream<Arguments> negativeTriangles() {
        return Stream.of(Arguments.of(new Triangle(0,3,3), "Sides must be positive"), //метод состоит из аргументов. в аргумент Arguments.of() пишем параметры, которые хотели проверить
                Arguments.of(new Triangle(3,0,3), "Sides must be positive"),                 //для других тестов тоже самое
                Arguments.of(new Triangle(3,3,0), "Sides must be positive"),
                Arguments.of(new Triangle(3,3,-1), "Sides must be positive"),
                Arguments.of(new Triangle(3,-1,3), "Sides must be positive"),
                Arguments.of(new Triangle(-1,3,3), "Sides must be positive"),
                Arguments.of(new Triangle(-1,3,3), "Sides must be positive"),
                Arguments.of(new Triangle(6,1,1), "One side can' be greater than sum of others"),
                Arguments.of(new Triangle(1,2,1), "One side can' be greater than sum of others")
                );
    }
    @ParameterizedTest(name = "Периметр треугольника: негативный сценарий (треугольника {0} ошибка: {1}")
    @MethodSource("negativeTriangles")
    @DisplayName("проверка правильности получившихся сторон:")
    public void countPerimeterNegativeTest(Triangle triangle, String errorText) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countPerimeter);  //понять, что есть ошибка - пишем какую ошибку ожидаем
        assertEquals(errorText, illegalArgumentException.getMessage());
    }
//Тесты для colour треугольника (пример с assumptions не естественный т.к. обычно используется в больших проектах, для проверки всех сред на случай недоступности)
    @ParameterizedTest                                                                //добавляем тестовый метод
    @EnumSource(Colour.class)                                                         // от сюда берем данные для передачи в  Triangle triangle = new Triangle(3, 3, 3)
    void paintTriangleTest(Colour colour) {                                           //Colour colour принимаем и перекрашиваем в определенный цвет
        Triangle triangle = new Triangle(3, 3, 3);                           //создаем треугольник для примера - неважно какого цвета и с какими сторонами
        Assumptions.assumeFalse(triangle.getColour().equals(colour));                 //для проверки всех сред на случай недоступности (заглушка для теста на цвет WHILE)
        triangle.paint(colour);                                                       //перекрасим в новый цвет
        assertEquals(colour, triangle.getColour());                                   //будем проверять, что новый цвет подходит
    }

}
