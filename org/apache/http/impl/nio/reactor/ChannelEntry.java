/*    */ package org.apache.http.impl.nio.reactor;
/*    */ 
/*    */ import java.nio.channels.SocketChannel;
/*    */ import org.apache.http.util.Args;
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
/*    */ public class ChannelEntry
/*    */ {
/*    */   private final SocketChannel channel;
/*    */   private final SessionRequestImpl sessionRequest;
/*    */   
/*    */   public ChannelEntry(SocketChannel channel, SessionRequestImpl sessionRequest) {
/* 55 */     Args.notNull(channel, "Socket channel");
/* 56 */     this.channel = channel;
/* 57 */     this.sessionRequest = sessionRequest;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ChannelEntry(SocketChannel channel) {
/* 66 */     this(channel, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SessionRequestImpl getSessionRequest() {
/* 78 */     return this.sessionRequest;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getAttachment() {
/* 88 */     return (this.sessionRequest != null) ? this.sessionRequest.getAttachment() : null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SocketChannel getChannel() {
/* 97 */     return this.channel;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/ChannelEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */