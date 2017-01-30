package com.shiro.util;

import java.io.ByteArrayOutputStream;
/*
 * Java加密算法还包括如下：
 *
 *	MD5(Message Digest algorithm 5，信息摘要算法)
 *	SHA(Secure Hash Algorithm，安全散列算法)
 *	HMAC(Hash Message Authentication Code，散列消息鉴别码)
 */
public class Base64Encode {
//	<script type="text/javascript">  
//    $(function() {  
//        $("#btn").click(function() {  
//            var username = encode64($("#username").val());  //对数据加密  
//            var password = encode64($("#password").val());  
//            $("#username").val(username);  
//            $("#password").val(password);  
//            document.fm.submit();  //fm为form表单name  
//        })  
//    })  
//      
//    // base64加密开始  
//    var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv"  
//            + "wxyz0123456789+/" + "=";  
//      
//    function encode64(input) {  
//  
//        var output = "";  
//        var chr1, chr2, chr3 = "";  
//        var enc1, enc2, enc3, enc4 = "";  
//        var i = 0;  
//        do {  
//            chr1 = input.charCodeAt(i++);  
//            chr2 = input.charCodeAt(i++);  
//            chr3 = input.charCodeAt(i++);  
//            enc1 = chr1 >> 2;  
//            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);  
//            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);  
//            enc4 = chr3 & 63;  
//            if (isNaN(chr2)) {  
//                enc3 = enc4 = 64;  
//            } else if (isNaN(chr3)) {  
//                enc4 = 64;  
//            }  
//            output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)  
//                    + keyStr.charAt(enc3) + keyStr.charAt(enc4);  
//            chr1 = chr2 = chr3 = "";  
//            enc1 = enc2 = enc3 = enc4 = "";  
//        } while (i < input.length);  
//  
//        return output;  
//    }  
//    // base64加密结束  
//</script>  

	private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D',  
	        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',  
	        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',  
	        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',  
	        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',  
	        '4', '5', '6', '7', '8', '9', '+', '/', };  
	  
	private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,  
	        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  
	        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  
	        -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,  
	        60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,  
	        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,  
	        -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,  
	        38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,  
	        -1, -1 };  
  
	/** 
	 * 解密 
	 * @param str 
	 * @return 
	 */  
	public static byte[] decode(String str) {  
	    byte[] data = str.getBytes();  
	    int len = data.length;  
	    ByteArrayOutputStream buf = new ByteArrayOutputStream(len);  
	    int i = 0;  
	    int b1, b2, b3, b4;  
	  
	    while (i < len) {  
	        do {  
	            b1 = base64DecodeChars[data[i++]];  
	        } while (i < len && b1 == -1);  
	        if (b1 == -1) {  
	            break;  
	        }  
	        do {  
	            b2 = base64DecodeChars[data[i++]];  
	        } while (i < len && b2 == -1);  
	        if (b2 == -1) {  
	            break;  
	        }  
	        buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));  
	        do {  
	            b3 = data[i++];  
	            if (b3 == 61) {  
	                return buf.toByteArray();  
	            }  
	            b3 = base64DecodeChars[b3];  
	        } while (i < len && b3 == -1);  
	        if (b3 == -1) {  
	            break;  
	        }  
	        buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));  
	  
	        do {  
	            b4 = data[i++];  
	            if (b4 == 61) {  
	                return buf.toByteArray();  
	            }  
	            b4 = base64DecodeChars[b4];  
	        } while (i < len && b4 == -1);  
	        if (b4 == -1) {  
	            break;  
	        }  
	        buf.write((int) (((b3 & 0x03) << 6) | b4));  
	    }  
	    return buf.toByteArray();  
	}  

	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(new String(decode("admin")));  // 使用decode()方法进行解密
	}



}
