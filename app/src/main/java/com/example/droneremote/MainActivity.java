package com.example.droneremote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MainActivity extends AppCompatActivity {
    int throttle=0,yaw=90,pitch=90,roll=90,mode=1;
    private Button xLeftButton, xRightButton, yUpButton, yDownButton;
    private boolean xLeftPressed, xRightPressed, yUpPressed, yDownPressed;

    TextView ip;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xLeftButton = findViewById(R.id.x_left_button);
        xRightButton = findViewById(R.id.x_right_button);
        yUpButton = findViewById(R.id.y_up_button);
        yDownButton = findViewById(R.id.y_down_button);

        xLeftPressed = false;
        xRightPressed = false;
        yUpPressed = false;
        yDownPressed = false;


        ip=findViewById(R.id.editTextTextPersonName);

        SeekBar seekBar1=findViewById(R.id.seekBar1);
        SeekBar seekBar2=findViewById(R.id.seekBar2);
//        SeekBar seekBar3=findViewById(R.id.seekBar3);
//        SeekBar seekBar4=findViewById(R.id.seekBar4);
        SeekBar seekBar=findViewById(R.id.seekBar);

        TextView val1=findViewById(R.id.textView1);
        TextView val2=findViewById(R.id.textView2);
//        TextView val3=findViewById(R.id.textView3);
//        TextView val4=findViewById(R.id.textView4);
        TextView val5=findViewById(R.id.textView5);

        throttle=seekBar1.getProgress();
        yaw=seekBar2.getProgress();
//        pitch=seekBar3.getProgress();
//        roll=seekBar4.getProgress();
        mode=1100+seekBar.getProgress()*100;

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mode=1100+i*100;
                val5.setText(mode+"");
                send();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                throttle= i;
                val1.setText(throttle+"");
                send();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                
            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                yaw=i;
                val2.setText(yaw+"");
                send();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBar.setProgress(90);
            }
        });
//        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                pitch=i;
//                val3.setText(pitch+"");
//                send();
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                seekBar.setProgress(90);
//            }
//        });
//        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                roll=i;
//                val4.setText(roll+"");
//                send();
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                seekBar.setProgress(90);
//            }
//        });



        xLeftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        xLeftPressed = true;
                        roll=60;
                      //  val4.setText(roll+"");
                        send();
                        break;
                    case MotionEvent.ACTION_UP:
                        xLeftPressed = false;
                        roll=90;
                      //  val4.setText(roll+"");
                        send();
                        break;
                }
                return false;
            }
        });

        xRightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        xRightPressed = true;
                        roll=120;
                      //  val4.setText(roll+"");
                        send();
                        break;
                    case MotionEvent.ACTION_UP:
                        xRightPressed = false;
                        roll=90;
                      //  val4.setText(roll+"");
                        send();
                        break;
                }
                return false;
            }
        });

        yUpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        yUpPressed = true;
                        pitch=60;
                      //  val3.setText(pitch+"");
                        send();
                        break;
                    case MotionEvent.ACTION_UP:
                        yUpPressed = false;
                        pitch=90;
                      //  val3.setText(pitch+"");
                        send();
                        break;
                }
                return false;
            }
        });

        yDownButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        yDownPressed = true;
                        pitch=120;
                     //   val3.setText(pitch+"");
                        send();
                        break;
                    case MotionEvent.ACTION_UP:
                        yDownPressed = false;
                        pitch=90;
                      //  val3.setText(pitch+"");
                        send();
                        break;
                }
                return false;
            }
        });



    }

    private DatagramSocket UDPSocket;
    private InetAddress address;
    private int port;


    public void send(){
        int nbrepet = 5;
        String data = throttle+" "+yaw+" "+pitch+" "+roll+" "+mode;
        int port = 9999;
        String address = ip.getText().toString();
        SendData(nbrepet,data,port,address);
    }
    public void SendData(final int nbRepet, final String Sdata , final int port, final String address) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Initreseau(InetAddress.getByName(address));
                    for (int i = 0; i < nbRepet; i++) {
                        byte[] data = Sdata.getBytes();
                        SendInstruction(data,port);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
    public void Initreseau(InetAddress address)  {

        try {
            this.UDPSocket =new DatagramSocket();
            this.address = address;
        } catch (SocketException e) {
            e.printStackTrace();

        }

    }
    public void SendInstruction(final byte[] data, final int port) {
        new Thread() {
            @Override
            public void run() {
                try {
                    DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                    UDPSocket.send(packet);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}