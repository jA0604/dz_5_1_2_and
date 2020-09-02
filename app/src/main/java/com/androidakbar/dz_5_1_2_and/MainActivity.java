package com.androidakbar.dz_5_1_2_and;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  static final int CHOOSE_IMG = 0;
    private Intent intentSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar appToolbar = findViewById(R.id.app_toolbar);
        appToolbar.setTitle(R.string.name_dz);
        appToolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryText));
        appToolbar.setTitleTextAppearance(this, R.style.ToolBarTitleTextAppearance);
        setSupportActionBar(appToolbar);

        intentSettings = new Intent(MainActivity.this, SettingsActivity.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mm_setting: {
                startActivityForResult(intentSettings, CHOOSE_IMG);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView ivSubstate = (ImageView) findViewById(R.id.iv_sub);

        if (requestCode == CHOOSE_IMG) {
            if (resultCode == RESULT_OK) {
                byte[] byteArray = data.getByteArrayExtra(SettingsActivity.IMG);
                Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                ivSubstate.setImageBitmap(bmp);

            }else {
                Toast.makeText(this, "Файл с картинкой отсутствует", Toast.LENGTH_LONG).show();
            }
        }
    }
}