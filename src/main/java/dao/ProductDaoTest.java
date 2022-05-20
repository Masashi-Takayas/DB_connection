package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Product;
import util.DbUtil;

public class ProductDaoTest {

    private Connection connection;
    private ProductDao productDao;
    
    @BeforeEach
    public void setUp() throws Exception {
        connection = DbUtil.getConnection();
        connection.setAutoCommit(false);

        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM products")) {
            stmt.executeUpdate();
        }

        try (Statement stmt = connection.createStatement()) {
            stmt.addBatch("INSERT INTO products VALUES (101, '鉛筆',50 )");
            stmt.addBatch("INSERT INTO products VALUES (102, '消しゴム',100 )");
            stmt.addBatch("INSERT INTO products VALUES (103, '地球儀',5000 )");
            
            stmt.executeBatch();
        }

        productDao = new ProductDao(connection);
    }

    @AfterEach
    public void tearDown() throws Exception {
        connection.rollback();
    }

    @Test
    public void findAllで全件取得できる() {
        List<Product> list = productDao.findAll();
        assertEquals(2, list.size());

        Product p = list.get(0);
        assertEquals(Integer.valueOf(1), p.getProductId());
        assertEquals("Alice", p.getProductName());
        assertEquals("password", p.getPrice());

        p = list.get(1);
        assertEquals(Integer.valueOf(2), p.getProductId());
        assertEquals("Bob", p.getProductName());
        assertEquals("password", p.getPrice());
    }

    @Test
    public void findAllはデータがないと空のリストを返す() throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM products")) {
            stmt.executeUpdate();
        }

        List<Product> list = productDao.findAll();
        assertEquals(0, list.size());
    }

    @Test
    public void findByIdで存在するデータが正しく取得できる() {
        Product product = productDao.findById(1);
        assertEquals(Integer.valueOf(1), product.getProductId());
        assertEquals("Alice", product.getProductName());
        assertEquals("password", product.getPrice());
    }

    @Test
    public void findByIdで存在しないデータはnullになる() {
        Product product = productDao.findById(10);
        assertNull(product);
    }

    @Test
    public void insertでデータを登録できる() {
        Product newProduct = new Product(105, "ボールペン", 200);
        productDao.insert(newProduct);

        Product getProduct = productDao.findById(10);
        assertEquals(newProduct.getProductId(), getProduct.getProductId());
        assertEquals(newProduct.getProductName(), getProduct.getProductName());
    }

    @Test
    public void insertで主キーが重複していると例外発生() {
    	Product newProduct = new Product(1, "鉛筆", 200);
        assertThrows(RuntimeException.class, () -> productDao.insert(newProduct));
    }
}
