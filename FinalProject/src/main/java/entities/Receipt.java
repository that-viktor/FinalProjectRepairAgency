package entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Receipt class is a class that describes the DB entity - Receipt
 * 
 * @author Viktor
 *
 */
public class Receipt {
    private long id;
    private long userId;
    private long adminId;
    private long masterId;
    private Timestamp date;
    private double totalSum;
    private long statusId;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public long getMasterId() {
        return masterId;
    }

    public void setMasterId(long masterId) {
        this.masterId = masterId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public long getStatus() {
        return statusId;
    }

    public void setStatus(long statusId) {
        this.statusId = statusId;
    }

	@Override
	public String toString() {
		return "Receipt [id=" + id + ", userId=" + userId + ", adminId=" + adminId + ", masterId=" + masterId
				+ ", date=" + date + ", totalSum=" + totalSum + ", statusId=" + statusId + "]" + System.lineSeparator();
	}

}
