package rccar.bartech.rccar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class connection extends AppCompatActivity {
    private static final String FILE_NAME = "ip.txt";
    String ip="192.168.4.1";
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);

        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

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
            webView.loadUrl("http://"+ip+"/connect/");






        final EditText ip1 = (EditText) findViewById(R.id.ip1);
        final EditText ip2 = (EditText) findViewById(R.id.ip2);
        final EditText ip3 = (EditText) findViewById(R.id.ip3);
        final EditText ip4 = (EditText) findViewById(R.id.ip4);


        StringTokenizer tokens = new StringTokenizer(ip, ".");
        String ip_1 = tokens.nextToken();
        String ip_2 = tokens.nextToken();
        String ip_3 = tokens.nextToken();
        String ip_4 = tokens.nextToken();
        ip1.setText(ip_1);
        ip2.setText(ip_2);
        ip3.setText(ip_3);
        ip4.setText(ip_4);

        Button menu = (Button) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent (connection.this, menu.class);
                startActivity(intent);
            }
        });



        Button connect = (Button) findViewById(R.id.connection);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ip = ip1.getText()+"." +ip2.getText()+"."+ip3.getText()+"."   +  ip4.getText();

                FileOutputStream fos = null;

                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(ip.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                webView.loadUrl("http://"+ip+"/connect/");

            }
        });

    }

}
