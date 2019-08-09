package com.hvktmm.at12i.utils;



import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Pass {
    public String encrypt(String pass){
        StringBuilder sb=new StringBuilder();
        try {
            MessageDigest bam=MessageDigest.getInstance("SHA-256");
            byte[] hashInBytes=bam.digest(pass.getBytes(StandardCharsets.UTF_8));
//            StringBuilder sb=new StringBuilder();
            for (byte b:hashInBytes){
                sb.append(String.format("%02x",b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Pass a=new Pass();
        System.out.println(a.encrypt("nam"));
    }
}

