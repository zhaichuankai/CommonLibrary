package com.enniu.library.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.Vector;

/**
 * Created by ${zac} on 2017/1/11.
 */

public class XxFileUtil {
    public static String getSaveDir(Context context) {
        String state = Environment.getExternalStorageState();
        File rootDir =
                state.equals(Environment.MEDIA_MOUNTED) ? context.getExternalCacheDir()
                        : context.getCacheDir();
        File folderDir = new File(rootDir.getAbsolutePath());
        if (folderDir == null) {
            folderDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        }
        if (!folderDir.exists() && folderDir.mkdirs()) {

        }
        return folderDir.getAbsolutePath();
    }

    /**
     * 复制单个文件
     *
     * @param oldPath$Name String 原文件路径+文件名 如：data/user/0/com.test/files/abc.txt
     * @param newPath$Name String 复制后路径+文件名 如：data/user/0/com.test/cache/abc.txt
     * @return <code>true</code> if and only if the file was copied;
     * <code>false</code> otherwise
     */
    public static boolean copyFile(String oldPath$Name, String newPath$Name) {
        try {
            File oldFile = new File(oldPath$Name);
            if (!oldFile.exists()) {
                Log.e("--Method--", "copyFile:  oldFile not exist.");
                return false;
            } else if (!oldFile.isFile()) {
                Log.e("--Method--", "copyFile:  oldFile not file.");
                return false;
            } else if (!oldFile.canRead()) {
                Log.e("--Method--", "copyFile:  oldFile cannot read.");
                return false;
            }

            /* 如果不需要打log，可以使用下面的语句
            if (!oldFile.exists() || !oldFile.isFile() || !oldFile.canRead()) {
                return false;
            }
            */

            FileInputStream fileInputStream = new FileInputStream(oldPath$Name);
            FileOutputStream fileOutputStream = new FileOutputStream(newPath$Name);
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = fileInputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Drawable stringToDrawable(String path) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //先转换成bitmap
        Bitmap bmp = BitmapFactory.decodeStream(input);

        //再转换成drawable
        @SuppressWarnings("deprecation")
        Drawable drawable = new BitmapDrawable(bmp);
        return drawable;
    }


    /**
     * 文件按照名字排序
     */
    public static Comparator comparator = new Comparator<File>() {
        @Override
        public int compare(File file1, File file2) {
            if (file1.isDirectory() && file2.isFile()) {
                return -1;
            } else if (file1.isFile() && file2.isDirectory()) {
                return 1;
            } else {
                return file1.getName().compareTo(file2.getName());
            }
        }
    };

    public static void getPictureSize(String picPath) {

        /*第一种直接把bitmap加载到内存中，通过对bitmap的测量，
        得出宽高，由于这个方法直接把图片引入内存，
        如果图片过大，将会引发OOM；*/
        //方法一：通过uri把图片转化为bitmap的方法
        Bitmap bitmap = BitmapFactory.decodeFile(picPath);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        Log.e("getPictureSize", "通过bitmap获取到的图片大小" + "width: " + width + " height: " + height);


        /*bitmap.options类为bitmap的裁剪类，通过他可以实现bitmap的裁剪；
        如果不设置裁剪后的宽高和裁剪比例，返回的bitmap对象将为空，
        但是这个对象存储了原bitmap的宽高信息。*/
        //方法二：使用Options类来获取
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//这个参数设置为true才有效，
        Bitmap bmp = BitmapFactory.decodeFile(picPath, options);//这里的bitmap是个空
        if (bmp == null) {
            Log.e("getPictureSize", "通过options获取到的bitmap为空 ===");
        }
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        Log.e("getPictureSize", "通过Options获取到的图片大小" + "width:" + outWidth + " height: " + outHeight);
    }

    /**
     * 判断文件是否存在
     *
     * @param path 文件路径
     * @return 文件是否存在
     */
    public static boolean checkFileExist(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        if (!file.exists()) {
            Log.e("XxFileUtil", path + " is not exist!");
            return false;
        }
        return true;
    }

    /**
     * 获取文件的子文件个数
     *
     * @param file
     * @return
     */
    public static int getFileChildCount(File file) {
        int count = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isHidden()) continue;
                count++;
            }
        }
        return count;
    }

    /**
     * 文件大小转换
     *
     * @param size
     * @return
     */
    public static String sizeToChange(long size) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");  //字符格式化，为保留小数做准备

        double G = size * 1.0 / 1024 / 1024 / 1024;
        if (G >= 1) {
            return df.format(G) + " GB";
        }

        double M = size * 1.0 / 1024 / 1024;
        if (M >= 1) {
            return df.format(M) + " MB";
        }

        double K = size * 1.0 / 1024;
        if (K >= 1) {
            return df.format(K) + " KB";
        }

        return size + " B";
    }

    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    public static void openAppIntent(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 打开图片资源
     *
     * @param context
     * @param file
     */
    public static void openImageIntent(Context context, File file) {
        Uri path = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(path, "image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 打开文本资源
     *
     * @param context
     * @param file
     */
    public static void openTextIntent(Context context, File file) {
        Uri path = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(path, "text/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * 打开音频资源
     *
     * @param context
     * @param file
     */
    public static void openMusicIntent(Context context, File file) {
        Uri path = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(path, "audio/*");
        context.startActivity(intent);
    }

    /**
     * 打开视频资源
     *
     * @param context
     * @param file
     */
    public static void openVideoIntent(Context context, File file) {
        Uri path = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(path, "video/*");
        context.startActivity(intent);
    }

    /**
     * 打开所有能打开应用资源
     *
     * @param context
     * @param file
     */
    public static void openApplicationIntent(Context context, File file) {
        Uri path = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(path, "application/*");
        context.startActivity(intent);
    }

    /**
     * 发送文件给第三方app
     *
     * @param context
     * @param file
     */
    public static void sendFile(Context context, File file) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.putExtra(Intent.EXTRA_STREAM,
                Uri.fromFile(file));
        share.setType("*/*");//此处可发送多种文件
        context.startActivity(Intent.createChooser(share, "发送"));
    }

    public static Vector<String> getFileName(String fileAbsolutePath) {
        Vector<String> vecFile = new Vector<String>();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();

        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            // 判断是否为文件夹
            if (!subFile[iFileLength].isDirectory()) {
                String filename = subFile[iFileLength].getName();
                vecFile.add(filename);
            }
        }
        return vecFile;
    }

    public static boolean saveTxt(String content, Context context, String path) {

        //sd卡检测
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(context, "SD 卡不可用", Toast.LENGTH_SHORT).show();
            return false;
        }
        //检测文件夹是否存在
        File file = new File(path);
        file.exists();
        file.mkdirs();
        String p = path + File.separator + "splitVideo.txt";
        FileOutputStream outputStream = null;
        try {
            //创建文件，并写入内容
            outputStream = new FileOutputStream(new File(p));
            String msg = new String(content);
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public static String readTxt(String path) {
        byte Buffer[] = new byte[1024];
        //得到文件输入流
        File file = new File(path + File.separator + "splitVideo.txt");
        FileInputStream in = null;
        ByteArrayOutputStream outputStream = null;
        try {
            in = new FileInputStream(file);
            //读出来的数据首先放入缓冲区，满了之后再写到字符输出流中
            int len = in.read(Buffer);
            //创建一个字节数组输出流
            outputStream = new ByteArrayOutputStream();
            outputStream.write(Buffer, 0, len);
            //把字节输出流转String
            return new String(outputStream.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
