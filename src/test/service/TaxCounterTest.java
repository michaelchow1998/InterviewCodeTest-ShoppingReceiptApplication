package service;

import config.CAConfig;
import config.Config;
import config.NYConfig;
import model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxCounterTest {


    @Test
    public void testCAConfigWithNonExemptProduct(){
        //given
        Config config = new CAConfig();
        Product product = new Product("anyThing", 2, 15.99);

        //when
        double tax = TaxCounter.countTax(config, product);

        assertEquals(tax, 3.15);
    }

    @Test
    public void testCAConfigWithExemptProduct(){
        //given
        Config config = new CAConfig();
        Product product = new Product("potato chips", 1, 3.99);

        //when
        double tax = TaxCounter.countTax(config, product);

        assertEquals(tax, 0);
    }

    @Test
    public void testNYConfigWithNonExemptProduct(){
        //given
        Config config = new NYConfig();
        Product product = new Product("anyThing", 1, 5.99);

        //when
        double tax = TaxCounter.countTax(config, product);

        assertEquals(tax, 0.55);
    }

    @Test
    public void testNYConfigWithExemptProduct(){
        //given
        Config config = new NYConfig();
        Product product = new Product("hat", 1, 3.99);

        //when
        double tax = TaxCounter.countTax(config, product);

        assertEquals(tax, 0);
    }

    @Test
    @DisplayName("Test Round up")
    public void testRoundup(){
        // Test Case 1
        double  testcase1 = 1.13;
        double expectResult1 = 1.15;
        double result1 = TaxCounter.roundup(testcase1);
        assertEquals(result1, expectResult1);

        // Test Case 2
        double  testcase2 = 1.16;
        double expectResult2 = 1.20;
        double result2 = TaxCounter.roundup(testcase2);
        assertEquals(result2, expectResult2);

        // Test Case 3
        double  testcase3 = 1.151;
        double expectResult3 = 1.20;
        double result3 = TaxCounter.roundup(testcase3);
        assertEquals(result3, expectResult3);

    }

}