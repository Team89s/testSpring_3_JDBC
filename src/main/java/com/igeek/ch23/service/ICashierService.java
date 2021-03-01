package com.igeek.ch23.service;

import java.io.IOException;
import java.util.List;

public interface ICashierService {

	//客户的结账
	public void cash(String username, List<Integer> bookIds) throws IOException;
}
