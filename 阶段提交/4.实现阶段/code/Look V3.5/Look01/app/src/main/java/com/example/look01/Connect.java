package com.example.look01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;

public class Connect extends AppCompatActivity implements OnGestureListener{

    // 定义手势检测器实例
    GestureDetector detector;
    ImageView imageView;
    //初始图片
    Bitmap bm ;
    // 定义图片的宽、高
    int width, height;
    // 记录当前的缩放比
    float currentScale = 1;
    // 控制图片缩放的Matrix对象
    Matrix matrix;

    /** Called when the activity is first created. */
    public static final String MIME_TYPE_IMAGE_JPEG = "image/jpeg";
    public static final int ACTIVITY_GET_IMAGE = 0;
    public static final String KEY_FILE_NAME = "name";
    public static final String KEY_FILE_TYPE = "type";
    public static final String KEY_FILE_BITS = "bits";
    public static final String KEY_FILE_OVERWRITE = "overwrite";
    public static final String KEY_FILE_URL = "url";
    private byte[] mContent;
    private Button back;//返回按钮定义

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
        getImage.addCategory(Intent.CATEGORY_OPENABLE);
        getImage.setType(MIME_TYPE_IMAGE_JPEG);
        startActivityForResult(getImage, ACTIVITY_GET_IMAGE);

        back = (Button) findViewById(R.id.button51);
       back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Connect.this, user.class);
                startActivityForResult(intent, 0);
            }
        });//完成到个人收藏的跳转
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // newImage(data.getData());
        if (resultCode != RESULT_OK) {
            return;
        }

        ContentResolver resolver = getContentResolver();

        // 创建手势检测器
        detector = new GestureDetector(this, this);
        imageView = (ImageView) findViewById(R.id.show);
        matrix = new Matrix();

        if (requestCode == ACTIVITY_GET_IMAGE) {
            try {
                //获得图片的uri
                Uri originalUri = data.getData();
                //将图片内容解析成字节数组
                mContent = getBytesFromInputStream(resolver.openInputStream(Uri
                        .parse(originalUri.toString())), 3500000);
                //将字节数组转换为ImageView可调用的Bitmap对象
                bm = getPicFromBytes(mContent, null);
                //显示图片
                imageView.setImageBitmap(bm);
                // 获得位图宽
                width = bm.getWidth();
                // 获得位图高
                height = bm.getHeight();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static Bitmap getPicFromBytes(byte[] bytes,
                                         BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                        opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }
    public static byte[] getBytesFromInputStream(InputStream is, int bufsiz)
            throws IOException {
        int total = 0;
        byte[] bytes = new byte[4096];
        ByteBuffer bb = ByteBuffer.allocate(bufsiz);
        while (true) {
            int read = is.read(bytes);
            if (read == -1)
                break;
            bb.put(bytes, 0, read);
            total += read;
        }
        byte[] content = new byte[total];
        bb.flip();
        bb.get(content, 0, total);
        return content;
    }

    @Override
    public boolean onTouchEvent(MotionEvent me)
    {
        // 将该Activity上的触碰事件交给GestureDetector处理
        return detector.onTouchEvent(me);
    }
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY)  // ②
    {
        velocityX = velocityX > 4000 ? 4000 : velocityX;
        velocityX = velocityX < -4000 ? -4000 : velocityX;
        // 根据手势的速度来计算缩放比，如果velocityX>0，放大图像，否则缩小图像
        currentScale += currentScale * velocityX / 4000.0f;
        // 保证currentScale不会等于0
        currentScale = currentScale > 0.01 ? currentScale: 0.01f;
        // 重置Matrix
        matrix.reset();
        // 缩放Matrix
        matrix.setScale(currentScale, currentScale, 160, 200);
        BitmapDrawable tmp = (BitmapDrawable)
                imageView.getDrawable();
        // 如果图片还未回收，先强制回收该图片
        if (!tmp.getBitmap().isRecycled())  // ①
        {
            tmp.getBitmap().recycle();
        }
        // 根据原始位图和Matrix创建新图片
        Bitmap bitmap2 = Bitmap.createBitmap(bm, 0, 0
                , width, height, matrix, true);
        // 显示新的位图
        imageView.setImageBitmap(bitmap2);
        return true;
    }
    @Override
    public boolean onDown(MotionEvent arg0)
    {
        return false;
    }
    @Override
    public void onLongPress(MotionEvent event)
    {
    }
    @Override
    public boolean onScroll(MotionEvent event1
            , MotionEvent event2, float distanceX, float distanceY)
    {
        return false;
    }
    @Override
    public void onShowPress(MotionEvent event)
    {
    }
    @Override
    public boolean onSingleTapUp(MotionEvent event)
    {
        return false;
    }
}



