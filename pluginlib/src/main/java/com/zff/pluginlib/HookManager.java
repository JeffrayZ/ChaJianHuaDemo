package com.zff.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class HookManager {
    private static final HookManager ourInstance = new HookManager();
    private Resources resources;
    private DexClassLoader loader;
    public PackageInfo packageInfo;
    private Configuration configuration;

    public static HookManager getInstance() {
        return ourInstance;
    }

    private HookManager() {
    }


    // 用来加载插件
    private String loadPlugin(Context context, String fileName) {
        try {
            File cacheDir = context.getCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdir();
            }
            File outFile = new File(cacheDir, fileName);
            if (!outFile.exists()) {
                boolean res = outFile.createNewFile();
                if (res) {
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream os = new FileOutputStream(outFile);
                    byte[] buffer = new byte[is.available()];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1) {
                        os.write(buffer, 0, byteCount);
                    }
                    os.flush();
                    is.close();
                    os.close();
                    Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }
            } else {
                Toast.makeText(context, "文件已存在", Toast.LENGTH_SHORT).show();
                return outFile.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadPathToPlugin(Context context, String fileName) {
        String path = loadPlugin(context, fileName);

        // 通过PackAgemanager 来获取插件的第一个activity是哪一个
        PackageManager packageManager = context.getPackageManager();
        packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        if (packageInfo == null) {
            return;
        }

        // 然后我们开始加载我们的apk 使用DexClassLoader
        File dexOutDir = context.getDir("dex", Context.MODE_PRIVATE);
        loader = new DexClassLoader(path, dexOutDir.getAbsolutePath(), null, context.getClassLoader());

        // 然后开始加载我们的资源 肯定要使用Resource 但是它是AssetManager创建出来的 就是AssertManager 有一个addAssertPath 这个方法 但是私有的 所有使用反射
        Class<?> assetManagerClass = AssetManager.class;
        try {
            AssetManager assertManagerObj = (AssetManager) assetManagerClass.newInstance();
            Method addAssetPathMethod = assetManagerClass.getMethod("addAssetPath", String.class);
            addAssetPathMethod.setAccessible(true);
            addAssetPathMethod.invoke(assertManagerObj, path);
            configuration =  context.getResources().getConfiguration();
            //在创建一个Resource
            resources = new Resources(assertManagerObj, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //对外提供插件的classLoader
    public ClassLoader getClassLoader() {
        return loader;
    }

    //插件中的Resource
    public Resources getResource() {
        return resources;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
