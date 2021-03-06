package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;
import com.mysql.cj.Session;


public class StudentLog extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private List<LogBean> list;
	private StudentBean cnBean;
	private int Student_ID;
	public List<LogBean> getList() {
		return list;
	}
	public void setList(List<LogBean> list) {
		this.list = list;
	}
	public StudentBean getCnBean() {
		return cnBean;
	}
	public void setCnBean(StudentBean cnBean) {
		this.cnBean = cnBean;
	}

	//处理用户请求的execute方法
	public String execute() throws Exception {
		
		//解决乱码，用于页面输出
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//创建session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		//验证是否正常登录
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}

		
		//查询所有
		list=new LogDao().GetList("Student_ID="+session.getAttribute("id"),"Log_Date desc");
		
	
		String strWhere="Student_ID="+session.getAttribute("id");
		//查询所有
		cnBean=new StudentDao().GetAllFirstBean(strWhere);
		Student_ID = cnBean.getStudent_ID();
		
		return SUCCESS;
		
	}
	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println();
	}
	public int getStudent_ID() {
		return Student_ID;
	}
	public void setStudent_ID(int student_ID) {
		Student_ID = student_ID;
	}
	
	
}
