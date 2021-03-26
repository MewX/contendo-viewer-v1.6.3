/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Library;
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.Structure.FieldOrder;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.win32.W32APIOptions;
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
/*     */ public interface IPHlpAPI
/*     */   extends Library
/*     */ {
/*  43 */   public static final IPHlpAPI INSTANCE = (IPHlpAPI)Native.load("IPHlpAPI", IPHlpAPI.class, W32APIOptions.DEFAULT_OPTIONS);
/*     */   
/*     */   public static final int IF_MAX_STRING_SIZE = 256;
/*     */   
/*     */   public static final int IF_MAX_PHYS_ADDRESS_LENGTH = 32;
/*     */   public static final int MAX_INTERFACE_NAME_LEN = 256;
/*     */   public static final int MAXLEN_IFDESCR = 256;
/*     */   public static final int MAXLEN_PHYSADDR = 8;
/*     */   public static final int MAX_HOSTNAME_LEN = 128;
/*     */   public static final int MAX_DOMAIN_NAME_LEN = 128;
/*     */   public static final int MAX_SCOPE_ID_LEN = 256;
/*     */   
/*     */   int GetIfEntry(MIB_IFROW paramMIB_IFROW);
/*     */   
/*     */   int GetIfEntry2(MIB_IF_ROW2 paramMIB_IF_ROW2);
/*     */   
/*     */   int GetNetworkParams(Pointer paramPointer, IntByReference paramIntByReference);
/*     */   
/*     */   @FieldOrder({"wszName", "dwIndex", "dwType", "dwMtu", "dwSpeed", "dwPhysAddrLen", "bPhysAddr", "dwAdminStatus", "dwOperStatus", "dwLastChange", "dwInOctets", "dwInUcastPkts", "dwInNUcastPkts", "dwInDiscards", "dwInErrors", "dwInUnknownProtos", "dwOutOctets", "dwOutUcastPkts", "dwOutNUcastPkts", "dwOutDiscards", "dwOutErrors", "dwOutQLen", "dwDescrLen", "bDescr"})
/*     */   public static class MIB_IFROW
/*     */     extends Structure
/*     */   {
/*  65 */     public char[] wszName = new char[256];
/*     */     public int dwIndex;
/*     */     public int dwType;
/*     */     public int dwMtu;
/*     */     public int dwSpeed;
/*     */     public int dwPhysAddrLen;
/*  71 */     public byte[] bPhysAddr = new byte[8];
/*     */     public int dwAdminStatus;
/*     */     public int dwOperStatus;
/*     */     public int dwLastChange;
/*     */     public int dwInOctets;
/*     */     public int dwInUcastPkts;
/*     */     public int dwInNUcastPkts;
/*     */     public int dwInDiscards;
/*     */     public int dwInErrors;
/*     */     public int dwInUnknownProtos;
/*     */     public int dwOutOctets;
/*     */     public int dwOutUcastPkts;
/*     */     public int dwOutNUcastPkts;
/*     */     public int dwOutDiscards;
/*     */     public int dwOutErrors;
/*     */     public int dwOutQLen;
/*     */     public int dwDescrLen;
/*  88 */     public byte[] bDescr = new byte[256];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @FieldOrder({"InterfaceLuid", "InterfaceIndex", "InterfaceGuid", "Alias", "Description", "PhysicalAddressLength", "PhysicalAddress", "PermanentPhysicalAddress", "Mtu", "Type", "TunnelType", "MediaType", "PhysicalMediumType", "AccessType", "DirectionType", "InterfaceAndOperStatusFlags", "OperStatus", "AdminStatus", "MediaConnectState", "NetworkGuid", "ConnectionType", "TransmitLinkSpeed", "ReceiveLinkSpeed", "InOctets", "InUcastPkts", "InNUcastPkts", "InDiscards", "InErrors", "InUnknownProtos", "InUcastOctets", "InMulticastOctets", "InBroadcastOctets", "OutOctets", "OutUcastPkts", "OutNUcastPkts", "OutDiscards", "OutErrors", "OutUcastOctets", "OutMulticastOctets", "OutBroadcastOctets", "OutQLen"})
/*     */   public static class MIB_IF_ROW2
/*     */     extends Structure
/*     */   {
/*     */     public long InterfaceLuid;
/*     */ 
/*     */ 
/*     */     
/*     */     public int InterfaceIndex;
/*     */ 
/*     */ 
/*     */     
/*     */     public Guid.GUID InterfaceGuid;
/*     */ 
/*     */     
/* 109 */     public char[] Alias = new char[257];
/* 110 */     public char[] Description = new char[257];
/*     */     public int PhysicalAddressLength;
/* 112 */     public byte[] PhysicalAddress = new byte[32];
/* 113 */     public byte[] PermanentPhysicalAddress = new byte[32];
/*     */     
/*     */     public int Mtu;
/*     */     
/*     */     public int Type;
/*     */     
/*     */     public int TunnelType;
/*     */     
/*     */     public int MediaType;
/*     */     
/*     */     public int PhysicalMediumType;
/*     */     
/*     */     public int AccessType;
/*     */     
/*     */     public int DirectionType;
/*     */     
/*     */     public byte InterfaceAndOperStatusFlags;
/*     */     
/*     */     public int OperStatus;
/*     */     
/*     */     public int AdminStatus;
/*     */     
/*     */     public int MediaConnectState;
/*     */     
/*     */     public Guid.GUID NetworkGuid;
/*     */     public int ConnectionType;
/*     */     public long TransmitLinkSpeed;
/*     */     public long ReceiveLinkSpeed;
/*     */     public long InOctets;
/*     */     public long InUcastPkts;
/*     */     public long InNUcastPkts;
/*     */     public long InDiscards;
/*     */     public long InErrors;
/*     */     public long InUnknownProtos;
/*     */     public long InUcastOctets;
/*     */     public long InMulticastOctets;
/*     */     public long InBroadcastOctets;
/*     */     public long OutOctets;
/*     */     public long OutUcastPkts;
/*     */     public long OutNUcastPkts;
/*     */     public long OutDiscards;
/*     */     public long OutErrors;
/*     */     public long OutUcastOctets;
/*     */     public long OutMulticastOctets;
/*     */     public long OutBroadcastOctets;
/*     */     public long OutQLen;
/*     */   }
/*     */   
/*     */   @FieldOrder({"String"})
/*     */   public static class IP_ADDRESS_STRING
/*     */     extends Structure
/*     */   {
/* 165 */     public byte[] String = new byte[16];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @FieldOrder({"Next", "IpAddress", "IpMask", "Context"})
/*     */   public static class IP_ADDR_STRING
/*     */     extends Structure
/*     */   {
/*     */     public ByReference Next;
/*     */ 
/*     */     
/*     */     public IPHlpAPI.IP_ADDRESS_STRING IpAddress;
/*     */ 
/*     */     
/*     */     public IPHlpAPI.IP_ADDRESS_STRING IpMask;
/*     */ 
/*     */     
/*     */     public int Context;
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends IP_ADDR_STRING
/*     */       implements Structure.ByReference {}
/*     */   }
/*     */ 
/*     */   
/*     */   @FieldOrder({"HostName", "DomainName", "CurrentDnsServer", "DnsServerList", "NodeType", "ScopeId", "EnableRouting", "EnableProxy", "EnableDns"})
/*     */   public static class FIXED_INFO
/*     */     extends Structure
/*     */   {
/* 196 */     public byte[] HostName = new byte[132];
/* 197 */     public byte[] DomainName = new byte[132];
/*     */     public IPHlpAPI.IP_ADDR_STRING.ByReference CurrentDnsServer;
/*     */     public IPHlpAPI.IP_ADDR_STRING DnsServerList;
/*     */     public int NodeType;
/* 201 */     public byte[] ScopeId = new byte[260];
/*     */     public int EnableRouting;
/*     */     public int EnableProxy;
/*     */     public int EnableDns;
/*     */     
/*     */     public FIXED_INFO(Pointer p) {
/* 207 */       super(p);
/* 208 */       read();
/*     */     }
/*     */     
/*     */     public FIXED_INFO() {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/win32/IPHlpAPI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */