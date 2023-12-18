package com.iipl.smoi.Screens.NavFragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.iipl.smoi.Adapter.GalleryListAdapter;
import com.iipl.smoi.Constant.AppConstant;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.Model.DeleteGalleryResponse;
import com.iipl.smoi.Model.GalleryResponse;
import com.iipl.smoi.R;
import com.iipl.smoi.Retrofit.APIClient;
import com.iipl.smoi.Retrofit.APIInterface;
import com.iipl.smoi.Retrofit.FragmentToActivity;
import com.iipl.smoi.Retrofit.OnItemClickListener;
import com.iipl.smoi.Screens.ActivityActions.UpdateProfileActivity;
import com.iipl.smoi.Screens.ActivityActions.UploadGalleryActivity;
import com.iipl.smoi.Screens.Fragment.GalleryDetails;
import com.iipl.smoi.Utils.RealPathUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryList extends Fragment  {

    private FragmentToActivity fragmentToActivity;

    int PICK_IMAGE_MULTIPLE = 4;
    ArrayList<Uri> mArrayUri;

    MainActivity mainActivity;

    //===

    GalleryListAdapter galleryListAdapter;

    RecyclerView recyclerView;

    ImageView img_back, image_upload;

    private ArrayList<GalleryResponse.Gallery> galleryArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_list, container, false);

        img_back = view.findViewById(R.id.img_back_glist);
        image_upload = view.findViewById(R.id.image_upload);

        recyclerView = view.findViewById(R.id.rv_galleryList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));



        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        image_upload.setOnClickListener(view1 -> {
//            startActivity(new Intent(getActivity(), UploadGalleryActivity.class));
//            Intent intent = new Intent();
//            intent.setType("image/*");
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            someActivityResultLauncher.launch(intent);
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

//            getGalleryList();

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
//            Snackbar snackbar = Snackbar.make(parent_view, "No Internet Connection", Snackbar.LENGTH_LONG);
//            snackbar.show();
        }


        return view;
    }







    @Override
    public void onResume() {
        super.onResume();
        Log.d("Load_Home::>>", MainActivity.currentFragment = "GalleryList");
        ((MainActivity) getActivity()).getFragmentTag(4);
    }


     /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentToActivity = (FragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentToActivity");
        }
    }


    public void onDetach() {
        this.fragmentToActivity = null;
        super.onDetach();
    }

    private void sendData(String comm) {
        fragmentToActivity.communicate(comm);
    }
*/
}