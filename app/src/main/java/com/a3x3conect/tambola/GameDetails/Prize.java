package com.a3x3conect.tambola.GameDetails;

/**
 * Created by b on 30/10/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Prize {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("prizeName")
    @Expose
    private String prizeName;
    @SerializedName("prizeCost")
    @Expose
    private Integer prizeCost;
    @SerializedName("prizeCompleted")
    @Expose
    private Boolean prizeCompleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Integer getPrizeCost() {
        return prizeCost;
    }

    public void setPrizeCost(Integer prizeCost) {
        this.prizeCost = prizeCost;
    }

    public Boolean getPrizeCompleted() {
        return prizeCompleted;
    }

    public void setPrizeCompleted(Boolean prizeCompleted) {
        this.prizeCompleted = prizeCompleted;
    }

}
