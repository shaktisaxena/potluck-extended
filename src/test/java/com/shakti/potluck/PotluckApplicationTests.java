package com.shakti.potluck;

import com.shakti.potluck.entity.PotLuck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PotluckApplicationTests {

	@Test
	void nameNotNull_Potluck() {

		String potluckName="My First Luck";
		PotLuck potluck= new PotLuck();
		potluck.setName(potluckName);
		assertEquals(potluckName,potluck.getName());

	}

	@Test
	void notPossibleFamilyCountAsNegativeNumber_Potluck() {

		String potluckName="My First Luck";
		PotLuck potluck= new PotLuck();
		potluck.setName(potluckName);
		potluck.setFamilyCount(-1);

		assertNotEquals(1, potluck.getFamilyCount());
	}

}
