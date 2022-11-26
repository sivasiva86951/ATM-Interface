package org.atm.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.atm.main.exception.InvalidAmountException;
import org.atm.main.dao.MysqlDBConnection;



public class AtmOperations {
	
	public static boolean enter(String accnumber, String pin) throws ClassNotFoundException, SQLException
	{
		try
		{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from atmbank_db where pin=?");
		statement.setString(1, pin);
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
		
			if(result.getString("pin").equals(pin))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Something went wrong!! "); 
		}
		catch(SQLException e)
		{
			System.out.println("Pin is incorrect!! "); 
		}
		return false;
		
		
	}
	
	public static double balanceCheck(long pin) throws ClassNotFoundException, SQLException
	{  
		double balance;
		try
		{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from atmbank_db where pin=?");		
		statement.setLong(1, pin);
		ResultSet result=statement.executeQuery();
		result.next();
		balance=result.getDouble("balance");
		return balance;
		
		}
		catch(SQLException e)
		{
			System.out.println("Wrong pin!!");
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong!!");
		}
		return 0;
	}

	
	public static double withdraw(long pinNumber, double withdrawalAmount) throws ClassNotFoundException, SQLException, InvalidAmountException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from atmbank_db where pin=?");
		statement.setLong(1, pinNumber);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double availableBalance=result.getDouble("balance");
		
		if(withdrawalAmount<availableBalance)
		{
		   double remainingBalance=availableBalance-withdrawalAmount;
		   statement=connection.prepareStatement("update atmbank_db set balance=? where pin=?");
		   statement.setDouble(1, new Double(remainingBalance));
		   statement.setLong(2, pinNumber);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return remainingBalance;  
		   }
		   else
		   {
			   return 0;
		   }
		}
		else
		{
			throw new InvalidAmountException("Unsufficient Withdrawal amount!!!");
		}
	}



	public static double deposit(long pinNumber, double depositAmount) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from atmbank_db where pin=?");
		statement.setLong(1, pinNumber);
		
		ResultSet result=statement.executeQuery();
		result.next();
		double avilableBalance=result.getDouble("balance");
		double newBalance=avilableBalance+depositAmount;
	
		statement=connection.prepareStatement("update atmbank_db set balance=? where pin=?");
		statement.setDouble(1, new Double(newBalance));
		statement.setLong(2, pinNumber);
		   
		   if(statement.executeUpdate()>0)
		   {
			   return newBalance;  
		   }
		   else
		   {
			   return 0;
	       }
	
   }
	
	public static ResultSet checkAccountInfo(long pinNumber) throws ClassNotFoundException, SQLException
	{
		Connection connection= MysqlDBConnection.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from atmbank_db where pin=?");
		statement.setLong(1, pinNumber);
		
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
			return result;
		}
		else
		{
			return null;
		}
	}

}

