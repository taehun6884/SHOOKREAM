package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mail.GoogleMailAuthenticator;
import svc.ReportService;
import vo.ActionForward;

public class ReportProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward =null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String id = request.getParameter("sId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
				
		ReportService service = new ReportService();
		service.reportadd(idx);
		
		boolean isReportplus = service.reportadd(idx);
		
		
		try{
			// System.out.println(sender + receiver + title + content);


			//메일 전송을 위한 설정 작업 
			// 메일 전송 프로토콜 :SMTP (Simple Mail Transfer Protocol) - 
			//-> 기본 포트 (Well-Known port)는 25번이지만 , Gmail 은 587번 포트 사용
			// 1. 시스템(서버 = 톰캣)의 속성정보 (=서버정보) java.util.Properties 객체로 리턴받기
			Properties properties = System.getProperties();
			//2. Properties 객체를 활용해서 메일 전송에 필요한 기본 설정 정보를 서버 속성 정보에 추가 
			//Properties 객체의 put()메서드 사용 

			//메일 전송에 사용할 메일 서버 지정(구글 네이버 아웃룩 등)
			properties.put("mail.smtp.host", "smtp.gmail.com"); // 구글(gmail) SMTP 서버 주소  
			properties.put("mail.smtp.auth", "true"); // SMTP 서버에 대한 인증 여부 설정
			properties.put("mail.smtp.port", "587"); // SMTP 서비스 포트 설정 
			//메일 인증 관련 정보 설정
			properties.put("mail.smtp.starttls.enable", "true"); // TLS 인증 프로토콜 사용 여부 설정
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); //인증 프로토콜 버전 설정
			//만약 TLS 버전 관련 문제 발생 (could not convert socket to TLS)

			//3. 메일 서버 인증 정보를 생성하는 사용자 정의 클래스 인스턴스 생성
			GoogleMailAuthenticator authenticator = new GoogleMailAuthenticator();

			//4. 자바 메일에서 메일 전송을 수행하는 작업 단위를 javax.mail.session 객체 얻어오기 
			//(주의 ! 웹에서 사용하는 기본 세션 객체는 java.servlet.http.HttpSession)
			Session mailSession = Session.getDefaultInstance(properties, authenticator);

			Message mailMessage = new MimeMessage(mailSession);
			//6. 전송할 메일에 대한 정보 설정
			// 1) 발신자 정보 설정
			// -> 단, 상용 메일 서버 ( 구글 네이버 )의 경우 스팸 메일 정책으로 발신자 주소 변경 불가
			Address senderAddress = new InternetAddress(id);
			//2. 수신자 정보 설정을 위한 InternetAddress 객체 생성
			// 파라미터 -> 수신자 주소
			Address receiverAddress = new InternetAddress("mjyskbmj@naver.com");
			//3. Message 객체를 통해 전송할 메일에 대한 내용 정보 설정
			//3-1 메일 헤더 정보 설정
			mailMessage.setHeader("content-type", "text/html; charset=UTF-8");
			//3-2 발신자 정보 설정
			mailMessage.setFrom(senderAddress);
			//3-3 수신자 정보 설정 
			// ->  addRecipient()메서드를 사용하여 수신자 정보 설정
			// 파라미터 : 수신 타입 (기본 수신자이므로 TO 상수 활용) , 수신자 정보 객체
			mailMessage.addRecipient(RecipientType.TO, receiverAddress);
			//3-4 메일 제목 설정
			mailMessage.setSubject(title);
			mailMessage.setContent(content, "text/html; charset=UTF-8");
			//3-5 메일 본문 설정

			// 7. 메일 전송
			//javax.mail.Transport 클래스의 static 메서드 send()호출
			// 파라미터 : message 객체
		
			Transport.send(mailMessage);
			if(isReportplus == true) {
				
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("confirm('신고를 접수하시겠습니까?')");
				out.println("alert('신고가 정상적으로 접수되었습니다');");
				out.println("</script>");
			}
			
			forward = new ActionForward();
			forward.setPath("./");
			forward.setRedirect(false);

			} catch(Exception e) {
				e.printStackTrace();
				try {
					PrintWriter out=response.getWriter();
					out.println("<h3>SMTP 서버 설정 또는 서비스 문제 발생</h3>");
					out.println("<script>");
					out.println("history.back()");
					out.println("</script>");
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			}

		return forward;
	}

}
