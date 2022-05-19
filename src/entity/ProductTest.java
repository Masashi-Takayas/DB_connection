package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void usersテーブルに対応している() {
        Product product = new Product();
        product.setProductId(10);
        product.setProductName("test");
        product.setPrice(1000);

        assertEquals(Integer.valueOf(10), product.getProductId());
        assertEquals("test", product.getProductName());
        assertEquals("password", product.getPrice());
    }

    @Test
    public void 引数のあるコンストラクターがある() {
        Product user = new Product(10, "test", 1000);

        assertEquals(Integer.valueOf(10), user.getProductId());
        assertEquals("test", user.getProductName());
        assertEquals("password", user.getPrice());
    }

}
