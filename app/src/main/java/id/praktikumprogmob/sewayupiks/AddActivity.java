package id.praktikumprogmob.sewayupiks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    String[] items ={"bit 150cc","miyo 200cc","pario 156cc"} ;
    AutoCompleteTextView pilihkendaraan;
    ArrayAdapter<String> adapterItems;
    EditText daritanggal;
    EditText hinggatanggal;
    SeekBar seekBar;
    TextView literbensin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        pilihkendaraan =findViewById(R.id.PilihKendaraan);
        daritanggal = findViewById(R.id.DariTanggal);
        hinggatanggal = findViewById(R.id.HinggaTanggal);

        adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, items);
        pilihkendaraan.setAdapter(adapterItems);

        pilihkendaraan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "Kendaraan: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        daritanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        daritanggal.setText(date);
                    }
                }, year, month,day);
                datePickerDialog.show();
            }
        });

        hinggatanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        hinggatanggal.setText(date);
                    }
                }, year, month,day);
                datePickerDialog.show();
            }
        });




    }
}