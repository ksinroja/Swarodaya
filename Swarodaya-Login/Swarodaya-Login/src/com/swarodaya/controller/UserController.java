package com.swarodaya.controller;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bad.common.response.datatable.DataTableConverter;
import com.swarodaya.base.BaseConstant;
import com.swarodaya.base.BaseController;
import com.swarodaya.utils.CommonUtils;
import com.swarodaya.utils.Utilities;
import com.swarodaya.vo.ResultObject;
import com.swarodaya.vo.UserVO;

public class UserController extends BaseController
{
	private static final long serialVersionUID = 1L;
	private static final String CLASSNAME = " [UserController] ";
	
	private static HashMap<String, Integer> mapVerificationCode = new HashMap<String, Integer>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(CLASSNAME + "Started");

		String strAction = request.getParameter("action");
		System.out.println(CLASSNAME + "strAction : " + strAction);

		if ("userHome".equals(strAction)) {
			response.sendRedirect(request.getContextPath() + "/jsp/user/user.jsp");
		}else if("addUser".equals(strAction)) {
			try{
				String strFirstName = request.getParameter("firstName");
				String strLastName = request.getParameter("lastName");
				String strBirthDate = request.getParameter("birthDate");
				String strGender = request.getParameter("gender");
				String strEmail = request.getParameter("email");
				String strMobileNumber = request.getParameter("mobileNo");
				String strCountry = request.getParameter("country");
				String strEmergencyNumber = request.getParameter("emergencyNo");
				String strAbout = request.getParameter("about");
				String strAddress = request.getParameter("address");
				String strIdentityNumber = request.getParameter("identityNo");
				String strIdentityType = request.getParameter("identityType");
				String strVerificationCode = request.getParameter("verificationcode");
				
				
				String json = null;

				if(mapVerificationCode.containsKey(strEmail) && mapVerificationCode.get(strEmail) != null) {
					Integer iVerificationCode = mapVerificationCode.get(strEmail);
					if(iVerificationCode == Integer.parseInt(strVerificationCode)) {

						String strQry = "insert into tblmuser(firstname,lastname,gender,address,country,email,mobile,aboutyou,emergencynumber,identityno,identitytype) values ('" + strFirstName 
								+ "','" + strLastName + "','" + strGender + "','" + strAddress + "','" 
								+ strCountry + "','" + strEmail + "','" + strMobileNumber + "','" + strAbout + "','" + strEmergencyNumber + "','" + strIdentityNumber + "','" + strIdentityType + "')";
						Collection colParam = new ArrayList();
						//colParam.add(new SimpleDateFormat(BaseConstant.DEFAULT_DATE_FORMAT).parse(strBirthDate));

						int executeUpdate = executeUpdate(strQry);

						if(executeUpdate > 0) {
							json = CommonUtils.createSuccessJson(true);
							mapVerificationCode.remove(strEmail);
						}else {
							System.out.println(CLASSNAME + "problem occurred while adding payment");
						}
					}else {
						System.out.println("Invalid Verification Code");
						json = CommonUtils.createSuccessJson(false, "Invalid Verification Code");
					}
				}else {
					System.out.println("Verification code not found");
				}

				System.out.println(CLASSNAME + "jsonStr : " + json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");

				response.getWriter().write(json);

			}catch(Exception e){
				e.printStackTrace();
			}
		}else if("sendVerification".equals(strAction)) {
			try{

				String strEmail = request.getParameter("email");
				String json = null;
				
				if(!isEmailIdEnrolled(strEmail)) {

					System.out.println("Going to generate verification code");
					json = CommonUtils.createSuccessJson(sendVerifyCode(strEmail));

					System.out.println(CLASSNAME + "jsonStr : " + json);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");

					response.getWriter().write(json);

				}else {
					System.out.println("Enrollment already exists");
					json = CommonUtils.createSuccessJson(false, "Enrollment already exists with Id : " + strEmail);
				}

				System.out.println(CLASSNAME + "json : " + json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");

				response.getWriter().write(json);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if("userDetail".equals(strAction)) {

			List<UserVO> userDetails = getUserDetails();

			System.out.println(CLASSNAME + "Size of users : " + userDetails.size());
			DataTableConverter dataTableConverter = new DataTableConverter();

			int iDraw = Integer.parseInt(request.getParameter("draw"));			
			String jsonStr = dataTableConverter.getDataTableJson(userDetails, userDetails.size(), iDraw, false);

			System.out.println(CLASSNAME + "jsonStr : " + jsonStr);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(jsonStr);
		} else if ("dashBoard".equals(strAction)) {
			response.sendRedirect(request.getContextPath() + "/jsp/admin/home.jsp");
		} else if ("logout".equals(strAction)) {
			response.sendRedirect(request.getContextPath() + "/jsp/admin/login.jsp");
		}
		System.out.println(CLASSNAME + "Exiting");
	}

	private List<UserVO> getUserDetails() {
		List<UserVO> detailVOs = new ArrayList<UserVO>();
		try {
			ResultSet resultSet = execute("SELECT cust.*,pay.paymentstatus,pay.receiptsent FROM tblmuser cust LEFT JOIN tbltpayment pay ON cust.userid = pay.userid");
			if(resultSet != null ) {
				while (resultSet.next()) {
					UserVO userDetailVO = new UserVO();

					userDetailVO.setUserId(Long.parseLong(resultSet.getString("userid")));
					userDetailVO.setFirstName(resultSet.getString("firstName"));
					userDetailVO.setLastName(resultSet.getString("lastName"));
					userDetailVO.setEmail(resultSet.getString("email"));
					userDetailVO.setMobileNo(resultSet.getString("mobile"));
					userDetailVO.setCountry(resultSet.getString("country"));
					if(BaseConstant.MALE.equals(resultSet.getString("gender"))) {
						userDetailVO.setGender(BaseConstant.MALE_NAME);
					}else if(BaseConstant.FEMALE.equals(resultSet.getString("gender"))) {
						userDetailVO.setGender(BaseConstant.FEMALE_NAME);
					}else {
						userDetailVO.setGender("-");
					}
					if(BaseConstant.Y.equals(resultSet.getString("paymentstatus"))) {
						userDetailVO.setPaymentStatus(BaseConstant.PAID_NAME);
					}else {
						userDetailVO.setPaymentStatus(BaseConstant.UNPAID_NAME);
					}

					if(BaseConstant.Y.equals(resultSet.getString("receiptsent"))) {
						userDetailVO.setReceiptSent(BaseConstant.YES);
					}else {
						userDetailVO.setReceiptSent(BaseConstant.NO);
					}
					
					detailVOs.add(userDetailVO);
					System.out.println(CLASSNAME + "userDetailVO : " + userDetailVO);
				}
				System.out.println(CLASSNAME + "Exiting with size : " + detailVOs.size());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return detailVOs;
	}
	
	public boolean sendVerifyCode(String strEmailId) {
		boolean bIsMailSent = false;
		try {
			int iVerficationCode = generateRandomNumber(1000, 9999);
			System.out.println("iVerficationCode : " + iVerficationCode);
			
			Utilities utilities = new Utilities();
			ResultObject resultObject = utilities.sendMail(strEmailId, "Verification from Swarodaya"
					, "Dear Participent, Please use following code for verification. Verification Code : " + iVerficationCode);
			
			if(resultObject != null && !resultObject.isError()) {
				bIsMailSent = true;
			}
			
			mapVerificationCode.put(strEmailId, iVerficationCode);
			
		}catch(Exception e) {
			bIsMailSent = false;
			e.printStackTrace();
		}
		return bIsMailSent;
	}

	private int generateRandomNumber(int iLow, int iHigh) {
		Random r = new Random();
		return (r.nextInt(iHigh-iLow) + iLow);
	}
	
	public boolean isEmailIdEnrolled(String strEmailId){
        System.out.println(CLASSNAME + " Check Email started with email : " + strEmailId );
        boolean bEmailExists = true;
        try{
            String strQuery = "select count(*) from tblmuser where email='" + strEmailId + "'";
            ResultSet resultSet = execute(strQuery);
            if(resultSet != null && !resultSet.next()) {
            	bEmailExists = false;
            }
            
        }catch(Exception e){
            e.printStackTrace();

        }
        System.out.println(CLASSNAME + " Check Email exists finished with boolean as: " + bEmailExists );
        return bEmailExists;
    }

}