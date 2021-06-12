package entities;

public class ReceiptService {
    private long receiptId;
    private long serviceId;
    private double service_price;

    public double getServicePrice() {
		return service_price;
	}

	public void setService_price(double service_price) {
		this.service_price = service_price;
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
    
    public ReceiptService(long serviceId, double service_price) {
    	this.serviceId = serviceId;
    	this.service_price = service_price;
    }
}
