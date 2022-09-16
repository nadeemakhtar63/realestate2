package com.example.realestate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.realestate.Fragments.NavigationScreens.ChatFragment;
import com.example.realestate.Fragments.NavigationScreens.HomeFragment;
import com.example.realestate.Fragments.NavigationScreens.ProfileFragment;
import com.example.realestate.myproperty.MyProperty;
import com.example.realestate.offerscreens.Recived_Offer;
import com.example.realestate.offerscreens.Send_Offer;
import com.example.realestate.sellerscreen.addpropertydetail;
import com.example.realestate.wantedscreen.WantedScreen;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeNav extends AppCompatActivity {
    BottomAppBar appBar;
    FloatingActionButton floatingActionButton;
    BottomNavigationView bottomNavigationView;

    private NavigationView navigationView;
    ImageView menuImage,search;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nav);
        mAuth=FirebaseAuth.getInstance();
        floatingActionButton = findViewById(R.id.fbnavbar);
//        setSupportActionBar(appBar);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        loadFragment(new HomeFragment());
//        HomeFragment homeFragment=new HomeFragment();
//        ChatFragment chatFragment=new ChatFragment();

        search=findViewById(R.id.searc_home_nave);
        navigationView = findViewById(R.id.nav_view);
        menuImage = findViewById(R.id.imageView);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeNav.this,Discover.class);
                startActivity(intent);
            }
        });
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        // this is the menu image to open Navigation Drawer.

        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case  R.id.sendoffer:
                        Intent in=new Intent(HomeNav.this, Send_Offer.class);
                        startActivity(in);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.reciveoffer:
                        Intent inte=new Intent(HomeNav.this, Recived_Offer.class);
                        startActivity(inte);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawermyads:
                        Intent intent=new Intent(HomeNav.this, MyProperty.class);
                        startActivity(intent);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawerprofile:
//                    Toast.makeText(HomeNav.this, "Chat", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(HomeNav.this, ProfileEdit.class);
                        startActivity(i);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    case  R.id.drawernotification:
                        Intent ie=new Intent(HomeNav.this, NotificationScreen.class);
                        startActivity(ie);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.drawerprivicy:
//
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ZamView");
                            String shareMessage= "\nHey check out my app at:\n\n";
                            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "choose one"));
                        } catch(Exception e) {
                            //e.toString();
                        }
                        return true;

                    case R.id.drawerlogout:
                        // on click logout a alert dialog show to ask that you want to logout or not.

                        AlertDialog alertDialog = new AlertDialog.Builder(HomeNav.this)

                                .setIcon(android.R.drawable.ic_dialog_alert)

                                .setTitle("Are you sure you want to Logout")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //set what would happen when positive button is clicked
                                        FirebaseAuth auth = FirebaseAuth.getInstance();
                                        auth.signOut();

                                        Intent intent = new Intent(HomeNav.this, registersecond.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //set what should happen when negative button is clicked


                                    }
                                })
                                .show();
                        return true;

                    default:
                        return false;
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
//                fb_bottomsheet fb=new fb_bottomsheet();
//                fb.show(getSupportFragmentManager(),"the bottomsheet");
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        loadFragment(new HomeFragment());
                        //getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                        return true;
                    case R.id.ic_favrit:
                        loadFragment(new ChatFragment());
//                        Toast.makeText(HomeNav.this, "Favirit", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, secondFragment).commit();
                        return true;
                    case R.id.ic_chat:
                        Intent intent=new Intent(HomeNav.this, OffersScreen.class);
                        startActivity(intent);
                       // Toast.makeText(HomeNav.this, "Chat", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, chatFragment).commit();
                        return true;
                    case R.id.ic_profile:
                        loadFragment(new ProfileFragment());
                        //Toast.makeText(HomeNav.this, "Profile", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, thirdFragment).commit();
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.bottomNavigationView);
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void showBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottomsheetlayout);
        ConstraintLayout wanted,seller;
        wanted=bottomSheetDialog.findViewById(R.id.constraintLayout);
        seller=bottomSheetDialog.findViewById(R.id.constraintLayout2);
        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser().getUid()!=null)
                {
                    Intent intent = new Intent(HomeNav.this, addpropertydetail.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(HomeNav.this, RegisterScreen.class);
                    startActivity(intent);
                }
            }
        });

        wanted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeNav.this, WantedScreen.class);
                startActivity(intent);
            }
        });
//        LinearLayout copy = bottomSheetDialog.findViewById(R.id.copyLinearLayout);
//        LinearLayout share = bottomSheetDialog.findViewById(R.id.shareLinearLayout);
//        LinearLayout upload = bottomSheetDialog.findViewById(R.id.uploadLinearLayout);
//        LinearLayout download = bottomSheetDialog.findViewById(R.id.download);
//        LinearLayout delete = bottomSheetDialog.findViewById(R.id.delete);

        bottomSheetDialog.show();
    }
}