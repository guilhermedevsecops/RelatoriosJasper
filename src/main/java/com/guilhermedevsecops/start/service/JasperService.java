package com.guilhermedevsecops.start.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class JasperService {

    private static final String JASPER_PREFIXO = "funcionarios-";
    private static final String JASPER_SUFIXO = ".jasper";

    @Autowired
    private Connection connection;

    private Map<String, Object> params = new HashMap<>();

    public JasperService() {
    }

    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    public byte[] exportarPDF(String code) throws JRException {
        byte[] bytes = null;

        try {
            String jasperFile = JASPER_PREFIXO + code + JASPER_SUFIXO;
            InputStream inputStream = new ClassPathResource("jasper/" + jasperFile).getInputStream();
            JasperPrint print = JasperFillManager.fillReport(inputStream, params, connection);
            bytes = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JRException("Erro ao exportar PDF", e);
        }
        return bytes;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
