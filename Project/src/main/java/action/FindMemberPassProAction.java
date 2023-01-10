package action;

import java.io.IOException;
import java.io.PrintWriter;
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
		
		try {
			if(isRightUser) { // 회원 아이디 일치할 때 임시 비밀번호 생성 및 메일 전송
				
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
				System.out.println("임시패스워드 : "+imsiPw.toString());
		         
		         String content = "임시 비밀번호로 로그인 한 후, 회원정보 수정에서 비밀번호를 변경하시기 바랍니다.<hr>";
		         content += id + " 회원님, 임시 비밀번호가 발급되었습니다.<br>";
		         content += "임시 비밀번호는 ";
		         content += "<b>" + imsiPw.toString() + "</b> 입니다.";
				
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
					msg.setSubject("[SHOOKREAM] 임시 비밀번호 입니다.");					 // 메일 제목
					msg.setContent(content, "text/html; charset=UTF-8"); 					 // 한글 처리
					msg.setSentDate(new Date());
					Transport.send(msg);
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				
				// 회원 비번 업데이트
				boolean result = service.updatePass(member, imsiPw); 
				System.out.println("result = " + result);
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('임시 비밀번호가 발송되었습니다.')");
				out.println("alert('이메일을 확인한 후 다시 로그인 하시길 바랍니다!')");
				out.println("history.back()");
				out.println("</script>");
				
				forward = new ActionForward();
				forward.setPath("LoginMember.me");
				forward.setRedirect(true);
				
			} else { // 회원 아이디 불일치
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('올바르지 않은 회원 정보입니다!')");
				out.println("history.back()");
				out.println("</script>");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
		return forward;
		
		
	}
	
}
