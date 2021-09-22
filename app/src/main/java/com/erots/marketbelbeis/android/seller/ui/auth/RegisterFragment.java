package com.erots.marketbelbeis.android.seller.ui.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.erots.marketbelbeis.android.seller.R;
import com.erots.marketbelbeis.android.seller.databinding.RegisterFragmentBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class RegisterFragment extends Fragment {

    private final Uri registerProfilePath = null;
    private final StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final Map<String, Object> map = new HashMap<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    private RegisterFragmentBinding binding;
    private String uid;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = RegisterFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setLifecycleOwner(this);
        reference = database.getReference();
        binding.btnRegisterSignUp.setOnClickListener(view -> {
            Toast.makeText(binding.getRoot().getContext(), "Creating your account...", Toast.LENGTH_SHORT).show();
            map.put("owner_name", binding.edtRegisterOwnerFirstName.getText().toString() + " " + binding.edtRegisterOwnerSecondName.getText().toString());
            map.put("owner_phone", Integer.valueOf(binding.edtRegisterOwnerPhoneNumber.getText().toString()));
            map.put("store_name", binding.edtRegisterStoreName.getText().toString());
            map.put("store_phone", Integer.valueOf(binding.edtRegisterStorePhoneNumber.getText().toString()));
            map.put("store_mail", binding.edtRegisterStoreMail.getText().toString());
            map.put("store_category", binding.edtRegisterStoreCategory.getText().toString());
            map.put("store_pass", binding.edtRegisterPassword.getText().toString());
            map.put("date_store_created", getCurrentDate());

            auth.createUserWithEmailAndPassword(binding.edtRegisterStoreMail.getText().toString(), binding.edtRegisterPassword.getText().toString())
                    .addOnSuccessListener(task -> {
                        auth.signInWithEmailAndPassword(binding.edtRegisterStoreMail.getText().toString(), binding.edtRegisterPassword.getText().toString())
                                .addOnSuccessListener(authResult -> {
                                    uid = Objects.requireNonNull(authResult.getUser()).getUid();
                                    map.put("store_uid", uid);
                                    reference.child("stores").child(uid).child("store_profile").setValue(map).addOnSuccessListener(unused -> {
                                        Toast.makeText(binding.getRoot().getContext(), "account created successfully...", Toast.LENGTH_SHORT).show();
                                    });

                                });


                    });
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        return df.format(c);
    }



}