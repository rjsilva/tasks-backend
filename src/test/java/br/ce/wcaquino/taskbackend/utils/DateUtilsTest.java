package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {
	
	
	@Test
	public void shouldReturnTrueIfDateIsFuture(){
		LocalDate local = LocalDate.of(2030, 01, 10);
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(local));
	}
	
	@Test
	public void shouldReturnTrueIfDateIsPresent(){
		LocalDate local = LocalDate.now();
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(local));
	}
	
	@Test
	public void shouldReturnTrueIfDateIsPast(){
		LocalDate local = LocalDate.of(2010, 01, 10);
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(local));
	}

}
