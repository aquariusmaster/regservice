package com.aquariusmaster.dao;

import com.aquariusmaster.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by harkonnen on 31.05.16.
 * @author Andrey Bobrov
 */
@Repository("accountsDao")
public class AccountsDaoImpl implements AccountsDao {

    private static Logger logger = Logger.getLogger(AccountsDaoImpl.class);

    @Autowired
    private JdbcOperations jdbcOperations;

    /** Saving new Account into DB
     *
     * @param account Account object for saving in DB
     * @return {@code true} if new Account saved successfully
     */
    @Override
    public boolean create(Account account) {
        System.out.println("In create");
        String sql = "INSERT INTO accounts(email, password, is_confirmed) VALUES (?,?,?)";
        int result = jdbcOperations.update(sql, account.getEmail().toLowerCase(), account.getPassword(), account.is_confirmed());
        logger.info("AccountsDao: " + account + "; saved status is " + (result > 0));
        return result > 0;
    }

    /** Retrive Account entry from DB
     *
     * @param email get saved Account by email
     * @return retrived Account object
     */
    @Override
    public Account getAccount(String email) {
        System.out.println("In getAccount");
        String sql = "SELECT * FROM accounts WHERE email = ?";

        Account result = jdbcOperations.queryForObject(sql, new Object[]{email} , new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                Account account = new Account();
                account.setEmail(resultSet.getString("email"));
                account.setPassword(resultSet.getString("password"));
                account.setIs_confirmed(resultSet.getBoolean("is_confirmed"));
                return account;
            }
        });
        logger.info("AccountsDao:getAccount : retrived account:  " + result);
        return result;
    }

    /** Check for Account entry already exists
     *
     * @param email Email for compare
     * @return {@code true} email is already exists; {@code false otherwise}
     */
    @Override
    public boolean exists(String email) {
        String sql = "SELECT COUNT(*) FROM accounts WHERE email = ?";
        int result = jdbcOperations.queryForObject(sql, new Object[]{email.toLowerCase()}, Integer.class);
        logger.info("AccountsDao:EXIST exists is :  " + (result == 1) + " for email " + email);
        return result == 1;
    }

    /**
     * Update existed Account
     * @param account Account with new values
     * @return {@code true} if rows was updated
     */
    @Override
    public boolean update(Account account) {
        System.out.println("In create");
        String sql = "UPDATE accounts SET password = ?, is_confirmed = ? WHERE email = ?";
        int result = jdbcOperations.update(sql, account.getPassword(), account.is_confirmed(), account.getEmail().toLowerCase());
        logger.info("AccountsDao:UPDATE " + account + "; update status is " + (result > 0));
        return result > 0;
    }


}
