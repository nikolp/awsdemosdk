package com.example.awsdemosdk.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityRequest;
import software.amazon.awssdk.services.sts.model.GetCallerIdentityResponse;

@Component
public class StsRunner implements CommandLineRunner {

    @Override
    public void run(String... args)  {
        AwsCredentialsProvider provider = DefaultCredentialsProvider.create();
        try (StsClient stsClient = StsClient.builder()
                .credentialsProvider(provider)
                .build()) {
            GetCallerIdentityResponse response = stsClient.getCallerIdentity(GetCallerIdentityRequest.builder().build());
            
            System.out.println("Account ID: " + response.account());
            System.out.println("User ID: " + response.userId());
            System.out.println("Arn: " + response.arn());
            System.out.println("Application started!");
        }
    }
}