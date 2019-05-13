package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
//import com.example.android.justjava.R;

/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
int quantity = 2;
int price = 0;
boolean isWhippedCream;
boolean isChocolate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**

     This method is called when the order button is clicked.
     */
    public void increment(View view) {

    if (quantity == 100) {
        Toast.makeText(this, getString(R.string.More), Toast.LENGTH_SHORT).show();
    return;
    }
        quantity = quantity + 1;
        displayQuantity(quantity);}


    public void decrement(View view) {

           if (quantity == 1) {
               Toast.makeText(this, getString(R.string.Less), Toast.LENGTH_SHORT).show();
           return;
           }
        quantity--;
            displayQuantity(quantity);

    }
    public void submitOrder(View view) {
        String message = createOrderSummary(quantity, check (), check2(), name(), calculatePrice());
        final Intent emailIntent = new Intent(android.content.Intent. ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Koffee");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"fischer@citydom.ru"});
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
          this.startActivity(Intent.createChooser(emailIntent, "Send Email"));


    }
public  boolean check(){
    CheckBox hasWhippedCream = findViewById(R.id.whipped_cream);
    isWhippedCream = hasWhippedCream.isChecked();
    return  isWhippedCream;
    }
public boolean check2(){
    CheckBox hasChocolate = findViewById(R.id.chocolate);
    isChocolate = hasChocolate.isChecked();
    return isChocolate;

}

public String name() {
    EditText name_q = (EditText) findViewById(R.id.name);
    String name_c = name_q.getText().toString();
    return name_c;
    }
    private int calculatePrice () {
        if (isWhippedCream == true && isChocolate == true) {
            price = quantity * 8;
        }
       else if (isWhippedCream == true) {
        price = quantity * 6;
        }
       else if (isChocolate == true){
            price = quantity * 7;
        }
            else
                price = quantity * 5;

        return price;
         }

   private String createOrderSummary (int quantity, boolean isWhippedCream, boolean isChocolate, String name_c, int price) {
        String message1 = getString(R.string.order_name,  name_c);
        String message11 = "\n" + getString(R.string.Add_whipped) + " " +  isWhippedCream;
        String message12 = "\n" + getString(R.string.Add_chocolate)+ " " + isChocolate;
        String message2 = "\n" + getString(R.string.Quantity_j) + " " + quantity;
        String message3 = "\n" + getString(R.string.Total) + price;
        String message4 = "\n" + getString(R.string.Thank);
        String message = message1 + message11 + message12 + message2 + message3 + message4;

        return message;
   }

    /**

     This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
