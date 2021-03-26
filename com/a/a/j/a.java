package com.a.a.j;

import com.a.a.e.b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface a {
  InputStream a() throws IOException;
  
  b b() throws IOException;
  
  InputStream a(File paramFile) throws IOException;
  
  b b(File paramFile) throws IOException;
  
  OutputStream c(File paramFile) throws IOException;
  
  void d(File paramFile) throws FileNotFoundException;
  
  void a(String paramString) throws FileNotFoundException;
  
  void a(OutputStream paramOutputStream);
  
  void e(File paramFile);
  
  void b(String paramString);
  
  void a(InputStream paramInputStream, String paramString) throws IOException;
  
  void a(InputStream paramInputStream, File paramFile) throws IOException;
  
  void a(InputStream paramInputStream, OutputStream paramOutputStream) throws IOException;
  
  void c();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */