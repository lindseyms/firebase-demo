package com.example.firebasedemo.service;

import com.example.firebasedemo.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    private final Firestore firestore;

    @Autowired
    public FirebaseService(Firestore firestore){
        this.firestore = firestore;
    }

    public void saveData(User user) throws ExecutionException, InterruptedException {
        firestore.collection("users").document("test-user").set(user).get();
    }

    public User fetchUser() throws ExecutionException, InterruptedException {
        DocumentReference documentReference = firestore.collection("users").document("test-user");
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();
        if (document.exists()) {
            User user = document.toObject(User.class);
            return user;
        } else {
            return null;
        }
    }
}