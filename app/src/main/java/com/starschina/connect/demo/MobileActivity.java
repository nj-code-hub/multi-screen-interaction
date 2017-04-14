package com.starschina.connect.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.starschina.connect.ConnectMobileEventListener;
import com.starschina.connect.ConnectMobileMan;
import com.starschina.connect.R;
import com.starschina.connect.VodInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MobileActivity extends AppCompatActivity {

    private static final String TAG = "MobileActivity";

    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.play)
    TextView play;
    @BindView(R.id.disconnect)
    TextView disconnect;
    @BindView(R.id.showMsg)
    TextView showMsg;

    private ConnectMobileMan mConnectMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        ButterKnife.bind(this);

        mConnectMan = new ConnectMobileMan(mListener);
    }

    @OnClick(R.id.start)
    public void startBCServer() {
        mConnectMan.startUdpBroadCast();
    }

    @OnClick(R.id.play)
    public void toTv() {
        VodInfo vodInfo = new VodInfo();
        vodInfo.id = "2100020062040980360961995";
        vodInfo.name = "喜剧总动员";
        vodInfo.url = "http://bsymedia1.starschinalive.com/video/2016/10/23/201610231477194425528_28_4179.mp4";
        vodInfo.imageUrl = "http://images.ott.cibntv.net/2016/03/31/s_qiangshengq.jpg";
        vodInfo.position = 100;
        vodInfo.productCode = "2100140023646354245640027";
        vodInfo.sequence = 5;
        mConnectMan.pushToTv(vodInfo);
    }

    @OnClick(R.id.sysn_history)
    public void sysnHistory() {
        Log.d(TAG, "同步观看记录到TV端");
        showMsg.append("同步观看记录到TV端\n");
        //for (int i-0; i<historyList.size(); i++) {
            VodInfo vodInfo = new VodInfo();
            vodInfo.id = "2100020062040980360961995";
            vodInfo.name = "神探夏洛克";
            vodInfo.url = "http://hls01.ott.disp.guttv.cibntv.net/2016/02/03/1dec373522b242d1a0667ec53d12aac4/00ff0646a9d061e1fdc1e0cc546a096d.m3u8";
            vodInfo.imageUrl = "http://images.ott.cibntv.net/2016/03/31/s_qiangshengq.jpg";
            vodInfo.position = 100;
            vodInfo.productCode = "2100140023646354245640027";
            vodInfo.sequence = 6;

            mConnectMan.syncMobileHistory(vodInfo);
        //}
    }

    @OnClick(R.id.forward)
    public void forward() {
        mConnectMan.forward();
    }

    @OnClick(R.id.backward)
    public void backward() {
        mConnectMan.backward();
    }

    @OnClick(R.id.volumeDecrease)
    public void volumeDecrease() {
        mConnectMan.volumeDecrease();
    }

    @OnClick(R.id.volumeIncrease)
    public void volumeIncrease() {
        mConnectMan.volumeIncrease();
    }

    @OnClick(R.id.disconnect)
    public void disconnect() {
        mConnectMan.close();
    }

    ConnectMobileEventListener mListener = new ConnectMobileEventListener() {
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
        public void getDevices(String s) {
            Log.d(TAG, "搜索的设备："+s);
        }

        @Override
        public void pushVod(VodInfo vodInfo) {
            Log.d(TAG, "收到TV端推送过来的视频");
        }

        @Override
        public void syncHistory(ArrayList<VodInfo> arrayList) {
            Log.d(TAG, "同步TV端 观看记录");
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mConnectMan.close();
    }
}
