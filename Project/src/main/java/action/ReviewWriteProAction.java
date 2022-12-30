package action;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ReviewWriteProService;
import vo.ActionForward;
import vo.ReviewBean;

public class ReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		try {
			String uploadPath = "upload"; 
			String realPath = request.getServletContext().getRealPath(uploadPath); 
			System.out.println("실제 업로드 경로 : " + realPath);		

			int fileSize = 1024 * 1024 * 10;
			
			String writerIpAddr = request.getRemoteAddr();
			
			MultipartRequest multi = new MultipartRequest(
					request, 
					realPath,
					fileSize,
					"UTF-8",
					new DefaultFileRenamePolicy()
			);
			
			ReviewBean review = new ReviewBean();
			
			review.setReview_img(multi.getParameter("review_img"));
			review.setReview_content(multi.getParameter("review_content"));
			review.setReview_img(multi.getOriginalFileName("review_img"));
			review.setReview_real_img(multi.getFilesystemName("review_img"));
			
			ReviewWriteProService service = new ReviewWriteProService();
			boolean isReviewSuccess = service.insertReview(review);
			
			
			if(!isReviewSuccess) { // 실패 시
				File f = new File(realPath, review.getReview_real_img());
				if(f.exists()) { //존재할 경우
					// File객체의 delete() 메서드를 호출하여 해당 파일 삭제
					f.delete();
				}
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('리뷰작성 실패')");
				out.println("history.back()");
				out.println("</script>");
			} else { 
				forward = new ActionForward();
				forward.setPath("BoardList.bo");
				forward.setRedirect(true);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
