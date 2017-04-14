package com.starschina.connect.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.starschina.connect.ConnectEventListener;
import com.starschina.connect.ConnectTvMan;
import com.starschina.connect.R;
import com.starschina.connect.VodInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TvActivity extends AppCompatActivity {

    private static final String TAG = "TvActivity";

    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.showMsg)
    TextView showMsg;


    private ConnectTvMan mConnectMan;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);
        ButterKnife.bind(this);

        mConnectMan = new ConnectTvMan(mListener);
        mConnectMan.searchUdpBroadCast();

        showMsg.setMovementMethod(ScrollingMovementMethod.getInstance());

        mHandler = new Handler();
    }

    @OnClick(R.id.send)
    public void pushToMobile() {
        VodInfo vodInfo = new VodInfo();
        vodInfo.id = "2100020062040980360961995";
        vodInfo.name = "神探夏洛克";
        //vodInfo.url = "http://hls01.ott.disp.guttv.cibntv.net/2016/02/03/1dec373522b242d1a0667ec53d12aac4/00ff0646a9d061e1fdc1e0cc546a096d.m3u8";
        vodInfo.imageUrl = "http://images.ott.cibntv.net/2016/03/31/s_qiangshengq.jpg";
        vodInfo.position = 200;
        vodInfo.productCode = "2100140023646354245640027";
        vodInfo.sequence = 3;
        mConnectMan.pushToMobile(vodInfo);
        showMsg.append("推送视频到手机播放\n");
    }

    @OnClick(R.id.sysn_history)
    public void sysnHistory() {
        Log.d(TAG, "同步观看记录到手机端");
        showMsg.append("同步观看记录到手机端\n");
        //for (int i-0; i<historyList.size(); i++) {
            VodInfo vodInfo = new VodInfo();
            vodInfo.id = "2100020062040980360961995";
            vodInfo.name = "神探夏洛克";
            vodInfo.url = "http://hls01.ott.disp.guttv.cibntv.net/2016/02/03/1dec373522b242d1a0667ec53d12aac4/00ff0646a9d061e1fdc1e0cc546a096d.m3u8";
            vodInfo.imageUrl = "http://images.ott.cibntv.net/2016/03/31/s_qiangshengq.jpg";
            vodInfo.position = 100;
            vodInfo.productCode = "2100140023646354245640027";
            vodInfo.sequence = 15;

            mConnectMan.syncTvHistory(vodInfo);
        //}
    }

    ConnectEventListener mListener = new ConnectEventListener() {
        @Override
        public void showEvent(String event) {
            final String msg = event;
            showMsg.post(new Runnable() {
                @Override
                public void run() {
                    showMsg.append(msg);
                }
            });
        }

        @Override
        public void pushVod(VodInfo vodInfo) {
            Log.d(TAG, "收到手机端推送过来的视频");
            showMsg("收到手机端推送过来的视频");
        }

        @Override
        public void syncHistory(ArrayList<VodInfo> arrayList) {
            Log.d(TAG, "同步手机端 观看记录");
            showMsg("同步手机端 观看记录");
        }

        @Override
        public void forward() {
            Log.d(TAG, "快进命令");
            showMsg("快进命令");
        }

        @Override
        public void backward() {
            Log.d(TAG, "快退命令");
            showMsg("快退命令");
        }

        @Override
        public void volumeIncrease() {
            Log.d(TAG, "音量增加");
            showMsg("音量增加");
        }

        @Override
        public void volumeDecrease() {
            Log.d(TAG, "音量减少");
            showMsg("音量减少");
        }
    };

    private void showMsg(String msg) {
        final String message = msg;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TvActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mConnectMan.close();
    }
}
