package com.example.yi_an.work;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import java.io.FileNotFoundException;

public class Register2 extends AppCompatActivity {
    private static  String DATABASE_TABLE = "user";
    private SQLiteDatabase db;
    private MyDBHelper dbHelper;
    private  RadioGroup rgoup2;
    private int radiosex=0;
    private DisplayMetrics mPhone;
    private final static int CAMERA = 66 ;
    private final static int PHOTO = 99 ;
    private ImageButton imgbtnphoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);
        dbHelper = new MyDBHelper(this);
        db = dbHelper.getWritableDatabase();
        rgoup2 = (RadioGroup)findViewById(R.id.rgroup2);
        rgoup2.setOnCheckedChangeListener(listener2);
        imgbtnphoto = (ImageButton)findViewById(R.id.imgbtnphoto);

    }
    private  RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radioButton:
                    radiosex=1;
                    Log.e("1", "1");
                    break;
                case R.id.radioButton2:
                    radiosex=2;
                    Log.e("2","2");
                    break;
            }
        }
    };

    public void btn_photo(View view)
    {
        final String[] lunch={"拍照","從相片選擇"};
        new AlertDialog.Builder(Register2.this)
                .setTitle("上傳照片")
                .setItems(lunch, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                ContentValues value = new ContentValues();
                                value.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                                Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        value);
                                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri.getPath());
                                startActivityForResult(intent, CAMERA);
                                break;
                            case 1:
                                Intent intent1 = new Intent();
                                intent1.setType("image/*");
                                intent1.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intent1, PHOTO);
                                break;

                        }
                    }
                }).show();
    }

    //拍照完畢或選取圖片後呼叫此函式
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        //藉由requestCode判斷是否為開啟相機或開啟相簿而呼叫的，且data不為null
        if ((requestCode == CAMERA || requestCode == PHOTO ) && data != null)
        {
            //取得照片路徑uri
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();

            try
            {
                //讀取照片，型態為Bitmap
                BitmapFactory.Options mOptions = new BitmapFactory.Options();
//Size=2為將原始圖片縮小1/2，Size=4為1/4，以此類推
                mOptions.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri),null,mOptions);

                //判斷照片為橫向或者為直向，並進入ScalePic判斷圖片是否要進行縮放
                if(bitmap.getWidth()>bitmap.getHeight())ScalePic(bitmap,
                        mPhone.heightPixels);
                else ScalePic(bitmap,200);
            }
            catch (FileNotFoundException e)
            {
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ScalePic(Bitmap bitmap,int phone)
    {
        //縮放比例預設為1
        float mScale = 1 ;

        //如果圖片寬度大於手機寬度則進行縮放，否則直接將圖片放入ImageView內
        if(bitmap.getWidth() > phone )
        {
            //判斷縮放比例
            mScale = (float)phone/(float)bitmap.getWidth();

            Matrix mMat = new Matrix() ;
            mMat.setScale(mScale, mScale);

            Bitmap mScaleBitmap = Bitmap.createBitmap(bitmap,
                    0,
                    0,
                    bitmap.getWidth(),
                    bitmap.getHeight(),
                    mMat,
                    false);
            imgbtnphoto.setImageBitmap(mScaleBitmap);
        }
        else imgbtnphoto.setImageBitmap(bitmap);
    }

    public void btn_registerdb(View view)
    {
        Bundle bundle = getIntent().getExtras();
        long id;
        ContentValues cv = new ContentValues();
        if("".equals(bundle.getString("account").trim()))
        {}
        else {
            cv.put("account", bundle.getString("account"));
        }
        if("".equals(bundle.getString("password").trim()))
        {}
        else {
           cv.put("password", bundle.getString("password"));
        }
        id = db.insert(DATABASE_TABLE,null,cv);
        Log.d("db",("新增記錄成功: "+id));
        Intent intent = new Intent();
        intent.setClass(Register2.this,Login.class);
        startActivity(intent);
    }
}
