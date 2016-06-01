package com.swarodaya.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.swarodaya.vo.ResultObject;
import com.swarodaya.vo.UserVO;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Utilities extends DatabaseManager {
	
	public static final String CLASSNAME = " [ Utilities ] ";
	
	public Utilities() {
		System.out.println("In Constructor");
	}
	
	
	public ResultObject sendMail(String strToMailId, String strSubject, String strMessage){
		ResultObject resultObject = new ResultObject();
		
		try {
		//String to = "seemakarecha@gmail.com";
		String from = "info@swarodya.com";//change accordingly
		final String username = "swarodayashastra@gmail.com";//change accordingly
		final String password = "suryachandra2";//change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(strToMailId));

			message.setSubject(strSubject);

			message.setText(strMessage);

			Transport.send(message);

			System.out.println("Sent message successfully....");
			resultObject.setResponseCode(0);
			resultObject.setResponseMessage("Successfully sent email");

		}catch(Exception e){
			resultObject.setException(e);
			resultObject.setResponseCode(-1);
			System.out.println("Exception occoured in send mail " + e);
			e.printStackTrace();
		}
		return resultObject;
	}
	
	public ResultObject sendMail(UserVO userVO,File attachment){
		 System.out.println("Started with senMail API with attachment as " + attachment.getName());
		ResultObject resultObject = new ResultObject();
		
		try{
			
			 System.out.println("Setting of properties " );
			// Recipient's email ID needs to be mentioned.
			
	  	      String to = userVO.getEmail();//change accordingly
	  	      //String to = "seemakarecha@gmail.com";
	  	      // Sender's email ID needs to be mentioned
	  	      String from = "info@swarodya.com";//change accordingly
	  	      final String username = "swarodayashastra@gmail.com";//change accordingly
	  	      final String password = "suryachandra2";//change accordingly

	  	      // Assuming you are sending email through relay.jangosmtp.net
	  	      String host = "smtp.gmail.com";

	  	      Properties props = new Properties();
	  	      props.put("mail.smtp.auth", "true");
	  	      props.put("mail.smtp.starttls.enable", "true");
	  	      props.put("mail.smtp.host", host);
	  	      props.put("mail.smtp.port", "587");

	  	      // Get the Session object.
	  	      Session session = Session.getInstance(props,
	  	      new javax.mail.Authenticator() {
	  	         protected PasswordAuthentication getPasswordAuthentication() {
	  	            return new PasswordAuthentication(username, password);
	  	         }
	  	      });
	    	  
	  	    System.out.println("Setting of message part 1" );
			
	  	 // Create a default MimeMessage object.
	          Message message = new MimeMessage(session);

	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          message.setRecipients(Message.RecipientType.TO,
	             InternetAddress.parse(to));

	          
	          // Set Subject: header field
	          message.setSubject("Swarodaya - Shubh Consulting");

	          // Create the message part
	          BodyPart messageBodyPart = new MimeBodyPart();

	          // Now set the actual message
	          messageBodyPart.setText("Welcome to swarodaya. We would like to thank you for your participation. Kindly see the receipt of your payment");

	          System.out.println("Setting of message part 2 for attachement" );
				
	          
	          // Create a multipar message
	          Multipart multipart = new MimeMultipart();

	          // Set text message part
	          multipart.addBodyPart(messageBodyPart);

	          // Part two is attachment
	          messageBodyPart = new MimeBodyPart();
	         
	         
	          DataSource source = new FileDataSource(attachment);
	          messageBodyPart.setDataHandler(new DataHandler(source));
	          messageBodyPart.setFileName(attachment.getName());
	          multipart.addBodyPart(messageBodyPart);

	          // Send the complete message parts
	          message.setContent(multipart);

	          System.out.println("About to send message" );
				
	          // Send message
	          Transport.send(message);

	          System.out.println("Sent message successfully....");
	          resultObject.setResponseCode(0);
	          resultObject.setResponseMessage("Successfully sent email");
	          
		}catch(Exception e){
			resultObject.setException(e);
			resultObject.setResponseCode(-1);
			System.out.println("Exception occoured in send mail " + e);
			e.printStackTrace();
		}
		return resultObject;
		
	}
	
	public ResultObject generateJasper(long userId, byte[] byteFileJRXML, String strLogoPath){
		System.out.println(CLASSNAME + " - Generate Jasper started with userid " + userId);
		ResultObject resultObject = new ResultObject();
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		Connection jdbcConnection = null;
		HashMap hInputParameter = new HashMap();
		byte[] byteOutPut = null;

		try{

		System.out.println(CLASSNAME + " - Step 1 to read from jrxml and read into byte array");
			/* Need to change the path of the logo where the logo is placed */

			if (byteFileJRXML.length > 0){

				System.out.println(CLASSNAME + " - Step 2 to read bytes into inputstream in jasper design");

				InputStream ipStream = new ByteArrayInputStream(byteFileJRXML);
				JasperDesign jasperDesign = JRXmlLoader.load(ipStream);

				if (jasperDesign != null){

					jasperReport = (JasperReport) JasperCompileManager.compileReport(jasperDesign);

					jdbcConnection =  getConnection(); // change the connection detail or use the method in base controller if any

					if (jdbcConnection != null){


						// set input parameters
						hInputParameter.put("Customerid",new Long(userId));
						hInputParameter.put("logo", strLogoPath);

						System.out.println(CLASSNAME + " - - Step 3 going for jasper print");

						jasperPrint = JasperFillManager.fillReport(jasperReport,hInputParameter, jdbcConnection);
						ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
						JRPdfExporter jp = new JRPdfExporter();
						jp.setParameter(JRExporterParameter.OUTPUT_STREAM,byteArray);
						jp.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						jp.exportReport();
						System.out.println(CLASSNAME + " - - Step 4 byte array after export ----- "  );

						byteOutPut = byteArray.toByteArray();

						System.out.println(CLASSNAME + " - Step 5 Going write into pdf file throuth file output stream");

						resultObject.setResponseCode(0);
						resultObject.setResponseObject(byteOutPut);

					}else{
						resultObject.setResponseCode(-1);
						System.out.println(CLASSNAME + "jdbcConnection  found null" );
					}

				}else{
					resultObject.setResponseCode(-1);
					System.out.println(CLASSNAME + " - jasperdesign found null " );
				}
			}else{
				resultObject.setResponseCode(-1);
				System.out.println(CLASSNAME + "byte from jrxml is 0" );
			}

		}catch(Exception e){
			resultObject.setResponseCode(-1);
			resultObject.setException(e);
			e.printStackTrace();
		}

		System.out.println(CLASSNAME + "Returning from generate method with resultObject code as " + resultObject.getResponseCode() );
		return resultObject;

	}
	
		
} 
