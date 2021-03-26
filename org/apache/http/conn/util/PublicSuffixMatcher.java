/*     */ package org.apache.http.conn.util;
/*     */ 
/*     */ import java.net.IDN;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.http.annotation.Contract;
/*     */ import org.apache.http.annotation.ThreadingBehavior;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Contract(threading = ThreadingBehavior.SAFE)
/*     */ public final class PublicSuffixMatcher
/*     */ {
/*     */   private final Map<String, DomainType> rules;
/*     */   private final Map<String, DomainType> exceptions;
/*     */   
/*     */   public PublicSuffixMatcher(Collection<String> rules, Collection<String> exceptions) {
/*  57 */     this(DomainType.UNKNOWN, rules, exceptions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PublicSuffixMatcher(DomainType domainType, Collection<String> rules, Collection<String> exceptions) {
/*  65 */     Args.notNull(domainType, "Domain type");
/*  66 */     Args.notNull(rules, "Domain suffix rules");
/*  67 */     this.rules = new ConcurrentHashMap<String, DomainType>(rules.size());
/*  68 */     for (String rule : rules) {
/*  69 */       this.rules.put(rule, domainType);
/*     */     }
/*  71 */     this.exceptions = new ConcurrentHashMap<String, DomainType>();
/*  72 */     if (exceptions != null) {
/*  73 */       for (String exception : exceptions) {
/*  74 */         this.exceptions.put(exception, domainType);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PublicSuffixMatcher(Collection<PublicSuffixList> lists) {
/*  83 */     Args.notNull(lists, "Domain suffix lists");
/*  84 */     this.rules = new ConcurrentHashMap<String, DomainType>();
/*  85 */     this.exceptions = new ConcurrentHashMap<String, DomainType>();
/*  86 */     for (PublicSuffixList list : lists) {
/*  87 */       DomainType domainType = list.getType();
/*  88 */       List<String> rules = list.getRules();
/*  89 */       for (String rule : rules) {
/*  90 */         this.rules.put(rule, domainType);
/*     */       }
/*  92 */       List<String> exceptions = list.getExceptions();
/*  93 */       if (exceptions != null) {
/*  94 */         for (String exception : exceptions) {
/*  95 */           this.exceptions.put(exception, domainType);
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean hasEntry(Map<String, DomainType> map, String rule, DomainType expectedType) {
/* 102 */     if (map == null) {
/* 103 */       return false;
/*     */     }
/* 105 */     DomainType domainType = map.get(rule);
/* 106 */     return (domainType == null) ? false : ((expectedType == null || domainType.equals(expectedType)));
/*     */   }
/*     */   
/*     */   private boolean hasRule(String rule, DomainType expectedType) {
/* 110 */     return hasEntry(this.rules, rule, expectedType);
/*     */   }
/*     */   
/*     */   private boolean hasException(String exception, DomainType expectedType) {
/* 114 */     return hasEntry(this.exceptions, exception, expectedType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDomainRoot(String domain) {
/* 125 */     return getDomainRoot(domain, null);
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
/*     */   public String getDomainRoot(String domain, DomainType expectedType) {
/* 139 */     if (domain == null) {
/* 140 */       return null;
/*     */     }
/* 142 */     if (domain.startsWith(".")) {
/* 143 */       return null;
/*     */     }
/* 145 */     String domainName = null;
/* 146 */     String segment = domain.toLowerCase(Locale.ROOT);
/* 147 */     while (segment != null) {
/*     */ 
/*     */       
/* 150 */       if (hasException(IDN.toUnicode(segment), expectedType)) {
/* 151 */         return segment;
/*     */       }
/*     */       
/* 154 */       if (hasRule(IDN.toUnicode(segment), expectedType)) {
/*     */         break;
/*     */       }
/*     */       
/* 158 */       int nextdot = segment.indexOf('.');
/* 159 */       String nextSegment = (nextdot != -1) ? segment.substring(nextdot + 1) : null;
/*     */       
/* 161 */       if (nextSegment != null && 
/* 162 */         hasRule("*." + IDN.toUnicode(nextSegment), expectedType)) {
/*     */         break;
/*     */       }
/*     */       
/* 166 */       if (nextdot != -1) {
/* 167 */         domainName = segment;
/*     */       }
/* 169 */       segment = nextSegment;
/*     */     } 
/* 171 */     return domainName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(String domain) {
/* 178 */     return matches(domain, null);
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
/*     */   public boolean matches(String domain, DomainType expectedType) {
/* 191 */     if (domain == null) {
/* 192 */       return false;
/*     */     }
/* 194 */     String domainRoot = getDomainRoot(domain.startsWith(".") ? domain.substring(1) : domain, expectedType);
/*     */     
/* 196 */     return (domainRoot == null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/conn/util/PublicSuffixMatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */