package com.shiro;
import java.io.IOException;

import org.snmp4j.*;  
import org.snmp4j.transport.*;  
import org.snmp4j.smi.*;  
import org.snmp4j.mp.*;  
import org.snmp4j.event.*;  
public class SNMP {
		
	
	public static final int DEFAULT_VERSION = SnmpConstants.version2c;	//�汾
	public static final String DEFAULT_PROTOCOL = "udp";	//ͨ��
	public static final int DEFAULT_PORT = 161;				//�˿�
	public static final long DEFAULT_TIMEOUT = 3 * 1000L;	//��ʱ����
	public static final int DEFAULT_RETRY = 3;				//���ó�ʱ����

	
	
    public static void main(String[] args) throws Exception{  
	    try{  
	        //�趨CommunityTarget  
	        CommunityTarget myTarget = new CommunityTarget();  
	        //"udp:localhost/161");
	        Address deviceAdd = GenericAddress.parse("udp:localhost:8080"); //����Զ�������ĵ�ַ  
	        //Address deviceAdd=new IpAddress("udp:127.0.0.1/161");  
	        myTarget.setAddress(deviceAdd);  					//�趨Զ�������ĵ�ַ  
	        myTarget.setCommunity(new OctetString("public"));   //����snmp��ͬ��  
	        myTarget.setRetries(2);  							//���ó�ʱ���Դ���  
	        myTarget.setTimeout(5*6000);   						//���ó�ʱ��ʱ��  
	        myTarget.setVersion(SnmpConstants.version2c);		//����snmp�汾  
	         
	        //�趨��ȡ��Э��  
	        TransportMapping transport = new DefaultUdpTransportMapping();  //�趨����Э��ΪUDP  
	        transport.listen();  
	        Snmp protocol = new Snmp(transport);  
	         
	        //��ȡmib  
	        PDU request = new PDU();  
	         
	        request.add(new VariableBinding(new OID("1.3.6.1.2.1.25.3.5.1.1.1")));  
	        //request.add(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,2})));  
	        
	        request.setType(PDU.GET);  
	        ResponseEvent responseEvent = protocol.send(request, myTarget);  
	        PDU response=responseEvent.getResponse();  
	        //���  
	        if(response != null){  
	            System.out.println("request.size()="+request.size());  
	            System.out.println("response.size()="+response.size());  
	            VariableBinding vb1 = response.get(0);  
	            VariableBinding vb2 = response.get(1);  
	            System.out.println(vb1);  
	            System.out.println(vb2);  
	            transport.close();  
	        }  
	        
	      }catch(IOException e){  
	          e.printStackTrace();  
	          
	      }  
    }  
	    
	    
	/**
	 * ��ȡ��Ϣ
	 * @param ip	ip��ַ
	 * @param community	snmp��ͬ�� 
	 * @param oid		OID
	 */
    public static void snmpGet(String ip, String community, String oid) {

		CommunityTarget target = createDefault(ip, community);
		Snmp snmp = null;
		try {
			PDU pdu = new PDU();
			// pdu.add(new VariableBinding(new OID(new int[]
			// {1,3,6,1,2,1,1,2})));
			pdu.add(new VariableBinding(new OID(oid)));

			DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
			snmp.listen();
			System.out.println("-------> ����PDU <-------");
			pdu.setType(PDU.GET);
			ResponseEvent respEvent = snmp.send(pdu, target);
			System.out.println("PeerAddress:" + respEvent.getPeerAddress());
			PDU response = respEvent.getResponse();

			if (response == null) {
				System.out.println("response is null, request time out");
			} else {

				// Vector<VariableBinding> vbVect =
				// response.getVariableBindings();
				// System.out.println("vb size:" + vbVect.size());
				// if (vbVect.size() == 0) {
				// System.out.println("response vb size is 0 ");
				// } else {
				// VariableBinding vb = vbVect.firstElement();
				// System.out.println(vb.getOid() + " = " + vb.getVariable());
				// }

				System.out.println("response pdu size is " + response.size());
				for (int i = 0; i < response.size(); i++) {
					VariableBinding vb = response.get(i);
					System.out.println(vb.getOid() + " = " + vb.getVariable());
				}

			}
			System.out.println("SNMP GET one OID value finished !");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SNMP Get Exception:" + e);
		} finally {
			if (snmp != null) {
				try {
					snmp.close();
				} catch (IOException ex1) {
					snmp = null;
				}
			}

		}
	}
    
    /**
     * ���ò���
     * @param ip
     * @param community
     * @return
     */
    public static CommunityTarget createDefault(String ip, String community) {
		Address address = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip
				+ "/" + DEFAULT_PORT);
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString(community));
		target.setAddress(address);
		target.setVersion(DEFAULT_VERSION);
		target.setTimeout(DEFAULT_TIMEOUT); // milliseconds
		target.setRetries(DEFAULT_RETRY);
		return target;
	}
}
