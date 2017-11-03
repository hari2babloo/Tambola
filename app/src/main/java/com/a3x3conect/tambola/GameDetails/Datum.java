package com.a3x3conect.tambola.GameDetails;

/**
 * Created by b on 30/10/17.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("startTime")
    @Expose
    private Integer startTime;
    @SerializedName("ticketCost")
    @Expose
    private Integer ticketCost;
    @SerializedName("prizeMoney")
    @Expose
    private Integer prizeMoney;
    @SerializedName("totalGameMoney")
    @Expose
    private Integer totalGameMoney;
    @SerializedName("minGameMoney")
    @Expose
    private Integer minGameMoney;
    @SerializedName("companyCost")
    @Expose
    private Integer companyCost;
    @SerializedName("companyCommission")
    @Expose
    private Integer companyCommission;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdDate")
    @Expose
    private Object createdDate;
    @SerializedName("prizes")
    @Expose
    private List<Prize> prizes = null;
    @SerializedName("rules")
    @Expose
    private List<Object> rules = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("passCode")
    @Expose
    private String passCode;
    @SerializedName("bumberGame")
    @Expose
    private Boolean bumberGame;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(Integer ticketCost) {
        this.ticketCost = ticketCost;
    }

    public Integer getPrizeMoney() {
        return prizeMoney;
    }

    public void setPrizeMoney(Integer prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public Integer getTotalGameMoney() {
        return totalGameMoney;
    }

    public void setTotalGameMoney(Integer totalGameMoney) {
        this.totalGameMoney = totalGameMoney;
    }

    public Integer getMinGameMoney() {
        return minGameMoney;
    }

    public void setMinGameMoney(Integer minGameMoney) {
        this.minGameMoney = minGameMoney;
    }

    public Integer getCompanyCost() {
        return companyCost;
    }

    public void setCompanyCost(Integer companyCost) {
        this.companyCost = companyCost;
    }

    public Integer getCompanyCommission() {
        return companyCommission;
    }

    public void setCompanyCommission(Integer companyCommission) {
        this.companyCommission = companyCommission;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    public List<Object> getRules() {
        return rules;
    }

    public void setRules(List<Object> rules) {
        this.rules = rules;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

    public Boolean getBumberGame() {
        return bumberGame;
    }

    public void setBumberGame(Boolean bumberGame) {
        this.bumberGame = bumberGame;
    }


    class Prize {

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


    public class Tambola {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
        @SerializedName("message")
        @Expose
        private String message;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}



