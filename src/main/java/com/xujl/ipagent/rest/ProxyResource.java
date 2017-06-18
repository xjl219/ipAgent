package com.xujl.ipagent.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xujl.ipagent.core.ProxyIPool;
@RestController
public class ProxyResource {
	
	 @RequestMapping("/upload")
	 public String upload(@RequestParam("upfile")MultipartFile upfile){
		 if(!upfile.isEmpty()){
	           try {
	        	   BufferedReader br = new BufferedReader( new InputStreamReader(upfile.getInputStream()));
	        	   ProxyIPool.initIPs(br.lines());
	           } catch (IOException e) {
	              e.printStackTrace();
	              return"上传失败,"+e.getMessage();
	           } catch (URISyntaxException e) {
				e.printStackTrace();
			}
	           return"上传成功";
	       }else{
	           return"上传失败，因为文件是空的.";
	       }
	 }
	

    
    @RequestMapping("init")
    public String init(String path) {
    	return "<!doctype html>"+
    			"<html lang='en'> <head> <title>init</title> </head> <body>"+
    			  "<form method='POST' enctype='multipart/form-data' action='upload'>"+
    			 "  File to upload: <input type='file' name='upfile'><br/>"+
    			  " <input type='submit' value='Press'> to upload the file!"+
    			"</form>"+
    			 "</body> </html>";
    }
    
    @RequestMapping("ipstat/{id}")
    
    public String getListByDomain(@PathVariable("id") String id) {
        return ProxyIPool.getListByDomain(id);
    }
    
    @RequestMapping("ipavailable/{id}")
    
    public String getAvailableByDomain(@PathVariable("id") String id) {
        return "available:"+ProxyIPool.getAvailableByDomain(id);
    }
    
    @RequestMapping("getip/{id}")
    public String getIP(@PathVariable("id") String id) {
        return ProxyIPool.getIP(id);
    }
    
    @RequestMapping("disable")
    public String disable(String domain,String ip){
    	
    	return ProxyIPool.disableIP(domain, ip);
    }
 
    
    @RequestMapping("addip/{ip}")
    
    public String addIP(@PathVariable("ip") String ip){
    	ProxyIPool.addIP(ip);
    	return "added "+ip;
    }
    
    
    @RequestMapping("reg/{domain}")
    
    public String register(@PathVariable("domain") String domain,int type,String url,String check,long interval){
    	
    	ProxyIPool.addDomain(domain, type, url, check,interval);
    	return String.format("registered domain:%s, type:%s, url:%s, check:%s,interval:%d",domain, type, url, check,interval);
    }
    
    
    @RequestMapping("recover")
    public String recover() throws Exception{
    	
    	ProxyIPool.load();
    	return ProxyIPool.getAllIP();
    }
    
    @RequestMapping("alldomain")
    
    public String allDomain(){
    	
    	return ProxyIPool.getAllDomain();
    }
    
    @RequestMapping("allip")
    
    public String allIP(){
    	
    	return ProxyIPool.getAllIP();
    }
}