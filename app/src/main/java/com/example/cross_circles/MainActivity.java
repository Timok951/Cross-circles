package com.example.cross_circles;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    Button buttons[];
    int wins;
    int loose;
    TextView winsdisplay;
    TextView loosedisplay;
    SharedPreferences themeSettings;
    SharedPreferences.Editor settingsEditor;
    ImageButton imageTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        themeSettings = getSharedPreferences("SETTINGS", MODE_PRIVATE);
        // Проверяем, есть ли уже сохраненные настройки
        if (!themeSettings.contains("MODE_NIGHT_ON")) {
            settingsEditor = themeSettings.edit();
            settingsEditor.putBoolean("MODE_NIGHT_ON", false);
            settingsEditor.apply();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            Toast.makeText(this, "С запуском", Toast.LENGTH_SHORT).show();
        } else {
            setCurrentTheme();
        }

        setContentView(R.layout.activity_main);

        // Находим кнопка для изменения темы
        imageTheme = findViewById(R.id.imgbtn1);
        updateImageButton();


         winsdisplay = findViewById(R.id.winscount);
         loosedisplay = findViewById(R.id.winscount2);
        winsdisplay.setText("Wins"+String.valueOf(wins));
        loosedisplay.setText("Loose"+String.valueOf(loose));

        buttons = new Button[]{findViewById(R.id.b1), findViewById(R.id.b2), findViewById(R.id.b3), findViewById(R.id.b4), findViewById(R.id.b5), findViewById(R.id.b6), findViewById(R.id.b7), findViewById(R.id.b8), findViewById(R.id.b9)};

        for (Button i: buttons
             ) {
            i.setOnClickListener(this::onButtonClick);
        }
        // при нажатии на кнопку
        imageTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Проверяем текущее состояние и переключаем тему
                if (themeSettings.getBoolean("MODE_NIGHT_ON", false)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    settingsEditor = themeSettings.edit();
                    settingsEditor.putBoolean("MODE_NIGHT_ON", false);
                    settingsEditor.apply();
                    Toast.makeText(MainActivity.this, "Тёмная тема отключена", Toast.LENGTH_SHORT).show();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    settingsEditor = themeSettings.edit();
                    settingsEditor.putBoolean("MODE_NIGHT_ON", true);
                    settingsEditor.apply();
                    Toast.makeText(MainActivity.this, "Тёмная тема включена", Toast.LENGTH_SHORT).show();
                }
                // Обновляем изображение кнопки
                updateImageButton();
            }
        });

    }


    // Метод для обновления изображения в зависимости от темы
    private void updateImageButton() {
        if (themeSettings.getBoolean("MODE_NIGHT_ON", false)) {
            imageTheme.setImageResource(R.drawable.moonlight); // здесь укажим иконку для светлой темы
        } else {
            imageTheme.setImageResource(R.drawable.moondark); // здесь укажим иконку для темной темы
        }
    }

    private void setCurrentTheme() {
        if (themeSettings.getBoolean("MODE_NIGHT_ON", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void onButtonClick(View view) {
        Button clickedButton = (Button) view;
        clickedButton.setText("x");
        clickedButton.setEnabled(false);
        AI();
        Wincon(buttons);
    }

    private void AI(){
        Random random = new Random();
        int randomint = random.nextInt(buttons.length);
        boolean isunique = false;
        do
        {


            String buttonText = buttons[randomint].getText().toString();

            if (buttonText.isEmpty() || buttonText.equals("_")) {
                buttons[randomint].setText("0");
                buttons[randomint].setEnabled(false);
                isunique = true;
            } else {
                randomint = random.nextInt(buttons.length);
            }
        }while (!isunique);
    }

    public void Wincon(Button[] buttons) {


        if ((buttons[0].getText().toString().equals("x") && buttons[1].getText().toString().equals("x") && buttons[2].getText().toString().equals("x")) ||
                (buttons[3].getText().toString().equals("x") && buttons[4].getText().toString().equals("x") && buttons[5].getText().toString().equals("x")) ||
                (buttons[6].getText().toString().equals("x") && buttons[7].getText().toString().equals("x") && buttons[8].getText().toString().equals("x")) ||
                (buttons[0].getText().toString().equals("x") && buttons[3].getText().toString().equals("x") && buttons[6].getText().toString().equals("x")) ||
                (buttons[1].getText().toString().equals("x") && buttons[4].getText().toString().equals("x") && buttons[7].getText().toString().equals("x")) ||
                (buttons[2].getText().toString().equals("x") && buttons[5].getText().toString().equals("x") && buttons[8].getText().toString().equals("x")) ||
                (buttons[0].getText().toString().equals("x") && buttons[4].getText().toString().equals("x") && buttons[8].getText().toString().equals("x")) ||
                (buttons[2].getText().toString().equals("x") && buttons[4].getText().toString().equals("x") && buttons[6].getText().toString().equals("x"))) {
                wins = wins +1;
            disableButtons(buttons);
            for (Button button : buttons) {
                button.setText(""); //
            }
            enableButtons(buttons);
            winsdisplay.setText("Wins"+String.valueOf(wins));




        }


        else if ((buttons[0].getText().toString().equals("0") && buttons[1].getText().toString().equals("0") && buttons[2].getText().toString().equals("0")) ||
                (buttons[3].getText().toString().equals("0") && buttons[4].getText().toString().equals("0") && buttons[5].getText().toString().equals("0")) ||
                (buttons[6].getText().toString().equals("0") && buttons[7].getText().toString().equals("0") && buttons[8].getText().toString().equals("0")) ||
                (buttons[0].getText().toString().equals("0") && buttons[3].getText().toString().equals("0") && buttons[6].getText().toString().equals("0")) ||
                (buttons[1].getText().toString().equals("0") && buttons[4].getText().toString().equals("0") && buttons[7].getText().toString().equals("0")) ||
                (buttons[2].getText().toString().equals("0") && buttons[5].getText().toString().equals("0") && buttons[8].getText().toString().equals("0")) ||
                (buttons[0].getText().toString().equals("0") && buttons[4].getText().toString().equals("0") && buttons[8].getText().toString().equals("0")) ||
                (buttons[2].getText().toString().equals("0") && buttons[4].getText().toString().equals("0") && buttons[6].getText().toString().equals("0"))) {

            disableButtons(buttons);
            loose = loose +1;
            for (Button button : buttons) {
                button.setText(""); //
            }
            enableButtons(buttons);
            loosedisplay.setText("Loose"+String.valueOf(loose));


        }
    }
    private void disableButtons(Button[] buttons) {
        for (Button button : buttons) {
            button.setEnabled(false);
        }



}
    private void enableButtons(Button[] buttons){
        for (Button button : buttons) {
            button.setEnabled(true);
        }
    }
}

