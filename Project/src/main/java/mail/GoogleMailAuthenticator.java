package mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleMailAuthenticator extends Authenticator {
	// 인증 정보(아이디, 패스워드)를 관리할 javax.mail.PasswordAuthentication 클래스 타입 변수 선언
	private PasswordAuthentication passwordAuthentication;

	// 기본 생성자 정의
	public GoogleMailAuthenticator() {
		passwordAuthentication = new PasswordAuthentication("hz0123hz", "xlsrtxkydybsbayw");
	}

	// 인증 정보를 관리하는 PasswordAuthentication 객체를 외부로 리턴하는 
	// getPasswordAuthentication() 메서드 정의
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return passwordAuthentication;
	}
	
}








