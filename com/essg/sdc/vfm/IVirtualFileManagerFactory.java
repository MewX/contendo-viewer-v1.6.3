package com.essg.sdc.vfm;

import java.io.IOException;

import com.essg.sdc.vfm.VirtualFileManager.KeyStore;

public interface IVirtualFileManagerFactory {
	VirtualFileManager create(Object context, KeyStore keyStore) throws IOException;
}
