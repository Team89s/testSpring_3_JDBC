package com.igeek.ch26.service.xml;

import java.util.List;

public interface ICashierService {

	//客户的结账
	public void cash(String username, List<Integer> bookIds);
}
