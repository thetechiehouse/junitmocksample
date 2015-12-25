package com.techiehouse.mocksample;

import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	UserUtil userUtil;

	@Override
	public boolean addData(int a, int b) {
		int data = userUtil.processRecord(a, b);
		if (userDAO.isRecordPersists(a, b)) {
			return true;
		} else
			return false;
	}

	@Override
	public int saveSubtractedData(int a, int b) {
		return a - b;
	}

	@Override
	public int saveMultiplicationData(int a, int b) {
		return a * b;
	}

	@Override
	public int getDivisonData(int a, int b) {

		return a / b;
	}

	@Override
	public boolean equalIntegers(int a, int b) {
		boolean result = false;

		if (a == b) {
			result = true;
		}

		return result;
	}

}
