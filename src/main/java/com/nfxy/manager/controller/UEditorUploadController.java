package com.nfxy.manager.controller;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nfxy.exception.IllegalImgException;
import com.nfxy.manager.upload.IMGAttr;
import com.nfxy.manager.upload.PictureTypeEnum;
import com.nfxy.service.PictureService;
import com.nfxy.util.UUIDUtils;

/**
 * UEditor后台服务
 * @author SIYUAN
 */
@Controller
@RequestMapping("/manager/ueditor")
public class UEditorUploadController extends BaseController {
	
	private static final Log LOGGER = LogFactory.getLog(UEditorUploadController.class);
	
	/* 支持的服务 */
	/** 获取配置 */
	public static final String CONFIG = "config";
	
	/** 图片上传 */
	public static final String UPLOAD_IMAGE = "uploadImage";
	
	/* 上传图片配置项 */
	/** 执行上传图片的action名称 */
	public static final String IMAGE_ACTION_NAME = "imageActionName";
	
	/** 提交的图片表单名称 */
	public static final String IMAGE_FIELD_NAME = "imageFieldName";
	
	/** 上传大小限制，单位B */
	public static final String IMAGE_MAX_SIZE = "imageMaxSize";
	
	/** 上传图片格式 */
	public static final String IMAGE_ALLOW_FILES = "imageAllowFiles";
	
	/** 是否压缩图片,默认是true */
	public static final String IMAGE_COMPRESSENABLE = "imageCompressEnable";
	
	/** 图片压缩最长边限制 */
	public static final String IMAGE_COMPRESS_BORDER = "imageCompressBorder";
	
	/** 插入的图片浮动方式 */
	public static final String IMAGE_INSERT_ALIGN = "imageInsertAlign";
	
	/** 图片URL前缀 */
	public static final String IMAGE_URL_PREFIX = "imageUrlPrefix";
	
	/* 上传图片返回项 */
	/** 处理结果 */
	public static final String STATE = "state";
	
	/** 处理结果：成功 */
	public static final String STATE_SUCCESS = "SUCCESS";
	
	/** 处理结果：失败 */
	public static final String STATE_FAIL = "FAIL";
	
	/** 图片src */
	public static final String URL = "url";
	
	/** 图片title */
	public static final String TITLE = "title";
	
	/** 图片alt */
	public static final String ORIGINAL = "original";
	
	@Autowired
	private PictureService pictureService;
	
	/**
	 * 根据action请求参数对UEditor请求进行分发
	 * @param action
	 * @return
	 */
	@ResponseBody
	@RequestMapping("")
	public Map<String, Object> service(@RequestParam("action") String action,
			HttpServletRequest request) {
		if (CONFIG.equals(action)) {
			return config();
		} else if (UPLOAD_IMAGE.equals(action)) {
			return uploadImage((MultipartHttpServletRequest) request);
		} else {
			return null;
		}
	}
	
	/**
	 * 返回后台支持的配置项
	 * @return
	 */
	public Map<String, Object> config() {
		Map<String, Object> cfg = new HashMap<String, Object>();
		cfg.put(IMAGE_ACTION_NAME, "uploadImage");
		cfg.put(IMAGE_FIELD_NAME, "file");
		cfg.put(IMAGE_MAX_SIZE, 10485760); //10M
		cfg.put(IMAGE_ALLOW_FILES, new String[]{".png", ".jpg", ".jpeg", ".gif"});
		cfg.put(IMAGE_COMPRESSENABLE, true);
		cfg.put(IMAGE_COMPRESS_BORDER, 1600);
		cfg.put(IMAGE_INSERT_ALIGN, "none");
		cfg.put(IMAGE_URL_PREFIX, "");
		return cfg;
	}
	
	/**
	 * 上传图片
	 * @return
	 */
	public Map<String, Object> uploadImage(MultipartHttpServletRequest request) {
		Map<String, Object> rs = new HashMap<String, Object>();
		PictureTypeEnum pictureType = PictureTypeEnum.INFO_CONTENT;
		InputStream inputValid = null;
		InputStream inputSave = null;
		try {
			MultipartFile file = request.getFile("file");
			
			inputValid = file.getInputStream();
			BufferedImage image = ImageIO.read(inputValid);
			if (image == null) {
				throw new IllegalImgException("上传的图片格式错误");
			}
			validate(image, pictureType);
			
			inputSave = file.getInputStream();
			String fileName = file.getOriginalFilename();
			String suffix = fileName.substring(fileName.indexOf("."));
			String fileURL = pictureService.save(inputSave, getPath(pictureType) + suffix);
			
			rs.put(STATE, STATE_SUCCESS);
			rs.put(URL, fileURL);
			rs.put(TITLE, "@南方校园");
			rs.put(ORIGINAL, "@南方校园");
		} catch (Exception e) {
			LOGGER.error("图片上传失败", e);
			rs.put(STATE, STATE_FAIL);
		} finally {
			IOUtils.closeQuietly(inputValid);
			IOUtils.closeQuietly(inputSave);
		}
		return rs;
	}
	
	/**
	 * 上传图片规格校验
	 * @param image
	 * @param pictureType
	 * @throws IllegalImgException
	 */
	protected void validate(BufferedImage image, PictureTypeEnum pictureType) 
			throws IllegalImgException {
		if (image == null)
			throw new IllegalArgumentException("图片不能为空");
		IMGAttr imgAttr = pictureType.getImgAttr();
		//该类型的图片对规格无限制
		if (imgAttr == null) {
			return;
		}
		//图片符合规格
		if (image.getWidth() == imgAttr.getWidth()
				&& image.getHeight() == imgAttr.getHeight()) {
			return;
		}
		StringBuffer result = new StringBuffer();
		result.append("上传图片规格不正确，请上传")
			.append(imgAttr.getWidth())
			.append("x")
			.append(imgAttr.getHeight())
			.append("格式的图片");
		throw new IllegalImgException(result.toString());
	}
	
	/**
	 * 获取图片的保存路径
	 * @return
	 */
	protected String getPath(PictureTypeEnum pictureType) {
		return pictureType.getPath() + "/" + UUIDUtils.getUUID();
	}
	
}
