/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public abstract class CFFCharset
/*     */ {
/*     */   private final boolean isCIDFont;
/*  32 */   private final Map<Integer, Integer> sidOrCidToGid = new HashMap<Integer, Integer>(250);
/*  33 */   private final Map<Integer, Integer> gidToSid = new HashMap<Integer, Integer>(250);
/*  34 */   private final Map<String, Integer> nameToSid = new HashMap<String, Integer>(250);
/*     */ 
/*     */   
/*  37 */   private final Map<Integer, Integer> gidToCid = new HashMap<Integer, Integer>();
/*  38 */   private final Map<Integer, String> gidToName = new HashMap<Integer, String>(250);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   CFFCharset(boolean isCIDFont) {
/*  47 */     this.isCIDFont = isCIDFont;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCIDFont() {
/*  57 */     return this.isCIDFont;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSID(int gid, int sid, String name) {
/*  68 */     if (this.isCIDFont)
/*     */     {
/*  70 */       throw new IllegalStateException("Not a Type 1-equivalent font");
/*     */     }
/*  72 */     this.sidOrCidToGid.put(Integer.valueOf(sid), Integer.valueOf(gid));
/*  73 */     this.gidToSid.put(Integer.valueOf(gid), Integer.valueOf(sid));
/*  74 */     this.nameToSid.put(name, Integer.valueOf(sid));
/*  75 */     this.gidToName.put(Integer.valueOf(gid), name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCID(int gid, int cid) {
/*  86 */     if (!this.isCIDFont)
/*     */     {
/*  88 */       throw new IllegalStateException("Not a CIDFont");
/*     */     }
/*  90 */     this.sidOrCidToGid.put(Integer.valueOf(cid), Integer.valueOf(gid));
/*  91 */     this.gidToCid.put(Integer.valueOf(gid), Integer.valueOf(cid));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getSIDForGID(int sid) {
/* 102 */     if (this.isCIDFont)
/*     */     {
/* 104 */       throw new IllegalStateException("Not a Type 1-equivalent font");
/*     */     }
/* 106 */     Integer gid = this.gidToSid.get(Integer.valueOf(sid));
/* 107 */     if (gid == null)
/*     */     {
/* 109 */       return 0;
/*     */     }
/* 111 */     return gid.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getGIDForSID(int sid) {
/* 122 */     if (this.isCIDFont)
/*     */     {
/* 124 */       throw new IllegalStateException("Not a Type 1-equivalent font");
/*     */     }
/* 126 */     Integer gid = this.sidOrCidToGid.get(Integer.valueOf(sid));
/* 127 */     if (gid == null)
/*     */     {
/* 129 */       return 0;
/*     */     }
/* 131 */     return gid.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGIDForCID(int cid) {
/* 142 */     if (!this.isCIDFont)
/*     */     {
/* 144 */       throw new IllegalStateException("Not a CIDFont");
/*     */     }
/* 146 */     Integer gid = this.sidOrCidToGid.get(Integer.valueOf(cid));
/* 147 */     if (gid == null)
/*     */     {
/* 149 */       return 0;
/*     */     }
/* 151 */     return gid.intValue();
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
/*     */   int getSID(String name) {
/* 163 */     if (this.isCIDFont)
/*     */     {
/* 165 */       throw new IllegalStateException("Not a Type 1-equivalent font");
/*     */     }
/* 167 */     Integer sid = this.nameToSid.get(name);
/* 168 */     if (sid == null)
/*     */     {
/* 170 */       return 0;
/*     */     }
/* 172 */     return sid.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNameForGID(int gid) {
/* 183 */     if (this.isCIDFont)
/*     */     {
/* 185 */       throw new IllegalStateException("Not a Type 1-equivalent font");
/*     */     }
/* 187 */     return this.gidToName.get(Integer.valueOf(gid));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCIDForGID(int gid) {
/* 198 */     if (!this.isCIDFont)
/*     */     {
/* 200 */       throw new IllegalStateException("Not a CIDFont");
/*     */     }
/*     */     
/* 203 */     Integer cid = this.gidToCid.get(Integer.valueOf(gid));
/* 204 */     if (cid != null)
/*     */     {
/* 206 */       return cid.intValue();
/*     */     }
/* 208 */     return 0;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CFFCharset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */