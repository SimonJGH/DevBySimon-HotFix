package com.tencent.bugly.hotfix.easy;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvCurrentVersion;
    private Button btnShowToast, btnImage,btnOther;
    private ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCurrentVersion = findViewById(R.id.tvCurrentVersion);
        btnShowToast = findViewById(R.id.btnShowToast);
        btnImage = findViewById(R.id.btnImage);
        btnOther = findViewById(R.id.btnOther);
        iv_image = findViewById(R.id.iv_image);

        btnShowToast.setOnClickListener(this);
        btnImage.setOnClickListener(this);
        btnOther.setOnClickListener(this);

        tvCurrentVersion.setText("当前版本sss：" + getCurrentVersion(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowToast:
                Toast.makeText(this, LoadBugClass.getBugString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnOther:
                Toast.makeText(this, getOther(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnImage:
                iv_image.setImageResource(R.mipmap.qibao0207);
                break;

        }
    }

    private String getOther(){
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 15; i++) {
            sb.append(i+"，");
        }
        return  sb.toString();
    }

    /**
     * 获取当前版本.
     *
     * @param context 上下文对象
     * @return 返回当前版本
     */
    public String getCurrentVersion(Context context) {
        try {
            PackageInfo packageInfo =
                    context.getPackageManager().getPackageInfo(this.getPackageName(),
                            PackageManager.GET_CONFIGURATIONS);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;

            return versionName + "." + versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
