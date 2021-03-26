/*    */ package jp.cssj.homare.driver;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import java.util.Map;
/*    */ import jp.cssj.b.a;
/*    */ import jp.cssj.b.c;
/*    */ import jp.cssj.c.a;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DirectDriver
/*    */   implements a, a<URI>
/*    */ {
/* 17 */   protected static final URI a = URI.create("copper:direct:");
/*    */ 
/*    */   
/*    */   public static final String DEFAULT_PROFILE_FILE_KEY = "jp.cssj.driver.default";
/*    */   
/*    */   private static final String b = "conf/profiles";
/*    */   
/*    */   private static final String c = "default";
/*    */ 
/*    */   
/*    */   public static File getProfileDir() {
/* 28 */     String file = System.getProperty("jp.cssj.driver.default");
/* 29 */     if (file != null) {
/* 30 */       return (new File(file)).getParentFile();
/*    */     }
/* 32 */     File profileDir = new File("conf/profiles");
/* 33 */     return profileDir;
/*    */   }
/*    */   
/*    */   public static File getProfileFile(String profile) {
/* 37 */     String file = System.getProperty("jp.cssj.driver.default");
/* 38 */     if (file != null) {
/* 39 */       return new File(file);
/*    */     }
/* 41 */     File profileDir = getProfileDir();
/* 42 */     if (profile == null || profile.length() == 0) {
/* 43 */       profile = "default";
/*    */     }
/* 45 */     return new File(profileDir, profile + ".properties");
/*    */   }
/*    */   
/*    */   public boolean match(URI uri) {
/* 49 */     if (uri == null) {
/* 50 */       return true;
/*    */     }
/* 52 */     if (a.getScheme().equals(uri.getScheme()) && 
/* 53 */       uri.getSchemeSpecificPart().startsWith(a.getSchemeSpecificPart())) {
/* 54 */       return true;
/*    */     }
/*    */     
/* 57 */     return false;
/*    */   }
/*    */   
/*    */   public c getSession(URI uri, Map<String, String> props) throws IOException, SecurityException {
/* 61 */     a session = new a();
/* 62 */     if (uri != null) {
/* 63 */       String spec = uri.getSchemeSpecificPart();
/* 64 */       int specLength = a.getSchemeSpecificPart().length();
/* 65 */       if (spec != null && spec.length() > specLength) {
/* 66 */         spec = spec.substring(specLength);
/* 67 */         session.a(new File(spec));
/*    */       } 
/*    */     } 
/* 70 */     session.g();
/* 71 */     return session;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/driver/DirectDriver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */