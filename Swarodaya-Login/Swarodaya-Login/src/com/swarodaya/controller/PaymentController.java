package com.swarodaya.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swarodaya.base.BaseConstant;
import com.swarodaya.base.BaseController;
import com.swarodaya.utils.CommonUtils;
import com.swarodaya.utils.Utilities;
import com.swarodaya.vo.PaymentVO;
import com.swarodaya.vo.ResultObject;
import com.swarodaya.vo.UserVO;

public class PaymentController extends BaseController
{
	private static final long serialVersionUID = 1L;
	private static final String CLASSNAME = " [PaymentController] ";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(CLASSNAME + "Started");
		try {
		String strAction = request.getParameter("action");
		System.out.println(CLASSNAME + "strAction : " + strAction);
		
		if ("addPayment".equals(strAction)) {

			System.out.println("In addPayment action...");
		
			SimpleDateFormat dateFormat = new SimpleDateFormat(BaseConstant.DEFAULT_DATE_FORMAT);
			
			PaymentVO paymentVO = new PaymentVO();
			paymentVO.setUserId(Long.parseLong(request.getParameter("userId")));
			paymentVO.setAmount(Long.parseLong(request.getParameter("amount")));
			paymentVO.setModeDetails(request.getParameter("details"));
			paymentVO.setPaymentMode(request.getParameter("mode"));
			paymentVO.setPaymentstatus(BaseConstant.Y);
			paymentVO.setReceiptsent(BaseConstant.N);
			paymentVO.setPaymentDate(dateFormat.parse(request.getParameter("pdate")));
			
			String json = CommonUtils.createSuccessJson(addPaymentDetails(paymentVO));
			System.out.println(CLASSNAME + "jsonStr : " + json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(json);

		}else if("sendReceipt".equals(strAction)) {
			
			Long lUserId = Long.parseLong(request.getParameter("userId"));
			ResultObject resultObject = generateReceiptAndSendMail(lUserId);
			
			String json = null;
			if(resultObject != null && !resultObject.isError()) {
				json = CommonUtils.createSuccessJson(true);	
			}else {
				json = CommonUtils.createSuccessJson(false);
			}
			
			System.out.println(CLASSNAME + "jsonStr : " + json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(json);
			
		}else if ("viewPayment".equals(strAction)) {

			Long lUserId = null;
			Long lPaymentId= null;
			if(request.getParameter("userid") != null) {
				lUserId = Long.parseLong(request.getParameter("userid"));
			}
			if(request.getParameter("paymentid") != null) {
				lPaymentId = Long.parseLong(request.getParameter("paymentid"));
			}
			
			List<PaymentVO> listPaymentVO = getPaymentDetails(lUserId, lPaymentId);

			System.out.println(CLASSNAME + "Size of payment : " + listPaymentVO.size());
			/*
			DataTableConverter dataTableConverter = new DataTableConverter();

			int iDraw = Integer.parseInt(request.getParameter("draw"));
			String jsonStr = dataTableConverter.getDataTableJson(listPaymentVO, listPaymentVO.size(), iDraw, false);

			System.out.println(CLASSNAME + "jsonStr : " + jsonStr);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(jsonStr);
			*/
		}
	}catch(Exception e) {
		System.out.println(CLASSNAME + "Exception occured : " + e.getMessage());
		e.printStackTrace();
	}
		System.out.println(CLASSNAME + "Exiting");
	}

	private boolean addPaymentDetails(PaymentVO paymentVO) {
		boolean bSuccess = false;
		try {
			
			String strQry = "insert into tbltpayment(userid,paymentstatus,amount,paymentmode,modedetails,receiptsent) values ("
					+ paymentVO.getUserId() + ",'" + paymentVO.getPaymentstatus() + "'," + paymentVO.getAmount() + ",'" + paymentVO.getPaymentMode() + "','" + paymentVO.getModeDetails()
					+ "','" + paymentVO.getReceiptsent() + "')";
		
			Collection colParam = new ArrayList();
		//	colParam.add(new java.sql.Date(paymentVO.getPaymentDate().getTime()));
			
			int executeUpdate = executeUpdate(strQry);
			if(executeUpdate > 0) {
				bSuccess = true;
				System.out.println(CLASSNAME + "payment added");
			}else {
				System.out.println(CLASSNAME + "problem occurred while adding payment");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bSuccess;
	}
	
	private List<PaymentVO> getPaymentDetails(Long lUserId, Long lPaymentId) {
		List<PaymentVO> listPaymentVO = new ArrayList<PaymentVO>();
		try {
			
			String strQry = "SELECT * FROM tbltpayment Where 1=1 ";
			if(lUserId != null) {
				strQry += " and userid=" + lUserId;
			}
			if(lPaymentId != null) {
				strQry += " and paymentid=" + lPaymentId;
			}
			
			ResultSet resultSet = execute(strQry);
			if(resultSet != null ) {
				while (resultSet.next()) {
					PaymentVO paymentVO = new PaymentVO();

					paymentVO.setUserId(resultSet.getLong("userid"));
					paymentVO.setPaymentId(resultSet.getLong("paymentid"));
					paymentVO.setPaymentMode(resultSet.getString("paymentmode"));
					paymentVO.setModeDetails(resultSet.getString("modedetails"));
					paymentVO.setAmount(resultSet.getLong("amount"));
					
					if(BaseConstant.Y.equals(resultSet.getString("receiptsent"))) {
						paymentVO.setReceiptsent(BaseConstant.YES);
					}else {
						paymentVO.setReceiptsent(BaseConstant.NO);
					}

					if(BaseConstant.Y.equals(resultSet.getString("paymentstatus"))) {
						paymentVO.setPaymentstatus(BaseConstant.PAID_NAME);
					}else {
						paymentVO.setPaymentstatus(BaseConstant.UNPAID_NAME);
					}
					paymentVO.setPaymentDate(resultSet.getDate("paymentdate"));
					//paymentVO.setReceipt();
					
					listPaymentVO.add(paymentVO);
					System.out.println(CLASSNAME + "PaymentVO : " + paymentVO);
				}
				System.out.println(CLASSNAME + "Exiting with size : " + listPaymentVO.size());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listPaymentVO;
	}
	
	private ResultObject generateReceiptAndSendMail(long userId) {
		ResultObject resultObject = new ResultObject();

		try {

			System.out.println(CLASSNAME + " - Started with sendMail user id : " + userId);

			UserVO userVO = null;

			System.out.println(CLASSNAME + "Step 1 : get user details " + userId);

			userVO = getUserDetails(userId);
			System.out.println(CLASSNAME + "userVO got for mail is " + userVO);
			if (userVO != null){

				System.out.println(CLASSNAME + "Step 2 : going to get recipt for " + userId);	  
				
				String realPath = getServletContext().getRealPath("/");
				System.out.println(realPath);
/*
				File fileJRXML = new File (realPath + "/../Receipt.jrxml");	
				System.out.println(fileJRXML.getName());
				System.out.println(fileJRXML.getAbsolutePath());
				System.out.println(fileJRXML.getCanonicalPath());*/

				File file = new File(realPath + "/../jasper/Receipt.jrxml");
				System.out.println(file.length());
				
				
				byte[] readAllBytes = new byte[(int)file.length()];
				
				System.out.println(readAllBytes.length);
				
				FileInputStream fileInputStream = new FileInputStream(file);
				System.out.println(fileInputStream);
				
				fileInputStream.read(readAllBytes);

				System.out.println(readAllBytes.length);
				
				
/*
				RandomAccessFile rf = new RandomAccessFile(fileJRXML, "r");
				byte[] byteFileJRXML = new byte[(int)fileJRXML.length()];
				rf.read(byteFileJRXML);*/
				
				System.out.println(realPath + "/../jasper/logo.JPG");
				
				Utilities u = new Utilities();
				resultObject = u.generateJasper(userId, readAllBytes, realPath + "/../jasper/logo.JPG");
				if (resultObject != null && resultObject.getResponseCode() == 0){
					System.out.println(CLASSNAME + "Step 3 : going to send mail " + userId);	  

					File someFile = new File(realPath + "/../paymentslip/" + userId + "_" + userVO.getFirstName() + "_Receipt.pdf"); // give the path where we can store the file
					System.out.println("OUTPUF FILE : " + someFile.getAbsolutePath());

					FileOutputStream fos = new FileOutputStream(someFile);
					fos.write((byte[])resultObject.getResponseObject());
					fos.flush();
					fos.close();

					resultObject =  u.sendMail(userVO, someFile);

					System.out.println(CLASSNAME + "Step 4 :after mail " + resultObject);	  

				}else{
					resultObject.setResponseCode(-1);
					System.out.println(CLASSNAME + "Unable to generate the slip " );
				}
			}else{
				resultObject.setResponseCode(-1);
				System.out.println(CLASSNAME + "Could not get user details " );
			}
		}catch(Exception e) {
			resultObject.setException(e);
			resultObject.setResponseCode(-1);
			System.out.println("Exception occoured in send mail " + e);
			e.printStackTrace();
		}
		return resultObject;
	}
	
	private UserVO getUserDetails(long userId) {
		System.out.println(CLASSNAME + " - Getting user details of user id : " + userId);
		UserVO userVO = null;
		try {
				ResultSet resultSet =  execute("select * from tblmuser where userid = " + userId);
			if (resultSet != null) {
				while (resultSet.next()) {
					userVO = new UserVO();

					userVO.setFirstName(resultSet.getString("firstName"));
					userVO.setLastName(resultSet.getString("lastName"));
					userVO.setEmail(resultSet.getString("email"));
					userVO.setMobileNo(resultSet.getString("mobile"));
					userVO.setCountry(resultSet.getString("country"));
					userVO.setGender(resultSet.getString("gender"));
				System.out.println(CLASSNAME + " - userDetailVO : " + userVO);
				}
			}else{
				System.out.println(CLASSNAME + " - resultSet found null " + userVO);
			}
			
		}catch (Exception e) {
			System.out.println(CLASSNAME + " - Exceptin occured in getUserDetails " + e);
			e.printStackTrace();
		}

		System.out.println( CLASSNAME + " - returning user details of user id : " + userVO);
		return userVO;

	}
}