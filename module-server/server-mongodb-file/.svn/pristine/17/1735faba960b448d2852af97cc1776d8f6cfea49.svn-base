package com.mongodb.file.controller;



import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.core.api.ApiCodeEnum;
import com.core.api.JsonApi;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.file.util.ContentTypeUtil;


 
@RestController
public class GridFsFileController {
 
    Logger logger = LoggerFactory.getLogger(GridFsFileController.class);
 
    @Autowired
    private GridFsTemplate gridFsTemplate;
 
    @Resource
    private GridFSBucket gridFSBucket;
 
    /** 
     * @author: WangHuaQiang
     * @date: 2019年4月24日
     * @param file MultipartFile
     * @return 文件唯一ID
     * @description: 上传文件,文件唯一标识使用mongodb的ObjectId,存入时保留原始文件名
     */
    @PostMapping("file/upload")
    public JsonApi saveFile(@RequestParam(value = "file",required = true) MultipartFile file){
        String fullName = file.getOriginalFilename();
        //logger.debug("Save File..."+fullName);
        ObjectId fileId = null;
        DBObject dbObject = new BasicDBObject();
        ((BasicDBObject) dbObject).put("createdDate",new Date());
        //logger.debug("File Name:"+fullName);
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            fileId = gridFsTemplate.store(inputStream,fullName,dbObject);
            //logger.debug("Saved File:"+fullName);
        }catch (IOException e){
            logger.error(e.getMessage());
            return new JsonApi(ApiCodeEnum.FAIL);
        }
        return new JsonApi(ApiCodeEnum.OK,fileId==null?null:fileId.toString());
    }
  
    /** 
     * @author: WangHuaQiang
     * @date: 2019年4月24日
     * @param id 文件在mongodb的ObjectId（的id）
     * @param request
     * @param response
     * @throws IOException
     * @description: 下载文件   使用mongodb的ObjectId（的id）下载文件，此时文件以上传时的原始名保存下载
     */
    @GetMapping("/file/download/{id}")
    public ResponseEntity<byte[]> getFileById(@PathVariable(value = "id",required = true) String id, HttpServletRequest request, HttpServletResponse response) throws IOException{
        logger.debug("Download Request File Id:"+id);
        Query query = Query.query(Criteria.where("_id").is(id));
        GridFSFile file = gridFsTemplate.findOne(query);
        if(file==null){
            logger.error("No File Id:"+id);
            return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
        }
        GridFSDownloadStream in = gridFSBucket.openDownloadStream(file.getObjectId());
        GridFsResource resource = new GridFsResource(file,in);
        String fileName = file.getFilename().replace(",", "");
        //处理中文文件名乱码
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        logger.debug("Download Request User-Agent:"+userAgent);
 
        if (userAgent.contains("MSIE") ||
                userAgent.contains("TRIDENT")
                || userAgent.contains("EDGE")
                || userAgent.contains("CHROME")
                || userAgent.contains("SAFARI")
                || userAgent.contains("MOZILLA")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
        	//fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        }
        // 通知浏览器进行文件下载
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        IOUtils.copy(resource.getInputStream(),response.getOutputStream());
        return null;
    }
    
    /** 
     * @author: WangHuaQiang
     * @date: 2019年4月24日
     * @param fileId
     * @return
     * @description: 根据文件ID(mongodb中的唯一ID)删除文件
     */
    @DeleteMapping("/file/delete/{id}")
    public JsonApi deleteFile(@PathVariable String id){
        Query query = Query.query(Criteria.where("_id").is(id));
        // 查询单个文件
        GridFSFile gfsfile = gridFsTemplate.findOne(query);
        if (gfsfile == null) {
        	return new JsonApi(ApiCodeEnum.NOT_FOUND);
        }
        gridFsTemplate.delete(query);
        return new JsonApi(ApiCodeEnum.OK);
    }
    
    /** 
     * @author: WangHuaQiang
     * @date: 2019年4月24日
     * @param id
     * @return
     * @throws IllegalStateException
     * @throws IOException
     * @description: 预览文件
     */
    @SuppressWarnings("unused")
	@GetMapping("/file/view/{id}")
	public ResponseEntity<byte[]> viewFile(@PathVariable String id) throws IllegalStateException, IOException {
		Query query = Query.query(Criteria.where("_id").is(id));
        GridFSFile file = gridFsTemplate.findOne(query);
        if(file==null){
            logger.error("No File Id:"+id);
            return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
        }
        GridFSDownloadStream in = gridFSBucket.openDownloadStream(file.getObjectId());
        GridFsResource resource = new GridFsResource(file,in);
        
        String fileName = file.getFilename().replace(",", "");
        String endFile = fileName.substring(fileName.lastIndexOf("."));
		if (file != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("inline").build());
			
			headers.setContentType(MediaType.parseMediaType(ContentTypeUtil.getContentType(endFile)));
			headers.setContentLength(file.getLength());
			headers.setConnection("close");
			//headers.setCacheControl("max-age=3600");//设置缓存时间
			
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(resource.getInputStream()), headers, HttpStatus.OK);
		}
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		
	}
    
    
    /**
     * 上传文件，由程序生成新的uuid名,后缀同原文件后缀
     * @param file MultipartFile
     * @return FileResponse 只包含生成的唯一文件名
     */
    /*@RequestMapping(value = "upload",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonApi saveFile(@RequestParam(value = "file",required = true) MultipartFile file){
        String fullName = file.getOriginalFilename();
        logger.debug("Save File..."+fullName);
        String endFile = fullName.substring(fullName.lastIndexOf("."));
        DBObject dbObject = new BasicDBObject();
        ((BasicDBObject) dbObject).put("createdDate",new Date());
 
        String fileName = UUID.randomUUID().toString().replace("-","")+endFile;
        logger.debug("File Name:"+fileName);
        InputStream inputStream = null;
        try {
          inputStream = file.getInputStream();
          gridFsTemplate.store(inputStream,fileName,dbObject);
          logger.debug("Saved File:"+fileName);
        }catch (IOException e){
          logger.error(e.getMessage());
        }
        return new JsonApi(ApiCodeEnum.OK,fileName);
    }*/
 
    /**
     *使用由程序生成的唯一文件名下载获取文件
     * @param fileName 唯一文件名 uuid.后缀
     * @param request
     * @return
     * @throws IOException
     */
    /*@RequestMapping(value = "download",method = RequestMethod.GET)
    public ResponseEntity<Resource> getFile(@RequestParam(value = "file",required = true) String fileName,HttpServletRequest request) throws IOException{
        logger.debug("Get File..."+fileName);
        GridFSFile file = gridFsTemplate.findOne(query(whereFilename().is(fileName)));
 
        if(file==null){
            logger.error("No File.");
            throw new RuntimeException("No file:"+fileName);
        }
        GridFSDownloadStream in = gridFSBucket.openDownloadStream(file.getObjectId());
        GridFsResource resource = new GridFsResource(file,in);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+fileName+"\"")
                .body((Resource) resource);
    }*/
 
    
 
    
 
    /**
     * 使用mongodb的ObjectId（的id）下载文件，此时文件以上传时的原始名保存下载,
     * @param fileId  文件在mongodb的ObjectId（的id）
     * @param request
     * @throws IOException
     * @return ResponseEntity<byte[]>
     */
    /*@RequestMapping(value = "oss",method = RequestMethod.GET)
    public ResponseEntity<byte[]> getFileByIdOss(@RequestParam(value = "fileName") String fileId, HttpServletRequest request) throws IOException{
        logger.debug("Download Request File Id:"+fileId);
        Query query = Query.query(Criteria.where("_id").is(fileId));
        GridFSFile file = gridFsTemplate.findOne(query);
        if(file==null){
            logger.error("No File Id:"+fileId);
            throw new RuntimeException("No file Id:"+fileId);
        }
 
        ResponseEntity<byte[]> entity = null;
        HttpHeaders headers = new HttpHeaders();
 
        GridFSDownloadStream in = gridFSBucket.openDownloadStream(file.getObjectId());
        GridFsResource resource = new GridFsResource(file,in);
        String fileName = file.getFilename().replace(",", "");
        //处理中文文件名乱码
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        logger.debug("Download Request User-Agent:"+userAgent);
 
        if (userAgent.contains("MSIE") ||
                userAgent.contains("TRIDENT")
                || userAgent.contains("EDGE")
                || userAgent.contains("CHROME")
                || userAgent.contains("SAFARI")
                || userAgent.contains("MOZILLA")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
//            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        }
        headers.setContentDisposition(ContentDisposition.builder("inline").build());
		headers.setContentType(MediaType.parseMediaType(file.getFilename()));
		headers.setContentLength(file.getLength());
		
		headers.setConnection("close");
		//headers.setCacheControl("max-age=3600");//设置缓存时间
        //headers.add("Content-Disposition", "attachment;filename="+fileName);
        HttpStatus status = HttpStatus.OK;
        // 封装响应
        InputStream inputStream = resource.getInputStream();
        entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), headers, status);
        return entity;
    }*/

}
