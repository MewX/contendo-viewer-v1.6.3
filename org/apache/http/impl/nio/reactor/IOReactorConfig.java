/*     */ package org.apache.http.impl.nio.reactor;
/*     */ 
/*     */ import org.apache.http.util.Args;
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
/*     */ public final class IOReactorConfig
/*     */   implements Cloneable
/*     */ {
/*  39 */   public static final IOReactorConfig DEFAULT = (new Builder()).build();
/*     */   
/*     */   private long selectInterval;
/*     */   
/*     */   private long shutdownGracePeriod;
/*     */   
/*     */   private boolean interestOpQueued;
/*     */   
/*     */   private int ioThreadCount;
/*     */   
/*     */   private int soTimeout;
/*     */   
/*     */   private boolean soReuseAddress;
/*     */   private int soLinger;
/*     */   private boolean soKeepAlive;
/*     */   private boolean tcpNoDelay;
/*     */   private int connectTimeout;
/*     */   private int sndBufSize;
/*     */   private int rcvBufSize;
/*     */   private final int backlogSize;
/*     */   
/*     */   @Deprecated
/*     */   public IOReactorConfig() {
/*  62 */     this.selectInterval = 1000L;
/*  63 */     this.shutdownGracePeriod = 500L;
/*  64 */     this.interestOpQueued = false;
/*  65 */     this.ioThreadCount = Builder.getDefaultMaxIoThreadCount();
/*  66 */     this.soTimeout = 0;
/*  67 */     this.soReuseAddress = false;
/*  68 */     this.soLinger = -1;
/*  69 */     this.soKeepAlive = false;
/*  70 */     this.tcpNoDelay = true;
/*  71 */     this.connectTimeout = 0;
/*  72 */     this.sndBufSize = 0;
/*  73 */     this.rcvBufSize = 0;
/*  74 */     this.backlogSize = 0;
/*     */   }
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
/*     */   IOReactorConfig(long selectInterval, long shutdownGracePeriod, boolean interestOpQueued, int ioThreadCount, int soTimeout, boolean soReuseAddress, int soLinger, boolean soKeepAlive, boolean tcpNoDelay, int connectTimeout, int sndBufSize, int rcvBufSize, int backlogSize) {
/*  92 */     this.selectInterval = selectInterval;
/*  93 */     this.shutdownGracePeriod = shutdownGracePeriod;
/*  94 */     this.interestOpQueued = interestOpQueued;
/*  95 */     this.ioThreadCount = Args.positive(ioThreadCount, "ioThreadCount");
/*  96 */     this.soTimeout = soTimeout;
/*  97 */     this.soReuseAddress = soReuseAddress;
/*  98 */     this.soLinger = soLinger;
/*  99 */     this.soKeepAlive = soKeepAlive;
/* 100 */     this.tcpNoDelay = tcpNoDelay;
/* 101 */     this.connectTimeout = connectTimeout;
/* 102 */     this.sndBufSize = sndBufSize;
/* 103 */     this.rcvBufSize = rcvBufSize;
/* 104 */     this.backlogSize = backlogSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSelectInterval() {
/* 114 */     return this.selectInterval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setSelectInterval(long selectInterval) {
/* 122 */     Args.positive(selectInterval, "Select internal");
/* 123 */     this.selectInterval = selectInterval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getShutdownGracePeriod() {
/* 133 */     return this.shutdownGracePeriod;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setShutdownGracePeriod(long gracePeriod) {
/* 141 */     Args.positive(gracePeriod, "Shutdown grace period");
/* 142 */     this.shutdownGracePeriod = gracePeriod;
/*     */   }
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
/*     */   public boolean isInterestOpQueued() {
/* 157 */     return this.interestOpQueued;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setInterestOpQueued(boolean interestOpQueued) {
/* 165 */     this.interestOpQueued = interestOpQueued;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIoThreadCount() {
/* 174 */     return this.ioThreadCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setIoThreadCount(int ioThreadCount) {
/* 182 */     Args.positive(ioThreadCount, "I/O thread count");
/* 183 */     this.ioThreadCount = ioThreadCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSoTimeout() {
/* 194 */     return this.soTimeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setSoTimeout(int soTimeout) {
/* 202 */     this.soTimeout = soTimeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSoReuseAddress() {
/* 214 */     return this.soReuseAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setSoReuseAddress(boolean soReuseAddress) {
/* 222 */     this.soReuseAddress = soReuseAddress;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSoLinger() {
/* 234 */     return this.soLinger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setSoLinger(int soLinger) {
/* 242 */     this.soLinger = soLinger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSoKeepalive() {
/* 254 */     return this.soKeepAlive;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setSoKeepalive(boolean soKeepAlive) {
/* 262 */     this.soKeepAlive = soKeepAlive;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTcpNoDelay() {
/* 274 */     return this.tcpNoDelay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setTcpNoDelay(boolean tcpNoDelay) {
/* 282 */     this.tcpNoDelay = tcpNoDelay;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getConnectTimeout() {
/* 291 */     return this.connectTimeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setConnectTimeout(int connectTimeout) {
/* 299 */     this.connectTimeout = connectTimeout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSndBufSize() {
/* 311 */     return this.sndBufSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setSndBufSize(int sndBufSize) {
/* 319 */     this.sndBufSize = sndBufSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRcvBufSize() {
/* 331 */     return this.rcvBufSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setRcvBufSize(int rcvBufSize) {
/* 339 */     this.rcvBufSize = rcvBufSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBacklogSize() {
/* 350 */     return this.backlogSize;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IOReactorConfig clone() throws CloneNotSupportedException {
/* 355 */     return (IOReactorConfig)super.clone();
/*     */   }
/*     */   
/*     */   public static Builder custom() {
/* 359 */     return new Builder();
/*     */   }
/*     */   
/*     */   public static Builder copy(IOReactorConfig config) {
/* 363 */     Args.notNull(config, "I/O reactor config");
/* 364 */     return (new Builder()).setSelectInterval(config.getSelectInterval()).setShutdownGracePeriod(config.getShutdownGracePeriod()).setInterestOpQueued(config.isInterestOpQueued()).setIoThreadCount(config.getIoThreadCount()).setSoTimeout(config.getSoTimeout()).setSoReuseAddress(config.isSoReuseAddress()).setSoLinger(config.getSoLinger()).setSoKeepAlive(config.isSoKeepalive()).setTcpNoDelay(config.isTcpNoDelay()).setConnectTimeout(config.getConnectTimeout()).setSndBufSize(config.getSndBufSize()).setRcvBufSize(config.getRcvBufSize()).setBacklogSize(config.getBacklogSize());
/*     */   }
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
/*     */   public static class Builder
/*     */   {
/* 382 */     private static int DefaultMaxIoThreadCount = -1;
/*     */     
/*     */     private long selectInterval;
/*     */     
/*     */     private long shutdownGracePeriod;
/*     */     private boolean interestOpQueued;
/*     */     private int ioThreadCount;
/*     */     private int soTimeout;
/*     */     private boolean soReuseAddress;
/*     */     
/*     */     public static int getDefaultMaxIoThreadCount() {
/* 393 */       return (DefaultMaxIoThreadCount > 0) ? DefaultMaxIoThreadCount : Runtime.getRuntime().availableProcessors();
/*     */     }
/*     */ 
/*     */     
/*     */     private int soLinger;
/*     */     private boolean soKeepAlive;
/*     */     private boolean tcpNoDelay;
/*     */     private int connectTimeout;
/*     */     private int sndBufSize;
/*     */     private int rcvBufSize;
/*     */     private int backlogSize;
/*     */     
/*     */     public static void setDefaultMaxIoThreadCount(int defaultMaxIoThreadCount) {
/* 406 */       DefaultMaxIoThreadCount = defaultMaxIoThreadCount;
/*     */     }
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
/*     */     Builder() {
/* 424 */       this.selectInterval = 1000L;
/* 425 */       this.shutdownGracePeriod = 500L;
/* 426 */       this.interestOpQueued = false;
/* 427 */       this.ioThreadCount = getDefaultMaxIoThreadCount();
/* 428 */       this.soTimeout = 0;
/* 429 */       this.soReuseAddress = false;
/* 430 */       this.soLinger = -1;
/* 431 */       this.soKeepAlive = false;
/* 432 */       this.tcpNoDelay = true;
/* 433 */       this.connectTimeout = 0;
/* 434 */       this.sndBufSize = 0;
/* 435 */       this.rcvBufSize = 0;
/* 436 */       this.backlogSize = 0;
/*     */     }
/*     */     
/*     */     public Builder setSelectInterval(long selectInterval) {
/* 440 */       this.selectInterval = selectInterval;
/* 441 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setShutdownGracePeriod(long shutdownGracePeriod) {
/* 445 */       this.shutdownGracePeriod = shutdownGracePeriod;
/* 446 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setInterestOpQueued(boolean interestOpQueued) {
/* 450 */       this.interestOpQueued = interestOpQueued;
/* 451 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setIoThreadCount(int ioThreadCount) {
/* 455 */       this.ioThreadCount = ioThreadCount;
/* 456 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setSoTimeout(int soTimeout) {
/* 460 */       this.soTimeout = soTimeout;
/* 461 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setSoReuseAddress(boolean soReuseAddress) {
/* 465 */       this.soReuseAddress = soReuseAddress;
/* 466 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setSoLinger(int soLinger) {
/* 470 */       this.soLinger = soLinger;
/* 471 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setSoKeepAlive(boolean soKeepAlive) {
/* 475 */       this.soKeepAlive = soKeepAlive;
/* 476 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setTcpNoDelay(boolean tcpNoDelay) {
/* 480 */       this.tcpNoDelay = tcpNoDelay;
/* 481 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setConnectTimeout(int connectTimeout) {
/* 485 */       this.connectTimeout = connectTimeout;
/* 486 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setSndBufSize(int sndBufSize) {
/* 490 */       this.sndBufSize = sndBufSize;
/* 491 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setRcvBufSize(int rcvBufSize) {
/* 495 */       this.rcvBufSize = rcvBufSize;
/* 496 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setBacklogSize(int backlogSize) {
/* 500 */       this.backlogSize = backlogSize;
/* 501 */       return this;
/*     */     }
/*     */     
/*     */     public IOReactorConfig build() {
/* 505 */       return new IOReactorConfig(this.selectInterval, this.shutdownGracePeriod, this.interestOpQueued, this.ioThreadCount, this.soTimeout, this.soReuseAddress, this.soLinger, this.soKeepAlive, this.tcpNoDelay, this.connectTimeout, this.sndBufSize, this.rcvBufSize, this.backlogSize);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 515 */     StringBuilder builder = new StringBuilder();
/* 516 */     builder.append("[selectInterval=").append(this.selectInterval).append(", shutdownGracePeriod=").append(this.shutdownGracePeriod).append(", interestOpQueued=").append(this.interestOpQueued).append(", ioThreadCount=").append(this.ioThreadCount).append(", soTimeout=").append(this.soTimeout).append(", soReuseAddress=").append(this.soReuseAddress).append(", soLinger=").append(this.soLinger).append(", soKeepAlive=").append(this.soKeepAlive).append(", tcpNoDelay=").append(this.tcpNoDelay).append(", connectTimeout=").append(this.connectTimeout).append(", sndBufSize=").append(this.sndBufSize).append(", rcvBufSize=").append(this.rcvBufSize).append(", backlogSize=").append(this.backlogSize).append("]");
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
/* 530 */     return builder.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/IOReactorConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */