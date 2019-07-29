package rccar.bartech.rccar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    String ip = "http://192.168.4.1/";
    int x=0;
    int y=0;
    int x1=5;
    int y1=5;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileInputStream fis = null;
        try {
            fis = openFileInput("ip.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text);
            }
            ip = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ip="http://"+ip+"/";


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
            //The key argument here must match that used in the other activity
        }


        // final SeekBar lewy = (SeekBar) findViewById(R.id.predkosc_lewy);
       // final SeekBar prawy = (SeekBar) findViewById(R.id.predkosc_prawy);
        final SeekBar czulosc = (SeekBar) findViewById(R.id.czulosc);
        final SeekBar predkosc = (SeekBar) findViewById(R.id.predkosc);

        Switch tryb = (Switch) findViewById(R.id.tryb);
        Switch s_przod = (Switch) findViewById(R.id.swiatla_przod);
        Switch s_tyl = (Switch) findViewById(R.id.swiatla_tyl);

        webView = (WebView) findViewById(R.id.webViewer);
        webView.setWebViewClient(new WebViewClient());

        final TextView text_czulosc = (TextView) findViewById(R.id.text_czulosc);
        final TextView text_predkosc = (TextView) findViewById(R.id.text_predkosc);

        final Button przod = (Button) findViewById(R.id.przod);
        final Button tyl = (Button) findViewById(R.id.tyl);
        final Button lewo = (Button) findViewById(R.id.lewo);
        final Button prawo = (Button) findViewById(R.id.prawo);

        final Drawable przod_image = (Drawable) getResources().getDrawable(R.drawable.przod);
        final Drawable tyl_image = (Drawable) getResources().getDrawable(R.drawable.tyl);
        final Drawable prawo_image = (Drawable) getResources().getDrawable(R.drawable.prawo);
        final Drawable lewo_image = (Drawable) getResources().getDrawable(R.drawable.lewo);

        final Drawable przod_click = (Drawable) getResources().getDrawable(R.drawable.przod_click);
        final Drawable tyl_click = (Drawable) getResources().getDrawable(R.drawable.tyl_click);
        final Drawable prawo_click = (Drawable) getResources().getDrawable(R.drawable.prawo_click);
        final Drawable lewo_click= (Drawable) getResources().getDrawable(R.drawable.lewo_click);


        Button menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent (MainActivity.this, menu.class);
                startActivity(intent);
            }
        });


        View.OnTouchListener elem = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (v.getId()) {

                    case R.id.przod:

                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            webView.loadUrl(ip+"forward");
                            przod.setBackground(przod_click);

                        }
                        if(event.getAction() == MotionEvent.ACTION_UP) {
                            webView.loadUrl(ip+"stop");
                            przod.setBackground(przod_image);

                        }
                        break;

                    case R.id.tyl:

                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            webView.loadUrl(ip+"backward");
                            tyl.setBackground(tyl_click);

                        }
                        if(event.getAction() == MotionEvent.ACTION_UP) {
                            webView.loadUrl(ip+"stop");
                            tyl.setBackground(tyl_image);

                        }
                        break;

                    case R.id.lewo:

                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            webView.loadUrl(ip+"left");
                            lewo.setBackground(lewo_click);

                        }
                        if(event.getAction() == MotionEvent.ACTION_UP) {
                            webView.loadUrl(ip+"stop");
                            lewo.setBackground(lewo_image);

                        }
                        break;

                    case R.id.prawo:

                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            webView.loadUrl(ip+"right");
                            prawo.setBackground(prawo_click);

                        }
                        if(event.getAction() == MotionEvent.ACTION_UP) {
                            webView.loadUrl(ip+"stop");
                            prawo.setBackground(prawo_image);

                        }
                        break;



                }
                return true;
            }
        };

        przod.setOnTouchListener(elem);
        tyl.setOnTouchListener(elem);
        lewo.setOnTouchListener(elem);
        prawo.setOnTouchListener(elem);


        tryb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    webView.loadUrl(ip+"tryb1/");
                    czulosc.setVisibility(View.INVISIBLE);
                    text_czulosc.setVisibility(View.INVISIBLE);
                    przod.setVisibility(View.VISIBLE);
                    tyl.setVisibility(View.VISIBLE);
                    lewo.setVisibility(View.VISIBLE);
                    prawo.setVisibility(View.VISIBLE);

                } else {
                    webView.loadUrl(ip+"tryb2/");
                    czulosc.setVisibility(View.VISIBLE);
                    text_czulosc.setVisibility(View.VISIBLE);
                    przod.setVisibility(View.INVISIBLE);
                    tyl.setVisibility(View.INVISIBLE);
                    lewo.setVisibility(View.INVISIBLE);
                    prawo.setVisibility(View.INVISIBLE);
                }
            }
        });

        s_przod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    webView.loadUrl(ip+"pon/");
                } else {
                    webView.loadUrl(ip+"poff/");
                }
            }
        });

        s_tyl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    webView.loadUrl(ip+"ton/");

                } else {
                    webView.loadUrl(ip+"toff/");
                }
            }
        });


       /* lewy.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                webView.loadUrl(ip+"lewy/"+i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        prawy.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                webView.loadUrl(ip+"prawy/"+i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
*/

        czulosc.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                text_czulosc.setText("DISTANCE: " + i*5 + "cm");
                webView.loadUrl(ip+"czulosc/"+i*5);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        predkosc.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                text_predkosc.setText("SPEED: " + i);
                webView.loadUrl(ip+"predkosc/"+i*102);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });



    }

}