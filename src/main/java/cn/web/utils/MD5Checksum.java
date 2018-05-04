package cn.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

public class MD5Checksum {
	
	private static final Logger log = LoggerFactory.getLogger(MD5Checksum.class);
	private static byte[] createChecksum(String filename) {
		InputStream fis = null;
		try {
			fis = new FileInputStream(filename);
			byte[] buffer = new byte[1024];
			MessageDigest complete = MessageDigest.getInstance("MD5");
			int numRead = -1;

			while ((numRead = fis.read(buffer)) != -1) {
				complete.update(buffer, 0, numRead);
			}
			return complete.digest();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (null != fis) {
					fis.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;

	}

	// see this How-to for a faster way to convert
	// a byte array to a HEX string
	public static String getMD5Checksum(String filename) {

		if (!new File(filename).isFile()) {
			log.error("Error: " + filename + " is not a valid file.");
			return null;
		}
		byte[] b = createChecksum(filename);
		if (null == b) {
			log.error("Error:create md5 string failure!");
			return null;
		}
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < b.length; i++) {
			result.append(Integer.toString((b[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return result.toString();

	}

	public static void main(String args[]) {
		try {
//			long beforeTime = System.currentTimeMillis();
			String path = "E:/DI_DATA_S/AutoEntry/IPPHDB_CONTROL_FULLTXT_C_CN_20170711_001.XML";
		//	String before = "999E42920C54CF7D66190731CD54F0E6".toLowerCase();
			String md5 = getMD5Checksum(path);
			System.out.println(md5);
		//	System.out.println(md5.equals(before));

//			File file = new File(path);
//
//			System.out.println(path + "'s size is : " + file.length()
//					+ " bytes, it consumes "
//					+ (System.currentTimeMillis() - beforeTime) + " ms.");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/** 利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException  
     */
    public static String EncoderByMd5(String str) {
    	try{
    		//确定计算方法
            MessageDigest md5=MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
            return newstr;
    	}catch(Exception e){
    		log.error("md5加密失败...");
    	}
        return null;
    }
}
