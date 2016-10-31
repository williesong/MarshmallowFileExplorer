package com.dp.marshmallowfileexplorer;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_BROWSE_DOCUMENT_ROOT = "android.provider.action.BROWSE_DOCUMENT_ROOT";    // For Marshmallow
    public static final String ACTION_BROWSE = "android.provider.action.BROWSE";                                // For Nougat

    private static final String DOCUMENT_AUTHORITY = "com.android.externalstorage.documents";
    private static final String DOCUMENT_ROOT_PRIMARY_EMULATED = "primary";

    private static final String PACKAGE_NAME = "com.android.documentsui";
    private static final String CLASS_NAME_DOCUMENTS_ACTIVITY = "com.android.documentsui.DocumentsActivity";    // For Marshmallow
    private static final String CLASS_NAME_FILES_ACTIVITY = "com.android.documentsui.FilesActivity";            // For Nougat

    private static final String EXTRA_SHOW_ADVANCED = "android.content.extra.SHOW_ADVANCED";    // For Nougat
    private static final String EXTRA_FANCY_FEATURES = "android.content.extra.FANCY";           // For Nougat
    private static final String EXTRA_SHOW_FILESIZE = "android.content.extra.SHOW_FILESIZE";    // For Nougat

    private static final String MEDIA_AUTHORITY = "com.android.providers.media.documents";
    private static final String MEDIA_ROOT_IMAGES = "images_root";
    private static final String MEDIA_ROOT_VIDEOS = "videos_root";
    private static final String MEDIA_ROOT_AUDIO = "audio_root";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
            Intent intent = new Intent(ACTION_BROWSE_DOCUMENT_ROOT);
            intent.setData(DocumentsContract.buildRootUri(DOCUMENT_AUTHORITY, DOCUMENT_ROOT_PRIMARY_EMULATED));
            intent.setComponent(new ComponentName(PACKAGE_NAME, CLASS_NAME_DOCUMENTS_ACTIVITY));
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Intent intent = new Intent(ACTION_BROWSE);
            intent.setData(DocumentsContract.buildRootUri(DOCUMENT_AUTHORITY, DOCUMENT_ROOT_PRIMARY_EMULATED));
            intent.setComponent(new ComponentName(PACKAGE_NAME, CLASS_NAME_FILES_ACTIVITY));
            intent.putExtra(EXTRA_SHOW_ADVANCED, true);
            intent.putExtra(EXTRA_FANCY_FEATURES, true);
            intent.putExtra(EXTRA_SHOW_FILESIZE, true);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.unsupported_android_version, Toast.LENGTH_LONG).show();
        }

        finish();
    }
}