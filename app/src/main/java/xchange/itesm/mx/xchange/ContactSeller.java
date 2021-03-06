package xchange.itesm.mx.xchange;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

/**
 * Created by Ciria on 26/11/2016.
 */
public class ContactSeller extends AppCompatActivity {
    Context context;
    AllData data=new AllData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_seller);
        ImageView imageView=(ImageView)findViewById(R.id.imgProd);
        ImageView imageViewUsr=(ImageView)findViewById(R.id.imgUsr);
        TextView txtV=(TextView)findViewById(R.id.txtProd);
        RatingBar ratingBar= (RatingBar)findViewById(R.id.ratingBar);
        final EditText editText=(EditText)findViewById(R.id.offerToSeller);
        Button sendButton=(Button)findViewById(R.id.sendButton);
        final Button radioButton=(Button) findViewById(R.id.radioButtonConfirmUsr);

        Intent intent=getIntent();
        final int position=intent.getIntExtra("pos",0);
        Picasso.with(context).load(data.getArticle()[position]).into(imageView);
        txtV.setText(data.getDescription()[position]);
        if(data.getOfferWishList()[position]!=0) {
            if (data.getSellerAccepted()[position]==true){
                //disable edittext for making offers
                editText.setFocusable(false);
                radioButton.setVisibility(View.VISIBLE);
                if(data.getConfirmDone()[position]=="DONE"){
                    radioButton.setText("Match Complete!");
                }
                else {
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            radioButton.setVisibility(View.INVISIBLE);
                            data.updateConfirmDone(position, "DONE");
                            Toast.makeText(ContactSeller.this, data.getConfirmDone()[position], Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ContactSeller.this, WishList.class);
                            startActivity(intent);

                        }
                    });
                }
            }
        }
        int seller=data.whosSeller(position);
        Picasso.with(context).load(data.getUsers()[seller]).into(imageViewUsr);
        ratingBar.setRating(data.getStars()[seller]);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int offer=Integer.parseInt(editText.getText().toString());
                data.updateOfferWishList(position,offer);
                Toast.makeText(ContactSeller.this, data.getOfferWishList()[0]+" "+data.getOfferWishList()[1]+" "+data.getOfferWishList()[2]+" "+data.getOfferWishList()[3], Toast.LENGTH_SHORT).show();
                Intent intent= new Intent (ContactSeller.this,WishList.class);
                startActivity(intent);
            }
        });
        imageViewUsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent (ContactSeller.this,SellerDetails.class);
                intent.putExtra("pos",position);
                startActivity(intent);
            }
        });


    }
}
