package com.example.module4lesson2drill1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("details",MODE_PRIVATE);
        editText = findViewById(R.id.input_et);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_menu_item){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm").setMessage("Are you sure you want to save?");
            builder .setPositiveButton("yes", (dialogInterface, i) -> {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("input",editText.getText().toString());
                editor.commit();
                editText.setText("");
                    });
            builder.setNegativeButton("no", (dialogInterface, i) ->
                Toast.makeText(this, "not save", Toast.LENGTH_SHORT).show()
            );

            builder.show();
            return true;
        }

        else if(item.getItemId() == R.id.load_menu_item){
            editText.setText(sp.getString("input",""));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}