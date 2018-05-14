package litecartPOPattern.tests;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CartAddDeleteProducts extends TestBase {

    int productNum = 3;

    @Test
    public void CartTest() {

        for (int i = 0; i < productNum; i++) {
            app.addProductToCart(i);
        }

        app.Checkout();
        assertTrue(app.removeAllFromCart());
        System.out.println("All items removed");
    }


}
