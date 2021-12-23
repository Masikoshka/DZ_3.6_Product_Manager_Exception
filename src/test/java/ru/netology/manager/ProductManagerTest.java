package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    private Product first = new Book("The Little Prince", 1, 900, "Antoine de Saint-Exupéry");
    private Product second = new Book("Harry Potter and the Prisoner of Azkaban", 2, 1100, "J. K. Rowling");
    private Product third = new Smartphone("Galaxy S21 Ultra", 3, 85000, "Samsung");
    private Product fourth = new Smartphone("Redmi Note 10 Pro", 4, 25000, "Xiaomi");

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
    }

    @Test
    void removeById() {
        manager.removeById(2);

        Product[] actual = manager.getAll();
        Product[] expected = new Product[]{first, third, fourth};

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdException() {
        assertThrows(NotFoundException.class, () -> {
            manager.removeById(10);
        });
    }
}