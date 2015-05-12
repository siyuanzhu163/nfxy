package com.nfxy.manager.controller;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nfxy.exception.IllegalImgException;
import com.nfxy.manager.AJAXResponse;
import com.nfxy.service.PictureService;
import com.nfxy.util.UUIDUtils;
import com.siyuan.manager.upload.IMGAttr;
import com.siyuan.manager.upload.PictureTypeEnum;

/**
 * 图片上传
 * @author SIYUAN
 */
@Controller
@RequestMapping("/manager/picture")
public class PictureUploadController extends BaseController {
	
	@Autowired
	private PictureService pictureService;
	
	@ResponseBody
	@RequestMapping(value = "/{pictureType}", method = RequestMethod.POST)
	public AJAXResponse<String> upload(
			@PathVariable("pictureType") PictureTypeEnum pictureType,
			@RequestParam("file") MultipartFile file) {
		AJAXResponse<String> ajaxResp = new AJAXResponse<String>();
		InputStream inputValid = null;
		InputStream inputSave = null;
		try {
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
			
			ajaxResp.setContent(fileURL);
		} catch (Exception e) {
			ajaxResp.setStatus(AJAXResponse.FAIL);
			ajaxResp.setMsg(e.getMessage());
		} finally {
			IOUtils.closeQuietly(inputValid);
			IOUtils.closeQuietly(inputSave);
		}
		return ajaxResp;
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
