package com.cn.eplat.utils.elead2huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;

import com.cn.eplat.consts.Constants;
import com.cn.eplat.timedtask.PushToHwTask;
import com.cn.eplat.utils.DateUtil;

/**
 * 获取token
 * @author zhangshun
 */
public class GetTokenHelper {
	
	private static String current_token = "";	// 当前token
	
	private static Date last_token_time = null;	// 上次获取token的时间
	
	private static Date token_expire_time = null;	// token失效时间
	
	private static long token_valid_time = Constants.TOKEN_VALID_TIME;	// 设置token有效时长为50分钟（实际有效期是1小时，设置一个小于1小时的有效期主要是为了防止token在未来不确定的异常情况下提前失效）
	
	private static Logger logger = Logger.getLogger(GetTokenHelper.class);
	
	public static String getCurrent_token() {
		return current_token;
	}
	public static void setCurrent_token(String current_token) {
		GetTokenHelper.current_token = current_token;
	}
	public static Date getLast_token_time() {
		return last_token_time;
	}
	public static void setLast_token_time(Date last_token_time) {
		GetTokenHelper.last_token_time = last_token_time;
	}
	public static Date getToken_expire_time() {
		return token_expire_time;
	}
	public static void setToken_expire_time(Date token_expire_time) {
		GetTokenHelper.token_expire_time = token_expire_time;
	}
	public static long getTokenValidTime() {
		return token_valid_time;
	}
	
	public static long getToken_valid_time() {
		return token_valid_time;
	}
	public static void setToken_valid_time(long token_valid_time) {
		GetTokenHelper.token_valid_time = token_valid_time;
		if(last_token_time!=null){
			GetTokenHelper.setToken_expire_time(DateUtil.calcDatePlusGivenTimeMills(last_token_time, token_valid_time));	// 计算得到token失效时间
		}else{
			logger.error("Trying to set token_expire_time encountered ===>[ last_token_time=null exception!!! ]<===");
		}
	}
	
	private static String sendSoapRequest(String requestBody) {
		URL postUrl = null;
		String dataLine;
		String result = "";
		BufferedReader reader = null;
		OutputStream output = null;
		InputStreamReader isr = null;
		HttpsURLConnection connection = null;
		try {
			postUrl = new URL(Constant.TOKEN_URL);
			connection = (HttpsURLConnection) postUrl.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			 connection.setConnectTimeout(20000);
			connection.addRequestProperty("SOAPAction", "");
			output = connection.getOutputStream();
			output.write(requestBody.getBytes("UTF-8"));
			isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
			reader = new BufferedReader(isr);
			while ((dataLine = reader.readLine()) != null) {
				result = dataLine + result;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != output) {
				try {
					output.close();
				} catch (IOException e) {
					output = null;
				}
			}
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					isr = null;
				}
			}
			if (null != isr) {
				try {
					isr.close();
				} catch (IOException e) {
					isr = null;
				}
			}
			if (null != connection) {
				connection.disconnect();
			}
		}
		return result;
	}

	/**
	 * 请求token的请求体
	 * 
	 * @param key
	 * @param secret
	 * @return
	 */
	private static String getSOAPTokenRequestBody() {
		StringBuilder soap = new StringBuilder();
		soap.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:oaut=\"http://oauth2soap.openapi.huawei.com/\">");
		soap.append("<soapenv:Header></soapenv:Header>");
		soap.append("<soapenv:Body>");
		soap.append("<oaut:getToken>");
		soap.append("<consumer_key>").append(Constant.APP_KEY).append("</consumer_key>");
		soap.append("<consumer_secret>").append(Constant.APP_SECRET).append("</consumer_secret>");
		soap.append("</oaut:getToken>");
		soap.append("</soapenv:Body>");
		soap.append("</soapenv:Envelope>");
		return soap.toString();
	}

	public static String getToken() {
		Date now=new Date();
		if(last_token_time==null){	// 当上次获取token的时间为null时，表示是第一次获取token，需要获取新token
//			last_token_time=now;
			current_token = getNewToken();
			logger.info("当前是初次获取token，获取到的新token = "+current_token+", last_token_time = "+DateUtil.formatDate(2, last_token_time)+
					", token有效时长：["+DateUtil.timeMills2ReadableStr(token_valid_time)+"]"+"token过期时间："+DateUtil.formatDate(2, token_expire_time));
			return current_token;
		}
		if(StringUtils.isNotBlank(current_token)&&now.before(token_expire_time)){	// 如果当前token不为空，且token未失效，则直接返回当前已有token
			logger.info("当前已有token且未失效，旧token = "+current_token+", last_token_time = "+DateUtil.formatDate(2, last_token_time)+
					", token有效时长：["+DateUtil.timeMills2ReadableStr(token_valid_time)+"]"+"token过期时间："+DateUtil.formatDate(2, token_expire_time));
			return current_token;
		}else{
			String old_token="<NULL_OLD_TOKEN>";
			if(current_token!=null){
				old_token=new String(current_token);
			}
//			last_token_time=now;
			current_token = getNewToken();
			logger.info("当前token为空或已失效，旧token = "+old_token+", 新获取到的token = "+current_token+", last_token_time = "+DateUtil.formatDate(2, last_token_time)+
					", token有效时长：["+DateUtil.timeMills2ReadableStr(token_valid_time)+"]"+"token过期时间："+DateUtil.formatDate(2, token_expire_time));
			return current_token;
		}
	}
	
	/**
	 * 获取新Token的操作
	 * @return
	 */
	public static String getNewToken(){
		setLast_token_time(new Date());
		// 计时获取新token所花费的时间
		long getNewToken_start_time = System.currentTimeMillis();
		String token = sendSoapRequest(getSOAPTokenRequestBody());
		long getNewToken_end_time = System.currentTimeMillis();
		String ret_token= getInnerTextByTag(token, "access_token");
		String expires_in_str= getInnerTextByTag(token, "expires_in");	// 得到Token失效时间（单位：秒）
		long expires_in=60000l;
		try {
			expires_in= Long.parseLong(expires_in_str)*1000;	// 转换为毫秒数
		} catch (Exception e) {
			logger.error("转换Token失效时间出现异常,error_info="+e.getMessage());
		}
		GetTokenHelper.setToken_valid_time(expires_in);	// 设置Token失效时间
		logger.info("本次获取新token(getNewToken方法)耗时："+DateUtil.timeMills2ReadableStr(getNewToken_end_time - getNewToken_start_time));
		return ret_token;
	}
	
	/**
	 * 从源字符串里截取对应的内容
	 * @param source 源字符串
	 * @param tag 标签
	 * @return 标签里的内容
	 */
	public static String getInnerTextByTag(String source,String tag){
		if(TextUtils.isEmpty(source)){
			return "";
		}
		
		if(TextUtils.isEmpty(tag)||!source.contains(tag)){
			return source;
		}
		
		String result = "";
		try {
			String startStr = "<"+tag+">";
			String endStr = "</"+tag+">";
			int startIndex = source.indexOf(startStr);
			int endIndex = source.indexOf(endStr);
			result = source.substring(startIndex+startStr.length(), endIndex);
		} catch (Exception e) {
			e.printStackTrace();
			result = source;
		}
		return result;
	}

}
