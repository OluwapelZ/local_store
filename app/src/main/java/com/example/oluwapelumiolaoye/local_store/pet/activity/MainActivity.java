package com.example.oluwapelumiolaoye.local_store.pet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oluwapelumiolaoye.local_store.R;
import com.example.oluwapelumiolaoye.local_store.pet.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.age)
    EditText age;

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.gender)
    EditText gender;

    @BindView(R.id.save_button)
    Button save;

    @BindView(R.id.read_button)
    Button read;

    @BindView(R.id.get_name)
    TextView get_name;

    @BindView(R.id.get_age)
    TextView get_age;

    @BindView(R.id.get_gender)
    TextView get_gender;

    public String full_name, gender_pick;
    public int age_range;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Butterknife instance
        ButterKnife.bind(this);

        //Realm instance.
        realm = Realm.getDefaultInstance();



        //Save current info to the sqlite DB.
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                full_name = name.getText().toString();
                age_range = Integer.parseInt(age.getText().toString());
                gender_pick = gender.getText().toString();
                if(!(full_name.isEmpty() && gender_pick.isEmpty())) {
                    write();
                } else {
                    Toast.makeText(getApplicationContext(), "All text field must" +
                            "be filled to save your details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Read from sqlite DB based on query.
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = name.getText().toString();
                if(!(user_name.isEmpty())) {
                    read(user_name);
                } else {
                    Toast.makeText(getApplicationContext(), "Name field must" +
                            "be filled to read your details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void write() {
        realm.executeTransactionAsync(new Realm.Transaction() {
              @Override
              public void execute(Realm realm_big) {
                  User user = realm_big.createObject(User.class);
                  full_name = name.getText().toString();
                  age_range = Integer.parseInt(age.getText().toString());
                  gender_pick = gender.getText().toString();
                  user.setUserName(full_name.trim());
                  user.setGender(gender_pick);
                  user.setAge(age_range);
              }
          }, new Realm.Transaction.OnSuccess() {
              @Override
              public void onSuccess() {
                Toast.makeText(getApplicationContext(), "Your details was " +
                        "successfully saved", Toast.LENGTH_SHORT).show();
              }
          }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(getApplicationContext(), "Sorry, " +
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void read(String name) {
        RealmResults<User> user = realm.where(User.class)
                .equalTo("name", name).findAllAsync();
        user.addChangeListener(new RealmChangeListener<RealmResults<User>>() {
            @Override
            public void onChange(RealmResults<User> element) {
                User user = element.get(0);
                get_name.setText(user.getUserName());
                get_gender.setText(user.getGender());
                get_age.setText(user.getAge());
            }
        });
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
