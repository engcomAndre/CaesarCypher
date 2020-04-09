package entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class FileEntity implements Serializable {

    @SerializedName("numero_casas")
    private Integer role;

    @SerializedName("token")
    private String token;

    @SerializedName("cifrado")
    private String cipherText;

    @SerializedName("decifrado")
    private String decipherText;

    @SerializedName("resumo_criptografico")
    private String encriptesSH1Resume;

    public FileEntity() {
    }

    public FileEntity(Integer role, String token, String cipherText, String decipherText, String encriptesSH1Resume) {

        this.role = role;
        this.token = token;
        this.cipherText = cipherText;
        this.decipherText = decipherText;
        this.encriptesSH1Resume = encriptesSH1Resume;
    }


    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public String getDecipherText() {
        return decipherText;
    }

    public void setDecipherText(String decipherText) {
        this.decipherText = decipherText;
    }

    public String getEncriptesSH1Resume() {
        return encriptesSH1Resume;
    }

    public void setEncriptesSH1Resume(String encriptesSH1Resume) {
        this.encriptesSH1Resume = encriptesSH1Resume;
    }
}

