package com.iipl.smoi.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.iipl.smoi.R;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;

public class UploadImage extends AppCompatActivity {
    //constants
    public static final int CAMERA_PERMISSION = 2323, STORAGE_PERMISSION = 2434;
    private Activity activity;
    private Context context;
    private PackageManager packageManager;
    private String packageName, TAG;
    private Dialog myDialog;

    //Single image
    private String pathToFile;
    private int width = 1, height = 1;//image width and x value for aspect ration, image height and y value for aspect ration
    private ImageView ivImage;
    //UI variables
    private TextView tvHeading, tvMessage, tvCamera, tvGallery, tvRemove;
    private LinearLayout llHeading,llMessage, llCancel,llRemove,rlButtons,llCamera,llGallery;

    //Image array
    private ImageView[] ivImageArr;
    private String[] pathToFileArr;
    private int[] widthArr, heightArr;//image width and x value for aspect ration, image height and y value for aspect ration
    //UI variables
    private TextView[] tvHeadingArr, tvMessageArr,tvCameraArr, tvGalleryArr, tvRemoveArr;
//    private LinearLayout[] llHeadingArr,llMessageArr, llCancelArr,llRemoveArr,rlButtonsArr,llCameraArr,llGalleryArr;

    /**
     * Single picture constructor
     *
     * @param activity       Activity
     * @param context        Context
     * @param packageManager getPackageManager()
     * @param myDialog       Dialog
     * @param ivImageArr     ImageView[]
     * @param packageName    e.g "www.ethichadebe.co.za.uploadpicture"
     * @param TAG            String
     */
    public UploadImage(final Activity activity, final Context context, PackageManager packageManager,
                       final Dialog myDialog, final ImageView[] ivImageArr, final String packageName, final String TAG) {
        this.activity = activity;
        this.context = context;
        this.packageManager = packageManager;
        this.packageName = packageName;
        this.TAG = TAG;
        this.myDialog = myDialog;
        this.ivImageArr = ivImageArr;
        this.pathToFileArr = new String[ivImageArr.length];
        this.widthArr = new int[ivImageArr.length];
        this.heightArr = new int[ivImageArr.length];
        this.tvCameraArr = new TextView[ivImageArr.length];
        this.tvGalleryArr = new TextView[ivImageArr.length];
        this.tvHeadingArr = new TextView[ivImageArr.length];
        this.tvMessageArr = new TextView[ivImageArr.length];
        this.tvRemoveArr = new TextView[ivImageArr.length];

        for (int i = 0; i < ivImageArr.length; i++) {
            widthArr[i] = 1;
            heightArr[i] = 1;
        }
    }


    /**
     * Open popup dialog for image array
     *
     * @param index image index
     */
    public void start(final int index) {
        myDialog.setContentView(R.layout.picture_popup_dialog);

        //Initialise variables
        TextView tvCancel = myDialog.findViewById(R.id.tvCancel);
        tvHeadingArr[index] = myDialog.findViewById(R.id.tvHeading);
        tvMessageArr[index] = myDialog.findViewById(R.id.tvMessage);
        tvCameraArr[index] = myDialog.findViewById(R.id.tvCamera);
        tvGalleryArr[index] = myDialog.findViewById(R.id.tvGallery);
        tvRemoveArr[index] = myDialog.findViewById(R.id.tvRemove);

        //Display "remove picture" option when there's an image
        if (ivImageArr[index].getDrawable() != null) {
            tvRemoveArr[index].setVisibility(View.VISIBLE);
        } else {
            tvRemoveArr[index].setVisibility(View.GONE);
        }

        tvRemoveArr[index].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivImageArr[index].setImageDrawable(null);
                myDialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        tvCameraArr[index].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "tvCamera onClick: Take picture");
                    takePicture(index);
                } else {
                    Log.d(TAG, "tvCamera onClick: Request permissions");
                    requestPermission(CAMERA_PERMISSION);
                }
                myDialog.dismiss();
            }
        });

        tvGalleryArr[index].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    activity.startActivityForResult(new Intent().setAction(Intent.ACTION_GET_CONTENT)
                            .setType("image/*"), STORAGE_PERMISSION);
                } else {
                    requestPermission(STORAGE_PERMISSION);
                }
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
        myDialog.setCancelable(false);
        myDialog.setCanceledOnTouchOutside(false);
    }


    /**
     * Paste Paste method in onRequestPermissionsResult
     *
     * @param requestCode  requestCode
     * @param grantResults grantResults
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
        if ((requestCode == STORAGE_PERMISSION) && ((grantResults.length) > 0) &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivityForResult(new Intent().setAction(Intent.ACTION_GET_CONTENT).setType("image/*"),
                    STORAGE_PERMISSION);
        } else if ((requestCode == CAMERA_PERMISSION) && ((grantResults.length) > 0) &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePicture();
        }
    }

    /**
     * Paste Paste method in onActivityResult for image Array
     *
     * @param file        getCacheDir()
     * @param requestCode requestCode
     * @param resultCode  resultCode
     * @param data        data
     * @param index       image index
     */
    public void onActivityResult(File file, int requestCode, int resultCode, @Nullable Intent data, int index) {
        Uri uri;
        if ((requestCode == STORAGE_PERMISSION) && (resultCode == RESULT_OK)) {
            Log.d(TAG, "onActivityResult: (requestCode == STORAGE_PERMISSION) && (resultCode == RESULT_OK)");
            uri = data.getData();
            if (uri != null) {
                Log.d(TAG, "onActivityResult: data.getData() != null");
                startCrop(file, uri, index);
            }
        } else if ((requestCode == CAMERA_PERMISSION) && (resultCode == RESULT_OK)) {
            Log.d(TAG, "onActivityResult: (requestCode == CAMERA_PERMISSION) && (resultCode == RESULT_OK)");
            if (BitmapFactory.decodeFile(pathToFileArr[index]) != null) {
                Log.d(TAG, "onActivityResult: BitmapFactory.decodeFile(pathToFile) != null");
                startCrop(file, Uri.fromFile(new File(pathToFileArr[index])), index);
            }
        } else if ((requestCode == UCrop.REQUEST_CROP) && (resultCode == RESULT_OK)) {
            Log.d(TAG, "onActivityResult: (requestCode == UCrop.REQUEST_CROP) && (resultCode == RESULT_OK)");
            uri = UCrop.getOutput(data);
            if (uri != null) {
                Log.d(TAG, "onActivityResult: uri != null");
                ivImageArr[index].setImageDrawable(null);
                ivImageArr[index].setImageURI(uri);

//                File file123 = FileUtils.getFile(activity, uri);
//                Log.e(TAG, "handleCropResult: file >>>> " + file123);
//                uploadProfilePic(file);

            }
        }
    }

    /**
     * Open camera activity
     */
    private void takePicture() {
        Log.d(TAG, "takePicture: Taking picture");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Check if there's an app available to take picture
        if (intent.resolveActivity(packageManager) != null) {
            Log.d(TAG, "takePicture: getPackageManager()) != null");
            File photo;
            photo = createFile();
            //Save picture into the photo var
            if (photo != null) {
                pathToFile = photo.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(context, packageName,
                        photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                Log.d(TAG, "takePicture: Start picture taking activity");
                activity.startActivityForResult(intent, CAMERA_PERMISSION);
            }
        }
    }

    /**
     * Open camera activity
     * @param index index
     */
    private void takePicture(int index) {
        Log.d(TAG, "takePicture: Taking picture");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Check if there's an app available to take picture
        if (intent.resolveActivity(packageManager) != null) {
            Log.d(TAG, "takePicture: getPackageManager()) != null");
            File photo;
            photo = createFile();
            //Save picture into the photo var
            if (photo != null) {
                pathToFileArr[index] = photo.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(context, packageName, photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                Log.d(TAG, "takePicture: Start picture taking activity");
                activity.startActivityForResult(intent, CAMERA_PERMISSION);
            }
        }
    }

    /**
     * Creates temp file to store image
     *
     * @return file
     */
    private File createFile() {
        Log.d(TAG, "createFile: Creating File");

        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        try {
            Log.d(TAG, "createFile: File created");
            return File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg", storageDir);
        } catch (IOException e) {
            Log.d(TAG, "createFile: " + e.toString());
        }
        return null;
    }

    /**
     * Requests camera and storage permissions
     *
     * @param permission permission
     */
    private void requestPermission(int permission) {
        if (permission == STORAGE_PERMISSION) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(context)
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed so you can gain access to your gallery")
                        .setPositiveButton("Enable permission", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(activity,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION);

                            }
                        })
                        .setNegativeButton("close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
            }
        } else if (permission == CAMERA_PERMISSION) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(context)
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed so you can gain access to your camera")
                        .setPositiveButton("Enable permission", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(activity,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                                        CAMERA_PERMISSION);

                            }
                        })
                        .setNegativeButton("close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, CAMERA_PERMISSION);
            }
        }
    }


    /**
     * Start cropping activity
     *
     * @param file file
     * @param uri  image uri
     * @param index index
     */
    private void startCrop(File file, @NonNull Uri uri, int index) {
        Log.d(TAG, "startCrop: Start crop");
        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(file, "SampleCropImg.jpg")));
        uCrop.withAspectRatio(widthArr[index], heightArr[index])
                .withMaxResultSize(widthArr[index] * 1000, heightArr[index] * 1000)
                .withOptions(getOptions())
                .start(activity);
    }

    /**
     * returns Ucrop options
     *
     * @return UCrop.Options
     */
    private UCrop.Options getOptions() {
        Log.d(TAG, "getOptions: get options");
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(70);
        options.setHideBottomControls(false);
        options.setFreeStyleCropEnabled(false);

        return options;
    }

    public LinearLayout getMessageBackground() {
        return llMessage;
    }

    public void setMessageBackground(LinearLayout llMessage) {
        this.llMessage = llMessage;
    }

    public LinearLayout getHeadingBackground() {
        return llHeading;
    }

    public void setHeadingBackground(LinearLayout llHeading) {
        this.llHeading = llHeading;
    }

    public LinearLayout getCancelBackground() {
        return llCancel;
    }

    public void setCancelBackground(LinearLayout llCancel) {
        this.llCancel = llCancel;
    }

    public LinearLayout getRemoveBackground() {
        return llRemove;
    }

    public void setRemoveBackground(LinearLayout llRemove) {
        this.llRemove = llRemove;
    }

    public LinearLayout getButtonsBackground() {
        return rlButtons;
    }

    public void setButtonsBackground(LinearLayout rlButtons) {
        this.rlButtons = rlButtons;
    }

    public LinearLayout getCameraBackground() {
        return llCamera;
    }

    public void setCameraBackground(LinearLayout llCamera) {
        this.llCamera = llCamera;
    }

    public LinearLayout getGalleryBackground() {
        return llGallery;
    }

    public void setGalleryBackground(LinearLayout llGallery) {
        this.llGallery = llGallery;
    }

}
