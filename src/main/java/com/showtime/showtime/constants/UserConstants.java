package com.showtime.showtime.constants;

import java.util.Map;
import static java.util.Map.entry;

public class UserConstants {
    public static final Map<String, String> SUCCESS_MESSAGES = Map.ofEntries(
        entry("FETCH_ALL_USERS", "Successfully fetched all users")
    );
    public static final Map<String, String> ERROR_MESSAGES = Map.ofEntries(
        entry("FETCH_ALL_USERS", "Failed to fetch all users")
    );
}
