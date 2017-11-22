package com.example.miyazakikazuki.myminislot;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    final int BANANA = 0;
    final int BAR = 1;
    final int BIGWIN = 2;
    final int CHERRY = 3;
    final int GRAPE = 4;
    final int LEMON = 5;
    final int ORANGE = 6;
    final int SEVEN = 7;
    final int WATERMELON = 8;

    private int STOPCOUNTLEFT = 0;
    private int STOPCOUNTCENTER = 0;
    private int STOPCOUNTRIGHT = 0;
    private int STOPCOUNT = 0;
    private int ORDER = 0;

    public int getORDER() {
        return ORDER;
    }

    public void setORDER(int ORDER) {
        this.ORDER = ORDER;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        loadData();

        final ImageView slotLeftImage = (ImageView) findViewById(R.id.imageView1);
        slotLeftImage.setImageResource(R.drawable.s_question);
        final ImageView slotCenterImage = (ImageView) findViewById(R.id.imageView2);
        slotCenterImage.setImageResource(R.drawable.s_question);
        final ImageView slotRightImage = (ImageView) findViewById(R.id.imageView3);
        slotRightImage.setImageResource(R.drawable.s_question);

        Button stop = (Button)findViewById(R.id.button1);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(STOPCOUNTLEFT < 1) {
                    STOPCOUNT += 1;
                    int sli = onStopButtonTapped(slotLeftImage);
                    setORDER(sli * 100);
                    STOPCOUNTLEFT += 1;
                }

                if(STOPCOUNT == 3){
                    String ret = slotResult(getORDER());
                    TextView resultText =(TextView) findViewById(R.id.textView5);
                    resultText.setText(ret);
                }
            }
        });
        stop = (Button)findViewById(R.id.button2);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(STOPCOUNTCENTER < 1) {
                    STOPCOUNT += 1;
                    int sci = onStopButtonTapped(slotCenterImage);
                    setORDER(getORDER() + sci * 10);
                    STOPCOUNTCENTER += 1;
                }

                if(STOPCOUNT == 3){
                    String ret = slotResult(getORDER());
                    TextView resultText =(TextView) findViewById(R.id.textView5);
                    resultText.setText(ret);
                }
            }
        });
        stop = (Button)findViewById(R.id.button3);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(STOPCOUNTRIGHT < 1) {
                    STOPCOUNT += 1;
                    int sri = onStopButtonTapped(slotRightImage);
                    setORDER(getORDER() + sri);
                    STOPCOUNTRIGHT += 1;
                }

                if(STOPCOUNT == 3){
                    String ret = slotResult(getORDER());
                    TextView resultText =(TextView) findViewById(R.id.textView5);
                    resultText.setText(ret);
                }
                loadData();
            }
        });
    }

    public void loadData(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        TextView haveCoin = (TextView) findViewById(R.id.textView5);
        TextView betCoin = (TextView) findViewById(R.id.textView7);
        String h = String.valueOf(pref.getInt("HAVE_COIN", 1000));
        String b = String.valueOf(pref.getInt("BET_COIN", 10));
        haveCoin.setText(h);
        betCoin.setText(b);

    }

    private void saveData(int betCoin, int haveCoin){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("HAVE_COIN", haveCoin);
        editor.putInt("BET_COIN", betCoin);
        editor.commit();
    }

    public int onStopButtonTapped(View view) {
        int slotImage = (int) (Math.random() * 9);
        ImageView iv = (ImageView) view;
        switch (slotImage) {
            case BANANA:
                iv.setImageResource(R.drawable.s_banana);
                break;
            case BAR:
                iv.setImageResource(R.drawable.s_bar);
                break;
            case BIGWIN:
                iv.setImageResource(R.drawable.s_bigwin);
                break;
            case CHERRY:
                iv.setImageResource(R.drawable.s_cherry);
                break;
            case GRAPE:
                iv.setImageResource(R.drawable.s_grape);
                break;
            case LEMON:
                iv.setImageResource(R.drawable.s_lemon);
                break;
            case ORANGE:
                iv.setImageResource(R.drawable.s_orange);
                break;
            case SEVEN:
                iv.setImageResource(R.drawable.s_seven);
                break;
            case WATERMELON:
                iv.setImageResource(R.drawable.s_waltermelon);
                break;
            default:
                iv.setImageResource(R.drawable.s_banana);
                break;
        }
        return slotImage;
    }

    public String slotResult(int order){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int haveCoin = pref.getInt("HAVE_COIN", 1000);
        int betCoin = pref.getInt("BET_COIN", 10);
        int l = order / 100;
        int c = order / 10 - l * 10;
        int r = order % 10;
        String cong = "おめでとう！！";
        String yatta = "やったね！";
        String way = "わーい";
        String ret = cong;
        if(order == 635){
            haveCoin = haveCoin + betCoin * 29;
        }else if(order == 804){
            haveCoin = haveCoin + betCoin * 9;
        } else if(l == c && c == r){
            if(l == 7){
                haveCoin = haveCoin + betCoin * 19;
            }else if(l == 2){
                haveCoin = haveCoin + betCoin * 9;
            }else if(l == 1){
                haveCoin = haveCoin + betCoin * 4;
                ret = yatta;
            }else{
                haveCoin = haveCoin + betCoin * 1;
                ret = way;
            }
        }else if(l == c || c == r || r == l){
            if((l == c && l == 7 )||(c == r && c == 7)||(r == l && r ==7)){
                haveCoin = haveCoin + betCoin * 2;
                ret = yatta;
            }else{
                ret = way;
            }
        }else if(l == 8 || c == 8 || r == 8){
            ret = way;
        }else{
            haveCoin = haveCoin - betCoin;
            ret = "ざんねん・・・";
        }
        saveData(betCoin, haveCoin);
        return ret;
    }

}