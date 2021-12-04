package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fileioexample.store.Order;
import com.example.fileioexample.store.Product;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.NavigationUtils;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Current.Current_Order;

public class Order_Details extends AppCompatActivity {
    ListView listview;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_order_detail);

        Order order = findOrderInfo();

        Intent intent = getIntent();
        TextView textView = findViewById(R.id.textView4);
        textView.setText("Store: " + CurrentUser.store.getName());
        TextView textView1 = findViewById(R.id.textView5);
        textView1.setText("Order: " + order.getOrderId());
        TextView textView2 = findViewById(R.id.textView6);
        textView2.setText("Customer: " + order.getCustomerUsername());

        /*Intent intent = getIntent();
        Current_Order Order = new Current_Order();
        String Owner = Order.Owner;
        TextView textView = findViewById(R.id.textView4);
        textView.setText("Store: " + Owner);
        String Order_number = Order.OrderToken;
        TextView textView1 = findViewById(R.id.textView5);
        textView1.setText("Order: " + Order_number);
        String Customer = Order.Customer;
        TextView textView2 = findViewById(R.id.textView6);
        textView2.setText("Customer: " + Customer);*/

        listview = findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_view, list);
        listview.setAdapter(adapter);

        /*for(Product p: order.getProducts()){
            String txt = info.getBrand() + " " + info.getName() + ": " + info.getPrice() + " " + info.getQuantity();
            list.add(txt);
        }*/

        DatabaseReference Database = FirebaseDatabase.getInstance().getReference().child("stores")
                .child(CurrentUser.username).child("orderList").child(Integer.toString(CurrentUser.currentOrderToken.getOrderId())).child("products");
        Database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list.clear();
                for(DataSnapshot snapshot: datasnapshot.getChildren()){
                    Product currentProduct = snapshot.getValue(Product.class);
                    String txt = currentProduct.getBrand() + " " + currentProduct.getName() + ": " + currentProduct.getPrice() + " " + currentProduct.getQuantity();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*DatabaseReference Database = FirebaseDatabase.getInstance().getReference().child("stores").child(Owner).child("orderList").child(Order_number).child("products");
        Database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list.clear();
                for(DataSnapshot snapshot: datasnapshot.getChildren()){
                    information info = snapshot.getValue(information.class);
                    String txt = info.getBrand() + " " + info.getName() + ": " + info.getPrice() + " " + info.getQuantity();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = database.getReference();
                    dbRef = database.getReference().child("stores").child(CurrentUser.username)
                            .child("orderList").child(Integer.toString(CurrentUser.currentOrderToken.getOrderId())).child("fulfilled");
                    dbRef.setValue(true);
                }
                else{
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = database.getReference();
                    dbRef = database.getReference().child("stores").child(CurrentUser.username)
                            .child("orderList").child(Integer.toString(CurrentUser.currentOrderToken.getOrderId())).child("fulfilled");
                    dbRef.setValue(false);
                }
            }
        });

        NavigationUtils.setupOwnerNavigationMenu(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationUtils.currentActivity = this;
    }

    //Fills the order info using CurrentUser.currentOrderToken
    private Order findOrderInfo(){

        for(Order o : CurrentUser.store.getOrdersList()){
            if(o.getOrderId() == CurrentUser.currentOrderToken.getOrderId()){
                return o;
            }
        }

        return null;

    }

    /*public void Click_list_product(){
        Intent intent = new Intent(this,OwnerListProductsActivity.class);
        startActivity(intent);
    }

    public void Click_see_all_orders(){
        Intent intent = new Intent(this,OwnerOrdersList.class);
        startActivity(intent);
    }

    public void Click_profile(){
        Intent intent = new Intent(this,OwnerProfileActivity.class);
        startActivity(intent);
    }

    public void Click_Logout(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }*/


    public static class information {
        String brand;
        String name;
        String price;
        String quantity;

        public information() {
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }
}