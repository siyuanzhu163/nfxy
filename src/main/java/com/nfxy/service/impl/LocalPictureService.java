package com.nfxy.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.nfxy.service.PictureService;

/**
 * 图片服务器和WEB应用部署在同一个主机中
 * 将图片直接保存在本地即可
 * @author SIYUAN
 */
public class LocalPictureService implements PictureService {
	
	/**
	 * 图片服务器域名或者主机名+端口号
	 * 如img.nfxy.com,localhost:8080
	 */
	private String domain;
	
	/**
	 * 保存图片的本地路径
	 */
	private String localPath;
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String save(File file, String path) throws FileNotFoundException, IOException {
		InputStream input = null;
		try {
			return save(new FileInputStream(file), path);
		} finally {
			IOUtils.closeQuietly(input);
		}
	}

	public String save(InputStream input, String path) throws IOException{
		FileOutputStream output = null;
		File dest = new File(this.localPath + path);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		output = new FileOutputStream(this.localPath + path);
		try {
			IOUtils.copy(input, output);
			return this.domain + path;
		} finally {
			IOUtils.closeQuietly(output);
		}
	}

	public String save(byte[] bytes, String path) throws IOException {
		return save(new ByteArrayInputStream(bytes), path);
	}

}
