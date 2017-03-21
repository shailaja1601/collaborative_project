package com.niit.collabaration.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collabaration.dao.BlogDAO;
import com.niit.collabaration.daoimpl.BlogDAOImpl;
import com.niit.collabaration.model.Blog;
import com.niit.collabaration.model.User;

@RestController
public class BlogController {
	
	@Autowired
	User user;
	
	@Autowired
	BlogDAO blogDAO;
	
	private static final Logger log = LoggerFactory.getLogger(BlogController.class);
	

	

	@RequestMapping(value = "/adduserblog/", method = RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog ublog, HttpSession session)
	{
		log.debug("calling => createBlog() method");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		ublog.setBlogcreatedate(dateFormat.format(date));
		ublog.setLikes(0);
		ublog.setApprove('N');
		ublog.setUserID((String)session.getAttribute("loggedInUserID"));
		
		
		boolean flag = blogDAO.saveBlog(ublog);
		
		if(!flag){
			log.debug("error in calling => createUserType() method");
			return new ResponseEntity<Blog>(ublog, HttpStatus.CONFLICT);
		}
		else
		{
			log.debug("Update user blog");
			return new ResponseEntity<Blog>(ublog, HttpStatus.OK);
		}
	}
	
	
	
	@RequestMapping(value = "/alluserblog", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> listAllBlog()	{

		log.debug("calling => listAllUserType() method");
		List<Blog> lsts = blogDAO.getAllBlogs();
		if(lsts.isEmpty()){
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(lsts, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getblogbyid/{blogid}", method = RequestMethod.GET)
	public ResponseEntity<Blog> getblogbyid(@PathVariable("blogid") int blogid)	{

		log.debug("calling => getblogbyid() method");
		Blog userblog = blogDAO.getBlogByID(blogid);
		
		if(userblog==null){
			return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Blog>(userblog, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/getapproveblog/{blogid}", method = RequestMethod.POST)
	public ResponseEntity<Blog> getapproveblog(@PathVariable("blogid") int blogid)	{

		log.debug("calling => getapproveblog() method");
		boolean flag = blogDAO.updateApprove(blogid, 'Y');
		if(!flag){
			return new ResponseEntity<Blog>(HttpStatus.BAD_REQUEST);
		}
		Blog userblog = blogDAO.getBlogByID(blogid);
		return new ResponseEntity<Blog>(userblog, HttpStatus.OK);
	}	

	
	@RequestMapping(value = "/getdeleteblog/{blogid}", method = RequestMethod.POST)
	public ResponseEntity<Blog> getdeleteblog(@PathVariable("blogid") int blogid)	{

		log.debug("calling => getapprovegetdeleteblogblog() method");
		boolean flag = blogDAO.getDelete(blogid);
		if(!flag){
			return new ResponseEntity<Blog>(HttpStatus.BAD_REQUEST);
		}
		Blog userblog = blogDAO.getBlogByID(blogid);
		return new ResponseEntity<Blog>(userblog, HttpStatus.OK);
	}	

	
	@RequestMapping(value = "/getupdatelike/{blogid}", method = RequestMethod.POST)
	public ResponseEntity<Blog> getupdatelike(@PathVariable("blogid") int blogid)	{

		log.debug("calling => getapproveblog() method");
		boolean flag = blogDAO.getUpdateLike(blogid);
		if(!flag){
			return new ResponseEntity<Blog>(HttpStatus.BAD_REQUEST);
		}
		Blog userblog = blogDAO.getBlogByID(blogid);
		return new ResponseEntity<Blog>(userblog, HttpStatus.OK);
	}	
}


