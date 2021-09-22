package com.erots.marketbelbeis.android.seller.ui.post_product;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.erots.marketbelbeis.android.seller.R;
import com.erots.marketbelbeis.android.seller.databinding.PostProductFragmentBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PostProductFragment extends Fragment {

    private final int PICK_IMAGE_REQUEST = 143;
    private final Map<String, Object> map = new HashMap<>();
    PostProductFragmentBinding binding;
    Uri uri = null;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    ArrayList<Uri> imagesArrayList = new ArrayList<>();
    int tempImages = 1;
    private PostProductViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PostProductFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PostProductViewModel.class);
        binding.setLifecycleOwner(this);
        spinners();

        binding.ivPostProductImage.setOnClickListener(view -> {
            getImageFromStorage();
        });


        binding.btnPostProductPost.setOnClickListener(view -> {
            postProduct("product_scheduled", binding.edtPostProductScheduledDate.getText().toString());

        });
        binding.btnPostProductScheduled.setOnClickListener(view -> {
            Long aLong = storageReference.child("stores").child(auth.getUid()).child("store_products").getMetadata().getResult().getSizeBytes();
            Toast.makeText(binding.getRoot().getContext(), aLong + " images selected…", Toast.LENGTH_SHORT).show();

        });

        // TODO: Use the ViewModel
    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(binding.getRoot().getContext());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_date_picker);

        bottomSheetDialog.show();
    }

    private void postProduct(String child, String key) {

        map.put("product_content", binding.edtPostProductContent.getText().toString());
        map.put("product_discount", binding.edtPostProductDiscount.getText().toString());
        map.put("product_store_name", binding.edtPostProductStoreName.getText().toString());
        map.put("product_price", binding.edtPostProductPrice.getText().toString());
        map.put("product_discount_ends_at", binding.edtPostProductDiscountEndsAt.getText().toString());
        map.put("product_discount_starts_at", binding.edtPostProductDiscountStartsAt.getText().toString());
        map.put("product_quantity", binding.edtPostProductQuantity.getText().toString());
        map.put("product_title", binding.edtPostProductTitle.getText().toString());
        map.put("product_type", binding.spinnerPostProductType.getSelectedItem().toString());
        map.put("product_grander", binding.spinnerPostProductGrander.getSelectedItem().toString());
        map.put("product_store_uid", auth.getUid());
        map.put(child, key);
        map.put("product_scheduled", binding.edtPostProductScheduledDate);


    }

    private void spinners() {
        ArrayAdapter<CharSequence> arrayAdapterCategory = ArrayAdapter.createFromResource(binding.getRoot().getContext(), R.array.product_type, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> arrayAdapterGrander = ArrayAdapter.createFromResource(binding.getRoot().getContext(), R.array.grander, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_item);
        binding.spinnerPostProductType.setAdapter(arrayAdapterCategory);
        binding.spinnerPostProductGrander.setAdapter(arrayAdapterGrander);


    }

    @SuppressLint("InlinedApi")
    private void getImageFromStorage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null || data.getClipData() != null) {

            if (data.getClipData() != null) {
                int tempImagesCount = data.getClipData().getItemCount();
                int tempCurrentImageSelected = 0;

                while (tempCurrentImageSelected < tempImagesCount) {
                    uri = data.getClipData().getItemAt(tempCurrentImageSelected).getUri();
                    imagesArrayList.add(uri);
                    tempCurrentImageSelected += 1;
                }

                Glide.with(this)
                        .load(imagesArrayList.get(1))
                        .into(binding.ivPostProductImage);

                Toast.makeText(binding.getRoot().getContext(), tempImagesCount + " images selected…", Toast.LENGTH_SHORT).show();
                uploadProfileImage(getUriToDrawable(binding.getRoot().getContext(), R.drawable.test_img));

            } else {
                uri = data.getData();
                imagesArrayList.add(uri);
                Glide.with(this)
                        .load(R.drawable.test_img)
                        .into(binding.ivPostProductImage);
                uploadProfileImage(getUriToDrawable(binding.getRoot().getContext(), R.drawable.test_img));

            }


        }
    }

    private Uri getUriToDrawable(@NonNull Context context,
                                 @AnyRes int drawableId) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId));

    }


    private void uploadProfileImage(Uri profilePat) {
        String tempImageName = getCurrentDate() + "-" + tempImages;
        storageReference.child("stores").child(auth.getUid()).child("store_products").child(binding.edtPostProductStoreName.getText().toString() + "-" + getCurrentDate()).child(tempImageName).putFile(profilePat)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        storageReference.child("stores").child(auth.getUid()).child("store_products").child(binding.edtPostProductStoreName.getText().toString() + "-" + getCurrentDate()).child(tempImageName).getDownloadUrl()
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Log.e("downloadURL", task1.getResult().toString(), task1.getException());
                                        Snackbar.make(binding.getRoot(), String.valueOf(task1.getResult()), Snackbar.LENGTH_LONG).show();

//                                        map.put("profile_url", );
//                                        firestore.collection("users").document("uid").set(map).addOnSuccessListener(aVoid -> {
//                                            //finish();
//                                        }).addOnFailureListener(e -> {
//                                            binding.btnPostProductPost.setClickable(true);
//                                            Snackbar.make(binding.getRoot(), String.valueOf(e), Snackbar.LENGTH_LONG).show();
//                                        });
//                                    } else {
//                                        binding.btnPostProductPost.setClickable(true);
//                                        Snackbar.make(binding.getRoot(), String.valueOf(task.getResult()), Snackbar.LENGTH_LONG).show();
                                    }
                                });
                    } else {
                        binding.btnPostProductPost.setClickable(true);
                        String errorMessage = task.getException().getMessage();
                        Toast.makeText(binding.getRoot().getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(taskSnapshot -> {

        });
    }

    public String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        return df.format(c);
    }

}