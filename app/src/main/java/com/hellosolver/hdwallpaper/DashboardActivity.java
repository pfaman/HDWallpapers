package com.hellosolver.hdwallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;


public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView beachTextView,treeTextView,oceanTextview,landTextView,sunriseTextView,mountainTextView,educationTextview,fashionTextview,healthTextview,natureTextview,peopleTextview,religionTextview,computerTextview,scienceTextview,placesTextview,animalsTextview,industryTextview,foodTextview,sportsTextview,transportationTextview,backgroundsTextview,buildingsTextview,businessTextview,musicTextview,videogameTextview,travelTextview,carTextview,bikeTextview,diwaliTextview,emotionsTextview;
    ImageView beachImageView,treeImageView,oceanImageView,landImageView,sunriseImageView,mountainImageView,educationImageview,fashionImageview,healthImageview,natureImageview,religionImageview,peopleImageview,computerImageView,scienceImageview,placesImageview,animalsImageview,industryImageview,foodImageview,sportsImageview,transportationImageview,backgroundsImageview,buildingsImageview,musicImageview,businessImageview,travelImageview,videogameImageview,carImageview,bikeImageview,diwaliImageview,emotionImageview;
    Intent beachIntent,treeIntent,oceanIntent,landIntent,sunriseIntent,mountainIntent,educationIntent,fashionIntent,healthIntent,natureIntent,peopleIntent,religionIntent,computerIntent,scienceIntent,placesIntent,animalsIntent,foodIntent,industryIntent,transportationIntent,sportsIntent,backgroundsIntent,musicIntent,businessIntent,buildingIntent,videogameIntent,travelIntent,carIntent,bikeIntent,diwaliIntent,emotionsIntent;
    String beachString,treeString,oceanString,landString,sunriseString,mountainString,educationString,fashionString,healthString,natureString,peopleString,religionString,computerString,scienceString,placesString,animalsString,foodString,industryString,transportationString,sportsString,businessString,musicString,backgoundsString,buildingsString,videogameString,travelString,carString,bikeString,diwaliString,emotionsString;
    FirebaseFirestore firestore;
    private int[] images;
    private String[] text;
    private SliderAdapter adapter;
    private SliderView sliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle("HD WALLPAPER");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firestore=FirebaseFirestore.getInstance();
        drawerLayout=findViewById(R.id.drawerLayout);
        sliderView=findViewById(R.id.sliderView);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        navigationView=findViewById(R.id.navigation_view);
        navigationView.bringToFront();
        navigationView.setCheckedItem(R.id.home);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(!isConnected(this)){
            showCustomDialog();
        }
        gettingImages();
       /* sliderLayout=findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL);
        sliderLayout.setScrollTimeInSec(5);
        setSliderViews();*/

        images=new int[]{R.drawable.s25,R.drawable.s10,R.drawable.s15,R.drawable.s20,R.drawable.s11,R.drawable.s12,R.drawable.s13,R.drawable.s14,R.drawable.s21,R.drawable.s22,R.drawable.s23,R.drawable.s24};
        text=new String[]{"","","","","",""};
        adapter=new SliderAdapter(images,text);
        sliderView.setSliderAdapter(adapter);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
        sliderView.startAutoCycle();
    }


 /* private void setSliderViews() {
        for(int i=0;i<=5;i++){
            SliderView sliderview=new SliderView(this);
            switch (i){
                case 0:
                    sliderview.setImageUrl("https://cdn.pixabay.com/photo/2015/05/15/14/54/sunset-768759_960_720.jpg");
                    sliderview.setDescription("Sunrise");
                    sliderview.setDescriptionTextSize(36);
                    sliderview.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Intent sunriseIntent=new Intent(DashboardActivity.this,SunriseActivity.class);
                            startActivity(sunriseIntent);
                        }
                    });
                    break;
                case 1:
                    sliderview.setImageUrl("https://cdn.pixabay.com/photo/2013/10/02/23/03/mountains-190055_960_720.jpg");
                    sliderview.setDescription("Mountain");
                    sliderview.setDescriptionTextSize(36);
                    sliderview.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Intent mountainIntent=new Intent(DashboardActivity.this,MountainActivity.class);
                            startActivity(mountainIntent);
                        }
                    });
                    break;
                case 2:
                    sliderview.setImageUrl("https://cdn.pixabay.com/photo/2015/07/09/22/45/tree-838667_960_720.jpg");
                    sliderview.setDescription("Tree");
                    sliderview.setDescriptionTextSize(36);
                    sliderview.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Intent treeIntent=new Intent(DashboardActivity.this,TreeActivity.class);
                            startActivity(treeIntent);
                        }
                    });
                    break;
                case 3:
                    sliderview.setImageUrl("https://cdn.pixabay.com/photo/2016/11/08/05/54/agriculture-1807581_960_720.jpg");
                    sliderview.setDescription("Land");
                    sliderview.setDescriptionTextSize(36);
                    sliderview.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Intent landIntent=new Intent(DashboardActivity.this,LandActivity.class);
                            startActivity(landIntent);
                        }
                    });
                    break;
                case 4:
                    sliderview.setImageUrl("https://cdn.pixabay.com/photo/2016/11/29/04/19/ocean-1867285_960_720.jpg");
                    sliderview.setDescription("Ocean");
                    sliderview.setDescriptionTextSize(36);
                    sliderview.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Intent oceanIntent=new Intent(DashboardActivity.this,OceanActivity.class);
                            startActivity(oceanIntent);
                        }
                    });
                    break;
                case 5:
                    sliderview.setImageUrl("https://cdn.pixabay.com/photo/2016/03/04/19/36/beach-1236581_960_720.jpg");
                    sliderview.setDescription("Beach");
                    sliderview.setDescriptionTextSize(36);
                    sliderview.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Intent beachIntent=new Intent(DashboardActivity.this,BeachActivity.class);
                            startActivity(beachIntent);
                        }
                    });
                    break;
            }
            sliderview.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderview);
        }
    }*/
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Exit Confirmation")
                    .setMessage("Are you want to exit")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).setNeutralButton("RATE US", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Uri uri=Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                    Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
            }).show();
        }
    }

    private void gettingImages() {
        beachTextView = findViewById(R.id.beaches_text);
        oceanTextview = findViewById(R.id.oceans_text);
        treeTextView = findViewById(R.id.trees_text);
        mountainTextView = findViewById(R.id.mountains_text);
        sunriseTextView = findViewById(R.id.sunrise_text);
        landTextView = findViewById(R.id.land_text);
        educationTextview=findViewById(R.id.education_text);
        fashionTextview=findViewById(R.id.fashion_text);
        healthTextview=findViewById(R.id.health_text);
        natureTextview=findViewById(R.id.nature_text);
        religionTextview=findViewById(R.id.religion_text);
        peopleTextview=findViewById(R.id.people_text);
        computerTextview=findViewById(R.id.science_text);
        scienceTextview=findViewById(R.id.computer_text);
        foodTextview=findViewById(R.id.food_text);
        industryTextview=findViewById(R.id.industry_text);
        animalsTextview=findViewById(R.id.animals_text);
        placesTextview=findViewById(R.id.places_text);
        transportationTextview=findViewById(R.id.transportation_text);
        sportsTextview=findViewById(R.id.sports_text);
        musicTextview=findViewById(R.id.music_text);
        businessTextview=findViewById(R.id.business_text);
        buildingsTextview=findViewById(R.id.buildings_text);
        backgroundsTextview=findViewById(R.id.backgounds_text);
        videogameTextview=findViewById(R.id.videogame_text);
        travelTextview=findViewById(R.id.travel_text);
        carTextview=findViewById(R.id.car_text);
        bikeTextview=findViewById(R.id.bike_text);

        diwaliTextview=findViewById(R.id.diwali_text);
        emotionsTextview=findViewById(R.id.emotions_text);



        videogameTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(videogameIntent);
            }
        });
        travelTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(travelIntent);
            }
        });
        beachTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(beachIntent);
            }
        });
        oceanTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(oceanIntent);
            }
        });
        treeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(treeIntent);
            }
        });
        mountainTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mountainIntent);
            }
        });
        sunriseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(sunriseIntent);
            }
        });
        landTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(landIntent);
            }
        });
        educationTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(educationIntent);
            }
        });
        fashionTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(fashionIntent);
            }
        });
        healthTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(healthIntent);
            }
        });
        natureTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(natureIntent);
            }
        });
        peopleTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(peopleIntent);
            }
        });
        religionTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(religionIntent);
            }
        });

        computerTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(computerIntent);
            }
        });
        scienceTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(scienceIntent);
            }
        });

        foodTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(foodIntent);
            }
        });
        industryTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(industryIntent);
            }
        });
        placesTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(placesIntent);
            }
        });
        animalsTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(animalsIntent);
            }
        });

        sportsTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(sportsIntent);
            }
        });

        transportationTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(transportationIntent);
            }
        });

        businessTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(businessIntent);
            }
        });

      backgroundsTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(backgroundsIntent);
            }
        });

        musicTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(musicIntent);
            }
        });

        buildingsTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(buildingIntent);
            }
        });
        carTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(carIntent);
            }
        });
        bikeTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(bikeIntent);
            }
        });
        diwaliTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(diwaliIntent);
            }
        });
        emotionsTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(emotionsIntent);
            }
        });


        videogameImageview= findViewById(R.id.videogame_image);
        travelImageview = findViewById(R.id.travel_image);
        beachImageView = findViewById(R.id.beaches_image);
        oceanImageView = findViewById(R.id.oceans_image);
        treeImageView = findViewById(R.id.trees_image);
        mountainImageView = findViewById(R.id.mountains_image);
        sunriseImageView = findViewById(R.id.sunrise_image);
        landImageView = findViewById(R.id.land_image);
        educationImageview=findViewById(R.id.education_image);
        fashionImageview=findViewById(R.id.fashion_image);
        healthImageview=findViewById(R.id.health_image);
        natureImageview=findViewById(R.id.nature_image);
        peopleImageview=findViewById(R.id.people_image);
        religionImageview=findViewById(R.id.religion_image);
        computerImageView=findViewById(R.id.computer_image);
        scienceImageview=findViewById(R.id.science_image);
       foodImageview=findViewById(R.id.food_image);
        animalsImageview=findViewById(R.id.animals_image);
        placesImageview=findViewById(R.id.places_image);
        industryImageview=findViewById(R.id.industry_image);
        sportsImageview=findViewById(R.id.sports_image);
        transportationImageview=findViewById(R.id.transportation_image);
        buildingsImageview=findViewById(R.id.buildings_image);
        businessImageview=findViewById(R.id.business_image);
        musicImageview=findViewById(R.id.music_image);
        backgroundsImageview=findViewById(R.id.backgrounds_image);
        carImageview=findViewById(R.id.car_image);
        bikeImageview=findViewById(R.id.bike_image);
        diwaliImageview=findViewById(R.id.diwali_image);
        emotionImageview=findViewById(R.id.emotions_image);




        firestore.collection("DashboardActivity").document("Categories").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot snapshot) {

                        beachString = snapshot.getString("beach");
                        sunriseString = snapshot.getString("sunrise");
                        oceanString = snapshot.getString("ocean");
                        treeString = snapshot.getString("tree");
                        mountainString = snapshot.getString("mountain");
                        landString = snapshot.getString("land");
                        educationString=snapshot.getString("education");
                        fashionString=snapshot.getString("fashion");
                        healthString=snapshot.getString("health");
                        natureString=snapshot.getString("nature");
                        peopleString=snapshot.getString("people");
                        religionString=snapshot.getString("religion");
                        computerString=snapshot.getString("computer");
                        scienceString=snapshot.getString("science");
                        placesString=snapshot.getString("places");
                        animalsString=snapshot.getString("animals");
                        industryString=snapshot.getString("industry");
                        foodString=snapshot.getString("food");
                        sportsString=snapshot.getString("sports");
                        transportationString=snapshot.getString("transportation");
                        musicString=snapshot.getString("music");
                        businessString=snapshot.getString("business");
                        buildingsString=snapshot.getString("buildings");
                        backgoundsString=snapshot.getString("backgrounds");
                        videogameString=snapshot.getString("videogame");
                        travelString=snapshot.getString("travel");
                        carString=snapshot.getString("car");
                        bikeString=snapshot.getString("bike");
                        diwaliString=snapshot.getString("diwali");
                        emotionsString=snapshot.getString("emotions");



                        Picasso.get().load(sunriseString).into(sunriseImageView);
                        Picasso.get().load(beachString).into(beachImageView);
                        Picasso.get().load(oceanString).into(oceanImageView);
                        Picasso.get().load(treeString).into(treeImageView);
                        Picasso.get().load(mountainString).into(mountainImageView);
                        Picasso.get().load(landString).into(landImageView);
                        Picasso.get().load(fashionString).into(fashionImageview);
                        Picasso.get().load(educationString).into(educationImageview);
                        Picasso.get().load(healthString).into(healthImageview);
                        Picasso.get().load(natureString).into(natureImageview);
                        Picasso.get().load(religionString).into(religionImageview);
                        Picasso.get().load(peopleString).into(peopleImageview);
                        Picasso.get().load(scienceString).into(scienceImageview);
                        Picasso.get().load(computerString).into(computerImageView);
                        Picasso.get().load(foodString).into(foodImageview);
                        Picasso.get().load(industryString).into(industryImageview);
                        Picasso.get().load(placesString).into(placesImageview);
                        Picasso.get().load(animalsString).into(animalsImageview);
                        Picasso.get().load(sportsString).into(sportsImageview);
                        Picasso.get().load(transportationString).into(transportationImageview);
                        Picasso.get().load(musicString).into(musicImageview);
                        Picasso.get().load(businessString).into(businessImageview);
                        Picasso.get().load(backgoundsString).into(backgroundsImageview);
                        Picasso.get().load(buildingsString).into(buildingsImageview);
                        Picasso.get().load(travelString).into(travelImageview);
                        Picasso.get().load(videogameString).into(videogameImageview);
                        Picasso.get().load(carString).into(carImageview);
                        Picasso.get().load(bikeString).into(bikeImageview);

                        Picasso.get().load(diwaliString).into(diwaliImageview);
                        Picasso.get().load(emotionsString).into(emotionImageview);

                    }
                });
        beachIntent = new Intent(DashboardActivity.this, BeachActivity.class);
        oceanIntent = new Intent(DashboardActivity.this, OceanActivity.class);
        treeIntent = new Intent(DashboardActivity.this, TreeActivity.class);
        mountainIntent = new Intent(DashboardActivity.this, MountainActivity.class);
        sunriseIntent = new Intent(DashboardActivity.this, SunriseActivity.class);
        landIntent = new Intent(DashboardActivity.this, LandActivity.class);
        fashionIntent = new Intent(DashboardActivity.this, FashionActivity.class);
        educationIntent = new Intent(DashboardActivity.this, EducationActivity.class);
        healthIntent = new Intent(DashboardActivity.this, HealthActivity.class);
        natureIntent = new Intent(DashboardActivity.this, NatureActivity.class);
        peopleIntent = new Intent(DashboardActivity.this, PeopleActivity.class);
        religionIntent = new Intent(DashboardActivity.this, ReligionActivity.class);
        scienceIntent = new Intent(DashboardActivity.this, ScienceActivity.class);
        computerIntent = new Intent(DashboardActivity.this, ComputerActivity.class);
        foodIntent = new Intent(DashboardActivity.this, FoodActivity.class);
        industryIntent = new Intent(DashboardActivity.this,IndustryActivity.class);
        placesIntent = new Intent(DashboardActivity.this, PlacesActivity.class);
        animalsIntent = new Intent(DashboardActivity.this, AnimalsActivity.class);
        transportationIntent = new Intent(DashboardActivity.this, TransportationActivity.class);
        sportsIntent = new Intent(DashboardActivity.this, SportsActivity.class);
        musicIntent = new Intent(DashboardActivity.this, MusicActivity.class);
        businessIntent = new Intent(DashboardActivity.this, BusinessActivity.class);
        buildingIntent = new Intent(DashboardActivity.this, BuildingsActivity.class);
        backgroundsIntent = new Intent(DashboardActivity.this, BackgroundsActivity.class);
        videogameIntent = new Intent(DashboardActivity.this, VideoGameActivity.class);
        travelIntent = new Intent(DashboardActivity.this, TravelActivity.class);
        carIntent = new Intent(DashboardActivity.this, CarActivity.class);
        bikeIntent = new Intent(DashboardActivity.this, BikeActivity.class);

        diwaliIntent = new Intent(DashboardActivity.this, DiwaliActivity.class);
        emotionsIntent = new Intent(DashboardActivity.this, EmotionsActivity.class);

    }

    private void showCustomDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Check Internet Connection.........")
        .setMessage("It seems you are not connected with internet. Turn on and Restart App")
        .setCancelable(false)
        .setPositiveButton("TURN ON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        }).setNeutralButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        }).show();
    }

    private boolean isConnected(DashboardActivity dashboardActivity) {
        ConnectivityManager connectivityManager=(ConnectivityManager)dashboardActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (wifiConn !=null && wifiConn.isConnected())||(mobileConn !=null && mobileConn.isConnected());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onShare(MenuItem item) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareLink="GET HD Wallpapers for free from this HD WALLPAPERS APP"
                +"\n "+
                "https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName();
        intent.putExtra(Intent.EXTRA_TEXT,shareLink);
        startActivity(Intent.createChooser(intent,"Share Using"));
    }

    public void onRate(MenuItem item) {
        Uri uri=Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.feedback:
                Intent feedbackIntent=new Intent(Intent.ACTION_SENDTO);
                feedbackIntent.setData(Uri.parse("mailto:"));
                String[] email={"help@hellosolver.com"};
                String subject="Feedback for your App";
                String text="Give your feedback here";
                feedbackIntent.putExtra(Intent.EXTRA_EMAIL,email);
                feedbackIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
                feedbackIntent.putExtra(Intent.EXTRA_TEXT,text);
                if(feedbackIntent.resolveActivity(getPackageManager())!=null){
                    startActivity(feedbackIntent);
                }
                break;

            case R.id.share:
                Intent intentShare=new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                String shareLink="GET HD Wallpapers for free from this HD WALLPAPERS APP"
                        +"\n "+
                        "https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName();
                intentShare.putExtra(Intent.EXTRA_TEXT,shareLink);
                startActivity(Intent.createChooser(intentShare,"Share Using"));
                break;
            case R.id.rate:
                Uri uri=Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                Intent intentRate=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intentRate);
                break;
            case R.id.disclaimer:
                drawerLayout.closeDrawer(GravityCompat.START);
                MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(this);
                builder.setTitle("Disclaimer")
                        .setMessage("We are doing our best to prepare the content of this app.HD Wallpapers App Support the content creator for amazing wallpaper.If you think that I used your property by mistake in my app then I'm Ready to give you whole credit.\n"+
                                "By using our app, you hereby consent to our disclaimer and agree to its terms.")
                        .setPositiveButton("Share App", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                String shareLink="Your Message \n"+
                                        "https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName();
                                intent.putExtra(Intent.EXTRA_TEXT,shareLink);
                                startActivity(Intent.createChooser(intent,"Share Using"));
                            }
                        }).setNegativeButton("Rate App", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri=Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                        Intent intentRate=new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intentRate);
                    }
                }).setNeutralButton("Okay......", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}