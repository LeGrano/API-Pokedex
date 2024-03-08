package fr.iut.sj.pkdxapi.models;


public class UserDTO {

    private String username;
    private String pswd;
    private boolean admin;
    
    public UserDTO(String username, String pswd) {
        this.username = username;
        this.pswd = pswd;
        this.admin = false;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return pswd;
    }
    
    public void setPasword(String pswd) {
        this.pswd = pswd;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
