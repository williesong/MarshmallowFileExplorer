package com.dp.marshmallowfileexplorer;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String MEDIA_AUTHORITY = "com.android.providers.media.documents";
    private static final String MEDIA_ROOT_IMAGES = "images_root";
    private static final String MEDIA_ROOT_VIDEOS = "videos_root";
    private static final String MEDIA_ROOT_AUDIO = "audio_root";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startActivity(buildBrowseIntentForO());
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                startActivity(buildBrowseIntentForN());
            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
                startActivity(buildBrowseIntentForM());
            } else {
                Toast.makeText(this, R.string.unsupported_android_version, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.open_file_explorer_failed, Toast.LENGTH_LONG).show();
        }

        finish();
    }

    public Intent buildBrowseIntentForM() {
        Intent intent = new Intent("android.provider.action.BROWSE_DOCUMENT_ROOT");
        intent.setData(DocumentsContract.buildRootUri("com.android.externalstorage.documents", "primary"));
        intent.setComponent(new ComponentName("com.android.documentsui", "com.android.documentsui.DocumentsActivity"));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        return intent;
    }

    public Intent buildBrowseIntentForN() {
        Intent intent = new Intent("android.provider.action.BROWSE");
        intent.setData(DocumentsContract.buildRootUri("com.android.externalstorage.documents", "primary"));
        intent.setComponent(new ComponentName("com.android.documentsui", "com.android.documentsui.FilesActivity"));
        intent.putExtra("android.content.extra.SHOW_ADVANCED", true);
        intent.putExtra("android.content.extra.FANCY", true);
        intent.putExtra("android.content.extra.SHOW_FILESIZE", true);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        return intent;
    }

    public Intent buildBrowseIntentForO() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(DocumentsContract.buildRootUri("com.android.externalstorage.documents", "primary"));
        intent.setComponent(new ComponentName("com.android.documentsui", "com.android.documentsui.files.FilesActivity"));
        intent.putExtra("android.content.extra.SHOW_ADVANCED", true);
        intent.putExtra("android.content.extra.FANCY", true);
        intent.putExtra("android.content.extra.SHOW_FILESIZE", true);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        return intent;
    }
}
