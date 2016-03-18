package com.dp.marshmallowfileexplorer;

import android.content.Intent;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_BROWSE_DOCUMENT_ROOT = "android.provider.action.BROWSE_DOCUMENT_ROOT";

    private static final String DOCUMENT_AUTHORITY = "com.android.externalstorage.documents";
    private static final String DOCUMENT_ROOT_PRIMARY_EMULATED = "primary";

    private static final String MEDIA_AUTHORITY = "com.android.providers.media.documents";
    private static final String MEDIA_ROOT_IMAGES = "images_root";
    private static final String MEDIA_ROOT_VIDEOS = "videos_root";
    private static final String MEDIA_ROOT_AUDIO = "audio_root";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(ACTION_BROWSE_DOCUMENT_ROOT);
        intent.setData(DocumentsContract.buildRootUri(DOCUMENT_AUTHORITY, DOCUMENT_ROOT_PRIMARY_EMULATED));
//        intent.setData(DocumentsContract.buildRootUri(MEDIA_AUTHORITY, MEDIA_ROOT_IMAGES)); // Images
//        intent.setData(DocumentsContract.buildRootUri(MEDIA_AUTHORITY, MEDIA_ROOT_VIDEOS)); // Videos
//        intent.setData(DocumentsContract.buildRootUri(MEDIA_AUTHORITY, MEDIA_ROOT_AUDIO)); //Audio
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);

        finish();
    }
}
