package action;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mail.GoogleMailAuthenticator;
import svc.isSendAuthService;
import vo.ActionForward;
import vo.AuthBean;

public class CheckAddrProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("CheckAddrProAction");
		ActionForward forward = null;
		
		String id = request.getParameter("id");
		String authCode = request.getParameter("authCode");
		String email = request.getParameter("email1") + "@" + request.getParameter("email2");
//		System.out.println(email); // 이메일 잘 받아왔는지 확인
		
		
		
		
		/* 인증 코드 난수 생성 */
		StringBuilder authCd = new  StringBuilder();
		
			String[] ch = {
				"0","1","2","3","4","5","6","7","8","9"
			};
			
			
			// ch 배열에서 랜덤 6글자 가져오기
			for(int i=0; i < 6; i++) {
				int num = (int)(Math.random()*ch.length);
				authCd.append(ch[num]);
				
			}
			

			AuthBean auth = new AuthBean();
			auth.setAuth_id(id);
			auth.setAuth_authCode(authCd.toString());
			
			isSendAuthService service = new isSendAuthService();
			boolean isRightAuth = service.isAuthUser(auth);
			
			System.out.println(authCd.toString());
			
		
		
			
			System.out.println("isRightAuth" + isRightAuth);
		
			String content = "회원가입창으로 돌아가 인증번호를 입력해 주세요.";
			content += "<hr>";
			content += "<b>"+authCd.toString() +"</b>";
		
			
			String mailServer = "smtp.gmail.com"; // 메일 서버 지정하기
			Properties properties = new Properties();
			properties.put("mail.smtp.host", mailServer);
			properties.put("mail.smtp.auth", true);
			properties.put("mail.smtp.port", "587"); 
			properties.put("mail.smtp.starttls.enable", "true");  
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
			Authenticator authenticator = new GoogleMailAuthenticator(); // 메일서버에서 인증받은 계정 + 비번
			Session mailSession = Session.getDefaultInstance(properties, authenticator); // 메일서버, 계정, 비번이 유효한지 검증
			try {
				InternetAddress address = new InternetAddress(email); 		// 받는사람 이메일 주소
				Message msg = new MimeMessage(mailSession);					// 메일 관련 정보 작성
				msg.setRecipient(Message.RecipientType.TO, address);		// 받는 사람
				msg.setFrom(new InternetAddress("hz0123hz@gmail.com"));		// 보내는 사람
				msg.setSubject("[SHOOKREAM] 이메일 인증코드입니다.");		// 메일 제목
				msg.setContent(content, "text/html; charset=UTF-8");		// 데이터 처리
				msg.setSentDate(new Date());								// 보낸 날짜
				Transport.send(msg);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
	
		return forward;
	}

}