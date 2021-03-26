/*      */ package org.apache.pdfbox.pdmodel.encryption;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.math.BigInteger;
/*      */ import java.nio.charset.Charset;
/*      */ import java.security.GeneralSecurityException;
/*      */ import java.security.MessageDigest;
/*      */ import java.security.NoSuchAlgorithmException;
/*      */ import java.security.SecureRandom;
/*      */ import java.util.Arrays;
/*      */ import javax.crypto.Cipher;
/*      */ import javax.crypto.spec.IvParameterSpec;
/*      */ import javax.crypto.spec.SecretKeySpec;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.pdfbox.cos.COSArray;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSName;
/*      */ import org.apache.pdfbox.cos.COSString;
/*      */ import org.apache.pdfbox.pdmodel.PDDocument;
/*      */ import org.apache.pdfbox.util.Charsets;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class StandardSecurityHandler
/*      */   extends SecurityHandler
/*      */ {
/*   54 */   private static final Log LOG = LogFactory.getLog(StandardSecurityHandler.class);
/*      */ 
/*      */   
/*      */   public static final String FILTER = "Standard";
/*      */ 
/*      */   
/*   60 */   public static final Class<?> PROTECTION_POLICY_CLASS = StandardProtectionPolicy.class;
/*      */ 
/*      */   
/*   63 */   private static final byte[] ENCRYPT_PADDING = new byte[] { 40, -65, 78, 94, 78, 117, -118, 65, 100, 0, 78, 86, -1, -6, 1, 8, 46, 46, 0, -74, -48, 104, 62, Byte.MIN_VALUE, 47, 12, -87, -2, 100, 83, 105, 122 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   75 */   private static final String[] HASHES_2B = new String[] { "SHA-256", "SHA-384", "SHA-512" };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int DEFAULT_VERSION = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   private StandardProtectionPolicy policy;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StandardSecurityHandler() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StandardSecurityHandler(StandardProtectionPolicy p) {
/*   95 */     this.policy = p;
/*   96 */     this.keyLength = this.policy.getEncryptionKeyLength();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int computeVersionNumber() {
/*  108 */     if (this.keyLength == 40)
/*      */     {
/*  110 */       return 1;
/*      */     }
/*  112 */     if (this.keyLength == 128 && this.policy.isPreferAES())
/*      */     {
/*  114 */       return 4;
/*      */     }
/*  116 */     if (this.keyLength == 256)
/*      */     {
/*  118 */       return 5;
/*      */     }
/*      */     
/*  121 */     return 2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int computeRevisionNumber(int version) {
/*  135 */     if (version < 2 && !this.policy.getPermissions().hasAnyRevision3PermissionSet())
/*      */     {
/*  137 */       return 2;
/*      */     }
/*  139 */     if (version == 5)
/*      */     {
/*      */       
/*  142 */       return 6;
/*      */     }
/*  144 */     if (version == 4)
/*      */     {
/*  146 */       return 4;
/*      */     }
/*  148 */     if (version == 2 || version == 3 || this.policy.getPermissions().hasAnyRevision3PermissionSet())
/*      */     {
/*  150 */       return 3;
/*      */     }
/*  152 */     return 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void prepareForDecryption(PDEncryption encryption, COSArray documentIDArray, DecryptionMaterial decryptionMaterial) throws InvalidPasswordException, IOException {
/*  172 */     if (!(decryptionMaterial instanceof StandardDecryptionMaterial))
/*      */     {
/*  174 */       throw new IOException("Decryption material is not compatible with the document");
/*      */     }
/*      */ 
/*      */     
/*  178 */     if (encryption.getVersion() >= 4) {
/*  179 */       setStreamFilterName(encryption.getStreamFilterName());
/*  180 */       setStringFilterName(encryption.getStreamFilterName());
/*      */     } 
/*  182 */     setDecryptMetadata(encryption.isEncryptMetaData());
/*  183 */     StandardDecryptionMaterial material = (StandardDecryptionMaterial)decryptionMaterial;
/*      */     
/*  185 */     String password = material.getPassword();
/*  186 */     if (password == null)
/*      */     {
/*  188 */       password = "";
/*      */     }
/*      */     
/*  191 */     int dicPermissions = encryption.getPermissions();
/*  192 */     int dicRevision = encryption.getRevision();
/*  193 */     int dicLength = (encryption.getVersion() == 1) ? 5 : (encryption.getLength() / 8);
/*      */     
/*  195 */     byte[] documentIDBytes = getDocumentIDBytes(documentIDArray);
/*      */ 
/*      */     
/*  198 */     boolean encryptMetadata = encryption.isEncryptMetaData();
/*      */     
/*  200 */     byte[] userKey = encryption.getUserKey();
/*  201 */     byte[] ownerKey = encryption.getOwnerKey();
/*  202 */     byte[] ue = null, oe = null;
/*      */     
/*  204 */     Charset passwordCharset = Charsets.ISO_8859_1;
/*  205 */     if (dicRevision == 6 || dicRevision == 5) {
/*      */       
/*  207 */       passwordCharset = Charsets.UTF_8;
/*  208 */       ue = encryption.getUserEncryptionKey();
/*  209 */       oe = encryption.getOwnerEncryptionKey();
/*      */     } 
/*      */     
/*  212 */     if (dicRevision == 6)
/*      */     {
/*  214 */       password = SaslPrep.saslPrepQuery(password);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  219 */     if (isOwnerPassword(password.getBytes(passwordCharset), userKey, ownerKey, dicPermissions, documentIDBytes, dicRevision, dicLength, encryptMetadata)) {
/*      */       byte[] computedPassword;
/*      */ 
/*      */       
/*  223 */       AccessPermission currentAccessPermission = AccessPermission.getOwnerAccessPermission();
/*  224 */       setCurrentAccessPermission(currentAccessPermission);
/*      */ 
/*      */       
/*  227 */       if (dicRevision == 6 || dicRevision == 5) {
/*      */         
/*  229 */         computedPassword = password.getBytes(passwordCharset);
/*      */       }
/*      */       else {
/*      */         
/*  233 */         computedPassword = getUserPassword(password.getBytes(passwordCharset), ownerKey, dicRevision, dicLength);
/*      */       } 
/*      */ 
/*      */       
/*  237 */       this
/*  238 */         .encryptionKey = computeEncryptedKey(computedPassword, ownerKey, userKey, oe, ue, dicPermissions, documentIDBytes, dicRevision, dicLength, encryptMetadata, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  247 */     else if (isUserPassword(password.getBytes(passwordCharset), userKey, ownerKey, dicPermissions, documentIDBytes, dicRevision, dicLength, encryptMetadata)) {
/*      */ 
/*      */ 
/*      */       
/*  251 */       AccessPermission currentAccessPermission = new AccessPermission(dicPermissions);
/*  252 */       currentAccessPermission.setReadOnly();
/*  253 */       setCurrentAccessPermission(currentAccessPermission);
/*      */       
/*  255 */       this
/*  256 */         .encryptionKey = computeEncryptedKey(password
/*  257 */           .getBytes(passwordCharset), ownerKey, userKey, oe, ue, dicPermissions, documentIDBytes, dicRevision, dicLength, encryptMetadata, false);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  267 */       throw new InvalidPasswordException("Cannot decrypt PDF, the password is incorrect");
/*      */     } 
/*      */     
/*  270 */     if (dicRevision == 6 || dicRevision == 5)
/*      */     {
/*  272 */       validatePerms(encryption, dicPermissions, encryptMetadata);
/*      */     }
/*      */     
/*  275 */     if (encryption.getVersion() == 4 || encryption.getVersion() == 5) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  280 */       PDCryptFilterDictionary stdCryptFilterDictionary = encryption.getStdCryptFilterDictionary();
/*      */       
/*  282 */       if (stdCryptFilterDictionary != null) {
/*      */         
/*  284 */         COSName cryptFilterMethod = stdCryptFilterDictionary.getCryptFilterMethod();
/*  285 */         setAES((COSName.AESV2.equals(cryptFilterMethod) || COSName.AESV3
/*  286 */             .equals(cryptFilterMethod)));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] getDocumentIDBytes(COSArray documentIDArray) {
/*      */     byte[] documentIDBytes;
/*  296 */     if (documentIDArray != null && documentIDArray.size() >= 1) {
/*      */       
/*  298 */       COSString id = (COSString)documentIDArray.getObject(0);
/*  299 */       documentIDBytes = id.getBytes();
/*      */     }
/*      */     else {
/*      */       
/*  303 */       documentIDBytes = new byte[0];
/*      */     } 
/*  305 */     return documentIDBytes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validatePerms(PDEncryption encryption, int dicPermissions, boolean encryptMetadata) throws IOException {
/*      */     try {
/*  316 */       Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
/*  317 */       cipher.init(2, new SecretKeySpec(this.encryptionKey, "AES"));
/*  318 */       byte[] perms = cipher.doFinal(encryption.getPerms());
/*      */ 
/*      */       
/*  321 */       if (perms[9] != 97 || perms[10] != 100 || perms[11] != 98)
/*      */       {
/*  323 */         LOG.warn("Verification of permissions failed (constant)");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  328 */       int permsP = perms[0] & 0xFF | (perms[1] & 0xFF) << 8 | (perms[2] & 0xFF) << 16 | (perms[3] & 0xFF) << 24;
/*      */ 
/*      */       
/*  331 */       if (permsP != dicPermissions)
/*      */       {
/*  333 */         LOG.warn("Verification of permissions failed (" + String.format("%08X", new Object[] { Integer.valueOf(permsP) }) + " != " + 
/*  334 */             String.format("%08X", new Object[] { Integer.valueOf(dicPermissions) }) + ")");
/*      */       }
/*      */       
/*  337 */       if ((encryptMetadata && perms[8] != 84) || (!encryptMetadata && perms[8] != 70))
/*      */       {
/*  339 */         LOG.warn("Verification of permissions failed (EncryptMetadata)");
/*      */       }
/*      */     }
/*  342 */     catch (GeneralSecurityException e) {
/*      */       
/*  344 */       logIfStrongEncryptionMissing();
/*  345 */       throw new IOException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void prepareDocumentForEncryption(PDDocument document) throws IOException {
/*  359 */     PDEncryption encryptionDictionary = document.getEncryption();
/*  360 */     if (encryptionDictionary == null)
/*      */     {
/*  362 */       encryptionDictionary = new PDEncryption();
/*      */     }
/*  364 */     int version = computeVersionNumber();
/*  365 */     int revision = computeRevisionNumber(version);
/*  366 */     encryptionDictionary.setFilter("Standard");
/*  367 */     encryptionDictionary.setVersion(version);
/*  368 */     if (version != 4 && version != 5)
/*      */     {
/*      */       
/*  371 */       encryptionDictionary.removeV45filters();
/*      */     }
/*  373 */     encryptionDictionary.setRevision(revision);
/*  374 */     encryptionDictionary.setLength(this.keyLength);
/*      */     
/*  376 */     String ownerPassword = this.policy.getOwnerPassword();
/*  377 */     String userPassword = this.policy.getUserPassword();
/*  378 */     if (ownerPassword == null)
/*      */     {
/*  380 */       ownerPassword = "";
/*      */     }
/*  382 */     if (userPassword == null)
/*      */     {
/*  384 */       userPassword = "";
/*      */     }
/*      */ 
/*      */     
/*  388 */     if (ownerPassword.isEmpty())
/*      */     {
/*  390 */       ownerPassword = userPassword;
/*      */     }
/*      */     
/*  393 */     int permissionInt = this.policy.getPermissions().getPermissionBytes();
/*      */     
/*  395 */     encryptionDictionary.setPermissions(permissionInt);
/*      */     
/*  397 */     int length = this.keyLength / 8;
/*      */     
/*  399 */     if (revision == 6) {
/*      */ 
/*      */       
/*  402 */       ownerPassword = SaslPrep.saslPrepStored(ownerPassword);
/*  403 */       userPassword = SaslPrep.saslPrepStored(userPassword);
/*  404 */       prepareEncryptionDictRev6(ownerPassword, userPassword, encryptionDictionary, permissionInt);
/*      */     }
/*      */     else {
/*      */       
/*  408 */       prepareEncryptionDictRev2345(ownerPassword, userPassword, encryptionDictionary, permissionInt, document, revision, length);
/*      */     } 
/*      */ 
/*      */     
/*  412 */     document.setEncryptionDictionary(encryptionDictionary);
/*  413 */     document.getDocument().setEncryptionDictionary(encryptionDictionary.getCOSObject());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void prepareEncryptionDictRev6(String ownerPassword, String userPassword, PDEncryption encryptionDictionary, int permissionInt) throws IOException {
/*      */     try {
/*  422 */       SecureRandom rnd = new SecureRandom();
/*  423 */       Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
/*      */ 
/*      */       
/*  426 */       this.encryptionKey = new byte[32];
/*  427 */       rnd.nextBytes(this.encryptionKey);
/*      */ 
/*      */       
/*  430 */       byte[] userPasswordBytes = truncate127(userPassword.getBytes(Charsets.UTF_8));
/*  431 */       byte[] userValidationSalt = new byte[8];
/*  432 */       byte[] userKeySalt = new byte[8];
/*  433 */       rnd.nextBytes(userValidationSalt);
/*  434 */       rnd.nextBytes(userKeySalt);
/*  435 */       byte[] hashU = computeHash2B(concat(userPasswordBytes, userValidationSalt), userPasswordBytes, (byte[])null);
/*      */       
/*  437 */       byte[] u = concat(hashU, userValidationSalt, userKeySalt);
/*      */ 
/*      */       
/*  440 */       byte[] hashUE = computeHash2B(concat(userPasswordBytes, userKeySalt), userPasswordBytes, (byte[])null);
/*      */       
/*  442 */       cipher.init(1, new SecretKeySpec(hashUE, "AES"), new IvParameterSpec(new byte[16]));
/*      */       
/*  444 */       byte[] ue = cipher.doFinal(this.encryptionKey);
/*      */ 
/*      */       
/*  447 */       byte[] ownerPasswordBytes = truncate127(ownerPassword.getBytes(Charsets.UTF_8));
/*  448 */       byte[] ownerValidationSalt = new byte[8];
/*  449 */       byte[] ownerKeySalt = new byte[8];
/*  450 */       rnd.nextBytes(ownerValidationSalt);
/*  451 */       rnd.nextBytes(ownerKeySalt);
/*  452 */       byte[] hashO = computeHash2B(concat(ownerPasswordBytes, ownerValidationSalt, u), ownerPasswordBytes, u);
/*      */       
/*  454 */       byte[] o = concat(hashO, ownerValidationSalt, ownerKeySalt);
/*      */ 
/*      */       
/*  457 */       byte[] hashOE = computeHash2B(concat(ownerPasswordBytes, ownerKeySalt, u), ownerPasswordBytes, u);
/*      */       
/*  459 */       cipher.init(1, new SecretKeySpec(hashOE, "AES"), new IvParameterSpec(new byte[16]));
/*      */       
/*  461 */       byte[] oe = cipher.doFinal(this.encryptionKey);
/*      */ 
/*      */       
/*  464 */       encryptionDictionary.setUserKey(u);
/*  465 */       encryptionDictionary.setUserEncryptionKey(ue);
/*  466 */       encryptionDictionary.setOwnerKey(o);
/*  467 */       encryptionDictionary.setOwnerEncryptionKey(oe);
/*      */       
/*  469 */       prepareEncryptionDictAES(encryptionDictionary, COSName.AESV3);
/*      */ 
/*      */       
/*  472 */       byte[] perms = new byte[16];
/*  473 */       perms[0] = (byte)permissionInt;
/*  474 */       perms[1] = (byte)(permissionInt >>> 8);
/*  475 */       perms[2] = (byte)(permissionInt >>> 16);
/*  476 */       perms[3] = (byte)(permissionInt >>> 24);
/*  477 */       perms[4] = -1;
/*  478 */       perms[5] = -1;
/*  479 */       perms[6] = -1;
/*  480 */       perms[7] = -1;
/*  481 */       perms[8] = 84;
/*  482 */       perms[9] = 97;
/*  483 */       perms[10] = 100;
/*  484 */       perms[11] = 98;
/*  485 */       for (int i = 12; i <= 15; i++)
/*      */       {
/*  487 */         perms[i] = (byte)rnd.nextInt();
/*      */       }
/*      */       
/*  490 */       cipher.init(1, new SecretKeySpec(this.encryptionKey, "AES"), new IvParameterSpec(new byte[16]));
/*      */ 
/*      */       
/*  493 */       byte[] permsEnc = cipher.doFinal(perms);
/*      */       
/*  495 */       encryptionDictionary.setPerms(permsEnc);
/*      */     }
/*  497 */     catch (GeneralSecurityException e) {
/*      */       
/*  499 */       logIfStrongEncryptionMissing();
/*  500 */       throw new IOException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void prepareEncryptionDictRev2345(String ownerPassword, String userPassword, PDEncryption encryptionDictionary, int permissionInt, PDDocument document, int revision, int length) throws IOException {
/*  509 */     COSArray idArray = document.getDocument().getDocumentID();
/*      */ 
/*      */     
/*  512 */     if (idArray == null || idArray.size() < 2) {
/*      */       
/*  514 */       MessageDigest md = MessageDigests.getMD5();
/*  515 */       BigInteger time = BigInteger.valueOf(System.currentTimeMillis());
/*  516 */       md.update(time.toByteArray());
/*  517 */       md.update(ownerPassword.getBytes(Charsets.ISO_8859_1));
/*  518 */       md.update(userPassword.getBytes(Charsets.ISO_8859_1));
/*  519 */       md.update(document.getDocument().toString().getBytes(Charsets.ISO_8859_1));
/*      */       
/*  521 */       byte[] arrayOfByte = md.digest(toString().getBytes(Charsets.ISO_8859_1));
/*  522 */       COSString idString = new COSString(arrayOfByte);
/*      */       
/*  524 */       idArray = new COSArray();
/*  525 */       idArray.add((COSBase)idString);
/*  526 */       idArray.add((COSBase)idString);
/*  527 */       document.getDocument().setDocumentID(idArray);
/*      */     } 
/*      */     
/*  530 */     COSString id = (COSString)idArray.getObject(0);
/*      */     
/*  532 */     byte[] ownerBytes = computeOwnerPassword(ownerPassword
/*  533 */         .getBytes(Charsets.ISO_8859_1), userPassword
/*  534 */         .getBytes(Charsets.ISO_8859_1), revision, length);
/*      */     
/*  536 */     byte[] userBytes = computeUserPassword(userPassword
/*  537 */         .getBytes(Charsets.ISO_8859_1), ownerBytes, permissionInt, id
/*  538 */         .getBytes(), revision, length, true);
/*      */     
/*  540 */     this.encryptionKey = computeEncryptedKey(userPassword.getBytes(Charsets.ISO_8859_1), ownerBytes, (byte[])null, (byte[])null, (byte[])null, permissionInt, id
/*  541 */         .getBytes(), revision, length, true, false);
/*      */     
/*  543 */     encryptionDictionary.setOwnerKey(ownerBytes);
/*  544 */     encryptionDictionary.setUserKey(userBytes);
/*      */     
/*  546 */     if (revision == 4)
/*      */     {
/*  548 */       prepareEncryptionDictAES(encryptionDictionary, COSName.AESV2);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void prepareEncryptionDictAES(PDEncryption encryptionDictionary, COSName aesVName) {
/*  554 */     PDCryptFilterDictionary cryptFilterDictionary = new PDCryptFilterDictionary();
/*  555 */     cryptFilterDictionary.setCryptFilterMethod(aesVName);
/*  556 */     cryptFilterDictionary.setLength(this.keyLength);
/*  557 */     encryptionDictionary.setStdCryptFilterDictionary(cryptFilterDictionary);
/*  558 */     encryptionDictionary.setStreamFilterName(COSName.STD_CF);
/*  559 */     encryptionDictionary.setStringFilterName(COSName.STD_CF);
/*  560 */     setAES(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOwnerPassword(byte[] ownerPassword, byte[] user, byte[] owner, int permissions, byte[] id, int encRevision, int keyLengthInBytes, boolean encryptMetadata) throws IOException {
/*  583 */     if (encRevision == 6 || encRevision == 5) {
/*      */       
/*  585 */       byte[] hash, truncatedOwnerPassword = truncate127(ownerPassword);
/*      */       
/*  587 */       byte[] oHash = new byte[32];
/*  588 */       byte[] oValidationSalt = new byte[8];
/*  589 */       System.arraycopy(owner, 0, oHash, 0, 32);
/*  590 */       System.arraycopy(owner, 32, oValidationSalt, 0, 8);
/*      */ 
/*      */       
/*  593 */       if (encRevision == 5) {
/*      */         
/*  595 */         hash = computeSHA256(truncatedOwnerPassword, oValidationSalt, user);
/*      */       }
/*      */       else {
/*      */         
/*  599 */         hash = computeHash2A(truncatedOwnerPassword, oValidationSalt, user);
/*      */       } 
/*      */       
/*  602 */       return Arrays.equals(hash, oHash);
/*      */     } 
/*      */ 
/*      */     
/*  606 */     byte[] userPassword = getUserPassword(ownerPassword, owner, encRevision, keyLengthInBytes);
/*  607 */     return isUserPassword(userPassword, user, owner, permissions, id, encRevision, keyLengthInBytes, encryptMetadata);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getUserPassword(byte[] ownerPassword, byte[] owner, int encRevision, int length) throws IOException {
/*  627 */     ByteArrayOutputStream result = new ByteArrayOutputStream();
/*  628 */     byte[] rc4Key = computeRC4key(ownerPassword, encRevision, length);
/*      */     
/*  630 */     if (encRevision == 2) {
/*      */       
/*  632 */       encryptDataRC4(rc4Key, owner, result);
/*      */     }
/*  634 */     else if (encRevision == 3 || encRevision == 4) {
/*      */       
/*  636 */       byte[] iterationKey = new byte[rc4Key.length];
/*  637 */       byte[] otemp = new byte[owner.length];
/*  638 */       System.arraycopy(owner, 0, otemp, 0, owner.length);
/*      */       
/*  640 */       for (int i = 19; i >= 0; i--) {
/*      */         
/*  642 */         System.arraycopy(rc4Key, 0, iterationKey, 0, rc4Key.length);
/*  643 */         for (int j = 0; j < iterationKey.length; j++)
/*      */         {
/*  645 */           iterationKey[j] = (byte)(iterationKey[j] ^ (byte)i);
/*      */         }
/*  647 */         result.reset();
/*  648 */         encryptDataRC4(iterationKey, otemp, result);
/*  649 */         otemp = result.toByteArray();
/*      */       } 
/*      */     } 
/*  652 */     return result.toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] computeEncryptedKey(byte[] password, byte[] o, byte[] u, byte[] oe, byte[] ue, int permissions, byte[] id, int encRevision, int keyLengthInBytes, boolean encryptMetadata, boolean isOwnerPassword) throws IOException {
/*  679 */     if (encRevision == 6 || encRevision == 5)
/*      */     {
/*  681 */       return computeEncryptedKeyRev56(password, isOwnerPassword, o, u, oe, ue, encRevision);
/*      */     }
/*      */ 
/*      */     
/*  685 */     return computeEncryptedKeyRev234(password, o, permissions, id, encryptMetadata, keyLengthInBytes, encRevision);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] computeEncryptedKeyRev234(byte[] password, byte[] o, int permissions, byte[] id, boolean encryptMetadata, int length, int encRevision) {
/*  695 */     byte[] padded = truncateOrPad(password);
/*      */     
/*  697 */     MessageDigest md = MessageDigests.getMD5();
/*  698 */     md.update(padded);
/*      */     
/*  700 */     md.update(o);
/*      */     
/*  702 */     md.update((byte)permissions);
/*  703 */     md.update((byte)(permissions >>> 8));
/*  704 */     md.update((byte)(permissions >>> 16));
/*  705 */     md.update((byte)(permissions >>> 24));
/*      */     
/*  707 */     md.update(id);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  712 */     if (encRevision == 4 && !encryptMetadata)
/*      */     {
/*  714 */       md.update(new byte[] { -1, -1, -1, -1 });
/*      */     }
/*  716 */     byte[] digest = md.digest();
/*      */     
/*  718 */     if (encRevision == 3 || encRevision == 4)
/*      */     {
/*  720 */       for (int i = 0; i < 50; i++) {
/*      */         
/*  722 */         md.update(digest, 0, length);
/*  723 */         digest = md.digest();
/*      */       } 
/*      */     }
/*      */     
/*  727 */     byte[] result = new byte[length];
/*  728 */     System.arraycopy(digest, 0, result, 0, length);
/*  729 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] computeEncryptedKeyRev56(byte[] password, boolean isOwnerPassword, byte[] o, byte[] u, byte[] oe, byte[] ue, int encRevision) throws IOException {
/*      */     byte[] hash;
/*      */     byte[] fileKeyEnc;
/*  738 */     if (isOwnerPassword) {
/*      */       
/*  740 */       byte[] oKeySalt = new byte[8];
/*  741 */       System.arraycopy(o, 40, oKeySalt, 0, 8);
/*      */       
/*  743 */       if (encRevision == 5) {
/*      */         
/*  745 */         hash = computeSHA256(password, oKeySalt, u);
/*      */       }
/*      */       else {
/*      */         
/*  749 */         hash = computeHash2A(password, oKeySalt, u);
/*      */       } 
/*      */       
/*  752 */       fileKeyEnc = oe;
/*      */     }
/*      */     else {
/*      */       
/*  756 */       byte[] uKeySalt = new byte[8];
/*  757 */       System.arraycopy(u, 40, uKeySalt, 0, 8);
/*      */       
/*  759 */       if (encRevision == 5) {
/*      */         
/*  761 */         hash = computeSHA256(password, uKeySalt, (byte[])null);
/*      */       }
/*      */       else {
/*      */         
/*  765 */         hash = computeHash2A(password, uKeySalt, (byte[])null);
/*      */       } 
/*      */       
/*  768 */       fileKeyEnc = ue;
/*      */     } 
/*      */     
/*      */     try {
/*  772 */       Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
/*  773 */       cipher.init(2, new SecretKeySpec(hash, "AES"), new IvParameterSpec(new byte[16]));
/*  774 */       return cipher.doFinal(fileKeyEnc);
/*      */     }
/*  776 */     catch (GeneralSecurityException e) {
/*      */       
/*  778 */       logIfStrongEncryptionMissing();
/*  779 */       throw new IOException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] computeUserPassword(byte[] password, byte[] owner, int permissions, byte[] id, int encRevision, int keyLengthInBytes, boolean encryptMetadata) throws IOException {
/*  802 */     ByteArrayOutputStream result = new ByteArrayOutputStream();
/*  803 */     byte[] encKey = computeEncryptedKey(password, owner, (byte[])null, (byte[])null, (byte[])null, permissions, id, encRevision, keyLengthInBytes, encryptMetadata, true);
/*      */ 
/*      */     
/*  806 */     if (encRevision == 2) {
/*      */       
/*  808 */       encryptDataRC4(encKey, ENCRYPT_PADDING, result);
/*      */     }
/*  810 */     else if (encRevision == 3 || encRevision == 4) {
/*      */       
/*  812 */       MessageDigest md = MessageDigests.getMD5();
/*  813 */       md.update(ENCRYPT_PADDING);
/*      */       
/*  815 */       md.update(id);
/*  816 */       result.write(md.digest());
/*      */       
/*  818 */       byte[] iterationKey = new byte[encKey.length];
/*  819 */       for (int i = 0; i < 20; i++) {
/*      */         
/*  821 */         System.arraycopy(encKey, 0, iterationKey, 0, iterationKey.length);
/*  822 */         for (int j = 0; j < iterationKey.length; j++)
/*      */         {
/*  824 */           iterationKey[j] = (byte)(iterationKey[j] ^ i);
/*      */         }
/*  826 */         ByteArrayInputStream input = new ByteArrayInputStream(result.toByteArray());
/*  827 */         result.reset();
/*  828 */         encryptDataRC4(iterationKey, input, result);
/*      */       } 
/*      */       
/*  831 */       byte[] finalResult = new byte[32];
/*  832 */       System.arraycopy(result.toByteArray(), 0, finalResult, 0, 16);
/*  833 */       System.arraycopy(ENCRYPT_PADDING, 0, finalResult, 16, 16);
/*  834 */       result.reset();
/*  835 */       result.write(finalResult);
/*      */     } 
/*  837 */     return result.toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] computeOwnerPassword(byte[] ownerPassword, byte[] userPassword, int encRevision, int length) throws IOException {
/*  855 */     if (encRevision == 2 && length != 5)
/*      */     {
/*  857 */       throw new IOException("Expected length=5 actual=" + length);
/*      */     }
/*      */     
/*  860 */     byte[] rc4Key = computeRC4key(ownerPassword, encRevision, length);
/*  861 */     byte[] paddedUser = truncateOrPad(userPassword);
/*      */     
/*  863 */     ByteArrayOutputStream encrypted = new ByteArrayOutputStream();
/*  864 */     encryptDataRC4(rc4Key, new ByteArrayInputStream(paddedUser), encrypted);
/*      */     
/*  866 */     if (encRevision == 3 || encRevision == 4) {
/*      */       
/*  868 */       byte[] iterationKey = new byte[rc4Key.length];
/*  869 */       for (int i = 1; i < 20; i++) {
/*      */         
/*  871 */         System.arraycopy(rc4Key, 0, iterationKey, 0, rc4Key.length);
/*  872 */         for (int j = 0; j < iterationKey.length; j++)
/*      */         {
/*  874 */           iterationKey[j] = (byte)(iterationKey[j] ^ (byte)i);
/*      */         }
/*  876 */         ByteArrayInputStream input = new ByteArrayInputStream(encrypted.toByteArray());
/*  877 */         encrypted.reset();
/*  878 */         encryptDataRC4(iterationKey, input, encrypted);
/*      */       } 
/*      */     } 
/*      */     
/*  882 */     return encrypted.toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] computeRC4key(byte[] ownerPassword, int encRevision, int length) {
/*  888 */     MessageDigest md = MessageDigests.getMD5();
/*  889 */     byte[] digest = md.digest(truncateOrPad(ownerPassword));
/*  890 */     if (encRevision == 3 || encRevision == 4)
/*      */     {
/*  892 */       for (int i = 0; i < 50; i++) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  897 */         md.update(digest, 0, length);
/*  898 */         digest = md.digest();
/*      */       } 
/*      */     }
/*  901 */     byte[] rc4Key = new byte[length];
/*  902 */     System.arraycopy(digest, 0, rc4Key, 0, length);
/*  903 */     return rc4Key;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] truncateOrPad(byte[] password) {
/*  916 */     byte[] padded = new byte[ENCRYPT_PADDING.length];
/*  917 */     int bytesBeforePad = Math.min(password.length, padded.length);
/*  918 */     System.arraycopy(password, 0, padded, 0, bytesBeforePad);
/*  919 */     System.arraycopy(ENCRYPT_PADDING, 0, padded, bytesBeforePad, ENCRYPT_PADDING.length - bytesBeforePad);
/*      */     
/*  921 */     return padded;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isUserPassword(byte[] password, byte[] user, byte[] owner, int permissions, byte[] id, int encRevision, int keyLengthInBytes, boolean encryptMetadata) throws IOException {
/*  944 */     switch (encRevision) {
/*      */       
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*  949 */         return isUserPassword234(password, user, owner, permissions, id, encRevision, keyLengthInBytes, encryptMetadata);
/*      */       
/*      */       case 5:
/*      */       case 6:
/*  953 */         return isUserPassword56(password, user, encRevision);
/*      */     } 
/*  955 */     throw new IOException("Unknown Encryption Revision " + encRevision);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isUserPassword234(byte[] password, byte[] user, byte[] owner, int permissions, byte[] id, int encRevision, int length, boolean encryptMetadata) throws IOException {
/*  963 */     byte[] passwordBytes = computeUserPassword(password, owner, permissions, id, encRevision, length, encryptMetadata);
/*      */     
/*  965 */     if (encRevision == 2)
/*      */     {
/*  967 */       return Arrays.equals(user, passwordBytes);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  972 */     return Arrays.equals(Arrays.copyOf(user, 16), Arrays.copyOf(passwordBytes, 16));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isUserPassword56(byte[] password, byte[] user, int encRevision) throws IOException {
/*  978 */     byte[] hash, truncatedPassword = truncate127(password);
/*  979 */     byte[] uHash = new byte[32];
/*  980 */     byte[] uValidationSalt = new byte[8];
/*  981 */     System.arraycopy(user, 0, uHash, 0, 32);
/*  982 */     System.arraycopy(user, 32, uValidationSalt, 0, 8);
/*      */ 
/*      */     
/*  985 */     if (encRevision == 5) {
/*      */       
/*  987 */       hash = computeSHA256(truncatedPassword, uValidationSalt, (byte[])null);
/*      */     }
/*      */     else {
/*      */       
/*  991 */       hash = computeHash2A(truncatedPassword, uValidationSalt, (byte[])null);
/*      */     } 
/*      */     
/*  994 */     return Arrays.equals(hash, uHash);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isUserPassword(String password, byte[] user, byte[] owner, int permissions, byte[] id, int encRevision, int keyLengthInBytes, boolean encryptMetadata) throws IOException {
/* 1017 */     if (encRevision == 6 || encRevision == 5)
/*      */     {
/* 1019 */       return isUserPassword(password.getBytes(Charsets.UTF_8), user, owner, permissions, id, encRevision, keyLengthInBytes, encryptMetadata);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1024 */     return isUserPassword(password.getBytes(Charsets.ISO_8859_1), user, owner, permissions, id, encRevision, keyLengthInBytes, encryptMetadata);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOwnerPassword(String password, byte[] user, byte[] owner, int permissions, byte[] id, int encRevision, int keyLengthInBytes, boolean encryptMetadata) throws IOException {
/* 1049 */     return isOwnerPassword(password.getBytes(Charsets.ISO_8859_1), user, owner, permissions, id, encRevision, keyLengthInBytes, encryptMetadata);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] computeHash2A(byte[] password, byte[] salt, byte[] u) throws IOException {
/*      */     byte[] userKey;
/* 1057 */     if (u == null) {
/*      */       
/* 1059 */       userKey = new byte[0];
/*      */     } else {
/* 1061 */       if (u.length < 48)
/*      */       {
/* 1063 */         throw new IOException("Bad U length");
/*      */       }
/* 1065 */       if (u.length > 48) {
/*      */ 
/*      */         
/* 1068 */         userKey = new byte[48];
/* 1069 */         System.arraycopy(u, 0, userKey, 0, 48);
/*      */       }
/*      */       else {
/*      */         
/* 1073 */         userKey = u;
/*      */       } 
/*      */     } 
/* 1076 */     byte[] truncatedPassword = truncate127(password);
/* 1077 */     byte[] input = concat(truncatedPassword, salt, userKey);
/* 1078 */     return computeHash2B(input, truncatedPassword, userKey);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static byte[] computeHash2B(byte[] input, byte[] password, byte[] userKey) throws IOException {
/*      */     try {
/* 1087 */       MessageDigest md = MessageDigest.getInstance("SHA-256");
/* 1088 */       byte[] k = md.digest(input);
/*      */       
/* 1090 */       byte[] e = null;
/* 1091 */       for (int round = 0; round < 64 || (e[e.length - 1] & 0xFF) > round - 32; round++) {
/*      */         byte[] k1;
/*      */         
/* 1094 */         if (userKey != null && userKey.length >= 48) {
/*      */           
/* 1096 */           k1 = new byte[64 * (password.length + k.length + 48)];
/*      */         }
/*      */         else {
/*      */           
/* 1100 */           k1 = new byte[64 * (password.length + k.length)];
/*      */         } 
/*      */         
/* 1103 */         int pos = 0;
/* 1104 */         for (int i = 0; i < 64; i++) {
/*      */           
/* 1106 */           System.arraycopy(password, 0, k1, pos, password.length);
/* 1107 */           pos += password.length;
/* 1108 */           System.arraycopy(k, 0, k1, pos, k.length);
/* 1109 */           pos += k.length;
/* 1110 */           if (userKey != null && userKey.length >= 48) {
/*      */             
/* 1112 */             System.arraycopy(userKey, 0, k1, pos, 48);
/* 1113 */             pos += 48;
/*      */           } 
/*      */         } 
/*      */         
/* 1117 */         byte[] kFirst = new byte[16];
/* 1118 */         byte[] kSecond = new byte[16];
/* 1119 */         System.arraycopy(k, 0, kFirst, 0, 16);
/* 1120 */         System.arraycopy(k, 16, kSecond, 0, 16);
/*      */         
/* 1122 */         Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
/* 1123 */         SecretKeySpec keySpec = new SecretKeySpec(kFirst, "AES");
/* 1124 */         IvParameterSpec ivSpec = new IvParameterSpec(kSecond);
/* 1125 */         cipher.init(1, keySpec, ivSpec);
/* 1126 */         e = cipher.doFinal(k1);
/*      */         
/* 1128 */         byte[] eFirst = new byte[16];
/* 1129 */         System.arraycopy(e, 0, eFirst, 0, 16);
/* 1130 */         BigInteger bi = new BigInteger(1, eFirst);
/* 1131 */         BigInteger remainder = bi.mod(new BigInteger("3"));
/* 1132 */         String nextHash = HASHES_2B[remainder.intValue()];
/*      */         
/* 1134 */         md = MessageDigest.getInstance(nextHash);
/* 1135 */         k = md.digest(e);
/*      */       } 
/*      */       
/* 1138 */       if (k.length > 32) {
/*      */         
/* 1140 */         byte[] kTrunc = new byte[32];
/* 1141 */         System.arraycopy(k, 0, kTrunc, 0, 32);
/* 1142 */         return kTrunc;
/*      */       } 
/*      */ 
/*      */       
/* 1146 */       return k;
/*      */     
/*      */     }
/* 1149 */     catch (GeneralSecurityException e) {
/*      */       
/* 1151 */       logIfStrongEncryptionMissing();
/* 1152 */       throw new IOException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static byte[] computeSHA256(byte[] input, byte[] password, byte[] userKey) throws IOException {
/*      */     try {
/* 1161 */       MessageDigest md = MessageDigest.getInstance("SHA-256");
/* 1162 */       md.update(input);
/* 1163 */       md.update(password);
/* 1164 */       return (userKey == null) ? md.digest() : md.digest(userKey);
/*      */     }
/* 1166 */     catch (NoSuchAlgorithmException e) {
/*      */       
/* 1168 */       throw new IOException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static byte[] concat(byte[] a, byte[] b) {
/* 1174 */     byte[] o = new byte[a.length + b.length];
/* 1175 */     System.arraycopy(a, 0, o, 0, a.length);
/* 1176 */     System.arraycopy(b, 0, o, a.length, b.length);
/* 1177 */     return o;
/*      */   }
/*      */ 
/*      */   
/*      */   private static byte[] concat(byte[] a, byte[] b, byte[] c) {
/* 1182 */     byte[] o = new byte[a.length + b.length + c.length];
/* 1183 */     System.arraycopy(a, 0, o, 0, a.length);
/* 1184 */     System.arraycopy(b, 0, o, a.length, b.length);
/* 1185 */     System.arraycopy(c, 0, o, a.length + b.length, c.length);
/* 1186 */     return o;
/*      */   }
/*      */ 
/*      */   
/*      */   private static byte[] truncate127(byte[] in) {
/* 1191 */     if (in.length <= 127)
/*      */     {
/* 1193 */       return in;
/*      */     }
/* 1195 */     byte[] trunc = new byte[127];
/* 1196 */     System.arraycopy(in, 0, trunc, 0, 127);
/* 1197 */     return trunc;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void logIfStrongEncryptionMissing() {
/*      */     try {
/* 1204 */       if (Cipher.getMaxAllowedKeyLength("AES") != Integer.MAX_VALUE)
/*      */       {
/* 1206 */         LOG.warn("JCE unlimited strength jurisdiction policy files are not installed");
/*      */       }
/*      */     }
/* 1209 */     catch (NoSuchAlgorithmException noSuchAlgorithmException) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasProtectionPolicy() {
/* 1220 */     return (this.policy != null);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/StandardSecurityHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */