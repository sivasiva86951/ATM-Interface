package org.atm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.sql.SQLException;

import org.atm.main.dao.AtmOperations;
import org.atm.main.exception.InvalidAmountException;
import org.junit.jupiter.api.Test;

public class WithdrawModuleTest {
	
	@Test
	public void testCase1() throws ClassNotFoundException, SQLException, InvalidAmountException
	{
	
		assertEquals(4500, AtmOperations.withdraw(1425,19500));
	}

}
 