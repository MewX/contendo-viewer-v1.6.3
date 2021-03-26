/*     */ package com.a.a.j.d;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.ServerSocket;
/*     */ import java.security.KeyStore;
/*     */ import java.security.KeyStoreException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.SecureRandom;
/*     */ import java.security.cert.CertificateException;
/*     */ import java.security.cert.X509Certificate;
/*     */ import javax.net.ssl.HostnameVerifier;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import javax.net.ssl.KeyManagerFactory;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLServerSocket;
/*     */ import javax.net.ssl.SSLServerSocketFactory;
/*     */ import javax.net.ssl.SSLSession;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import javax.net.ssl.X509TrustManager;
/*     */ 
/*     */ public class g
/*     */   extends f
/*     */ {
/*     */   private static final String ak = "TLS";
/*     */   private static final String al = "X509";
/*     */   private static final String am = "BKS";
/*  28 */   KeyStore d = null;
/*  29 */   char[] e = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private TrustManager[] an;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public g(d manager, int port, b authorizer) throws IOException
/*     */   {
/*  40 */     super(manager, port, authorizer);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     this
/*  92 */       .an = new TrustManager[] { new X509TrustManager(this) {
/*     */           public void checkClientTrusted(X509Certificate[] certs, String authType) {} public void checkServerTrusted(X509Certificate[] certs, String authType) {}
/*  94 */           public X509Certificate[] getAcceptedIssuers() { return null; } } }; } public g(d manager, int port) throws IOException { super(manager, port); this.an = new TrustManager[] { new X509TrustManager(this) { public void checkClientTrusted(X509Certificate[] certs, String authType) {} public X509Certificate[] getAcceptedIssuers() { return null; } public void checkServerTrusted(X509Certificate[] certs, String authType) {} } }; } public g(d manager) throws IOException { super(manager); this.an = new TrustManager[] { new X509TrustManager(this) { public X509Certificate[] getAcceptedIssuers() { return null; }
/*     */           
/*     */           public void checkClientTrusted(X509Certificate[] certs, String authType) {}
/*     */           public void checkServerTrusted(X509Certificate[] certs, String authType) {} }
/*     */          }; }
/*     */   
/*     */   public g a(InputStream istream, char[] password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
/*     */     this.d = KeyStore.getInstance("BKS");
/*     */     this.d.load(istream, password);
/*     */     this.e = password;
/*     */     return this;
/*     */   }
/*     */   public String c() {
/*     */     return "https";
/*     */   }
/*     */   protected ServerSocket a(int port) throws IOException {
/*     */     try {
/* 111 */       KeyManagerFactory kmf = KeyManagerFactory.getInstance("X509");
/* 112 */       kmf.init(this.d, this.e);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       SSLContext ssl = SSLContext.getInstance("TLS");
/* 118 */       ssl.init(kmf.getKeyManagers(), this.an, new SecureRandom());
/*     */       
/* 120 */       HttpsURLConnection.setDefaultHostnameVerifier(
/* 121 */           new HostnameVerifier(this)
/*     */           {
/*     */             public boolean verify(String paramString, SSLSession paramSSLSession)
/*     */             {
/* 125 */               return true;
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 130 */       SSLServerSocketFactory ssf = ssl.getServerSocketFactory();
/* 131 */       SSLServerSocket sock = (SSLServerSocket)ssf.createServerSocket(port);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 138 */       return sock;
/* 139 */     } catch (Exception e) {
/* 140 */       throw new IOException("Create socket error!!", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/d/g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */