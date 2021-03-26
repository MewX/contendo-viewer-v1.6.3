package net.zamasoft.reader.book;

import java.io.File;
import java.io.IOException;
import net.zamasoft.reader.book.a.a;
import net.zamasoft.reader.book.epub.a;

public class d {
  public static b a(File paramFile) throws IOException, Exception {
    return (b)(paramFile.getName().toLowerCase().endsWith(".pdf") ? new a(paramFile) : new a(paramFile));
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/d.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */