package com.example.boardswipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.boardswipe.Profile; // Import the Profile class or use the correct package if it's in a different package

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.net.URL;
import java.net.URLConnection;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {     //All of the Activity.java files come with this, just ignore it
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        user = auth.getCurrentUser();   //get the user
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);  //logout and go to login page if null
            startActivity(intent);              //start login activity page
            finish();                           //finish current activity
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();      //sign out user from firebase
                Intent intent = new Intent(getApplicationContext(), Login.class);  //logout and go to login page
                startActivity(intent);              //start login activity page
                finish();                           //finish current activity
            }
        });

        // Create and add profile views to the container
        /*
        int hundredThousand = (int) (Math.random() * 10);
        int countHThou = 0;
        int tenThousand = (int) (Math.random() * 10);
        int countTenThou = 0;
        int thousand = (int) (Math.random() * 10);
        int countThou = 0;
        int hundred = (int) (Math.random() * 10);
        int countHun = 0;
        int ten = (int) (Math.random() * 10);
        int countTen = 0;
        int one = (int) (Math.random() * 10);
        int countOne = 0;

         */

        ArrayList<Profile> profiles = new ArrayList<>();
        /*
        while (countHThou != 11) {
            while (countTenThou != 11) {
                while (countThou != 11) {
                    while (countHun != 11) {
                        while (countTen != 11) {
                            while (countOne != 11) {
                                try () {
                                    Profile profile = new Profile(one, ten, hundred, thousand, tenThousand, hundredThousand);
                                    profiles.add(profile);
                                } catch (Exception e) {
                                    System.console();
                                }
                                one++;
                                if (one == 10) {
                                    one = 0;
                                }
                                countOne++;
                            }
                            countTen++;
                            ten++;
                            if (ten == 10) {
                                ten = 0;
                            }
                            countOne = 0;
                            one = (int) (Math.random() * 10);
                        }
                        countHun++;
                        hundred++;
                        if (hundred == 10) {
                            hundred = 0;
                        }
                        countTen = 0;
                        ten = (int) (Math.random() * 10);
                    }
                    countThou++;
                    thousand++;
                    if (thousand == 10) {
                        thousand = 0;
                    }
                    countHun = 0;
                    hundred = (int) (Math.random() * 10);
                }
                countTenThou++;
                tenThousand++;
                if (tenThousand == 10) {
                    tenThousand = 0;
                }
                countThou = 0;
                thousand = (int) (Math.random() * 10);
            }
            countHThou++;
            hundredThousand++;
            if (hundredThousand == 10) {
                hundredThousand = 0;
            }
            countTenThou = 0;
            tenThousand = (int) (Math.random() * 10);
        }
        */

        LinearLayout profileContainer = findViewById(R.id.profileContainer);

        //hardcode starts here for the 5 profiles
        URL image1 = null;
        URL link1 = null;
        try {
            image1 = new URL("https://cf.geekdo-images.com/qXT1U-nFh9PE8ujfdmI7dA__itemrep/img/4VpX36FMypCo-iRbA_dkP9lhi-8=/fit-in/246x300/filters:strip_icc()/pic7754663.jpg");
        } catch (Exception e) {
            System.out.println("Failed image url");
        }

        try {
            link1 = new URL("https://boardgamegeek.com/boardgame/371942/white-castle");
        } catch (Exception e) {
            System.out.println("Failed website url");
        }

        Profile first = new Profile("The White Castle", image1, link1, 8, 30, 80, 1, 4, 2.98);

        URL image2 = null;
        URL link2 = null;
        try {
            image2 = new URL("https://cf.geekdo-images.com/hItZjdDTNuaCZ7fEztwcUQ__itemrep/img/Uzot_otbvYXpwkTpnc1uwiXUWqs=/fit-in/246x300/filters:strip_icc()/pic6153324.jpg");
        } catch (Exception e) {
            System.out.println("Failed image url");
        }

        try {
            link2 = new URL("https://boardgamegeek.com/boardgame/337627/voidfall");
        } catch (Exception e) {
            System.out.println("Failed website url");
        }

        Profile second = new Profile("Voidfall", image2, link2, 8, 90, 2400, 1, 4, 4.58);

        URL image3 = null;
        URL link3 = null;
        try {
            image3 = new URL("https://cf.geekdo-images.com/dT1vJbUizZFmJAphKg-byA__itemrep/img/pu4eSfZNzf3r7B-7pES03cfFROY=/fit-in/246x300/filters:strip_icc()/pic7720813.png");
        } catch (Exception e) {
            System.out.println("Failed image url");
        }

        try {
            link3 = new URL("https://boardgamegeek.com/boardgame/400314/apiary");
        } catch (Exception e) {
            System.out.println("Failed website url");
        }

        Profile third = new Profile("Apiary", image3, link3, 8, 60, 90, 1, 5, 2.69);

        URL image4 = null;
        URL link4 = null;
        try {
            image4 = new URL("https://cf.geekdo-images.com/6-Pj_AxP0mjjFXYs2ukMWw__itemrep/img/bccl-KT11ZTMu6vbjnCP0dqta1Y=/fit-in/246x300/filters:strip_icc()/pic7611495.jpg");
        } catch (Exception e) {
            System.out.println("Failed image url");
        }

        try {
            link4 = new URL("https://boardgamegeek.com/boardgame/199383/calimala");
        } catch (Exception e) {
            System.out.println("Failed website url");
        }

        Profile fourth = new Profile("Calimala", image4, link4, 7, 30, 75, 3, 5, 2.79);

        URL image5 = null;
        URL link5 = null;
        try {
            image5 = new URL("https://cf.geekdo-images.com/TEx3V_COEF6Vik8y3Ax3hA__itemrep/img/RTOe818yvlpJVmeLIihlFp_au6g=/fit-in/246x300/filters:strip_icc()/pic6810993.jpg");
        } catch (Exception e) {
            System.out.println("Failed image url");
        }

        try {
            link5 = new URL("https://boardgamegeek.com/boardgame/360692/septima");
        } catch (Exception e) {
            System.out.println("Failed website url");
        }

        Profile fifth = new Profile("Septima", image5, link5, 8, 50, 100, 1, 4, 3.56);
        profiles.add(first);
        profiles.add(second);
        profiles.add(third);
        profiles.add(fourth);
        profiles.add(fifth);

        //start profile display
        for (Profile profile : profiles) {
            View profileView = getLayoutInflater().inflate(R.layout.profile_display_layout, null);

            ImageView profileImage = profileView.findViewById(R.id.profile_image);
            TextView gameName = profileView.findViewById(R.id.profile_name);
            TextView minMaxPlayTime = profileView.findViewById(R.id.min_max_play_time);
            TextView minMaxPlayers = profileView.findViewById(R.id.min_max_players);
            TextView difficulty = profileView.findViewById(R.id.difficulty);

            profileImage.setImageBitmap(getBitmapFromURL(profile.getGameImage()));
            gameName.setText(profile.getName());
            String minMaxTime = ("Min-Max Play Time: " + profile.getMinPlayTime() + " - " + profile.getMaxPlayTime());
            minMaxPlayTime.setText(minMaxTime);
            String minMaxPlayer = ("Min-Max Players: " + profile.getMinPlayers() + " - " + profile.getMaxPlayers());
            minMaxPlayers.setText(minMaxPlayer);
            String weight = ("Difficulty: " + profile.getWeight());
            difficulty.setText(weight);

            profileContainer.addView(profileView);

            // Add an OnClickListener to the profile view to show more details
            profileView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click on the profile to show more details
                    showProfileDetails(profile);
                }
            });
        }

    } //Sign up Page


    private void showProfileDetails(Profile profile) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile Details");
        builder.setMessage("Min-Max Play Time: " + profile.getMinPlayTime() + " - " + profile.getMaxPlayTime() + "\n" +
                "Min-Max Players: " + profile.getMinPlayers() + " - " + profile.getMaxPlayers() + "\n" +
                "Difficulty: " + profile.getWeight());
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    public static Bitmap getBitmapFromURL(URL src) {
        try {
            URLConnection connection = src.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            System.out.println("Failed to convert to image");
            return null;
        }
    }
}
