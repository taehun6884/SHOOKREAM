package vo;

import java.sql.Date;

public class MemberBean {
private int member_idx; //회원번호
private String member_id; // 회원아이디
private String member_name; // 회원 이름
private String member_pass; // 회원 비밀번호
private String member_email; // 회원 이메일
private Date member_date; // 회원 가입 날짜
private String member_phone; // 회원 전화번호
private int member_dec; // 회원 신고수
private int member_point; // 회원 적립금
private String member_address; // 회원 주소

//-------------------------------------------
// getter/setter 메서드
public int getMember_idx() {
	return member_idx;
}
public void setMember_idx(int member_idx) {
	this.member_idx = member_idx;
}
public String getMember_id() {
	return member_id;
}
public void setMember_id(String member_id) {
	this.member_id = member_id;
}
public String getMember_name() {
	return member_name;
}
public void setMember_name(String member_name) {
	this.member_name = member_name;
}
public String getMember_pass() {
	return member_pass;
}
public void setMember_pass(String member_pass) {
	this.member_pass = member_pass;
}
public String getMember_email() {
	return member_email;
}
public void setMember_email(String member_email) {
	this.member_email = member_email;
}
public Date getMember_date() {
	return member_date;
}
public void setMember_date(Date member_date) {
	this.member_date = member_date;
}
public String getMember_phone() {
	return member_phone;
}
public void setMember_phone(String member_phone) {
	this.member_phone = member_phone;
}
public int getMember_dec() {
	return member_dec;
}
public void setMember_dec(int member_dec) {
	this.member_dec = member_dec;
}
public int getMember_point() {
	return member_point;
}
public void setMember_point(int member_point) {
	this.member_point = member_point;
}
public String getMember_address() {
	return member_address;
}
public void setMember_address(String member_address) {
	this.member_address = member_address;
}

// ------------------------------------------------------
// toString 메서드
@Override
public String toString() {
	return "MemberBean [member_idx=" + member_idx + ", member_id=" + member_id + ", member_name=" + member_name
			+ ", member_pass=" + member_pass + ", member_email=" + member_email + ", member_date=" + member_date
			+ ", member_phone=" + member_phone + ", member_dec=" + member_dec + ", member_point=" + member_point
			+ ", member_address=" + member_address + "]";
}


}
