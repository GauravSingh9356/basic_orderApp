package com.example.orderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    int val;
    CheckBox checkBox;
    CheckBox choclate_checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btn);
        textView = findViewById(R.id.value);
         val=Integer.parseInt(textView.getText().toString());

    }
    public void increaseval(View view){
        val++;
        textView.setText(Integer.toString(val));
    }
    public void decreaseval(View view){
         val=Integer.parseInt(textView.getText().toString());
        val--;
        textView.setText(Integer.toString(val));
    }
    public void display(){
        if(val<1){
            Toast.makeText(MainActivity.this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(val>100){
            Toast.makeText(MainActivity.this, "Please be countable", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            int total;
            EditText et_name = findViewById(R.id.name);
            String name = et_name.getText().toString();
            TextView textView_price = findViewById(R.id.price);
            checkBox = findViewById(R.id.checkbox);
            choclate_checkbox = findViewById(R.id.chocolate_checkbox);
            if (checkBox.isChecked() && choclate_checkbox.isChecked()) {
                total = (5 + 2 + 1) * val;
                textView_price.setText("Name: " + name + "\nQuantity: " + val + "\nChoclate  included\nWhipped Ice-Cream included\n" + "Total: $" + total + "\nThank You!");
            } else if (choclate_checkbox.isChecked()) {
                total = (5 + 2) * val;
                textView_price.setText("Name: " + name + "\nQuantity: " + val + "\nChoclate  included\n" + "Total: $" + total + "\nThank You!");
            } else if (checkBox.isChecked()) {
                total = (val + 1) * 5;
                textView_price.setText("Name: " + name + "\nQuantity: " + val + "\nWhipped Ice-Cream included\n" + "Total: $" + total + "\nThank You!");
            } else
                textView_price.setText("Name: " + name + "\nQuantity: " + val + "\n" + "Total: $" + val * 5 + "\nThank You!");
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, "Simple order" + name);
            intent.putExtra(Intent.EXTRA_TEXT, textView_price.getText().toString());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }

    }
    public void order(View view){
        display();
    }
}
