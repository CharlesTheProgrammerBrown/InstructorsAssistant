package com.example.casper.instructorsassistant;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by CASPER on 11/2/2017.
 */

public class TokenGeneratorService extends FirebaseInstanceIdService {
    FirebaseUser user;
    DatabaseReference ref;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshToken= FirebaseInstanceId.getInstance().getToken();
        Log.d("FIREBASE_TOKEN","Token : "+refreshToken);
        sendToken(refreshToken);
//        user= FirebaseAuth.getInstance().getCurrentUser();
//        ref= FirebaseDatabase.getInstance().getReference();
//        ref.child("fcmToken").child(user.getUid()).setValue(refreshToken);
    }

    private void sendToken(String refreshToken) {
        ref= FirebaseDatabase.getInstance().getReference();
        ref.child("fcmToken").push().setValue(refreshToken);
    }
}
