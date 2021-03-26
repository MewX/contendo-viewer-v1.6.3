package com.essg.sdc.vfm.http;

public interface HttpAuthorizer {
	String authorize(String text);

	String[] getOnetimeKey();
}
