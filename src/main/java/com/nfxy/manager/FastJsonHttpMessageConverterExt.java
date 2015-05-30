package com.nfxy.manager;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

public class FastJsonHttpMessageConverterExt extends
		FastJsonHttpMessageConverter {
	
	@SuppressWarnings("rawtypes")
	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		OutputStream out = outputMessage.getBody();
		SerializeFilter[] serializeFilters = null;
		if (obj instanceof AJAXResponse) {
			serializeFilters = ((AJAXResponse) obj).getSerializeFilters();
		}
		String text = "";
		if (serializeFilters == null) {
			text = JSON.toJSONString(obj, super.getFeatures());
		} else {
			text = JSON.toJSONString(obj, serializeFilters, super.getFeatures());
		}
        byte[] bytes = text.getBytes(super.getCharset());
        out.write(bytes);
	}
	
}
