/*     */ package org.apache.pdfbox.pdmodel.encryption;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.InvalidAlgorithmParameterException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.CipherInputStream;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.util.Charsets;
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
/*     */ public abstract class SecurityHandler
/*     */ {
/*  68 */   private static final Log LOG = LogFactory.getLog(SecurityHandler.class);
/*     */ 
/*     */   
/*     */   private static final int DEFAULT_KEY_LENGTH = 40;
/*     */   
/*  73 */   private static final byte[] AES_SALT = new byte[] { 115, 65, 108, 84 };
/*     */ 
/*     */   
/*  76 */   protected int keyLength = 40;
/*     */ 
/*     */   
/*     */   protected byte[] encryptionKey;
/*     */ 
/*     */   
/*  82 */   private final RC4Cipher rc4 = new RC4Cipher();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean decryptMetadata;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   private final Set<COSBase> objects = Collections.newSetFromMap(new IdentityHashMap<COSBase, Boolean>());
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean useAES;
/*     */ 
/*     */ 
/*     */   
/* 101 */   private AccessPermission currentAccessPermission = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSName streamFilterName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSName stringFilterName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setDecryptMetadata(boolean decryptMetadata) {
/* 120 */     this.decryptMetadata = decryptMetadata;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setStringFilterName(COSName stringFilterName) {
/* 130 */     this.stringFilterName = stringFilterName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setStreamFilterName(COSName streamFilterName) {
/* 140 */     this.streamFilterName = streamFilterName;
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
/*     */   public abstract void prepareDocumentForEncryption(PDDocument paramPDDocument) throws IOException;
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
/*     */   public abstract void prepareForDecryption(PDEncryption paramPDEncryption, COSArray paramCOSArray, DecryptionMaterial paramDecryptionMaterial) throws InvalidPasswordException, IOException;
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
/*     */   private void encryptData(long objectNumber, long genNumber, InputStream data, OutputStream output, boolean decrypt) throws IOException {
/* 180 */     if (this.useAES && this.encryptionKey.length == 32) {
/*     */       
/* 182 */       encryptDataAES256(data, output, decrypt);
/*     */     }
/*     */     else {
/*     */       
/* 186 */       byte[] finalKey = calcFinalKey(objectNumber, genNumber);
/*     */       
/* 188 */       if (this.useAES) {
/*     */         
/* 190 */         encryptDataAESother(finalKey, data, output, decrypt);
/*     */       }
/*     */       else {
/*     */         
/* 194 */         encryptDataRC4(finalKey, data, output);
/*     */       } 
/*     */     } 
/* 197 */     output.flush();
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
/*     */   private byte[] calcFinalKey(long objectNumber, long genNumber) {
/* 209 */     byte[] newKey = new byte[this.encryptionKey.length + 5];
/* 210 */     System.arraycopy(this.encryptionKey, 0, newKey, 0, this.encryptionKey.length);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     newKey[newKey.length - 5] = (byte)(int)(objectNumber & 0xFFL);
/* 216 */     newKey[newKey.length - 4] = (byte)(int)(objectNumber >> 8L & 0xFFL);
/* 217 */     newKey[newKey.length - 3] = (byte)(int)(objectNumber >> 16L & 0xFFL);
/* 218 */     newKey[newKey.length - 2] = (byte)(int)(genNumber & 0xFFL);
/* 219 */     newKey[newKey.length - 1] = (byte)(int)(genNumber >> 8L & 0xFFL);
/*     */     
/* 221 */     MessageDigest md = MessageDigests.getMD5();
/* 222 */     md.update(newKey);
/* 223 */     if (this.useAES)
/*     */     {
/* 225 */       md.update(AES_SALT);
/*     */     }
/* 227 */     byte[] digestedKey = md.digest();
/*     */     
/* 229 */     int length = Math.min(newKey.length, 16);
/* 230 */     byte[] finalKey = new byte[length];
/* 231 */     System.arraycopy(digestedKey, 0, finalKey, 0, length);
/* 232 */     return finalKey;
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
/*     */   protected void encryptDataRC4(byte[] finalKey, InputStream input, OutputStream output) throws IOException {
/* 247 */     this.rc4.setKey(finalKey);
/* 248 */     this.rc4.write(input, output);
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
/*     */   protected void encryptDataRC4(byte[] finalKey, byte[] input, OutputStream output) throws IOException {
/* 262 */     this.rc4.setKey(finalKey);
/* 263 */     this.rc4.write(input, output);
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
/*     */   private void encryptDataAESother(byte[] finalKey, InputStream data, OutputStream output, boolean decrypt) throws IOException {
/* 280 */     byte[] iv = new byte[16];
/*     */     
/* 282 */     if (!prepareAESInitializationVector(decrypt, iv, data, output)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*     */       Cipher decryptCipher;
/*     */ 
/*     */       
/*     */       try {
/* 292 */         decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/*     */       }
/* 294 */       catch (NoSuchAlgorithmException e) {
/*     */ 
/*     */         
/* 297 */         throw new RuntimeException(e);
/*     */       } 
/*     */       
/* 300 */       SecretKey aesKey = new SecretKeySpec(finalKey, "AES");
/* 301 */       IvParameterSpec ips = new IvParameterSpec(iv);
/* 302 */       decryptCipher.init(decrypt ? 2 : 1, aesKey, ips);
/* 303 */       byte[] buffer = new byte[256];
/*     */       int n;
/* 305 */       while ((n = data.read(buffer)) != -1) {
/*     */         
/* 307 */         byte[] dst = decryptCipher.update(buffer, 0, n);
/* 308 */         if (dst != null)
/*     */         {
/* 310 */           output.write(dst);
/*     */         }
/*     */       } 
/* 313 */       output.write(decryptCipher.doFinal());
/*     */     }
/* 315 */     catch (InvalidKeyException e) {
/*     */       Cipher decryptCipher;
/* 317 */       throw new IOException(decryptCipher);
/*     */     }
/* 319 */     catch (InvalidAlgorithmParameterException e) {
/*     */       
/* 321 */       throw new IOException(e);
/*     */     }
/* 323 */     catch (NoSuchPaddingException e) {
/*     */       
/* 325 */       throw new IOException(e);
/*     */     }
/* 327 */     catch (IllegalBlockSizeException e) {
/*     */       
/* 329 */       throw new IOException(e);
/*     */     }
/* 331 */     catch (BadPaddingException e) {
/*     */       
/* 333 */       throw new IOException(e);
/*     */     } 
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
/*     */   private void encryptDataAES256(InputStream data, OutputStream output, boolean decrypt) throws IOException {
/*     */     Cipher cipher;
/* 348 */     byte[] iv = new byte[16];
/*     */     
/* 350 */     if (!prepareAESInitializationVector(decrypt, iv, data, output)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 358 */       cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 359 */       SecretKeySpec keySpec = new SecretKeySpec(this.encryptionKey, "AES");
/* 360 */       IvParameterSpec ivSpec = new IvParameterSpec(iv);
/* 361 */       cipher.init(decrypt ? 2 : 1, keySpec, ivSpec);
/*     */     }
/* 363 */     catch (GeneralSecurityException e) {
/*     */       
/* 365 */       throw new IOException(e);
/*     */     } 
/*     */     
/* 368 */     CipherInputStream cis = new CipherInputStream(data, cipher);
/*     */     
/*     */     try {
/* 371 */       IOUtils.copy(cis, output);
/*     */     }
/* 373 */     catch (IOException exception) {
/*     */ 
/*     */ 
/*     */       
/* 377 */       if (!(exception.getCause() instanceof GeneralSecurityException))
/*     */       {
/* 379 */         throw exception;
/*     */       }
/* 381 */       LOG.debug("A GeneralSecurityException occured when decrypting some stream data", exception);
/*     */     }
/*     */     finally {
/*     */       
/* 385 */       cis.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean prepareAESInitializationVector(boolean decrypt, byte[] iv, InputStream data, OutputStream output) throws IOException {
/* 391 */     if (decrypt) {
/*     */ 
/*     */       
/* 394 */       int ivSize = data.read(iv);
/* 395 */       if (ivSize == -1)
/*     */       {
/* 397 */         return false;
/*     */       }
/* 399 */       if (ivSize != iv.length)
/*     */       {
/* 401 */         throw new IOException("AES initialization vector not fully read: only " + ivSize + " bytes read instead of " + iv.length);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 409 */       SecureRandom rnd = new SecureRandom();
/* 410 */       rnd.nextBytes(iv);
/* 411 */       output.write(iv);
/*     */     } 
/* 413 */     return true;
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
/*     */   public void decrypt(COSBase obj, long objNum, long genNum) throws IOException {
/* 427 */     if (!(obj instanceof COSString) && !(obj instanceof COSDictionary) && !(obj instanceof COSArray)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 432 */     if (obj instanceof COSString) {
/*     */       
/* 434 */       if (this.objects.contains(obj)) {
/*     */         return;
/*     */       }
/*     */       
/* 438 */       this.objects.add(obj);
/* 439 */       decryptString((COSString)obj, objNum, genNum);
/*     */     }
/* 441 */     else if (obj instanceof COSStream) {
/*     */       
/* 443 */       if (this.objects.contains(obj)) {
/*     */         return;
/*     */       }
/*     */       
/* 447 */       this.objects.add(obj);
/* 448 */       decryptStream((COSStream)obj, objNum, genNum);
/*     */     }
/* 450 */     else if (obj instanceof COSDictionary) {
/*     */       
/* 452 */       decryptDictionary((COSDictionary)obj, objNum, genNum);
/*     */     }
/* 454 */     else if (obj instanceof COSArray) {
/*     */       
/* 456 */       decryptArray((COSArray)obj, objNum, genNum);
/*     */     } 
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
/*     */   public void decryptStream(COSStream stream, long objNum, long genNum) throws IOException {
/* 472 */     if (COSName.IDENTITY.equals(this.streamFilterName)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 477 */     COSName cOSName = stream.getCOSName(COSName.TYPE);
/* 478 */     if (!this.decryptMetadata && COSName.METADATA.equals(cOSName)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 483 */     if (COSName.XREF.equals(cOSName)) {
/*     */       return;
/*     */     }
/*     */     
/* 487 */     if (COSName.METADATA.equals(cOSName)) {
/*     */ 
/*     */       
/* 490 */       InputStream is = stream.createRawInputStream();
/* 491 */       byte[] buf = new byte[10];
/* 492 */       is.read(buf);
/* 493 */       is.close();
/* 494 */       if (Arrays.equals(buf, "<?xpacket ".getBytes(Charsets.ISO_8859_1))) {
/*     */         
/* 496 */         LOG.warn("Metadata is not encrypted, but was expected to be");
/* 497 */         LOG.warn("Read PDF specification about EncryptMetadata (default value: true)");
/*     */         return;
/*     */       } 
/*     */     } 
/* 501 */     decryptDictionary((COSDictionary)stream, objNum, genNum);
/* 502 */     byte[] encrypted = IOUtils.toByteArray(stream.createRawInputStream());
/* 503 */     ByteArrayInputStream encryptedStream = new ByteArrayInputStream(encrypted);
/* 504 */     OutputStream output = stream.createRawOutputStream();
/*     */     
/*     */     try {
/* 507 */       encryptData(objNum, genNum, encryptedStream, output, true);
/*     */     }
/*     */     finally {
/*     */       
/* 511 */       output.close();
/*     */     } 
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
/*     */   public void encryptStream(COSStream stream, long objNum, int genNum) throws IOException {
/* 528 */     byte[] rawData = IOUtils.toByteArray(stream.createRawInputStream());
/* 529 */     ByteArrayInputStream encryptedStream = new ByteArrayInputStream(rawData);
/* 530 */     OutputStream output = stream.createRawOutputStream();
/*     */     
/*     */     try {
/* 533 */       encryptData(objNum, genNum, encryptedStream, output, false);
/*     */     }
/*     */     finally {
/*     */       
/* 537 */       output.close();
/*     */     } 
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
/*     */   private void decryptDictionary(COSDictionary dictionary, long objNum, long genNum) throws IOException {
/* 552 */     if (dictionary.getItem(COSName.CF) != null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 557 */     COSBase type = dictionary.getDictionaryObject(COSName.TYPE);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 562 */     boolean isSignature = (COSName.SIG.equals(type) || COSName.DOC_TIME_STAMP.equals(type) || (dictionary.getDictionaryObject(COSName.CONTENTS) instanceof COSString && dictionary.getDictionaryObject(COSName.BYTERANGE) instanceof COSArray));
/* 563 */     for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)dictionary.entrySet()) {
/*     */       
/* 565 */       if (isSignature && COSName.CONTENTS.equals(entry.getKey())) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 570 */       COSBase value = entry.getValue();
/*     */       
/* 572 */       if (value instanceof COSString || value instanceof COSArray || value instanceof COSDictionary)
/*     */       {
/* 574 */         decrypt(value, objNum, genNum);
/*     */       }
/*     */     } 
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
/*     */   private void decryptString(COSString string, long objNum, long genNum) throws IOException {
/* 591 */     if (COSName.IDENTITY.equals(this.stringFilterName)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 596 */     ByteArrayInputStream data = new ByteArrayInputStream(string.getBytes());
/* 597 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/*     */     
/*     */     try {
/* 600 */       encryptData(objNum, genNum, data, outputStream, true);
/* 601 */       string.setValue(outputStream.toByteArray());
/*     */     }
/* 603 */     catch (IOException ex) {
/*     */       
/* 605 */       LOG.error("Failed to decrypt COSString of length " + (string.getBytes()).length + " in object " + objNum + ": " + ex
/* 606 */           .getMessage());
/*     */     } 
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
/*     */   public void encryptString(COSString string, long objNum, int genNum) throws IOException {
/* 621 */     ByteArrayInputStream data = new ByteArrayInputStream(string.getBytes());
/* 622 */     ByteArrayOutputStream buffer = new ByteArrayOutputStream();
/* 623 */     encryptData(objNum, genNum, data, buffer, false);
/* 624 */     string.setValue(buffer.toByteArray());
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
/*     */   private void decryptArray(COSArray array, long objNum, long genNum) throws IOException {
/* 638 */     for (int i = 0; i < array.size(); i++)
/*     */     {
/* 640 */       decrypt(array.get(i), objNum, genNum);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getKeyLength() {
/* 650 */     return this.keyLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeyLength(int keyLen) {
/* 660 */     this.keyLength = keyLen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentAccessPermission(AccessPermission currentAccessPermission) {
/* 670 */     this.currentAccessPermission = currentAccessPermission;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AccessPermission getCurrentAccessPermission() {
/* 681 */     return this.currentAccessPermission;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAES() {
/* 691 */     return this.useAES;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAES(boolean aesValue) {
/* 702 */     this.useAES = aesValue;
/*     */   }
/*     */   
/*     */   public abstract boolean hasProtectionPolicy();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/SecurityHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */