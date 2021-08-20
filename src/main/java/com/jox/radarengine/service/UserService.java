package com.jox.radarengine.service;

import com.jox.radarengine.model.UserData;
import java.math.BigDecimal;

public interface UserService {

	public String createDataUser(UserData userData);

	public String updateDataUser(UserData userData);

	public String deleteByNPWP(BigDecimal npwp);

	public String deleteBySIUP(String siup);
}
