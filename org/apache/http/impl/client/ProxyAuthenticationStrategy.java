/*    */ package org.apache.http.impl.client;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.Queue;
/*    */ import org.apache.http.Header;
/*    */ import org.apache.http.HttpHost;
/*    */ import org.apache.http.HttpResponse;
/*    */ import org.apache.http.annotation.Contract;
/*    */ import org.apache.http.annotation.ThreadingBehavior;
/*    */ import org.apache.http.auth.AuthScheme;
/*    */ import org.apache.http.auth.MalformedChallengeException;
/*    */ import org.apache.http.client.config.RequestConfig;
/*    */ import org.apache.http.protocol.HttpContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Contract(threading = ThreadingBehavior.IMMUTABLE)
/*    */ public class ProxyAuthenticationStrategy
/*    */   extends AuthenticationStrategyImpl
/*    */ {
/* 47 */   public static final ProxyAuthenticationStrategy INSTANCE = new ProxyAuthenticationStrategy();
/*    */   
/*    */   public ProxyAuthenticationStrategy() {
/* 50 */     super(407, "Proxy-Authenticate");
/*    */   }
/*    */ 
/*    */   
/*    */   Collection<String> getPreferredAuthSchemes(RequestConfig config) {
/* 55 */     return config.getProxyPreferredAuthSchemes();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/client/ProxyAuthenticationStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */