package action;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ReviewWriteProService;
import vo.ActionForward;
import vo.ProductBean;
import vo.ReviewBean;

public class ReviewWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		try {
			String uploadPath = "upload"; 
			String realPath = request.getServletContext().getRealPath(uploadPath); 
//			System.out.println("실제 업로드 경로 : " + realPath);		

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
			
//			review.setReview_content(multi.getParameter("review_content"));
			review.setReview_idx(Integer.parseInt(multi.getParameter("review_idx")));
			review.setProduct_idx(Integer.parseInt(multi.getParameter("prodcut_idx")));
			review.setMember_idx(Integer.parseInt(multi.getParameter("member_idx")));
			review.setReview_img(multi.getOriginalFileName("review_img")); //살리기
			review.setReview_real_img(multi.getFilesystemName("review_img")); //살리기
			review.setReview_content(multi.getParameter("content"));
			review.setRe_order_detail(multi.getParameter("product_size")+","+multi.getParameter("product_color"));
			
			System.out.println("리뷰 작성 : " + review);
			
			ReviewWriteProService service = new ReviewWriteProService();
			boolean isReviewSuccess = service.insertReview(review);
			
			ProductBean product = new ProductBean();
			List<String>categorylist =  service.getCategoryList(product.getProduct_name());
			List<String> colorlist = service.ProductColorCategory(product.getProduct_name());
			
			request.setAttribute("categorylist", categorylist);
			request.setAttribute("colorlist", colorlist );
			
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			if(!isReviewSuccess) { // 실패 시
				File f = new File(realPath, review.getReview_real_img());

				
				if(f.exists()) { //존재할 경우
					// File객체의 delete() 메서드를 호출하여 해당 파일 삭제
					f.delete();
				}
				
				out.println("<script>");
				out.println("alert('리뷰작성 실패')");
				out.println("history.back()");
				out.println("</script>");
			} else { 
				out.println("<script>");
				// out.println("alert('작성이 등록되었습니다!')");
				out.println("window.close()");
				out.println("</script>");
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
