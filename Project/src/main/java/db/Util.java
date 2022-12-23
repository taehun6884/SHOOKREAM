package db;

import java.security.MessageDigest;

public class Util {

	// 임시 패스워드 생성 유틸
		public static String getTempPassword(int length) {
			int index = 0;
			char[] charArr = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < length; i++) {
				index = (int) (charArr.length * Math.random());
				sb.append(charArr[index]);
			}

			return sb.toString();
		}

		// 임시 패스워드 암호화 유틸
		public static String sha256(String base) {
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] hash = digest.digest(base.getBytes("UTF-8"));
				StringBuffer hexString = new StringBuffer();

				for (int i = 0; i < hash.length; i++) {
					String hex = Integer.toHexString(0xff & hash[i]);
					if (hex.length() == 1)
						hexString.append('0');
					hexString.append(hex);
				}

				return hexString.toString();

			} catch (Exception ex) {
				return "";
			}
		}
}
