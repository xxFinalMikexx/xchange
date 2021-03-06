package xchange.itesm.mx.xchange;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by erikiado on 11/30/16.
 */

public class AdapterSellerProduct extends RecyclerView.Adapter<AdapterSellerProduct.ViewHolder> {
    private List<Product> mDataSet;
    private String mId;
    private Context context;

    private static final int CHAT_RIGHT = 1;
    private static final int CHAT_LEFT = 2;

    /**
     * Inner Class for a recycler view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, description;
        public ImageView productImage;
        public RelativeLayout background;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) itemView.findViewById(R.id.tv_product_seller_title);
            price = (TextView) itemView.findViewById(R.id.tv_product_seller_price);
            description = (TextView) itemView.findViewById(R.id.tv_product_seller_description);
            productImage = (ImageView) itemView.findViewById(R.id.iv_product_seller_image);
            background = (RelativeLayout) itemView.findViewById(R.id.rv_product_seller_bg);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param dataSet Message list
     */
    public AdapterSellerProduct(Context cxt, List<Product> dataSet) {
        mDataSet = dataSet;
        context = cxt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rv_product_seller, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        return CHAT_RIGHT;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product product = mDataSet.get(position);
        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,ActivitySellerProductDetail.class);
                i.putExtra("productId",product.getId());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        holder.title.setText(product.getTitle());
        holder.price.setText("$"+product.getPrice());
        holder.description.setText(product.getDescription());
        if(!product.getImagePath().equals(""))
            Picasso.with(context).load(product.getImagePath()).resize(100,100).centerCrop().into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}