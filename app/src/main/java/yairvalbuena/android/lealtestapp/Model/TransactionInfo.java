package yairvalbuena.android.lealtestapp.Model;

import com.google.gson.annotations.SerializedName;

public class TransactionInfo {
    @SerializedName("transactionId")
    public int transactionId;
    @SerializedName("value")
    public int value;
    @SerializedName("points")
    public int points;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
