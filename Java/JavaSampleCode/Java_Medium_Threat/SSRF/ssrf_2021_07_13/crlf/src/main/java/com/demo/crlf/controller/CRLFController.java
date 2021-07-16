package com.demo.crlf.controller;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import com.alibaba.fastjson.JSON;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Negative
@RestController
public class CRLFController {
	
	@Value("https://www.bird.com/")
	private String birdUrl;
	
	@Value("${mgrplatform.groupUrl}")
	private String groupUrl;
	
	private final String exUrl = "https://www.something.net/";

	private String httpClientGet(String destAddress) {
		CloseableHttpClient httpClient = httpClientBuilder.build();
		StringBuilder builder = new StringBuilder();
		String key = request.getParameter("key");
		builder.append(key);
		HttpGet httpGet = new HttpGet(destAddress + "?" + builder.toString());
		HtpResonse httpResponse = httpClient.execute(httpGet);
		return result;
	}

    @RequestMapping("/crlf")
    public String crlf(HttpServletRequest request, HttpServletResponse response){
        String origin = request.getParameter("Origin");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        response.setHeader("Host", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return "hello";
    }

    @RequestMapping (value = "/ssrf", method = RequestMethod.GET)
    public int ssrf_demo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getParameter("url");
        String baseUrl = "http://www.baidu.com/";
        String finalUrl = baseUrl + url;
        System.out.println(finalUrl);
        URL obj = new URL(finalUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		String someUrl = groupUrl + url;
		URL obj2 = new URL(someUrl);
        HttpURLConnection con2 = (HttpURLConnection) obj2.openConnection();
		
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        
		String result = httpClientGet(groupUrl + url);
		
		return responseCode;
    }
	
    @RequestMapping (value = "/download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> downloadPolicyTar(@RequestBody Map<String, Object> requestInfo,
                           HttpServletRequest request){
        ResponseEntity<byte[]> response = null;
        String bizId = requestInfo.get("bizId").toString();
        String version = requestInfo.get("version").toString();
        String fileName = bizId + ".zip";
        String tarPublishedPath = "/Users";
        String fileFullPath = tarPublishedPath + File.separator + bizId + File.separator + version + File.separator
                + fileName;
        File file = new File(fileFullPath);
        InputStream in;
        if (!file.exists() || file == null) {
            System.out.println(fileFullPath);
        }
        try {
            in = new FileInputStream(file);
            byte[] b = new byte[in.available()];
            in.read(b);
            HttpHeaders headers = new HttpHeaders();
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            HttpStatus statusCode = HttpStatus.OK;
            response = new ResponseEntity<byte[]>(b, headers, statusCode);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(1);
        } catch (IOException e) {
            System.out.println(2);
        }
        return response;
    }


    @RequestMapping(value = "/mgr/v2/authconfig", method = RequestMethod.POST)
    @ResponseBody
    public void mgrV3AuthConfig(HttpServletRequest request) {

        try {
            String requestString = readBodyFromHttpRequst(request);
            if (requestString.isEmpty()) {
                System.out.println(1);
            }

            JSONObject requestJson = new JSONObject(requestString);
            if (!requestJson.has("method")) {
                System.out.println(2);
            }
            String newAppid = requestJson.getString("appid");
            if (StringUtils.isBlank(newAppid)) {
                System.out.println(3);
            }

            JSON.parseObject(requestString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readBodyFromHttpRequst(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}

