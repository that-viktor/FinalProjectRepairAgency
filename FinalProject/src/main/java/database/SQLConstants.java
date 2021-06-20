package database;

/**
 * SQLConstants class provides a names of the DB fields to simplify their refactoring
 * @author Viktor
 *
 */
public abstract class SQLConstants {
	public static final String ID_USER = "iduser";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "pass";
	public static final String ROLE = "idrole";
	public static final String ACCOUNT = "idaccount";
	public static final String FIRST_NAME = "fname";
	public static final String SURNAME = "surname";
	public static final String LAST_NAME = "lname";
	public static final String BALANCE = "balance";
	public static final String ID_SERVICE = "idservice";
	public static final String SERVICE_NAME = "service_name";
	public static final String PRICE_PER_UNIT= "price_per_unit";
	public static final String ID_RECEIPT = "idreceipt";
	public static final String ID_ADMIN = "idadmin";
	public static final String ID_MASTER = "idmaster";
	public static final String REC_DATE = "rec_date";
	public static final String TOTAL_SUM = "total_sum";
	public static final String ID_STATUS = "idstatus";
	public static final int INITIAL_STATUS = 1;
	public static final int ADMIN_ROLE_ID = 1;
	public static final int CLIENT_ROLE_ID = 2;
	public static final int MASTER_ROLE_ID = 3;
	public static final String EMAIL = "email";
	public static final String PHONE_NUM = "phone_num";
	public static final String STATUS_NAME = "status_name";
	public static final int STATUS_WAITING_FOR_PAYMENT_ID = 1;
	public static final int STATUS_PAYED_ID = 2;
	public static final int STATUS_CANCELLED_ID = 3;
	public static final int STATUS_PROCESSING_ID = 4;
	public static final int STATUS_PROCESSED_ID = 5;
	public static final String DESC = "descending";
	public static final String ASC = "ascending";
	public static final String AMOUNT_OF_WORK = "amount_of_work";
	public static final String SUM_TO_INCREASE_BALANCE = "sum";
	public static final int LIMIT = 5;
	public static final String ID_COMMENT = "idcomment";
	public static final String COMMENT_TEXT = "comment_text";
	public static final String COMMENT_DATE = "comment_date";
	public static final String STATUS_WAITING_FOR_PAYMENT = "waiting for payment";
	public static final String STATUS_PAYED = "payed";
	public static final String STATUS_CANCELLED = "cancelled";
	public static final String STATUS_PROCESSING = "processing";
	public static final String STATUS_PROCESSED = "processed";
	
}
