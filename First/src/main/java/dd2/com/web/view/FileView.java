package dd2.com.web.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 30
 * Time: 오후 12:35
 * To change this template use File | Settings | File Templates.
 */
public final class FileView extends AbstractView {
    private static final Log logger = LogFactory.getLog(FileView.class);
    private static final int MAX_BUFFER_LEN = 1024;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = (String)model.get("filePath");
        String fileType = (String)model.get("fileType");
        String orignalFileName = (String)model.get("orignalFileName");

        FileInputStream fis = null;
        try {
            File imageFile = new File(filePath);
            fis = new FileInputStream(imageFile);

            ByteArrayOutputStream baos = null;
            byte[] imageBuffer = null;
            try {
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[MAX_BUFFER_LEN];
                int readLength = 0;
                while( (readLength=fis.read(buffer)) != -1 ) {
                    baos.write(buffer, 0, readLength);
                }

                if ( fileType != null && fileType.equals("") == false ) {
                    response.setContentType(fileType);
                }

                if ( orignalFileName != null && orignalFileName.equals("") == false ) {
                    response.setHeader( "Content-Disposition", "attachment;filename=" + orignalFileName );
                }

                imageBuffer = baos.toByteArray();
                if ( imageBuffer != null ) {
                    OutputStream outputStream = response.getOutputStream();
                    outputStream.write(imageBuffer, 0, imageBuffer.length);
                    outputStream.close();
                }
            } catch (Exception e) {
                if ( logger.isErrorEnabled() ) {
                    logger.error(e.getMessage());
                }
            } finally {
                if ( baos != null ) {
                    baos.close();
                    baos = null;
                }
            }
        } catch (Exception e) {
            if ( logger.isErrorEnabled() ) {
                logger.error(e.getMessage());
            }
        } finally {
            if ( fis != null ) {
                fis.close();
                fis = null;
            }
        }
    }
}
