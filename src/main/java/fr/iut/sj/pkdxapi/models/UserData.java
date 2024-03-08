package fr.iut.sj.pkdxapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model document for users
 */
@Document("User")
@TypeAlias("UserData")
public class UserData {
    @Id
    private String login;
    private String password;
    private boolean admin;

    /**
     * Init a user
     * @param login the user login
     * @param password the user pass
     * @param admin tells if the user is an admin
     */
    public UserData(String login, String password, boolean admin){
        this.login = login;
        this.password = password;
        this.admin = admin;        
    }

    /**
     * 
     * @return true if user has admin role
     */
    public boolean admin(){
        return this.admin;
    }

    /**
     * 
     * @return user's login
     */
    public String getLogin(){
        return login;
    }

    /**
     * 
     * @return user's password (plain text)
     */
    public String getPassword(){
        return password;
    }
}