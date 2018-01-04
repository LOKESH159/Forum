package com.full.dto;

import java.util.UUID;

public class GenerateUUID {
public static String generateUUID(){
	UUID uuid = UUID.randomUUID();
    String randomUUIDString = uuid.toString();
    return randomUUIDString;
}
}
