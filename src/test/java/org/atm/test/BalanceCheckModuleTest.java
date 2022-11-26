package org.atm.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;
import org.atm.main.dao.AtmOperations;
import org.atm.main.exception.InvalidAmountException;
import org.junit.jupiter.api.Test;

public class BalanceCheckModuleTest {


	@Test
	public void testCase1() throws ClassNotFoundException, SQLException, InvalidAmountException
	{
	
		assertEquals(1500, AtmOperations.balanceCheck(1425));
	}
}	
