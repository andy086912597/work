package com.example.yi_an.work;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class Zing extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zing);
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        if(getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size()==0)
        {
            // 未安裝
            Toast.makeText(this, "請至 Play 商店安裝 ZXing 條碼掃描器", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // SCAN_MODE, 可判別所有支援的條碼
            // QR_CODE_MODE, 只判別 QRCode
            // PRODUCT_MODE, UPC and EAN 碼
            // ONE_D_MODE, 1 維條碼
            intent.putExtra("SCAN_MODE", "SCAN_MODE");

            // 呼叫ZXing Scanner，完成動作後回傳 1 給 onActivityResult 的 requestCode 參數
            startActivityForResult(intent, 1);
        }
    }
    // 接收 ZXing 掃描後回傳來的結果
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                // ZXing回傳的內容
                String contents = intent.getStringExtra("SCAN_RESULT");
                Toast.makeText(this,contents.toString(), Toast.LENGTH_SHORT).show();

            }
            else
            if(resultCode==RESULT_CANCELED)
            {
                Toast.makeText(this, "取消掃描", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

