package net.ddns.nimna.chat_away_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.ddns.nimna.chat_away_v2.Model.User;

/**
 * @author Nimna Ekanayaka
 * @since 1/18/2016
 */
public class SigninActivity extends AppCompatActivity {

    private TextView tvSignup;
    private Button bttnSignup;
    private Button login;
    private EditText etUsername;
    private EditText etPassword;

    private String username;
    private String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        etUsername = (EditText)findViewById(R.id.tfUsername);
        etPassword = (EditText)findViewById(R.id.tfPassword);
        login = (Button)findViewById(R.id.bttnLogin);

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                userSignIn();

            }
        });
    }

    /**
     * This method will determine if the login information that the user enters is valid via the ServerRequests async task
     */
    public void userSignIn(){

        //creating ServerRequest object and then calling the method to fetch the users data
        ServerRequests sr = new ServerRequests();
        sr.fetchUserDataInBackground(this.username, this.password, new AsyncResponse() {
            @Override
            public void done(User user) {
                //if a user is returned then the user is brought to the profile activity
                if(user!=null) {
                    Intent i = new Intent(SigninActivity.this, ProfileActivity.class);
                    final Intent serviceIntent = new Intent(getApplicationContext(), MessageService.class);
                    i.putExtra("username", user.getUserName());
                    i.putExtra("email", user.getEmail());
                    i.putExtra("accountLevel", user.getAccountLevel());
                    i.putExtra("userID", user.getId()+"");
                    serviceIntent.putExtra("username", user.getUserName());
                    startActivity(i);
                    startService(serviceIntent);
                }
                else {
                    //if a user is not returned then the user is displayed an error message
                    Toast.makeText(getApplicationContext(), "Username or Password is wrong!", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etUsername.setText("");
                }
            }
        });
    }

    public void bttnSignup_Click(View view) {
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}