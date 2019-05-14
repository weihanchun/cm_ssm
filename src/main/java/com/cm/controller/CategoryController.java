package com.cm.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cm.pojo.Category;
import com.cm.service.CategoryService;
import com.cm.util.ImageUtil;
import com.cm.util.Page;
import com.cm.util.UploadedImageFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    
    @RequestMapping("admin_category_list")
    public String list(Model model,Page page){
        /*不用插件*/
    	//List<Category> cs= categoryService.list(page);
        //int total=categoryService.total();
    	//通过分页插件指定分页参数
    	PageHelper.offsetPage(page.getStart(),page.getCount() );
    	List<Category> cs=categoryService.list();
    	//通过PageInfo获取总数
    	int total=(int)new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page",page);
        return "admin/listCategory";
    }
   
    @RequestMapping("admin_category_add")
    public String add(Category c,HttpSession session,UploadedImageFile uploadedImageFile) throws IOException {
    	categoryService.add(c);
    	File imageFolder=new File(session.getServletContext().getRealPath("img/category"));
    	File file=new File(imageFolder,c.getId()+".jpg");
    	if(!file.getParentFile().exists())
    		file.getParentFile().mkdirs();
    	uploadedImageFile.getImage().transferTo(file);//把浏览器传递过来的图片保存在上述指定的位置
    	BufferedImage img=ImageUtil.change2jpg(file);//确保图片格式一定是jpg，而不仅仅是后缀名是jpg.
    	ImageIO.write(img, "jpg", file);
    	
    	return "redirect:/admin_category_list";
    }
 
    
    @RequestMapping("admin_category_delete")
    public String delete(int id,HttpSession session) throws IOException{
    	Category c=categoryService.get(id);
    	c.setIsdelete("Y");
    	categoryService.delete(c);
    	File imageFolder=new File(session.getServletContext().getRealPath("img/category"));
    	File file=new File(imageFolder,id+".jpg");
    	file.delete();
    	return "redirect:/admin_category_list";
    }
    
    @RequestMapping("admin_category_edit")
    public String get(Model model,int id) {
    	Category c=categoryService.get(id);
    	model.addAttribute("c",c);   	
    	return "admin/editCategory";
    }
   
    @RequestMapping("admin_category_update")
    public String update(Category c,HttpSession session,UploadedImageFile uploadedImageFile) throws IOException {
    	categoryService.update(c);
    	MultipartFile image=uploadedImageFile.getImage();
        if(null!=image&&!image.isEmpty()) {
        	File imageFolder=new File(session.getServletContext().getRealPath("img/category"));
        	File file=new File(imageFolder,c.getId()+".jpg");
        	image.transferTo(file);
        	BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
    	return "redirect:admin_category_list";
    }  
}
