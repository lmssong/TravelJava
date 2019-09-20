/**
 * AliSmsUtil.java 2016年11月10日
 * 
 * Copyright 2001-2016  All rights reserved.
 *  PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.iss.sdb.pet.commons;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;

/**
 *
 * <p>
 * 
 * @author hqsunc
 * @since 2016年11月10日
 * @see [Class/Method]
 *
 */
public class AliSmsUtil {
    //阿里云短信ACCESS_KEY_ID和ACCESS_KEY_SECRET
    private static final String ACCESS_KEY_ID = "LTAIAfxgXUMB76tC";
    private static final String ACCESS_KEY_SECRET = "Hllp35mKcWudDvbodc7qYcADnlqGNt";
    
    public static void sendSms(String code, String phoneNo) throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendSmsRequest request = new SingleSendSmsRequest();
        try {
            request.setSignName("A法条");
            request.setTemplateCode("SMS_25200026");
            request.setParamString("{\"code\":\"" + code + "\"}");
            request.setRecNum(phoneNo);
            SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
            System.out.println(httpResponse.toString());
        }
        catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
