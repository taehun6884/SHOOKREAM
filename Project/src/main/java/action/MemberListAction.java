package action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberListService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		//리스트가 있을 경우 memberList.jsp로 이동하는 작업
		MemberListService service = new MemberListService();
		List<MemberBean> memberList = new ArrayList<MemberBean>();
		
		//service의 getMemberList 메서드를 통해 List 값을 저장.
		memberList = service.getMemberList();
		
		//리스트 정보를 request 객체에 저장.
		request.setAttribute("memberList", memberList);
		
		//포워딩 정보 저장 (경로 설정, redirect 여부)
		forward = new ActionForward();
		forward.setPath("admin/admin_Member_List.jsp");
		forward.setRedirect(false);
		
		
		
		return forward;
	}

}
