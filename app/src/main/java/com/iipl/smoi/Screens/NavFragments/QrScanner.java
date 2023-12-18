package com.iipl.smoi.Screens.NavFragments;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.iipl.smoi.Dashboard.MainActivity;
import com.iipl.smoi.R;
import com.iipl.smoi.Screens.Fragment.WebviewFragment;

import java.io.IOException;


public class QrScanner extends Fragment {

    RelativeLayout rl_qr_scan;

    SurfaceView surfaceView;
    EditText textViewBarCodeValue;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    String intentData = "";

    Button btn_next,btn_qr_scaneer;

    ImageView img_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr_scanner, container, false);

//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.qr_scanner);

        btn_next = view.findViewById(R.id.btn_next);
        btn_qr_scaneer = view.findViewById(R.id.btn_qr_scaneer);

        img_back = view.findViewById(R.id.img_back_qrscnr);

        textViewBarCodeValue = view.findViewById(R.id.txtBarcodeValue);
        surfaceView = view.findViewById(R.id.surfaceView);

        rl_qr_scan = view.findViewById(R.id.rl_qr_scan);


        btn_qr_scaneer.setOnClickListener(v -> {
            rl_qr_scan.setVisibility(View.VISIBLE);
            btn_qr_scaneer.setVisibility(View.GONE);
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string_label = String.valueOf(textViewBarCodeValue.getText());
                if (!string_label.trim().isEmpty()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("string_label", string_label);
                    WebviewFragment webviewFragment = new WebviewFragment();
                    webviewFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.fragment_container, webviewFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }else {
                    Toast.makeText(getActivity(), "Please Scan or Type Label No", Toast.LENGTH_SHORT).show();
                }

            }
        });


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        ScanDetectorsAndSources();

        return view;
    }

    private void ScanDetectorsAndSources() {
        barcodeDetector = new BarcodeDetector.Builder(getActivity())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(getActivity(), barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                openCamera();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
//                Toast.makeText(getActivity(), "To prevent memory leaks QR scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barCode = detections.getDetectedItems();
                if (barCode.size() > 0) {
                    setBarCode(barCode);
                }
            }
        });
    }

    private void openCamera() {
        try {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                cameraSource.start(surfaceView.getHolder());
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setBarCode(final SparseArray<Barcode> barCode) {
        textViewBarCodeValue.post(new Runnable() {
            @Override
            public void run() {
                intentData = barCode.valueAt(0).displayValue;
                textViewBarCodeValue.setText(intentData);
                copyToClipBoard(intentData);
            }
        });
    }

    private void copyToClipBoard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("QR Code Scanner", text);
        clipboard.setPrimaryClip(clip);
    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED)
                getActivity().onBackPressed();
            else
                openCamera();
        } else
            getActivity().onBackPressed();
    }*/

    @Override
    public void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.currentFragment = "QR Scanner";
        Log.d("QR Scanner::>>", MainActivity.currentFragment = "QR Scanner");
        ((MainActivity) getActivity()).getFragmentTag(1);

        ScanDetectorsAndSources();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MainActivity.currentFragment = "QR Scanner";
        Log.d("QR Scanner::>>", MainActivity.currentFragment = "QR Scanner");
        ((MainActivity) getActivity()).getFragmentTag(0);
    }



}