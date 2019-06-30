package com.example.smartclassapp.Fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.smartclassapp.AboutUsFragment;
import com.example.smartclassapp.FragmentForm;
import com.example.smartclassapp.MainActivity;
import com.example.smartclassapp.PDFWork.FragmentNotes;
import com.example.smartclassapp.PaperView.FragmentPaper;
import com.example.smartclassapp.ProfileView.FragmentShowProfile;
import com.example.smartclassapp.ProfileView.ProfilePicActivity;
import com.example.smartclassapp.R;
import com.example.smartclassapp.SignUp;
import com.example.smartclassapp.SlideShow.IntroActivity;
import com.example.smartclassapp.VideoFragment;
import com.google.firebase.auth.FirebaseAuth;

public class CommentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private FloatingActionButton addPostBtn;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //navigation open
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CommentFragment()).commit();


        addPostBtn = findViewById(R.id.add_post_btn);
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newPostIntent = new Intent(CommentActivity.this, NewPostActivity.class);
                startActivity(newPostIntent);

            }
        });

    }

    //navigation work
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CommentActivity.this,MainActivity.class));
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VideoFragment()).commit();
                break;
            case R.id.nav_profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                startActivity(new Intent(CommentActivity.this,ProfilePicActivity.class));
                break;
            case R.id.nav_notes:
                Toast.makeText(this, "Notes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CommentActivity.this,MainActivity.class));
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentNotes()).commit();
                break;
            case R.id.nav_papers:
                Toast.makeText(this, "Papers", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CommentActivity.this,MainActivity.class));
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentPaper()).commit();
                break;
            case R.id.nav_fill_form:
                Toast.makeText(this, "Fill Form", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CommentActivity.this,MainActivity.class));
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentForm()).commit();
                break;
            case R.id.nav_view_user:
                Toast.makeText(this, "View Users Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CommentActivity.this,MainActivity.class));
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentShowProfile()).commit();
                break;
            case R.id.nav_comment:
                Toast.makeText(this, "Comments", Toast.LENGTH_SHORT).show();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                startActivity(new Intent(CommentActivity.this,CommentActivity.class));
                break;
            case R.id.nav_about_us:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CommentActivity.this,MainActivity.class));
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutUsFragment()).commit();
                break;
            case R.id.nav_contact_us:
                startActivity(new Intent(CommentActivity.this,MainActivity.class));
                Toast.makeText(this, "Contact Us", Toast.LENGTH_SHORT).show();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;

            case R.id.nav_logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(CommentActivity.this, SignUp.class));

                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                //startActivity(new Intent(CommentActivity.this, IntroActivity.class));

                break;

        }
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
    //navigation work end


}
