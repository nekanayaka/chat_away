package net.ddns.nimna.chat_away_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Nimna Ekanayaka on 20/01/2016.
 */
public class ProfileActivity extends AppCompatActivity {

    private TextView tvUsernameProfile;
    private String username;
    private Button btnChat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
//        Bundle extra = getIntent().getExtras();
//        tvUsernameProfile = (TextView)findViewById(R.id.tvUsernameProfile);
        //Intent intent = getIntent();
//        tvUsernameProfile.setText(extra.getString("username") + "!");
        btnChat = (Button)findViewById(R.id.btnChat);
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MessagingActivity.class);
                intent.putExtra("RECIPIENT_ID", "Bill");
                startActivity(intent);
            }
        });
    }
    public void setUsername(String username) {
        this.username = username;

    }
}