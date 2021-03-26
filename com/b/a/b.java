package com.b.a;

import com.b.a.a.a.a.a;
import com.b.a.a.a.b.b;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class b {
  public byte[] a(String... paramVarArgs) {
    byte[] arrayOfByte = null;
    b b1 = new b();
    int[] arrayOfInt = b1.b();
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      CRC32 cRC32 = new CRC32();
      DataOutputStream dataOutputStream = new DataOutputStream(new CheckedOutputStream(byteArrayOutputStream, cRC32));
      dataOutputStream.writeInt(2);
      dataOutputStream.writeInt(arrayOfInt.length);
      for (int k : arrayOfInt)
        dataOutputStream.writeInt(k); 
      dataOutputStream.flush();
      int i = byteArrayOutputStream.size();
      if (paramVarArgs == null) {
        dataOutputStream.writeInt(0);
      } else {
        dataOutputStream.writeInt(paramVarArgs.length);
        for (String str : paramVarArgs)
          dataOutputStream.writeUTF(str); 
      } 
      dataOutputStream.flush();
      dataOutputStream.writeLong(cRC32.getValue());
      dataOutputStream.close();
      int j = byteArrayOutputStream.size();
      arrayOfByte = byteArrayOutputStream.toByteArray();
      byte[] arrayOfByte1 = new byte[i];
      (new b(305419896, 396776, 1040058, -1745425238)).nextBytes(arrayOfByte1);
      byte b2;
      for (b2 = 8; b2 < arrayOfByte1.length; b2++)
        arrayOfByte[b2] = (byte)((arrayOfByte[b2] ^ arrayOfByte1[b2]) & 0xFF); 
      byte[] arrayOfByte2 = new byte[j];
      b1.nextBytes(arrayOfByte2);
      while (b2 < arrayOfByte2.length) {
        arrayOfByte[b2] = (byte)((arrayOfByte[b2] ^ arrayOfByte2[b2]) & 0xFF);
        b2++;
      } 
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
    return arrayOfByte;
  }
  
  public String[] a(byte[] paramArrayOfbyte) {
    String[] arrayOfString = null;
    byte[] arrayOfByte = null;
    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    try {
      int i = dataInputStream.readInt();
      int j = dataInputStream.readInt();
      arrayOfByte = new byte[8 + j * 4];
      (new b(305419896, 396776, 1040058, -1745425238)).nextBytes(arrayOfByte);
      for (byte b1 = 8; b1 < arrayOfByte.length; b1++)
        paramArrayOfbyte[b1] = (byte)((paramArrayOfbyte[b1] ^ arrayOfByte[b1]) & 0xFF); 
      int[] arrayOfInt = new int[j];
      for (byte b2 = 0; b2 < j; b2++)
        arrayOfInt[b2] = dataInputStream.readInt(); 
      byte[] arrayOfByte1 = new byte[paramArrayOfbyte.length];
      (new b(arrayOfInt)).nextBytes(arrayOfByte1);
      int k;
      for (k = arrayOfByte.length; k < arrayOfByte1.length; k++)
        paramArrayOfbyte[k] = (byte)((paramArrayOfbyte[k] ^ arrayOfByte1[k]) & 0xFF); 
      if (i >= 2) {
        CRC32 cRC32 = new CRC32();
        cRC32.update(paramArrayOfbyte, 0, paramArrayOfbyte.length - 8);
        if (cRC32.getValue() != a.d(paramArrayOfbyte, paramArrayOfbyte.length - 8))
          throw new IOException("CRC error!!"); 
      } 
      k = dataInputStream.readInt();
      arrayOfString = new String[k];
      for (byte b3 = 0; b3 < k; b3++)
        arrayOfString[b3] = dataInputStream.readUTF(); 
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } finally {
      try {
        dataInputStream.close();
      } catch (Exception exception) {}
    } 
    return arrayOfString;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/b/a/b.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */