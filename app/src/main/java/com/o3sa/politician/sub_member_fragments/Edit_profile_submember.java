package com.o3sa.politician.sub_member_fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.o3sa.politician.AddressPicking.FetchCurntLocatn;
import com.o3sa.politician.R;
import com.o3sa.politician.circularimageview.CircularImageView;
import com.o3sa.politician.customfonts.CustomButton;
import com.o3sa.politician.customfonts.CustomEditText;
import com.o3sa.politician.partymember_fragments.Test;
import com.o3sa.politician.servicesparsing.InterNetChecker;
import com.o3sa.politician.sidemenu.SideMenu;
import com.o3sa.politician.sidemenu.SideMenu_SubMember;
import com.o3sa.politician.storedobjects.CameraUtils;
import com.o3sa.politician.storedobjects.StoredObjects;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Random;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class Edit_profile_submember extends Fragment {

    CustomButton edit_profile_submt_btn;
    CircularImageView edt_prfile_image;
    CustomEditText edtprf_gndr_edtx,edtprf_dob_edtx,edtprf_adres_edtx,edtprf_area_edtx,edtprf_city_edtx,edit_profile_uploadpic_edtxt;

    //capture camera
    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    // key to store image path in savedInstance state
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";
    public static final int MEDIA_TYPE_IMAGE = 1;
    // Bitmap sampling size
    public static final int BITMAP_SAMPLE_SIZE = 8;
    // Gallery directory name to store the images or videos
    public static final String GALLERY_DIRECTORY_NAME = "ProfileImages";
    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";
    private static String imageStoragePath;

    protected static final int SELECT_FILE = 1;
    private static final int PIC_CROP = 3;
    String image_str="";
    private Bitmap myImg;
    private String fileName;
    //String file_name;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    private ListPopupWindow listPopupWindow,listPopupWindowone,listPopupWindowtwo;
    String[] arealst = {"Lb Nagar","Uppal","Yamjal","Malkajgiri"};
    String[] gndrlst = {"Male","Female"};
    String[] citylst = {"Hyderabad","Mumbai","Chennai","Goa"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.edit_profile,null,false );
        listPopupWindow = new ListPopupWindow(getActivity());
        listPopupWindowone = new ListPopupWindow(getActivity());
        listPopupWindowtwo = new ListPopupWindow(getActivity());
        StoredObjects.page_type="home";
        StoredObjects.back_type="edit_profile";
        SideMenu_SubMember.updatemenu(StoredObjects.page_type);
        initilization(v);
        return v;
    }

    private void initilization(View v) {  //,,,;

        edtprf_gndr_edtx = v.findViewById( R.id. edtprf_gndr_edtx);
        edtprf_dob_edtx = v.findViewById( R.id. edtprf_dob_edtx);
        edtprf_adres_edtx = v.findViewById( R.id. edtprf_adres_edtx);
        edtprf_area_edtx = v.findViewById( R.id. edtprf_area_edtx);
        edtprf_city_edtx = v.findViewById( R.id. edtprf_city_edtx);
        edit_profile_uploadpic_edtxt = v.findViewById( R.id. edit_profile_uploadpic_edtxt);

        edit_profile_uploadpic_edtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);

                    } else {

                        profile_pic();
                    }
                } catch (Exception e) {
                }

            }
        });

        edt_prfile_image = (CircularImageView) v.findViewById(R.id.edt_prfile_image);

        edt_prfile_image.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);

                    } else {

                        profile_pic();
                    }
                } catch (Exception e) {
                }

            }
        } );

        edit_profile_submt_btn = (CustomButton)v.findViewById( R.id.edit_profile_submt_btn );

        edtprf_dob_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                edtprf_dob_edtx.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, dayOfMonth);
                 datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        edtprf_gndr_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GenderListPopup (edtprf_gndr_edtx);
            }
        });

        edtprf_area_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AreasListPopup (edtprf_area_edtx);
            }
        });

        edtprf_city_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CityListPopup (edtprf_city_edtx);
            }
        });


        edtprf_adres_edtx.setText(StoredObjects.pickupval);

        edtprf_adres_edtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StoredObjects.tab_type ="submemberprofile";
                fragmentcallinglay(new FetchCurntLocatn());
            }
        });

        edit_profile_submt_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentcallinglay( new Profile_submember() );
            }
        } );
    }

    public void fragmentcallinglay(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack("").commit();
    }


    private void GenderListPopup(final CustomEditText prfilenme){
        listPopupWindow.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.drpdwn_lay,gndrlst));
        listPopupWindow.setAnchorView(prfilenme);
        listPopupWindow.setHeight(LinearLayout.MarginLayoutParams.WRAP_CONTENT);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prfilenme.setText(gndrlst[position]);
                listPopupWindow.dismiss();

            }
        });

        listPopupWindow.show();
    }

    private void AreasListPopup(final CustomEditText prfilenme){
        listPopupWindowone.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.drpdwn_lay,arealst));
        listPopupWindowone.setAnchorView(prfilenme);
        listPopupWindowone.setHeight(LinearLayout.MarginLayoutParams.WRAP_CONTENT);

        listPopupWindowone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                prfilenme.setText(arealst[position]);
                listPopupWindowone.dismiss();

            }
        });

        listPopupWindowone.show();
    }

    private void CityListPopup(final CustomEditText prfilenme){
        listPopupWindowtwo.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.drpdwn_lay,citylst));
        listPopupWindowtwo.setAnchorView(prfilenme);
        listPopupWindowtwo.setHeight(LinearLayout.MarginLayoutParams.WRAP_CONTENT);

        listPopupWindowtwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                prfilenme.setText(citylst[position]);
                listPopupWindowtwo.dismiss();

            }
        });

        listPopupWindowtwo.show();
    }


    public void profile_pic() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {

                    if (CameraUtils.checkPermissions(getActivity())) {
                        captureImage();
                    } else {
                        //requestCameraPermission(MEDIA_TYPE_IMAGE);
                    }

                } else if (items[item].equals("Choose from Library")) {


                    Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    getIntent.setType("image/*");


                    Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    pickIntent.setType("image/*");

                    Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                    startActivityForResult(chooserIntent, SELECT_FILE);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }



            }
        });
        builder.show();
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getActivity(), file);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

     private void showPermissionsAlert() {
        /*android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());*/

        AlertDialog .Builder builder  = new AlertDialog.Builder(getActivity());
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(getActivity());
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    private Uri picUri;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //user is returning from capturing an image using the camera

        StoredObjects.LogMethod("response", "response:---"+CAMERA_CAPTURE_IMAGE_REQUEST_CODE+"<><>"+resultCode+"<>"+RESULT_OK);
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Refreshing the gallery
                CameraUtils.refreshGallery(getActivity(), imageStoragePath);
                String deviceMan = Build.MANUFACTURER;

                if (Build.VERSION.SDK_INT >Build.VERSION_CODES.N||deviceMan.contains("HUAWEI")||deviceMan.contains("motorola")|| deviceMan.equalsIgnoreCase("motorola")||deviceMan.contains("Meizu")||deviceMan.contains("meizu")||deviceMan.equalsIgnoreCase("mi") || deviceMan.equalsIgnoreCase("Mi") || deviceMan.equalsIgnoreCase("MI")||deviceMan.equalsIgnoreCase("LG nexus 5x")||deviceMan.equalsIgnoreCase("ZTE")||deviceMan.contains("LG")||deviceMan.contains("nexus")||deviceMan.equalsIgnoreCase("LG nexus 5")||deviceMan.equalsIgnoreCase("LG Nexus 5X")|| deviceMan.equalsIgnoreCase("VIVO")||deviceMan.equalsIgnoreCase("LG Nexus 5")||deviceMan.equalsIgnoreCase("oppo")||deviceMan.equalsIgnoreCase("OPPO")||deviceMan.equalsIgnoreCase("Oppo")||deviceMan.equalsIgnoreCase("ZTE Blade V6")||deviceMan.equalsIgnoreCase("zte blade v6")||deviceMan.equalsIgnoreCase("Zte blade v6")||deviceMan.equalsIgnoreCase("ZTE V6")||deviceMan.equalsIgnoreCase("zte v6")||deviceMan.equalsIgnoreCase("Zte v6")) {//||deviceMan.equalsIgnoreCase("YU")
                    //  if (deviceMan.contains("Meizu")||deviceMan.contains("meizu")|| deviceMan.contains("oppo") || deviceMan.contains("OPPO") || deviceMan.contains("Oppo")||deviceMan.contains("mi") ||deviceMan.contains("Mi") || deviceMan.contains("MI") ||deviceMan.contains("vivo")||deviceMan.contains("VIVO")||deviceMan.equalsIgnoreCase("Meizu")|| deviceMan.equalsIgnoreCase("oppo")  || deviceMan.equalsIgnoreCase("MI")|| deviceMan.equalsIgnoreCase("VIVO")) {//||deviceMan.equalsIgnoreCase("YU")
                    try {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imageStoragePath);

                        f_new = createNewFile("CROP_");
                        try {
                            f_new.createNewFile();
                        } catch (IOException ex) {
                            Log.e("io", ex.getMessage());
                        }
                        Photo_SHowDialog(getActivity(),f_new,imageStoragePath,myBitmap);

                    } catch (Exception e) {
                        e.printStackTrace();
                        StoredObjects.LogMethod("", "imagepathexpection:--" + e);

                    }

                } else {
                    Uri uri = picUri;
                    //carry out the crop operation
                    try {
                        performCrop(picUri);
                        Log.d("picUri", uri.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                // successfully captured the image
                // display it in image view
                // Bitmap bitmap = CameraUtils.optimizeBitmap(BITMAP_SAMPLE_SIZE, imageStoragePath);

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getActivity(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getActivity(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        else if (requestCode == SELECT_FILE) {
            String deviceMan = Build.MANUFACTURER;
            StoredObjects.LogMethod("onresume", "deviceMan:-" + deviceMan);
           picUri = data.getData();

            String url = data.getData().toString();
            if (url.startsWith("content://com.google.android.apps.photos.content")){
                String tempPath = getPath(picUri, getActivity());
                StoredObjects.LogMethod("onresume", "deviceMan:-" + tempPath);
                try {
                    InputStream is = getActivity().getContentResolver().openInputStream(picUri);
                    if (is != null) {
                        Bitmap pictureBitmap = BitmapFactory.decodeStream(is);
                        f_new = createNewFile("CROP_");
                        try {
                            f_new.createNewFile();
                        } catch (IOException ex) {
                            Log.e("io", ex.getMessage());
                        }
                        Photo_SHowDialog(getActivity(),f_new,tempPath,pictureBitmap);
                    }
                    else {
                        StoredObjects.ToastMethod( "Cancelled to pick image",getActivity() );
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{


                if (Build.VERSION.SDK_INT >Build.VERSION_CODES.N||deviceMan.contains("HUAWEI")||deviceMan.contains("motorola")|| deviceMan.equalsIgnoreCase("motorola")||deviceMan.contains("Meizu")|| deviceMan.equalsIgnoreCase("VIVO")||deviceMan.contains("meizu")||deviceMan.equalsIgnoreCase("mi") || deviceMan.equalsIgnoreCase("Mi") || deviceMan.equalsIgnoreCase("MI")||deviceMan.equalsIgnoreCase("LG nexus 5x")||deviceMan.equalsIgnoreCase("ZTE")||deviceMan.contains("LG")||deviceMan.contains("nexus")||deviceMan.equalsIgnoreCase("LG nexus 5")||deviceMan.equalsIgnoreCase("LG Nexus 5X")||deviceMan.equalsIgnoreCase("LG Nexus 5")||deviceMan.equalsIgnoreCase("oppo")||deviceMan.equalsIgnoreCase("OPPO")||deviceMan.equalsIgnoreCase("Oppo")||deviceMan.equalsIgnoreCase("ZTE Blade V6")||deviceMan.equalsIgnoreCase("zte blade v6")||deviceMan.equalsIgnoreCase("Zte blade v6")||deviceMan.equalsIgnoreCase("ZTE V6")||deviceMan.equalsIgnoreCase("zte v6")||deviceMan.equalsIgnoreCase("Zte v6")) {//||deviceMan.equalsIgnoreCase("YU")
                    // if (deviceMan.contains("Meizu")||deviceMan.contains("meizu")|| deviceMan.contains("oppo") || deviceMan.contains("OPPO") || deviceMan.contains("Oppo")||deviceMan.contains("mi") ||deviceMan.contains("Mi") || deviceMan.contains("MI") ||deviceMan.contains("vivo")||deviceMan.contains("VIVO")||deviceMan.equalsIgnoreCase("Meizu")|| deviceMan.equalsIgnoreCase("oppo")  || deviceMan.equalsIgnoreCase("MI")|| deviceMan.equalsIgnoreCase("VIVO")) {//||deviceMan.equalsIgnoreCase("YU")
                    StoredObjects.LogMethod("onresume", "deviceMan:-" + deviceMan);
                    try {
                        Bitmap myBitmap=null;
                        picUri = data.getData();
                        final String docFilePath = getFileNameByUri(getActivity(), picUri);

                        StoredObjects.LogMethod("", "imagepath:--" + docFilePath);

                        myBitmap = BitmapFactory.decodeFile(docFilePath);

                        try {

                            f_new = createNewFile("CROP_");
                            try {
                                f_new.createNewFile();
                            } catch (IOException ex) {
                                Log.e("io", ex.getMessage());
                            }
                            Photo_SHowDialog(getActivity(),f_new,docFilePath,myBitmap);
                            //new ImageUploadTaskNew().execute(docFilePath.toString());
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            StoredObjects.LogMethod("", "Exception:--" + e1);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        StoredObjects.LogMethod("", "Exception:--" + e);
                    }


                } else {
                    picUri = data.getData();
                    try {
                        Log.d("uriGallery", picUri.toString());
                        performCrop(picUri);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

        }

        //user is returning from cropping the image
        else if (requestCode == PIC_CROP) {
            //get the returned data

            try {
                try {
                    Bitmap myBitmap = BitmapFactory.decodeFile(mCropImagedUri.getPath());
                    //ed_pro_image.setImageBitmap(myBitmap);
                    Photo_SHowDialog(getActivity(),f_new,mCropImagedUri.getPath(),myBitmap);

                } catch (Exception e) {
                    StoredObjects.LogMethod("Exception", "Exception:::" +e);
                    e.printStackTrace();
                }

                StoredObjects.LogMethod("Exception", "Exception::" + mCropImagedUri.getPath());


            } catch (Exception e1) {
                StoredObjects.LogMethod("Exception", "Exception::" +e1);
                e1.printStackTrace();
            }

        }


    }
    private void performCrop(Uri picUri) {
        try {

            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("scale", true);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 500);
            cropIntent.putExtra("outputY", 500);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            // startActivityForResult(cropIntent, PIC_CROP);

            f_new = createNewFile("CROP_");
            try {
                f_new.createNewFile();
            } catch (IOException ex) {
                Log.e("io", ex.getMessage());
            }

            mCropImagedUri = Uri.fromFile(f_new);
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCropImagedUri);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);

        }

        //respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public String getPath(Uri uri, Activity activity) {
        Cursor cursor = null;
        try {
            String[] projection = {MediaStore.MediaColumns.DATA};
            cursor = activity.getContentResolver().query(uri, projection, null, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                return cursor.getString(column_index);
            }
        } catch (Exception e) {
        } finally {
            cursor.close();
        }
        return "";
    }

    private String getFileNameByUri(Context context, Uri uri) {
        String filepath = "";//default fileName
        //Uri filePathUri = uri;
        File file;
        StoredObjects.LogMethod("", "imagepath:--" + uri);
        if (uri.getScheme().toString().compareTo("content") == 0) {
            Cursor cursor = context
                    .getContentResolver()
                    .query(uri,
                            new String[] {
                                    MediaStore.Images.ImageColumns.DATA,
                                    MediaStore.Images.Media.ORIENTATION },
                            null, null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            cursor.moveToFirst();

            String mImagePath = cursor.getString(column_index);
            cursor.close();
            filepath = mImagePath;

        }else if (uri.getScheme().compareTo("file") == 0) {
            try {
                file = new File(new URI(uri.toString()));
                if (file.exists())
                    filepath = file.getAbsolutePath();

            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            filepath = uri.getPath();
        }
        return filepath;
    }

    private Uri mCropImagedUri;
    File f_new;

    /**
     * Crop the image
     *
     * @return returns <tt>true</tt> if crop supports by the device,otherwise false
     */

    private File createNewFile(String prefix) {
        if (prefix == null || "".equalsIgnoreCase(prefix)) {
            prefix = "IMG_";
        }
        File newDirectory = new File( Environment.getExternalStorageDirectory() + "/mypics/");
        if (!newDirectory.exists()) {
            if (newDirectory.mkdir()) {
                Log.d(getActivity().getClass().getName(), newDirectory.getAbsolutePath() + " directory created");
            }
        }
        File file = new File(newDirectory, (prefix + System.currentTimeMillis() + ".jpg"));
        if (file.exists()) {
            //this wont be executed
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }


    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static Bitmap getUnRotatedImage(String imagePath, Bitmap rotatedBitmap)
    {
        int rotate = 0;
        try
        {
            File imageFile = new File(imagePath);
            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation)
            {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        return Bitmap.createBitmap(rotatedBitmap, 0, 0, rotatedBitmap.getWidth(), rotatedBitmap.getHeight(), matrix,
                true);
    }

    public void bitmapToUriConverter(Bitmap mBitmap) {
        Uri uri = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();

            File file = new File(getActivity().getFilesDir(), "ProfileImages"
                    + new Random().nextInt() + ".png");

            FileOutputStream out;
            int currentAPIVersion = Build.VERSION.SDK_INT;
            if (currentAPIVersion > Build.VERSION_CODES.M) {
                out = getActivity().openFileOutput(file.getName(),
                        Context.MODE_PRIVATE);
            }else{
                out = getActivity().openFileOutput(file.getName(),
                        Context.MODE_WORLD_READABLE);
            }

            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            //get absolute path
            String realPath = file.getAbsolutePath();
            File f = new File(realPath);
            uri = Uri.fromFile(f);
            StoredObjects.LogMethod("onresume", "realPath:-" + realPath);
            if (InterNetChecker.isNetworkAvailable(getActivity())) {
               // new ImageUploadTaskNew ().execute(realPath);
            }else{
                Toast.makeText(getActivity(), "Please Check Internet Connection.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Your Error Message", e.getMessage());
        }
    }

    Dialog dialog_show;
    public void Photo_SHowDialog(final Context context, final File captured_file, final String path, final Bitmap bitmap){
        dialog_show = new Dialog(context);
        dialog_show.getWindow();

        dialog_show.requestWindowFeature( Window.FEATURE_NO_TITLE);
        dialog_show.setContentView(R.layout.photoshow_popup );
        dialog_show.getWindow().setLayout( WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_show.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ImageView captured_image = (ImageView) dialog_show.findViewById(R.id.captured_image);
        Button cancel_btn = (Button) dialog_show.findViewById(R.id.cancel_btn);
        Button saveandcontinue__btn = (Button) dialog_show.findViewById(R.id.saveandcontinue__btn);

        String fileNameSegments[] = path.split("/");
        fileName = fileNameSegments[fileNameSegments.length - 1];

        myImg = Bitmap.createBitmap(getResizedBitmap(getUnRotatedImage(path, BitmapFactory.decodeFile(path)), 300));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        myImg.compress(Bitmap.CompressFormat.JPEG, 80, stream);

        captured_image.setImageBitmap(myImg);


        saveandcontinue__btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (InterNetChecker.isNetworkAvailable(getActivity())) {

                    //StoredObjects.LogMethod("", "pictureFile_uploading:--"+"::path::"+path);

                    //profile_image.setImageBitmap(myImg);

                    //uploadImage();
                  //  bitmapToUriConverter(myImg);

                    dialog_show.dismiss();
                }else{
                    Toast toast = Toast.makeText(context, "Please Check Internet Connection.", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog_show.dismiss();

            }
        });
        dialog_show.show();

    }
}
