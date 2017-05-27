package com.example.eduardo.ranchoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dao.ProductDAO;
import model.Product;

/**
 * Created by eduardo on 25/05/2017.
 */

public class ProductAddActivity extends AppCompatActivity {

    public double price;
    TextWatcher watcherQuantity;
    TextWatcher watcherPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        EditText fieldQuantity = (EditText) findViewById(R.id.txtItemQuantity);


        watcherQuantity = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                TextView fieldTotalPrice = (TextView) findViewById(R.id.fieldTotalPrice);
                EditText editTextPrice = (EditText) findViewById(R.id.txtItemPrice);
                EditText editTextQuantity = (EditText) findViewById(R.id.txtItemQuantity);
                //double total = 10.0;
                int qtd = 0;

                qtd = Integer.parseInt(editTextQuantity.getText().toString());

                double price = Double.parseDouble(editTextPrice.getText().toString());

                double result = qtd * price;

                fieldTotalPrice.setText("R$"+Double.toString(result));
            }
        };

        watcherPrice = new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                TextView fieldTotalPrice = (TextView) findViewById(R.id.fieldTotalPrice);
                fieldTotalPrice.setText(Double.toString(price));
            }
        };

        //muda valor do preço quando a quantidade e alterada
        fieldQuantity.addTextChangedListener(watcherQuantity);

    }

    public double calcPrice(double price, int quantity){
        return price * quantity;
    }

    public void save(View v){
        EditText editTextName = (EditText) findViewById(R.id.txtItemName);
        EditText editTextDescription = (EditText) findViewById(R.id.txtItemDescription);
        EditText editTextPrice = (EditText) findViewById(R.id.txtItemPrice);
        EditText editTextQuantity = (EditText) findViewById(R.id.txtItemQuantity);

        //cria objeto Item e salva pelo DAO
        Product product = new Product(editTextName.getText().toString(),  editTextDescription.getText().toString(),  Double.parseDouble(editTextPrice.getText().toString()), Integer.parseInt(editTextQuantity.getText().toString()));
        ProductDAO dao = new ProductDAO(this);
        if(dao.create(product)){
            Toast.makeText(this,"Produto incluído com sucesso!",Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this,"Erro ao incluir item",Toast.LENGTH_SHORT).show();
        }
    }
}
