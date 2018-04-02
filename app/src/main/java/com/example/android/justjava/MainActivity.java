package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    String name;
    int ppc=5;
    int quan=1;
    int price;
    int bool=0;
    int booll=0;
    String s;
    public void increment(View view)
    {
        if(quan==100)
        {
            Toast.makeText(this,"You cannot have more than 100 coffee.",Toast.LENGTH_SHORT).show();
            return;
        }
        quan++;
        displayquan(quan);

    }
    public void decrement(View view)
    {
        if(quan==1)
        {
            Toast.makeText(this,"You cannot have less than 1 coffee.",Toast.LENGTH_SHORT).show();
            return;
        }
        quan--;
        displayquan(quan);
    }
    public void submitOrder(View view)
    {
        price=calculatePrice(quan);
        EditText n=(EditText) findViewById(R.id.name);
        name=n.getText().toString();       /*getText returns editable so converted to string to tore in a variable having data type string.*/
        CheckBox c=(CheckBox) findViewById(R.id.cream);
       if(c.isChecked())
            {
                bool=1;
                price+=quan*1;
            }
        CheckBox d=(CheckBox) findViewById(R.id.choco);
        if(d.isChecked())
        {
            booll=1;
            price+=quan*2;
        }
        //displayMsg(price);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:30.331462,76.404948"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
        //TextView a =(TextView)  findViewById(R.id.price_text_view);
        s="Name: "+name;
        if(bool==1)
            s=s+"\nWanna add some Whipped cream? yes";
        else
            s=s+"\nWanna add some Whipped cream? no";
        if(booll==1)
            s=s+"\nWanna add some Chocolate? yes";
        else
            s=s+"\nWanna add some Chocolate? no";
        s=s+"\nQuantity: "+quan;
        s=s+"\nTotal: "+"₹"+price;
        s=s+"\n"+getString(R.string.thank);
        //a.setText(s);
       /* a.setText(NumberFormat.getCurrencyInstance().format(number)+"\n Thanks for ordering!");*/
       Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Coffee Order for "+name );
        intent.putExtra(Intent.EXTRA_TEXT,s);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private int calculatePrice(int quan)
    {
        return(quan*ppc);
    }
    private void displayquan(int n)
    {
        TextView a=(TextView)findViewById(R.id.quantity_text_view);
        a.setText(""+n);
    }
}
