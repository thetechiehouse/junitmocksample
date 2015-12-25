package com.techiehouse.mocksample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

/**
 * 
 * @author Prithvi Atal
 *
 */
//enable mockito annotations
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserDAO userDAO;

	@Mock
	UserUtil userUtilMock;

	@InjectMocks
	private static UserService userService;

	private static long startTime;

	/*
	 * uncomment this if @RunWith(MockitoJUnitRunner.class) is not added
	 * 
	 * @Before public void init() {     MockitoAnnotations.initMocks(this); }
	 */

	@BeforeClass
	public static void initCalculator() {
		userService = new UserServiceImpl();
		startTime = System.currentTimeMillis();
	}

	@Before
	public void logInfo() {
		System.out.println("Going to start test method");
	}

	@Before
	public void beforeEachTest() {
		System.out.println("This is executed before each Test");
	}

	@After
	public void afterEachTest() {
		System.out.println("This is exceuted after each Test");
	}

	@Test
	public void testAddData() {
		boolean result = userService.addData(3, 4);
		assertFalse(result);
	}

	@Test
	public void testAddDataIsSuccess() {
		ArgumentCaptor<Integer> arg = ArgumentCaptor.forClass(Integer.class);
		when(userDAO.isRecordPersists(any(Integer.class), any(Integer.class))).thenReturn(true);
		boolean result = userService.addData(3, 4);
		Mockito.verify(userUtilMock).processRecord(arg.capture(), arg.capture());
		assertEquals("3", arg.getAllValues().get(0).toString());
		assertEquals("4", arg.getAllValues().get(1).toString());
		assertTrue(result);
	}

	@Test
	public void testDivisonData() {
		try {
			int result = userService.getDivisonData(10, 2);

			assertEquals(5, result);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	@Test(expected = ArithmeticException.class)
	public void testDivisionException() {
		userService.getDivisonData(10, 0);
	}

	@Ignore
	@Test
	public void testEqual() {
		boolean result = userService.equalIntegers(20, 20);

		assertFalse(result);
	}

	@Ignore
	@Test
	public void testSubstractionUsrData() {
		int result = 10 - 3;
		assertTrue(result == 9);
	}

	@After
	public void tearDown() {
		System.out.println(" test run completes");

	}

	@AfterClass
	public static void logAfterRun() {
		System.out.println("After test run");
		System.out.println("Total time taken: " + (System.currentTimeMillis() - startTime) + " ms.");

	}

}
