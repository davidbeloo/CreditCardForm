package com.thepinkandroid.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.holo_theme_form:
                startActivity(new Intent(this, HoloThemeActivity.class));
                break;
            case R.id.material_light_theme_form:
                startActivity(new Intent(this, MaterialLightThemeActivity.class));
                break;
            case R.id.material_dark_theme_form:
                startActivity(new Intent(this, MaterialDarkThemeActivity.class));
            default:
                break;
        }
    }
}
