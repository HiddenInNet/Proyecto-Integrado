package com.ms.motorsphere_api.error;

public class ErrorResponse {
    // Login / Register errors
    public static final String SERVER_001 = "Login: User not found or Invalid Credentials (userDTO = null)";
    public static final String SERVER_002 = "Login: Incorrect password, user and password does not match";

    // User errors

    // Bidders errors
    public static final String SERVER_201 = "Bidder: not found with 'id': ";
    public static final String SERVER_202 = "Bidder: can not add Bidder";

}
