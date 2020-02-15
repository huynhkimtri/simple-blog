/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.utilities;

/**
 *
 * @author TriHK
 */
public class ConstantsUtils {

    public static final String USER_ROLE_ADMIN = "ADMIN";
    public static final String USER_ROLE_MEMBER = "MEMBER";

    public static final String USER_STATUS_NEW = "NEW";
    public static final String USER_STATUS_ACTIVE = "ACTIVE";

    public static final String STATUS_ARTICLE_NEW = "NEW";
    public static final String STATUS_ARTICLE_DELETE = "DELETE";
    public static final String STATUS_ARTICLE_ACTIVE = "ACTIVE";

    public static final String GENDER_MALE = "MALE";
    public static final String GENDER_FEMALE = "FEMALE";

}

enum UserRole {
    ADMIN, MEMBER
}
