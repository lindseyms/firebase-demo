package com.example.firebasedemo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Configuration
public class FirebaseConfig {

    @Value("${firebaseDatabaseUrl}")
    private String firebaseDatabaseUrl;

    @PostConstruct
    public void initialize() {
        try {
            ClassLoader classLoader = FirebaseConfig.class.getClassLoader();

            File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(firebaseDatabaseUrl)
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
