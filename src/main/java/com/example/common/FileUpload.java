package com.example.common;

import com.example.bean.BoardVO;
import com.example.dao.BoardDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class FileUpload {
    public BoardVO uploadPhoto(HttpServletRequest request) {
        String filename = "";
        int sizeLimit = 15 * 1024 * 1024;

        // 실제로 서버에 저장되는 path를 upload로 정한다.
        String realPath = request.getServletContext().getRealPath("upload");
        //System.out.println(realPath);

        // 혹시 저장될 경로가 없으면 생성한다.
        File dir = new File(realPath);
        if (!dir.exists()) dir.mkdirs();

        BoardVO one = null;
        MultipartRequest multipartRequest = null;
        try {
            // 파일 업로드 처리하는 과정
            // 파라미터 : request, 파일저장경로, 최대용량, 인코딩, 중복파일명에 대한 정책
            // DefaultFileRenamePlicy() - 중복된 이름이 존재할 경우의 처리방법- 뒤에 숫자 붙임
            multipartRequest = new MultipartRequest(request, realPath,sizeLimit, "utf-8", new DefaultFileRenamePolicy());

            // "photo"라는 이름으로 전송되어 업로드된 파일 이름을 가져옴
            filename = multipartRequest.getFilesystemName("file");
            one = new BoardVO();
            String seq = multipartRequest.getParameter("seq");
            if(seq!=null && !seq.equals("")) one.setSeq(Integer.parseInt(seq));
            one.setCategory(multipartRequest.getParameter("category"));
            one.setTitle(multipartRequest.getParameter("title"));
            one.setWriter(multipartRequest.getParameter("writer"));
            one.setContent(multipartRequest.getParameter("content"));
            one.setFile(multipartRequest.getParameter("file"));
            if(seq!=null && !seq.equals("")) {
                BoardDAO dao = new BoardDAO();
                String oldfilename = dao.getPhotoFilename(Integer.parseInt(seq));
                if(filename != null && oldfilename != null)
                    FileUpload.deleteFile(request, oldfilename);
                else if (filename == null && oldfilename != null)
                    filename = oldfilename;
            }
            one.setFile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return one;
    }
    public static void deleteFile(HttpServletRequest request, String filename) {
        String filePath = request.getServletContext().getRealPath("upload");

        File f= new File(filePath + "/" + filename);
        if( f.exists()) f.delete();
    }
}
