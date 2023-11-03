package edu.carroll.bankapp.service;

import edu.carroll.bankapp.jpa.model.SiteUser;

/**
 * Interface for managing users.
 */
public interface UserService {
    /**
     * Get a user from the given user id.
     *
     * @param userId the ID of the user
     * @return the user if found, or null if not found
     */
    SiteUser getUser(int userId);

    /**
     * Get a user from the given username.
     *
     * @param username the username of the user
     * @return the user if found, or null if not found
     */
    SiteUser getUser(String username);

    /**
     * Create a new user and save it in the database (without confirm password).
     *
     * @param fullName    the full name of the user
     * @param email       the email of the user
     * @param username    the username of the user
     * @param rawPassword the raw password of the user
     * @return the created user
     */
    SiteUser createUser(String fullName, String email, String username, String rawPassword);

    /**
     * Delete all site users. (Should ONLY be used for testing)
     */
    void deleteAllSiteUsers();

    /**
     * Allows the user to update their password and compares that the passwords that they enter will match.
     *
     * @param user - user using our site
     * @param oldPassword - the old password for the user's account
     * @param newPassword - the new password for the user's account
     * @param newConfirm - the confirmation password for the user's account
     * @return true / false if the password is updated
     */
    boolean updatePassword(SiteUser user, String oldPassword, String newPassword, String newConfirm);

    /**
     * Allows the user to update their username and password, then logs them out and back in immediately.
     *
     * @param user - user using our site
     * @param confirmPassword - the old username for the user's account
     * @param newUsername - the new username for the user's account
     * @return true / false if the username is updated
     */
    boolean updateUsername(SiteUser user, String confirmPassword, String newUsername);

}
