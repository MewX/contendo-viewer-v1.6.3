package net.zamasoft.reader.book.a;

import com.b.a.f;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import net.zamasoft.reader.b;
import net.zamasoft.reader.shelf.b;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.io.ScratchFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;

public class a extends d {
  private boolean s;
  
  public a(File paramFile) throws IOException, Exception {
    super(paramFile);
  }
  
  protected boolean y() throws IOException {
    this.s = false;
    FileInputStream fileInputStream = new FileInputStream(c());
    try {
      byte[] arrayOfByte = new byte[4];
      int i = fileInputStream.read(arrayOfByte);
      if (i == 4 && ((arrayOfByte[0] == 73 && arrayOfByte[1] == 71 && arrayOfByte[2] == 69 && arrayOfByte[3] == 70) || (arrayOfByte[0] == 67 && arrayOfByte[1] == 68 && arrayOfByte[2] == 69 && arrayOfByte[3] == 70)))
        this.s = true; 
    } finally {
      fileInputStream.close();
    } 
    if (super.y())
      return true; 
    if (this.s && !com.b.a.a.a().h())
      throw new f(b.a.getString("requireLogin")); 
    return false;
  }
  
  public boolean z() {
    return (!this.s || com.b.a.a.a().h());
  }
  
  public b a(boolean paramBoolean) throws Exception {
    if (this.s && !com.b.a.a.a().h())
      throw new f(b.a.getString("requireLogin")); 
    return (b)this;
  }
  
  public synchronized PDDocument A() {
    if (this.p == null) {
      b b = null;
      RandomAccessBufferedFileInputStream randomAccessBufferedFileInputStream = null;
      try {
        try {
          PDFParser pDFParser;
          ScratchFile scratchFile = new ScratchFile(MemoryUsageSetting.setupTempFileOnly());
          if (this.s) {
            b = new b(c(), com.b.a.a.a());
            pDFParser = new PDFParser(b, scratchFile);
          } else {
            pDFParser = new PDFParser((RandomAccessRead)(randomAccessBufferedFileInputStream = new RandomAccessBufferedFileInputStream(c())), scratchFile);
          } 
          pDFParser.parse();
          this.p = pDFParser.getPDDocument();
        } catch (IOException iOException) {
          throw new RuntimeException(iOException);
        } 
      } catch (RuntimeException runtimeException) {
        try {
          if (b != null)
            b.close(); 
          if (randomAccessBufferedFileInputStream != null)
            randomAccessBufferedFileInputStream.close(); 
        } catch (IOException iOException) {}
        throw runtimeException;
      } 
    } 
    return this.p;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/a/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */