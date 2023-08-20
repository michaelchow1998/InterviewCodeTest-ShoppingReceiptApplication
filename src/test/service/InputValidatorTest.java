package service;

import common.Location;
import config.CAConfig;
import config.NYConfig;
import model.Order;
import model.Product;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


public class InputValidatorTest {

    InputValidator validator;

    @BeforeEach
    public void setUp(){
        validator = new InputValidator();
    }

    @Nested
    class CreateTaskTest{

        @Test()
        @DisplayName("Create Task with CA config")
        public void testTaskWithCAConfig(){
            String inputString = "Location: CA, 1 book at 17.99, 1 potato chips at 3.99";
            InputValidator validator1 = Mockito.spy(validator);
            Mockito.doReturn(inputString).when(validator1).getInputString();
            Task task = validator1.createTask();
            assertEquals(task.getConfig().getClass(), CAConfig.class);
        }

        @Test()
        @DisplayName("Create Task with NY config")
        public void testTaskWithNYConfig(){
            String inputString = "Location: NY, 1 book at 17.99, 1 potato chips at 3.99";
            InputValidator validator1 = Mockito.spy(validator);
            Mockito.doReturn(inputString).when(validator1).getInputString();
            Task task = validator1.createTask();
            assertEquals(task.getConfig().getClass(), NYConfig.class);
        }

    }


    @Nested
    class GetOrderTest{
        @ParameterizedTest
        @ValueSource(strings = {
                "Location: CA, 1 book at 17.99, 1 potato chips at 3.99, 1 shirt at 29.99",
                "Location: CA, 1 book at 17.99, 1 potato chips at 3.99",
                "Location: NY, 1 book at 17.99, 3 pencils at 2.99",
                "Location: NY, 2 pencils at 2.99, 1 shirt at 29.99",
                "Location: NY, 2 pencils at 2.99"
        })
        @DisplayName("Correct inputString to get Order")
        public void testCorrectInputToGetOrder(String inputString){

            //when
            Order order = validator.getOrder(inputString);

            //then
            assertEquals(order.getLocation().getClass(), Location.class);
            assertNotEquals(order.getPurchasedProducts().size(),0);
        }

        @Test()
        @DisplayName("Wrong Location throw error")
        public void testWrongLocationToThrowError(){
            //given
            String inputString = "Location: HK, 1 book at 17.99, 1 potato chips at 3.99";
            //then
            assertThrows(IllegalArgumentException.class, ()->validator.getOrder(inputString));

        }

        @Test()
        @DisplayName("Wrong product's quantity throw error")
        public void testWrongProductQuantityToThrowError(){
            //given
            String inputString = "Location: CA, a book at 17.99, 1 potato chips at 3.99";
            //then
            assertThrows(NumberFormatException.class, ()->validator.getOrder(inputString));

        }

        @Test()
        @DisplayName("Wrong product format throw error")
        public void testWrongProductFormatToThrowError(){
            //given
            String inputString = "Location: CA, book 1 at 17.99, 1 potato chips at 3.99";
            //then
            assertThrows(IllegalArgumentException.class, ()->validator.getOrder(inputString));

        }

        @Test()
        @DisplayName("Empty input string throw error")
        public void testEmptyInputStringToThrowError(){
            //given
            String inputString = "";
            //then
            assertThrows(IllegalArgumentException.class, ()->validator.getOrder(inputString));
        }

        @Test()
        @DisplayName("Empty product throw error")
        public void testEmptyProductToThrowError(){
            //given
            String inputString = "Location: CA";
            //then
            assertThrows(IllegalArgumentException.class, ()->validator.getOrder(inputString));
        }
    }

    @Nested
    class GetProductFromStringTest{

        @Test()
        @DisplayName("One word product name")
        public void testOneWordProductName(){
            //given
            String inputString = "1 book at 17.99";

            //when
            Product product = validator.getProductFromString(inputString);

            //then
            assertEquals(product.getQuantity(), 1);
            assertEquals(product.getProductName(), "book");
            assertEquals(product.getPrice(), 17.99);
        }

        @Test()
        @DisplayName("Two word product name")
        public void testTwoWordProductName(){
            //given
            String inputString = "3 potato chips at 3.99";

            //when
            Product product = validator.getProductFromString(inputString);

            //then
            assertEquals(product.getQuantity(), 3);
            assertEquals(product.getProductName(), "potato chips");
            assertEquals(product.getPrice(), 3.99);
        }

        @Test()
        @DisplayName("More than two word product name")
        public void testMoreThanTwoWordProductName(){
            //given
            String inputString = "10 x x x x x potato chips at 3.99";

            //when
            Product product = validator.getProductFromString(inputString);

            //then
            assertEquals(product.getQuantity(), 10);
            assertEquals(product.getProductName(), "x x x x x potato chips");
            assertEquals(product.getPrice(), 3.99);
        }

        @Test()
        @DisplayName("Test product name start with number")
        public void testProductNameStartWithNumber(){
            //given
            String inputString = "1 9book at 3.99";

            //when
            Product product = validator.getProductFromString(inputString);

            //then
            assertEquals(product.getQuantity(), 1);
            assertEquals(product.getProductName(), "9book");
            assertEquals(product.getPrice(), 3.99);
        }

        @Test()
        @DisplayName("Test input String missing at")
        public void testInputStringMissingAt(){
            //given
            String inputString = "1 9book 3";
            //when
            assertThrows(IllegalArgumentException.class, ()->validator.getProductFromString(inputString));
        }

        @Test()
        @DisplayName("Test input String not start with number")
        public void testInputStringNotStartWithNumber(){
            //given
            String inputString = "a 9book at 3";
            //when
            assertThrows(IllegalArgumentException.class, ()->validator.getProductFromString(inputString));
        }
    }


}