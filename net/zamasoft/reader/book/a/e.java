package net.zamasoft.reader.book.a;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import net.zamasoft.reader.book.m;

public class e {
  public static final int a = 3;
  
  public String b;
  
  public String c;
  
  public long d;
  
  public int e = 1;
  
  public m.a[] f;
  
  public m.a[] g;
  
  public int h;
  
  public boolean a(File paramFile) throws IOException {
    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(paramFile));
    try {
      if (objectInputStream.readInt() == 3) {
        this.h = objectInputStream.readInt();
        this.e = objectInputStream.readInt();
        this.b = objectInputStream.readUTF();
        this.c = objectInputStream.readUTF();
        this.d = objectInputStream.readLong();
        try {
          this.f = (m.a[])objectInputStream.readObject();
          this.g = (m.a[])objectInputStream.readObject();
        } catch (ClassNotFoundException classNotFoundException) {}
        boolean bool = true;
        objectInputStream.close();
        return bool;
      } 
      objectInputStream.close();
    } catch (Throwable throwable) {
      try {
        objectInputStream.close();
      } catch (Throwable throwable1) {
        throwable.addSuppressed(throwable1);
      } 
      throw throwable;
    } 
    return false;
  }
  
  public void b(File paramFile) throws IOException {
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(paramFile));
    try {
      objectOutputStream.writeInt(3);
      objectOutputStream.writeInt(this.h);
      objectOutputStream.writeInt(this.e);
      objectOutputStream.writeUTF(this.b);
      objectOutputStream.writeUTF(this.c);
      objectOutputStream.writeLong(this.d);
      objectOutputStream.writeObject(this.f);
      objectOutputStream.writeObject(this.g);
      objectOutputStream.close();
    } catch (Throwable throwable) {
      try {
        objectOutputStream.close();
      } catch (Throwable throwable1) {
        throwable.addSuppressed(throwable1);
      } 
      throw throwable;
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/a/e.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */