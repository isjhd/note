package com.hspedu.qqcommon;

import java.io.Serializable;

/* @author  i-s-j-h-d
 * @version 1.0
 * 表示一个用户的信息
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userID;//用户ID
    private String passwd;//用户密码

    public User() {

    }

    public User(String userID, String passwd) {
        this.userID = userID;
        this.passwd = passwd;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
