package com.cm.util;

import org.springframework.web.multipart.MultipartFile;

public class UploadedImageFile {
    //MultipartFile类型的属性，用于接收上传文件的注入
	//这里的属性名称image必须和页面中的增加分类部分中的type="file"的name值保持一致。
	MultipartFile image;

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
