package com.backpacker.yflLibrary.java;

import android.content.Context;
import android.graphics.*;
import android.view.View;
import android.widget.*;
import androidx.core.widget.NestedScrollView;
import com.example.UtilsLibrary.R;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication2
 * @Copyright: 2018
 */
public class ScreenShot {
    /**
     * 截取scrollview的屏幕
     *
     * @param scrollView
     * @return
     */
    public static Bitmap getBitmapByView(Context context, ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取scrollview实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            //只分享题干
//            int height = getRecyclerViewHeight(childAt);
//            mHight = 0;
//            h -= height;
            h += scrollView.getChildAt(i).getHeight();
            //默认背景修改
//            scrollView.getChildAt(i).setBackgroundColor(
//                    Color.parseColor("#ffffff"));

        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        //要拼接的图片
//        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.qbank_shareimg);
//        拼后图片
//        Bitmap bitmap2 = ImgerSplit.add2Bitmap(bitmap, bitmap1);
//        压缩
//        Bitmap bitmap4 = compressImage(bitmap2);
        return bitmap;
    }

    /**
     * 截取scrollview的屏幕
     *
     * @param scrollView
     * @return
     */
    public static Bitmap getBitmapByView(Context context, NestedScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取scrollview实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            View childAt = scrollView.getChildAt(i);
            //只分享题干
//            int height = getRecyclerViewHeight(childAt);
//            mHight = 0;
            h += scrollView.getChildAt(i).getHeight();
//            h -= height;
            scrollView.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#ffffff"));
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
//        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.qbank_shareimg);
        //拼后图片
//        Bitmap bitmap2 = ImgerSplit.add2Bitmap(bitmap, bitmap1);
        //压缩
//        Bitmap bitmap4 = compressImage(bitmap2);
//        Bitmap bitmap4 = compressImage(bitmap2);
        return bitmap;
    }

    private static int mHight = 0;

    /***
     * 获取评价高度
     * @param view
     * @return
     */
    private static int getRecyclerViewHeight(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout ll = (LinearLayout) view;
            int childCount = ll.getChildCount();
            if (childCount != 0) {
                for (int k = 0; k < childCount; k++) {
                    if (mHight == 0)
                        getInteger(ll, k);
                    else
                        break;
                }

            } else {
                return mHight;
            }

        }
        return mHight;
    }

    /**
     * 取决与recyclerview套得层数
     *
     * @param ll
     * @param i
     */
    private static void getInteger(LinearLayout ll, int i) {
        View child = ll.getChildAt(i);
        if (child instanceof LinearLayout) {
            LinearLayout a = (LinearLayout) child;
            if (a.getChildCount() > 0)
                for (int w = 0; w < a.getChildCount(); w++) {
                    child = a.getChildAt(w);
                    if (child instanceof RelativeLayout) {
                        RelativeLayout rl = (RelativeLayout) child;
                        for (int k = 0; k < rl.getChildCount(); k++) {
                            View childAt = rl.getChildAt(k);
//                            if (childAt instanceof MyRecyclerView) {
//                                mHight = childAt.getHeight();
//                                View childAt1 = a.getChildAt(w - 1);
//                                mHight += childAt1.getHeight();
//                                return;
//                            }
                        }
                    }
                }
        }
    }

    /**
     * 压缩图片
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        int options = 50;
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > 100 && options >= 0) {
            // 重置baos
            baos.reset();
            // 这里压缩options%，把压缩后的数据存放到baos中
            image.compress(Bitmap.CompressFormat.PNG, options, baos);
            // 每次都减少10
            options -= 10;
        }
        // 把压缩后的数据baos存放到ByteArrayInputStream中
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        // 把ByteArrayInputStream数据生成图片
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    /**
     * 保存到sdcard
     *
     * @param b
     * @return
     */
    public static String savePic(Bitmap b) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss",
                Locale.US);
        String dbDir = android.os.Environment.getExternalStorageDirectory().toString();
        dbDir += "/xuechuan";
        dbDir += "/image";//数据库所在目录
        String dbPath = dbDir;//数据库路径
        //判断目录是否存在，不存在则创建该目录
//        File dirFile = new File(dbDir);
        File dbFile = new File(dbPath);
        if (!dbFile.exists())
            dbFile.mkdirs();
        //数据库文件是否创建成功
        boolean isFileCreateSuccess = false;
        //判断文件是否存在，不存在则创建该文件
        if (!dbFile.exists()) {
            try {
                isFileCreateSuccess = dbFile.createNewFile();//创建文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File outfile = null;
        if (isFileCreateSuccess) {
            outfile = dbFile;
        } else {
            outfile = new File("/sdcard/xuechuan/image");
        }
        // 如果文件不存在，则创建一个新文件
        if (!outfile.isDirectory()) {
            try {
                outfile.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String fname = outfile + "/" + sdf.format(new Date()) + ".png";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fname);
            if (null != fos) {
                b.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fname;
    }

    /**
     * 截取LinearLayout
     **/
    public static Bitmap getLinearLayoutBitmap(Context context, LinearLayout linearLayout) {
        int h = 0;
        Bitmap bitmap;
        int childCount = linearLayout.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childAt = linearLayout.getChildAt(i);

            h += linearLayout.getChildAt(i).getHeight();
            linearLayout.getChildAt(i).setBackgroundColor(
                    Color.parseColor("#ffffff"));
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(linearLayout.getWidth(), h,
                Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        linearLayout.draw(canvas);
        return bitmap;
    }

    /**
     *
     */
    public static Bitmap shotListView(ListView listview) {

        ListAdapter adapter = listview.getAdapter();
        int itemscount = adapter.getCount();
        int allitemsheight = 0;
        List<Bitmap> bmps = new ArrayList<Bitmap>();

        for (int i = 0; i < itemscount; i++) {
            View childView = adapter.getView(i, null, listview);

            childView.measure(
                    View.MeasureSpec.makeMeasureSpec(listview.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            childView.setDrawingCacheEnabled(true);
            childView.buildDrawingCache();

            bmps.add(childView.getDrawingCache());
            allitemsheight += childView.getMeasuredHeight();
        }
        int w = listview.getMeasuredWidth();
        Bitmap bigbitmap = Bitmap.createBitmap(w, allitemsheight, Bitmap.Config.ARGB_8888);
        Canvas bigcanvas = new Canvas(bigbitmap);

        Paint paint = new Paint();
        int iHeight = 0;

        for (int i = 0; i < bmps.size(); i++) {
            Bitmap bmp = bmps.get(i);
            bigcanvas.drawBitmap(bmp, 0, iHeight, paint);
            iHeight += bmp.getHeight();

            bmp.recycle();
            bmp = null;
        }
        return bigbitmap;
    }


}
