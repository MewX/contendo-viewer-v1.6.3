/*    */ package jp.cssj.d.a;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FilterInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.URI;
/*    */ import java.net.URLDecoder;
/*    */ import java.net.URLEncoder;
/*    */ import java.util.zip.ZipEntry;
/*    */ import java.util.zip.ZipFile;
/*    */ 
/*    */ public class o implements a {
/*    */   private final File a;
/*    */   private final ZipFile b;
/*    */   
/*    */   public o(File file) throws IOException {
/* 19 */     this.a = file;
/* 20 */     this.b = new ZipFile(file);
/*    */   }
/*    */   
/*    */   public o(File file, ZipFile zip) {
/* 24 */     this.a = file;
/* 25 */     this.b = zip;
/*    */   }
/*    */   
/*    */   public boolean a(String path) {
/*    */     try {
/* 30 */       return (this.b.getEntry(path) != null);
/* 31 */     } catch (IllegalStateException e) {
/*    */       
/* 33 */       try (ZipFile zip = new ZipFile(this.a)) {
/* 34 */         return (zip.getEntry(path) != null);
/*    */       }
/* 36 */       catch (Exception e1) {
/* 37 */         return false;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public InputStream b(String path) throws FileNotFoundException, IOException {
/* 43 */     path = URLDecoder.decode(URI.create(URLEncoder.encode(path, "UTF-8")).normalize().toString(), "UTF-8");
/*    */     try {
/* 45 */       ZipEntry entry = this.b.getEntry(path);
/* 46 */       if (entry == null) {
/* 47 */         throw new FileNotFoundException(path);
/*    */       }
/* 49 */       return this.b.getInputStream(entry);
/* 50 */     } catch (IllegalStateException e) {
/* 51 */       ZipFile zip = new ZipFile(this.a);
/* 52 */       ZipEntry entry = zip.getEntry(path);
/* 53 */       if (entry == null) {
/* 54 */         throw new FileNotFoundException(path);
/*    */       }
/* 56 */       return new FilterInputStream(this, zip.getInputStream(entry), zip) {
/*    */           public void close() throws IOException {
/* 58 */             super.close();
/* 59 */             this.a.close();
/*    */           }
/*    */         };
/*    */     } 
/*    */   }
/*    */   
/*    */   public void a() throws IOException {
/* 66 */     this.b.close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/d/a/o.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */