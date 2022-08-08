package com.online.bookstore;

import org.apache.catalina.valves.rewrite.Substitution.StaticElement;
import org.apache.tomcat.jdbc.pool.interceptor.StatementCache;

public class UserEmail {
    static String userEmail;

	public static String getUserEmail() {
		return userEmail;
	}

	public static void setUserEmail(String userEmail1) {
	userEmail = userEmail1;
	}
    
}
