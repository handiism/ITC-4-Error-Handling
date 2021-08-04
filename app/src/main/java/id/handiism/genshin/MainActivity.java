package id.handiism.genshin;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import id.handiism.genshin.vision.Anemo;
import id.handiism.genshin.vision.Hydro;
import id.handiism.genshin.vision.Pyro;

public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private ConstraintLayout constraintLayout;
    private TextView tvCharacters, tvList, tvMine, tvEnemy, tvReaction, tvResult;
    private EditText etName, etVision, etHP, etAttack, etElemental;
    private Button btnInput;

    private String name, vision;
    private int healthPoint, attack, elemental;

    private Anemo anemo1, anemo2;
    private Hydro hydro1, hydro2;
    private Pyro pyro1, pyro2;
    private Object mineObj, enemyObj;
    private boolean war = false;

    private boolean check() {
        boolean bool = false;
        try {
            name = etName.getText().toString();
            vision = etVision.getText().toString();
            healthPoint = Integer.parseInt(etHP.getText().toString());
            attack = Integer.parseInt(etAttack.getText().toString());
            elemental = Integer.parseInt(etElemental.getText().toString());
            if (vision.equals("Anemo") || vision.equals("Hydro") || vision.equals("Pyro")) {
                if (name.isEmpty()) {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new ClassNotFoundException();
            }
            return true;
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Besaran Nilai Harus Bilangan Bulat", Toast.LENGTH_SHORT).show();
            return false;
        }  catch (IllegalArgumentException e) {
            Toast.makeText(MainActivity.this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            return false;
        } catch (ClassNotFoundException e) { // cek
            Toast.makeText(MainActivity.this, "Vision Tidak Tersedia", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private String result(Object mine, Object enemy) {
        ArrayList<Object> list = new ArrayList<>();
        String rtn = null;
        list.add(mine);
        list.add(enemy);
        int random = ThreadLocalRandom.current().nextInt(0,2);
        try {
            if ((Anemo) list.get(random) != null) {
                rtn = ((Anemo) list.get(random)).getName();
            }
        } catch (Exception e) {
            Log.d(TAG, "Kelas Salah");
        }
        try {
            if ((Pyro) list.get(random) != null) {
                rtn = ((Pyro) list.get(random)).getName();
            }
        } catch (Exception e) {
            Log.d(TAG, "Kelas Salah");
        }
        try {
            if ((Hydro) list.get(random) != null) {
                rtn =  ((Hydro) list.get(random)).getName();
            }
        } catch (Exception e) {
            Log.d(TAG, "Kelas Salah");
        }
        return rtn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.constraint);
        tvCharacters = findViewById(R.id.tv_characters);
        tvList = findViewById(R.id.tv_list);
        tvMine = findViewById(R.id.tv_mine);
        tvEnemy = findViewById(R.id.tv_enemy);
        tvReaction = findViewById(R.id.tv_reaction);
        tvResult = findViewById(R.id.tv_result);
        etName = findViewById(R.id.et_name);
        etVision = findViewById(R.id.et_vision);
        etHP = findViewById(R.id.et_hp);
        etAttack = findViewById(R.id.et_attack);
        etElemental = findViewById(R.id.et_elemental);
        btnInput = findViewById(R.id.btn_input);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean reset = false;
                if (check()) {
                    if (vision.equals("Anemo")) {
                        anemo1 = new Anemo(name, healthPoint, attack, elemental);
                        mineObj = anemo1;
                        reset = true;
                    } else if (vision.equals("Hydro")) {
                        hydro1 = new Hydro(name, healthPoint, attack, elemental);
                        mineObj = hydro1;
                        reset = true;
                    } else if (vision.equals("Pyro")) {
                        pyro1 = new Pyro(name, healthPoint, attack, elemental);
                        mineObj = pyro1;
                        reset = true;
                    }
                }
                if (reset) {
                    Toast.makeText(MainActivity.this, "Penambahan Karakter Berhasil", Toast.LENGTH_SHORT).show();
                    tvMine.setText(name+" /"+vision+" (Aku)");
                    tvCharacters.setText("Masukkan Karakter Musuh");
                    etName.getText().clear();
                    etVision.getText().clear();
                    etHP.getText().clear();
                    etAttack.getText().clear();
                    etElemental.getText().clear();
                    btnInput.setHint("Tarungkan");
                    btnInput.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (check()) {
                                if (vision.equals("Anemo")) {
                                    anemo2 = new Anemo(name, healthPoint, attack, elemental);
                                    enemyObj = anemo2;
                                    war = true;
                                } else if (vision.equals("Hydro")) {
                                    hydro2 = new Hydro(name, healthPoint, attack, elemental);
                                    enemyObj = hydro2;
                                    war = true;
                                } else if (vision.equals("Pyro")) {
                                    pyro2 = new Pyro(name, healthPoint, attack, elemental);
                                    enemyObj = pyro2;
                                    war = true;
                                }
                                tvEnemy.setText(name+" - "+vision+" (Musuh)");
                                tvResult.setText(result(mineObj,enemyObj) + " Menang");
                            }
                        }
                    });
                }
            }
        });
    }
}