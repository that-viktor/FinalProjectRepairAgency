package database;

public abstract class SQLQueries {
	public static final String INSERT_NEW_ACCOUNT = "INSERT INTO repair_agency.account (balance) VALUES (?);";
	public static final String TOP_UP_BALANCE = "UPDATE repair_agency.account SET balance = balance + ? "
			+ "WHERE idaccount = ?;";
	public static final String GET_ACCOUNT_BALANCE = "SELECT balance FROM repair_agency.account WHERE "
			+ "idaccount = ?";
	public static final String GET_ACCOUNT_BY_ID = "SELECT idaccount, balance FROM repair_agency.account WHERE "
			+ "idaccount = ?;";
	public static final String GET_ALL_USERS = "SELECT * FROM repair_agency.user ORDER BY iduser;";
	public static final String GET_USER_BY_ID = "SELECT * FROM repair_agency.user WHERE iduser=?;";
	public static final String GET_USER_BY_LOGIN = "SELECT * FROM repair_agency.user WHERE login=?;";
	public static final String GET_USERS_BY_ROLE = "SELECT * FROM repair_agency.user WHERE idrole = ?;";
	public static final String INSERT_USER = "INSERT INTO repair_agency.user "
			+ "(login, pass, idrole, idaccount, fname, surname, lname, email, phone_num) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String GET_ALL_SERVICES = "SELECT * FROM repair_agency.service;";
	public static final String GET_SERVICE_BY_ID = "SELECT * FROM repair_agency.service WHERE idservice = ?;";
	public static final String INSERT_NEW_RECEIPT = "INSERT INTO repair_agency.receipt "
			+ "(iduser, rec_date, idstatus) VALUES (?, ?, ?);";
	public static final String INSERT_RECEIPT_SERVICE = "INSERT INTO repair_agency.receipt_has_service "
			+ "(idreceipt, idservice, service_price) VALUES (?, ?, ?);";
	public static final String SET_MASTER_FOR_RECEIPT = "UPDATE repair_agency.receipt SET idmaster = ? WHERE idreceipt = ?;";
	public static final String SET_ADMIN_FOR_RECEIPT = "UPDATE repair_agency.receipt SET idadmin = ? WHERE idreceipt = ?;";
	public static final String GET_ALL_RECEIPTS = "SELECT * FROM repair_agency.receipt ORDER BY idreceipt;";
	public static final String GET_RECEIPT_BY_ID = "SELECT * FROM repair_agency.receipt WHERE idreceipt = ?;";
	public static final String SET_RECEIPT_TOTAL_SUM = "UPDATE repair_agency.receipt SET total_sum = ? WHERE idreceipt = ?;";
	public static final String GET_RECEIPT_SERVICES = "SELECT service.idservice, service_name, price_per_unit FROM receipt_has_service"
			+ " INNER JOIN service ON service.idservice = receipt_has_service.idservice WHERE idreceipt = ?;";
	public static final String GET_RECEIPT_USER = "SELECT user.iduser, login, pass ,idrole, idaccount, fname, lname, surname, email, phone_num"
			+ " FROM repair_agency.receipt INNER JOIN repair_agency.user ON RECEIPT.iduser = user.iduser"
			+ " WHERE idreceipt = ? AND user.idrole = 2;";
	public static final String GET_RECEIPT_ADMIN= "SELECT user.iduser, login, pass ,idrole, idaccount, fname, lname, surname, email, phone_num"
			+ " FROM repair_agency.receipt INNER JOIN repair_agency.user ON RECEIPT.idadmin = user.iduser"
			+ " WHERE idreceipt = ? AND user.idrole = 1;";
	public static final String GET_RECEIPT_MASTER = "SELECT user.iduser, login, pass ,idrole, idaccount, fname, lname, surname, email, phone_num"
			+ " FROM repair_agency.receipt INNER JOIN repair_agency.user ON RECEIPT.idmaster = user.iduser"
			+ " WHERE idreceipt = ? AND user.idrole = 3;";
	public static final String REMOVE_RECEIPT = "DELETE FROM repair_agency.receipt WHERE idreceipt = ?;";
	public static final String GET_RECEIPT_TOTAL_SUM = "SELECT total_sum FROM repair_agency.receipt WHERE idreceipt = ?;";
	public static final String GET_RECEIPTS_BY_STATUS = "SELECT * FROM repair_agency.receipt WHERE idstatus = ?;";
	public static final String GET_ALL_STATUSES = "SELECT * FROM repair_agency.rec_status ORDER BY idstatus;";
	public static final String UPDATE_RECEIPT_STATUS = "UPDATE repair_agency.receipt SET idstatus = ? WHERE idreceipt = ?;";
	public static final String GET_RECEIPTS_ORDER_BY_DATE_ASC = "SELECT * FROM repair_agency.receipt ORDER BY rec_date;";
	public static final String GET_RECEIPTS_ORDER_BY_DATE_DESC = "SELECT * FROM repair_agency.receipt ORDER BY rec_date DESC;";
	public static final String GET_RECEIPTS_ORDER_BY_RECEIPT_SUM_ASC = "SELECT * FROM repair_agency.receipt ORDER BY total_sum;";
	public static final String GET_RECEIPTS_ORDER_BY_RECEIPT_SUM_DESC = "SELECT * FROM repair_agency.receipt ORDER BY total_sum DESC;";
	public static final String REMOVE_MASTER_BY_ID_RECEIPT = "UPDATE repair_agency.receipt SET idmaster = NULL WHERE idreceipt = ?;";
	public static final String GET_RECEIPTS_FILTERED_BY_ID_MASTER = "SELECT * FROM repair_agency.receipt WHERE idmaster = ?;";
	public static final String GET_SERVICES_LIMITED = "SELECT * FROM repair_agency.service LIMIT ? OFFSET ?";
	
}
