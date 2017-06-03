package com.example.eduardo.ranchoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.ProductAdapter;
import dao.ProductDAO;
import model.Product;

public class MainActivity extends AppCompatActivity {
    List<Product> listProducts = new ArrayList<>();
    ProductAdapter adapter;
    ProductDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //joga conteudo inflate do adapter na view da lista
        adapter = new ProductAdapter(listProducts, this);
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        Product product = listProducts.get(position);

                        Intent it = new Intent(MainActivity.this, ProductShowActivity.class);
                        //envia objeto serializado para outra acitivity
                        it.putExtra("produto",product);
                        startActivity(it);
                    }
                }
        );

        //TextView txtTotal = (TextView) findViewById(R.id.txtTotal);
        //coloca os produtos de volta no carrinho quando inicia a aplicacao
        dao = new ProductDAO(this);
        dao.getCartAll();
    }

    @Override
    public void onResume() {
        super.onResume();
        //faz nova busca no banco e atualiza em caso de inclusao de item
        dao = new ProductDAO(this);
        listProducts = dao.all();
        adapter.setListProducts(listProducts);
        adapter.notifyDataSetChanged();
        TextView txtTotal = (TextView) findViewById(R.id.txtTotal);
    }

    public void openItemAdd(View v){
        Intent it = new Intent(MainActivity.this, ProductAddActivity.class);
        startActivity(it);
    }

    public void deleteItem(View v) {

    }
}
