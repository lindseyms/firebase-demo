package com.example.firebasedemo.service;

import com.example.firebasedemo.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class FirebaseService {

    private final Firestore firestore;

    @Autowired
    public FirebaseService(Firestore firestore){
        this.firestore = firestore;
    }

    public void saveData(User user) throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = firestore.collection("users");
        collectionReference.add(user).get();
    }

    public User fetchUserById(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = firestore.collection("users").document(id);
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