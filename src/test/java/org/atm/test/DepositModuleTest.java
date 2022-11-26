package org.atm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;
import org.atm.main.dao.AtmOperations;
import org.atm.main.exception.InvalidAmountException;
import org.junit.jupiter.api.Test;

public class DepositModuleTest {
	
	 @Test 
	 public void testCase1() throws ClassNotFoundException, SQLException,InvalidAmountException 
	 {
	  
	  assertEquals(12500, AtmOperations.deposit(8695,500)); 
	  }
} 


	