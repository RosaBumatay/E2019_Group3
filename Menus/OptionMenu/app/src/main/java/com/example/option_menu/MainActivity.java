package com.example.option_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.search_item:
                Toast.makeText(this, "Search" +item.getTitle(), Toast.LENGTH_SHORT).show();

                return true;
            case R.id.upload_item:
                Toast.makeText(this, "Upload" +item.getTitle(), Toast.LENGTH_SHORT).show();

                return true;
            case R.id.copy_item:
                Toast.makeText(this, "Copy" +item.getTitle(), Toast.LENGTH_SHORT).show();

                return true;
            case R.id.print_item:
                Toast.makeText(this, "Print" +item.getTitle(), Toast.LENGTH_SHORT).show();

                return true;
            case R.id.share_item:
                Toast.makeText(this, "Share" +item.getTitle(), Toast.LENGTH_SHORT).show();

                return true;
            case R.id.bookmark_item:
                Toast.makeText(this, "Book Mark" +item.getTitle(), Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}