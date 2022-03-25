//package Helper;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//import Interface.ChangeNumItems;
//import Model.Cart;
//
//public class ManagementCart {
//    private Context context;
//    private TinyDB tinyDB;
//    private Cart cart;
//
//    public ManagementCart(Context context) {
//        this.context = context;
//        this.tinyDB = new TinyDB(context);
//    }
//
//    public void insertGrocery (Cart item){
//        ArrayList<Cart> listGrocery = getListCard();
////        ArrayList<Cart> listGrocery = getListCard();
//        boolean existAlready = false;
//        int n=0;
//        for (int i = 0; i < listGrocery.size(); i++) {
//            if (listGrocery.get(i).getProductName().equals(item.getProductName())) {
//                existAlready = true;
//                n = i;
//                break;
//            }
//        }
//
//        if (existAlready) {
//            listGrocery.get(n).setQuantity(item.getQuantity());
//        } else {
//            listGrocery.add(item);
//        }
//
//        tinyDB.putListObject("CartList", listGrocery);
//        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
//
//    }
//
//
//    public ArrayList<Cart> getListCard() {
//        return tinyDB.getListObject("CardList");
//    }
//
//    public void plusNumberFood(ArrayList<Cart> listitem, int position, ChangeNumItems changeNumItems) {
//        listitem.get(position).setQuantity(listitem.get(position).getQuantity() + 1);
//        tinyDB.putListObject("CardList", listitem);
//        changeNumItems.changed();
//    }
//
//    public void MinusNumerFood(ArrayList<Cart> listitem, int position, ChangeNumItems changeNumItems) {
//        int getCount = Integer.valueOf(listitem.get(position).getQuantity());
//        int getCount2 = Integer.valueOf(listitem.get(position).getQuantity());
//        if (getCount == 1) {
//            listitem.remove(position);
//        } else {
//            listitem.get(position).setQuantity(String.valueOf(getCount2 - 1));
//        }
//        tinyDB.putListObject("CardList", listitem);
//        changeNumItems.changed();
//    }
//
//    public Double getTotalFee() {
//        ArrayList<Cart> listFood2 = getListCard();
//
//        double fee = 0;
//        for (int i = 0; i < listFood2.size(); i++) {
//            double getCount = Double.valueOf(listFood2.get(i).getQuantity());
//            String strPri = listFood2.get(i).getProductPrice();
//            fee = fee + (Double.valueOf(strPri) * getCount);
//        }
//        return fee;
//    }
//}
