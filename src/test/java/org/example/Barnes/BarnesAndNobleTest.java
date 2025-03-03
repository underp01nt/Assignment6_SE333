package org.example.Barnes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BarnesAndNobleTest {

    /*
        SPECIFICATION

            Invalid ISBN
            Valid ISBN
            Empty ISBN
            null ISBN

            Quantity -- 0 or greater than 0

            possible outputs -- nullptr exception, total >= 0

    */

    BarnesAndNoble barnesAndNoble;
    BookDatabase bookDatabase;
    BuyBookProcess buyBookProcess;

    @BeforeEach
    void createBarnesAndNobles() {
        buyBookProcess = mock(BuyBookProcess.class);
        bookDatabase = mock(BookDatabase.class);
        barnesAndNoble = new BarnesAndNoble(bookDatabase, buyBookProcess);
    }

    @DisplayName("Specification-based Testing")
    @Test
    void testEmptyOrder() {
        Map<String, Integer> order = new HashMap<>();
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);

        assertNotNull(summary);
        assertEquals(0, summary.getTotalPrice());  // No books in the order, total price should be 0
    }

    @DisplayName("Specification-based Testing")
    @Test
    void nullOrder() {
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(null);
        assertNull(summary);  // Null order should return null
    }

    @DisplayName("Specification-based Testing")
    @Test
    void validISBN() {
        Map<String, Integer> order = new HashMap<>();
        Book book = new Book("123", 123, 123);
        when(bookDatabase.findByISBN("123")).thenReturn(book);

        order.put("123", 1);
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);
        assertEquals(123, summary.getTotalPrice());
    }

    @DisplayName("Specification-based Testing")
    @Test
    void invalidISBN() {
        Map<String, Integer> order = new HashMap<>();
        when(bookDatabase.findByISBN("invalid")).thenReturn(null);

        order.put("invalid", 1);
        assertThrows(NullPointerException.class, () -> barnesAndNoble.getPriceForCart(order));
    }

    @DisplayName("Specification-based Testing")
    @Test
    void nullISBN() {
        Map<String, Integer> order = new HashMap<>();
        when(bookDatabase.findByISBN(null)).thenReturn(null);

        order.put(null, 1);
        assertThrows(NullPointerException.class, () -> barnesAndNoble.getPriceForCart(order));
    }

    @DisplayName("Specification-based Testing")
    @Test
    void emptyISBN() {
        Map<String, Integer> order = new HashMap<>();
        when(bookDatabase.findByISBN("")).thenReturn(null);

        order.put("", 1);
        assertThrows(NullPointerException.class, () -> barnesAndNoble.getPriceForCart(order));
    }

    @DisplayName("Specification-based Testing")
    @Test
    void buyAll() {
        Map<String, Integer> order = new HashMap<>();
        Book book = new Book("123", 1, 123);
        when(bookDatabase.findByISBN("123")).thenReturn(book);

        order.put("123", 123);
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);
        assertEquals(123, summary.getTotalPrice());
    }

    @DisplayName("Specification-based Testing")
    @Test
    void enough() {
        Map<String, Integer> order = new HashMap<>();
        Book book = new Book("123", 1, 123);
        when(bookDatabase.findByISBN("123")).thenReturn(book);

        order.put("123", 1);
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);
        assertEquals(1, summary.getTotalPrice());
    }

    @DisplayName("Specification-based Testing")
    @Test
    void notEnough() {
        Map<String, Integer> order = new HashMap<>();
        Book book = new Book("123", 1, 1);
        when(bookDatabase.findByISBN("123")).thenReturn(book);

        order.put("123", 2);
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);
        assertEquals(1, summary.getTotalPrice());
    }

    // perform structural-based testing
    @DisplayName("Structural-based Testing")
    @Test
    void sameISBN() {
        Book book1 = new Book("123", 1, 1);
        Book book2 = new Book("123", 2, 1);
        assertEquals(book1, book2);
    }

    @DisplayName("Structural-based Testing")
    @Test
    void diffISBN() {
        Book book1 = new Book("123", 1, 1);
        Book book2 = new Book("321", 2, 1);
        assertNotEquals(book1, book2);
    }
}