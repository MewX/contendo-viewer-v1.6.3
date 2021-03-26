/*    */ package de.codecentric.centerdevice.b;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class c {
/*    */   private static final int a = 1768124019;
/* 12 */   private int d = 0; private static final int b = 8; private final File c;
/* 13 */   private long e = 0L;
/*    */   
/* 15 */   private final HashMap<String, a> f = new HashMap<>();
/*    */   
/*    */   protected c(File icnsFile) {
/* 18 */     this.c = icnsFile;
/*    */   }
/*    */   
/*    */   public static c a(String path) throws IOException {
/* 22 */     return a(new File(path));
/*    */   }
/*    */   
/*    */   public static c a(File file) throws IOException {
/* 26 */     c parser = new c(file);
/* 27 */     parser.a();
/* 28 */     return parser;
/*    */   }
/*    */   
/*    */   public void a() throws IOException {
/* 32 */     try(InputStream fileInputStream = new FileInputStream(this.c); 
/* 33 */         DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {
/* 34 */       b(dataInputStream);
/* 35 */       a(dataInputStream);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(d iconType) {
/* 41 */     return this.f.containsKey(iconType.b());
/*    */   }
/*    */   
/*    */   public InputStream b(d iconType) throws IOException {
/* 45 */     if (!this.f.containsKey(iconType.b())) {
/* 46 */       return null;
/*    */     }
/*    */     
/* 49 */     return b(iconType.b());
/*    */   }
/*    */   
/*    */   private InputStream b(String osType) throws IOException {
/* 53 */     a icon = this.f.get(osType);
/* 54 */     InputStream iconData = new FileInputStream(this.c);
/* 55 */     iconData.skip(icon.b);
/* 56 */     return new b(iconData, icon.c);
/*    */   }
/*    */   
/*    */   private void a(DataInputStream dataInputStream) throws IOException {
/* 60 */     while (this.e < this.d) {
/* 61 */       a icon = c(dataInputStream);
/* 62 */       if (icon.c <= 0) {
/*    */         break;
/*    */       }
/*    */       
/* 66 */       this.f.put(icon.a, icon);
/* 67 */       this.e += icon.c;
/*    */     } 
/*    */   }
/*    */   
/*    */   private void b(DataInputStream stream) throws IOException {
/* 72 */     int magic = stream.readInt();
/* 73 */     if (magic != 1768124019) {
/* 74 */       throw new IllegalArgumentException("Provided file is not a valid icns file");
/*    */     }
/*    */     
/* 77 */     this.d = stream.readInt();
/* 78 */     this.e += 8L;
/*    */   }
/*    */   
/*    */   private a c(DataInputStream stream) throws IOException {
/* 82 */     byte[] type = new byte[4];
/* 83 */     stream.read(type);
/* 84 */     int length = stream.readInt();
/*    */     
/* 86 */     stream.skipBytes(length - 8);
/*    */     
/* 88 */     return new a(new String(type), this.e + 8L, length);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */