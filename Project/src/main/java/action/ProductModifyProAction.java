package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ProductModifyProService;
import vo.ActionForward;
import vo.ProductBean;
import vo.imageBean;

public class ProductModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			int idx = Integer.parseInt(request.getParameter("product_idx"));
			MultipartRequest multi;
			
			String uploadPath = "/upload"; // 업로드 가상 디렉토리(이클립스가 관리)
			String realPath = request.getServletContext().getRealPath(uploadPath);
			System.out.println("실제 업로드 경로 : " + realPath);
			int filesize = 1024 * 1024 * 10;
			int maxSize = 10 * 1024 * 1024; 
				multi = new MultipartRequest(
						request, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy()
						);
				ProductBean product = new ProductBean();	
				product.setProduct_name(multi.getParameter("name"));
				product.setProduct_brand(multi.getParameter("brand"));
				product.setProduct_price(Integer.parseInt(multi.getParameter("price")));
				product.setProduct_discount_price(Integer.parseInt(multi.getParameter("discount")));
				product.setProduct_size(multi.getParameter("size"));
				product.setProduct_amount(Integer.parseInt(multi.getParameter("amount")));
				product.setProduct_color(multi.getParameter("color"));
				product.setProduct_exp(multi.getParameter("exp"));
				product.setProduct_detail_exp(multi.getParameter("detail_exp"));
				System.out.println(product);//확인용
				
				
//				imageBean image = new imageBean();
//				
//				
//				//새로운 파일이 선택됬는지 여부 확인
//				image.setImage_main_file(multi.getOriginalFileName("file"));
//				image.setImage_real_file1(multi.getOriginalFileName("file2"));
//				image.setImage_real_file2(multi.getOriginalFileName("file3"));
//				
//				if(image.getImage_main_file() == null) {//선택한 파일이 없을 때
//					//원래 파일의 파라미터를 넘김
//					image.setImage_main_file(multi.getParameter("origin_file"));
//					
//				}else {//선택한 파일이 있을 때
//					//기존 파일 삭제
//					File file = new File(realPath, multi.getParameter("origin_file"));
//					if(file.exists()) {
//						file.delete();
//					}
//					//새로운 파일 업로드
//					//1. 새로운 파일 파라미터를 imageBean에 저장
//					image.setImage_main_file(multi.getFilesystemName("file"));
//					System.out.println("image 확인 : " + image.getImage_main_file());
//				}
//				
//				//-----------------파일2-----------------
//				if(image.getImage_real_file1() == null) {//선택한 파일이 없을 때
//					//원래 파일의 파라미터를 넘김
//					image.setImage_real_file1(multi.getParameter("origin_file2"));
//					
//				}else {
//					//기존 파일 삭제
//					File file2 = new File(realPath, multi.getFilesystemName("origin_file2"));
//					if(file2.exists()) {
//						file2.delete();
//					}
//					// 새로운 파일 업로드
//					image.setImage_real_file1(multi.getFilesystemName("file2"));
//				}
//				
//				//-----------------파일3-----------------
//				
//				if(image.getImage_real_file1() == null) {//선택한 파일이 없을 때
//					//원래 파일의 파라미터를 넘김
//					image.setImage_real_file2(multi.getParameter("origin_file3"));
//					
//				}else {
//					//기존 파일 삭제
//
//					File file3 = new File(realPath, multi.getFilesystemName("origin_file3"));
//					if(file3.exists()) {
//						file3.delete();
//					}
//					// 새로운 파일 업로드
//					image.setImage_real_file2(multi.getFilesystemName("file3"));
//					
//				}
				
				
				
				ProductModifyProService service = new ProductModifyProService();
				boolean isUpdateSuccess = false;
				//1. 상품 업데이트
				isUpdateSuccess = service.modifyProduct(idx, product);
				
				if(!isUpdateSuccess) {
					
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('상품 수정 실패!')");
					out.println("history.back()");
					out.println("</script>");	
					
					
					
				} else{ // 업데이트 성공 시 
					
					forward = new ActionForward();
					forward.setPath("ProductList.po");
					forward.setRedirect(false);
				}
				
				return forward;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return forward;
	}
	
}
