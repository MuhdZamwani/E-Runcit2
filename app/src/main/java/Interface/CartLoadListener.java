package Interface;

import java.util.List;

import Model.Cart;

public interface CartLoadListener {
    void onCartLoadSuccess(List<Cart> cart);
    void onCartLoadFailed(String message);
}
