package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.eduardo.ranchoapp.R;

import java.text.NumberFormat;
import java.util.List;

import model.Product;

/**
 * Created by iossenac on 27/05/17.
 */

public class ProductAdapter extends BaseAdapter{
    private List<Product> listProducts;
    private Context context;

    public ProductAdapter(List<Product> listProducts, Context context) {
        this.listProducts = listProducts;
        this.context = context;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount(){
        return listProducts.size();
    }

    @Override
    public Object getItem(int position){
        return  listProducts.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Product product = listProducts.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.product_list_item,null);

        TextView textName = (TextView) view.findViewById(R.id.textName);
        textName.setText(product.getName());

        TextView textQuantity = (TextView) view.findViewById(R.id.textQuantity);
        textQuantity.setText(Integer.toString(product.getQuantity()));

        return view;

    }
}
