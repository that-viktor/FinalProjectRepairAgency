package dao;

import database.DBManager;
import database.SQLConstants;
import database.SQLQueries;
import entities.Account;
import entities.User;
import exceptions.AccountException;

import java.sql.*;

public class AccountDAO {
	private static DBManager connector;
    public static int createNewAccount(Connection connection) throws AccountException {
        int accountId = 0;
        try (PreparedStatement pst = connection.prepareStatement(SQLQueries.INSERT_NEW_ACCOUNT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setDouble(1, 0);
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    accountId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AccountException("Error while creating account");
        }
        return accountId;
    }

    public static boolean topUpBalance(User u, double sum) throws AccountException {
    	connector = DBManager.getInstance();
        if (sum <= 0) {
            throw new AccountException("Invalid operation. Sum to increase must be > 0");
        }
        try (Connection connection = connector.getConnection();
             PreparedStatement pst = connection.prepareStatement(SQLQueries.TOP_UP_BALANCE)) {
            pst.setDouble(1, sum);
            pst.setLong(2, u.getAccountId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AccountException("Error increasing balance");
        }
    }

    private static double getAccountBalance(User u, Connection connection) throws AccountException {
        double balance = 0;
        ResultSet rs = null;
        try (PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_ACCOUNT_BALANCE)) {
            pst.setLong(1, u.getAccountId());
            rs = pst.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble(SQLConstants.BALANCE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new AccountException("Error getting account balance");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return balance;
    }

    public static boolean debitTheAccount(User u, double sum) throws AccountException {
    	connector = DBManager.getInstance();
        try (Connection connection = connector.getConnection();
             PreparedStatement pst = connection.prepareStatement(SQLQueries.TOP_UP_BALANCE)) {
            if (sum > getAccountBalance(u, connection)) {
                throw new AccountException("Insufficient funds");
            }
            pst.setDouble(1, -sum);
            pst.setLong(2, u.getAccountId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AccountException("Error debiting balance");
        }
    }

    public static Account getAccountById(long id) throws AccountException {
    	connector = DBManager.getInstance();
        Account account = new Account();
        ResultSet rs = null;
        try (Connection connection = connector.getConnection();
             PreparedStatement pst = connection.prepareStatement(SQLQueries.GET_ACCOUNT_BY_ID)) {
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                account.setAccountId(rs.getLong(SQLConstants.ACCOUNT));
                account.setBalance(rs.getDouble(SQLConstants.BALANCE));
            }
            return account;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AccountException("Account not found");
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
