package xyz.btpink.w.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.codec.binary.Base64;

public class Reader {
	//reference URL : http://www.dailycoding.com/posts/convert_image_to_base64_string_and_base64_string_to_image.aspx
	
	private static final String FPATH = "/images/";
	private static final String SPATH = "/storage/";
	private static final String IMGNAME = "sunfish.jpg";
	
	private static void makeFileWithString(String base64){
		byte decode[] = Base64.decodeBase64(base64);
		FileOutputStream fos;
		try{
			String target_path = System.getProperty("user.dir") + SPATH;
			File target = new File(target_path  + "Copy of " + IMGNAME);
			target.createNewFile();
			System.out.println(target.exists());
			fos = new FileOutputStream(target);
			fos.write(decode);
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("DONE");
	}
	
	private static final String toBase64String(File file){
		String encodedStr = "";
		FileInputStream fis;
		try{
			byte[] bArr = new byte[(int)file.length()];
			fis = new FileInputStream(file);
			fis.read(bArr, 0, bArr.length - 1);
			fis.close();
			encodedStr = Base64.encodeBase64String(bArr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return encodedStr;
	}
	
	public static void main(String[] args) {
		File oriImg = new File(
				System.getProperty("user.dir") + FPATH + IMGNAME);
		if(oriImg.exists()){
			System.out.println(toBase64String(oriImg));
			makeFileWithString(toBase64String(oriImg));
		}
	}
}
