package com.essg.sdc.vfm.file;

import java.io.FileNotFoundException;
import java.io.OutputStream;

public interface WriteableFileInterface extends FileInterface {
	public OutputStream openOutputStream() throws FileNotFoundException;
}
