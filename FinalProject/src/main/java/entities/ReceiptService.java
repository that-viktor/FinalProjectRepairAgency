package entities;

/**
 * ReceiptService class is a class that describes the DB entity - ReceiptService
 * 
 * @author Viktor
 *
 */
public class ReceiptService {
    private long receiptId;
    private long serviceId;
    private double servicePrice;

    public double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}

	public long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(long receiptId) {
        this.receiptId = receiptId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }
     
    public ReceiptService() {
    	
    }
    
    public ReceiptService(long serviceId, double servicePrice) {
    	this.serviceId = serviceId;
    	this.servicePrice = servicePrice;
    }

	@Override
	public String toString() {
		return "ReceiptService [receiptId=" + receiptId + ", serviceId=" + serviceId + ", servicePrice=" + servicePrice
				+ "]";
	}
    
}
