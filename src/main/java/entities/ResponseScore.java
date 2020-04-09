package entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseScore implements Serializable {

    @SerializedName("score")
    private String score;

    public ResponseScore() {
    }

    public ResponseScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
