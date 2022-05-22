package com.teste.estudo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.teste.estudo.DAO.UserDAO;
import com.teste.estudo.entidades.User;
import com.teste.estudo.utils.BancoDeDados;

import java.util.regex.Pattern;

public class AdminCreateAcc extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    BancoDeDados banco;
    EditText username, email, password, confirmPassword;
    Button btnCreate;
    String text;
    User user1 = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_acc);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Disable Landscape Mode

        username = (EditText) findViewById(R.id.txtCreateUsernameAdmin);
        email = (EditText) findViewById(R.id.txtEmailAdmin);
        password = (EditText) findViewById(R.id.createPasswordAdmin);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordAdmin);
        btnCreate = (Button) findViewById(R.id.btnCreateAdmin);

        Spinner spinner = findViewById(R.id.spinnerCrt);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipoUser, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //conexão com o banco de dados:
                banco = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class,"BancoTeste").allowMainThreadQueries().build();

                String user_name = username.getText().toString().trim();
                String e_mail = email.getText().toString().trim();
                String pass_word = password.getText().toString();
                String pass_word2 = confirmPassword.getText().toString();
                if(isValidUsername(user_name)){
                    if(isValidEmail(e_mail)){
                        if(isValidPassword(pass_word)){
                            if(pass_word.equals(pass_word2)){
                                user1.nome = user_name;
                                user1.email = e_mail;
                                user1.senha = pass_word;
                                if(text == "NORMAL"){
                                    user1.tipoUser = User.TipoUser.NORMAL;
                                } else if(text == "FIDELIZADO") {
                                    user1.tipoUser = User.TipoUser.FIDELIZADO;
                                } else if(text == "ADMIN"){
                                    user1.tipoUser = User.TipoUser.ADMIN;
                                }
                                UserDAO userDAO = banco.userDAO();
                                userDAO.insertAll(user1);
                                Toast.makeText(AdminCreateAcc.this, getText(R.string.accountCreated), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                confirmPassword.setError(getText(R.string.passwordNoMatch));
                            }
                        } else {
                            password.setError(getText(R.string.passwordError));
                        }
                    } else {
                        email.setError(getText(R.string.emailError));
                    }
                } else {
                    username.setError(getText(R.string.usernameError));
                }
            }
        });
    }

    private boolean isValidEmail(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
    public static boolean isValidUsername(String target) {
        return(!(target.length() < 3));
    }
    public static boolean isValidPassword(String target) {
        return(!(target.length() < 4));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        user1.tipoUser = User.TipoUser.valueOf(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}