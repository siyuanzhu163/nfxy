package com.nfxy.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片服务
 * 将图片保存后返回一个可直接访问的url
 * @author SIYUAN
 */
public interface PictureService {
	
	String save(File file, String path) throws FileNotFoundException, IOException;
	
	String save(InputStream input, String path) throws IOException;
	
	String save(byte[] bytes, String path) throws IOException;
	
}
