package com.example.realestate.sellerscreen;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.Getsellerimages.RecyclerAdaptor;
import com.example.realestate.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class addpropertydetail extends AppCompatActivity {
    private  TextInputEditText title,description,mothrent,dateAvalible,arealooking,dimentions_get,rentmax,bedrooms,bathrooms;

    RecyclerView recyclerView;
    TextView textView;
    ImageView buttonImagepicker;
    Button btnpreview;
    ArrayList<Uri> list;
    RecyclerAdaptor adaptor;
    AppCompatButton demention,cumercial,plot,apotment,btnrent,btnsell;
    String colum[] = {
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE};
    final Calendar myCalendar= Calendar.getInstance();
    TextInputLayout dategettext;
    TextInputEditText deteshow;
    String selected="No Data";
    String sellorRent="No Data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpropertydetail);
        list = new ArrayList<>();
        btnpreview=findViewById(R.id.btnprevsell);
        bedrooms=findViewById(R.id.bedrooms);
        bathrooms=findViewById(R.id.bathrooms);
        recyclerView = findViewById(R.id.recyclerView);
        title=findViewById(R.id.title_add_input_addpro);
        description=findViewById(R.id.description_add_input_addpro);
        mothrent=findViewById(R.id.rent_per_month_addpro);
        dimentions_get=findViewById(R.id.dimention);
        rentmax=findViewById(R.id.rent_per_month_max);
        arealooking=findViewById(R.id.area_loking);
        btnsell=findViewById(R.id.materialButton2);
        btnrent=findViewById(R.id.materialButton3);
        dategettext=(TextInputLayout) findViewById(R.id.availible_from_date);
//        textView = findViewById(R.id.textView);
        demention=findViewById(R.id.materialButton);
        cumercial=findViewById(R.id.commercial);
        plot=findViewById(R.id.plat);
        apotment=findViewById(R.id.apatment);

        btnsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellorRent="sell";
                btnsell.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                btnrent.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));

            }
        });
        btnrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellorRent="rent";
                btnrent.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                btnsell.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));

            }
        });

        demention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected="Dimension";
                demention.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                cumercial.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                plot.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                apotment.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
            }
        });
        cumercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected="Commercial";
                cumercial.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                demention.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                plot.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                apotment.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
            }
        });
        plot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected="Plot";
                plot.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                cumercial.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                demention.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                apotment.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
            }
        });
        apotment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected="Apartment";
                apotment.setBackground(getResources().getDrawable(R.drawable.propertysellcluickonchange));
                demention.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                plot.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
                cumercial.setBackground(getResources().getDrawable(R.drawable.propertychoosebuttonbarder));
            }
        });


        buttonImagepicker = findViewById(R.id.sellerimagepic);
        adaptor = new RecyclerAdaptor(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(adaptor);
        deteshow= (TextInputEditText) findViewById(R.id.availible_from_date_addpro);
        dategettext=(TextInputLayout) findViewById(R.id.availible_from_date);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        deteshow.setEnabled(true);
        deteshow.setTextIsSelectable(true);
        deteshow.setFocusable(false);
        deteshow.setFocusableInTouchMode(false);
        deteshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(addpropertydetail.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        buttonImagepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.sellerimagepic:
                        openGalley();
                        break;
                }
            }
        });
        if ((ActivityCompat.checkSelfPermission(
                this, colum[0]) != PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(this, colum[1]) != PackageManager.PERMISSION_GRANTED)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(colum, 123);
            }
        }
        btnpreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringtitle,stringdes,stringmonthrent,stringdateav,stringareaAvlible,stringdim,bed,bath;
                stringtitle=title.getText().toString();
                stringdes=description.getText().toString();
                stringmonthrent=mothrent.getText().toString();
                stringdim=dimentions_get.getText().toString();
                stringareaAvlible=arealooking.getText().toString();
                stringdateav=deteshow.getText().toString();
                String maxprentget=rentmax.getText().toString();
                bed=bedrooms.getText().toString();
                bath=bathrooms.getText().toString();
                if (stringtitle.isEmpty())
                {
                    title.setError("Please Enter Title");
                }
                else if(stringdes.isEmpty())
                {
                    description.setError("Please Add Some Description");
                }
                else if(stringdateav.isEmpty())
                {
                    deteshow.setError("Choose Date When its Available");
                }
                else if (stringareaAvlible.isEmpty())
                {
                    arealooking.setError("Enter Address of Property");
                }
                else if(stringdim.isEmpty())
                {
                    dimentions_get.setError("Please Enter Dimensions");
                }
                else if(stringmonthrent.isEmpty())
                {
                    mothrent.setError("Enter Rant");
                }
                else if (list.isEmpty())
                {
                    Toast.makeText(addpropertydetail.this, "Please Insert Images", Toast.LENGTH_SHORT).show();
                }
                else if (selected=="No Data")
                {
                    Toast.makeText(addpropertydetail.this, "Select any one from property type", Toast.LENGTH_SHORT).show();
                }
                else if (maxprentget.isEmpty())
                {
                    rentmax.setError("Maximum Price??");
                    // Toast.makeText(WantedScreen.this, "Please Insert Images", Toast.LENGTH_SHORT).show();
                }
                else if (bed.isEmpty())
                {
                    bedrooms.setError("Bedrooms??");
                    // Toast.makeText(WantedScreen.this, "Please Insert Images", Toast.LENGTH_SHORT).show();
                }
                else if (bath.isEmpty())
                {
                    bathrooms.setError("Bathrooms??");
                    // Toast.makeText(WantedScreen.this, "Please Insert Images", Toast.LENGTH_SHORT).show();
                }
                else if (sellorRent=="No Data")
                {
                    Toast.makeText(addpropertydetail.this, "Select For Sell Or For Rent", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent=new Intent(addpropertydetail.this, previopropertyselladd.class);

                    intent.putExtra("title",stringtitle);
                    intent.putExtra("des",stringdes);
                    intent.putExtra("dateavalible",stringdateav);
                    intent.putExtra("Area",stringareaAvlible);
                    intent.putExtra("dim",stringdim);
                    intent.putExtra("minrent",stringmonthrent);
                    intent.putExtra("maxrent",maxprentget);
                    intent.putExtra("sellorRent",sellorRent);
                    intent.putExtra("bedrooms",bed);
                    intent.putExtra("bathrooms",bath);
                    intent.putExtra("listimage",list);
                    intent.putExtra("property_type",selected);
                    startActivity(intent);
                }
            }
        });
    }
    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        deteshow.setText(dateFormat.format(myCalendar.getTime()));
    }
    private void openGalley() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode==RESULT_OK){
            if(data.getClipData()!=null){
                int x=data.getClipData().getItemCount();
                for(int i=0;i<x;i++)
                {
                    list.add(data.getClipData().getItemAt(i).getUri());
                }

                adaptor.notifyDataSetChanged();
//                textView.setText("Image("+list.size()+")");
            }else if(data.getData()!=null){
                String imgurl=data.getData().getPath();
                list.add(Uri.parse(imgurl));
            }
        }
    }
    public void proprertyback(View view) {
        finish();
    }

    public void backmyproperty(View view) {
        finish();
    }
}