package com.example.eduardo.ranchoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import dao.ProductDAO;
import model.Product;

/**
 * Created by iossenac on 27/05/17.
 */

public class ProductShowActivity extends AppCompatActivity {
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_show);

        Intent it = getIntent();
        //recebe objeto serializado
        this.product = (Product) it.getSerializableExtra("produto");

        TextView txtName = (TextView) findViewById(R.id.txtProductNameShow);
        txtName.setText(product.getName());

        TextView txtDescription = (TextView) findViewById(R.id.txtProductDescriptionShow);
        txtDescription.setText(product.getDescription());

        TextView txtQuantity = (TextView) findViewById(R.id.txtQuantityShow);
        txtQuantity.setText(product.getQuantity());

        TextView txtPrice = (TextView) findViewById(R.id.txtPriceShow);
        txtPrice.setText("R$ "+Double.toString(product.getTotal()));

    }

    public void getProduct(View v){
        ProductDAO dao = new ProductDAO(this);
        dao.getCart(product);

        Toast.makeText(this,"Produto adicionado ao carrinho com sucesso!",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void deleteProduct(View v){
        ProductDAO dao = new ProductDAO(this);
        dao.destroy(product);

        Toast.makeText(this,"Produto excluído com sucesso!",Toast.LENGTH_SHORT).show();
        finish();

    }
}
