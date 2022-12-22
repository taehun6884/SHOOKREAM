package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ProductInsertService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		ActionForward forward= null;
		
		ProductInsertService service = new ProductInsertService();
		
		
		String uploadPath = "/upload"; // 업로드 가상 디렉토리(이클립스가 관리)
		String realPath = request.getServletContext().getRealPath(uploadPath);
		System.out.println("실제 업로드 경로 : " + realPath);
		int filesize = 1024 * 1024 * 10;
		//2. 크기 : 10MB (크기가 클수록 서버에는 부담)
		int maxSize = 10 * 1024 * 1024; 
		
		//3. multipart객체생성
		try {
			MultipartRequest multi;
			multi = new MultipartRequest(
					request, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy()
					);
			System.out.println("M : 파일업로드완료"+multi);
		

		//4. 폼으로 넘어온 값들을 product에 저장
		ProductBean product = new ProductBean();
		product.setProduct_name(multi.getParameter("name")); // 상품명
		product.setProduct_brand(multi.getParameter("brand")); // 상품 브랜드
		product.setProduct_price(Integer.parseInt(multi.getParameter("price"))); // 상품 가격
		product.setProduct_discount_price(Integer.parseInt(multi.getParameter("discount"))); // 할인율
		product.setProduct_release_price(Integer.parseInt(multi.getParameter("release_price"))); // 할인 적용가격
		product.setProduct_price(Integer.parseInt(multi.getParameter("price"))); // 상품 가격
		product.setProduct_size(multi.getParameter("size")); // 사이즈
		product.setProduct_color(multi.getParameter("color")); // 색상
		product.setProduct_amount(Integer.parseInt(multi.getParameter("amount"))); //상품 재고량
		product.setProduct_exp(multi.getParameter("exp")); // 상품 요약 설명
		product.setProduct_detail_exp(multi.getParameter("detail_exp")); //상품 상세 설명
		product.setProduct_img(multi.getFilesystemName("file")); //파일명
		System.out.println(product);
		
		int insertCount = service.insertProduct(product);

		if(insertCount > 0) {
			forward = new ActionForward();
			forward.setPath("AdminProduct.ad");
			forward.setRedirect(true);
			
		}
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forward;
	}

}