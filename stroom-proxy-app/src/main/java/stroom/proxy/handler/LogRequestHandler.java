package stroom.proxy.handler;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import stroom.proxy.util.logging.StroomLogger;
import stroom.proxy.util.zip.StroomZipEntry;
import stroom.proxy.util.zip.HeaderMap;

public class LogRequestHandler implements RequestHandler {
    private static StroomLogger LOGGER = StroomLogger.getLogger(LogRequestHandler.class);

    @Resource
    HeaderMap headerMap;

    @Resource
    LogRequestConfig logRequestConfig;

    @Override
    public void handleHeader() throws IOException {
        List<String> logConfigList = logRequestConfig.getLogRequestList();

        if (logConfigList != null) {
            StringBuilder builder = new StringBuilder();
            for (String logKey : logConfigList) {
                if (builder.length() == 0) {
                    builder.append("log() - ");
                } else {
                    builder.append(",");
                }
                builder.append(logKey);
                builder.append("=");
                builder.append(headerMap.get(logKey));
            }
            LOGGER.info(builder.toString());
        }
    }

    @Override
    public void handleEntryStart(StroomZipEntry stroomZipEntry) throws IOException {
        // NA for LogRequestHandler
    }

    @Override
    public void handleEntryEnd() throws IOException {
        // NA for LogRequestHandler
    }

    @Override
    public void handleEntryData(byte[] buffer, int off, int len) throws IOException {
        // NA for LogRequestHandler
    }

    @Override
    public void handleError() throws IOException {
        // NA for LogRequestHandler
    }

    @Override
    public void handleFooter() throws IOException {
        // NA for LogRequestHandler
    }

    @Override
    public void validate() {
    }

}
