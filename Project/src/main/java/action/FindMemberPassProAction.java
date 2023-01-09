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
import svc.isRightUserService;
import vo.ActionForward;
import vo.MemberBean;

public class FindMemberPassProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		System.out.println("FindMemberPassProAction");
		
		String id = request.getParameter("member_id");
		String email = request.getParameter("member_email");
		
		MemberBean member = new MemberBean();
		member.setMember_id(id);
		member.setMember_email(email);
		
		isRightUserService service = new isRightUserService();
		boolean isRightUser = service.isLoginUser(member);
		
		System.out.println("isRightUser" + isRightUser);
		
		StringBuilder imsiPw = new  StringBuilder();
		
		if(isRightUser) {
			
			String[] ch = {
				"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
				"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
				"0","1","2","3","4","5","6","7","8","9"
			};
			
			
			// ch 배열에서 랜덤 10글자 가져오기
			for(int i=0; i < 10; i++) {
				int num = (int)(Math.random()*ch.length);
				imsiPw.append(ch[num]);
				
			}
			System.out.println(imsiPw.toString());
			
		}
			
			String content = "임시 비밀번호로 로그인 한 후, 회원정보 수정에서 비밀번호를 변경하시기 바랍니다.";
			content += "<hr>";
			content += "<table>";
			content += "<tr>";
			content += "	<th> 아이디 </th>";
			content += "	<td>"+ id + "</td>";
			content += "</tr>";
			content += "<tr>";
			content += "	<th> 임시 비밀번호 </th>";
			content += "	<td>"+ imsiPw.toString() + "</td>" ;
			content += "</tr>";
			content += "</table>";
			
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
				InternetAddress address = new InternetAddress(member.getMember_email()); // 받는사람 이메일 주소
				Message msg = new MimeMessage(mailSession);								 // 메일 관련 정보 작성
				msg.setRecipient(Message.RecipientType.TO, address);					 // 받는 사람
				msg.setFrom(new InternetAddress("hz0123hz@gmail.com"));					 // 보내는 사람
				msg.setSubject("[SHOOKREAM] 임시 비밀번호 입니다.");							// 메일 제목
				msg.setContent(content, "text/html; charset=UTF-8");
				msg.setSentDate(new Date());
				Transport.send(msg);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
		
			boolean result = service.updatePass(member, imsiPw); 
			System.out.println("result = " + result);
			
		
		forward = new ActionForward();
		forward.setPath("main.MAIN");
		forward.setRedirect(true);
			
			
		return forward;
		
		
	}
	
}
