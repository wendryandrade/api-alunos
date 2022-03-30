package com.rodarte.nogueira.challenge.service;

import com.rodarte.nogueira.challenge.exception.ReadFileException;
import com.rodarte.nogueira.challenge.model.dto.AlunoDTO;
import lombok.SneakyThrows;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class GetFileFromResourcesService {

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String PATH_XLSX = "classpath:xlsx/BaseImportacaoTesteRn.xlsx";

    public List<AlunoDTO> recuperarListaAlunos() throws Exception {
        var alunos = new ArrayList<AlunoDTO>();

        var file = getFileFromResources();

        try {
            var pkg = OPCPackage.open(file);
            var wb = new XSSFWorkbook(pkg);
            var sheetAlunos = wb.getSheetAt(0);

            Iterator<Row> rowIterator = sheetAlunos.rowIterator();

            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                var aluno = new AlunoDTO();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()) {
                        case 0:
                            aluno.setIdentificacao((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            aluno.setNome(cell.getStringCellValue());
                            break;
                        case 2:
                            aluno.setSexo(cell.getStringCellValue());
                            break;
                        case 3:
                            aluno.setDataNascimento(cell.getDateCellValue());
                            break;
                        case 4:
                            aluno.setNota1((int) Math.round(cell.getNumericCellValue()));
                            break;
                        case 5:
                            aluno.setNota2((int) Math.round(cell.getNumericCellValue()));
                            break;
                        case 6:
                            aluno.setNota3((int) Math.round(cell.getNumericCellValue()));
                            break;
                    }
                }
                alunos.add(aluno);
            }
            wb.close();
            pkg.close();
            file.deleteOnExit();
        } catch (Exception e) {
            throw new ReadFileException("Falha na leitura do arquivo", e.getCause());
        }
        return alunos;
    }

    @SneakyThrows
    private File getFileFromResources() {
        Resource resource = resourceLoader.getResource(PATH_XLSX);
        InputStream baseImportacaoTesteRnResource = resource.getInputStream();

        File tempFile = File.createTempFile("tempFile", ".xlsx");
        Files.copy(baseImportacaoTesteRnResource, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        baseImportacaoTesteRnResource.close();
        return tempFile;
    }
}
