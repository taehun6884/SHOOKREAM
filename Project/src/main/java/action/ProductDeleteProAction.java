package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductDeleteProService;
import vo.ActionForward;
import vo.imageBean;

public class ProductDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("product_idx"));
		System.out.println(idx);
		ProductDeleteProService service = new ProductDeleteProService();
		imageBean image = new imageBean();
		
		//이미지 정보 가져와서 저장
		image = service.getImages(idx);
		//1. 경로 설정, 파일명에 대한 변수 선언
		String uploadPath = "/upload"; // 가상 경로
		String realPath = request.getServletContext().getRealPath(uploadPath); // 실제 경로

		String main_image = image.getImage_main_file();
		String real_image1 = image.getImage_real_file1();
		String real_image2 = image.getImage_real_file2();
		//2. 객체 생성
		File file = new File(realPath, main_image);
		File file2 = new File(realPath, real_image1);
		File file3 = new File(realPath, real_image2);
		//3. 해당 파일 객체가 존재할 경우 삭제 작업
		if(file.exists()) {
			file.delete();
		}
		if(file2.exists()){
			file2.delete();
		}
		if(file3.exists()) {
			file3.delete();
		}
		//4. 이미지 삭제 후에 해당 상품을 DB에서 삭제작업을 실시.
		
		if(image != null) {//가져온 이미지가 널이 아닐 경우에만 DB삭제 작업
			boolean isDeleteProduct = service.isDeleteProduct(idx);
			if(!isDeleteProduct) { // 삭제 권한 없음
				try {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('삭제 권한이 없습니다!')");
					out.println("history.back()");
					out.println("</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else { // 삭제 성공 시
				forward = new ActionForward();
				forward.setPath("./ProductList.po");
				forward.setRedirect(true);
				
			}
		}
		
		return forward;
	}

}