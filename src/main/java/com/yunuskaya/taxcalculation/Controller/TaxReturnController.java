package com.yunuskaya.taxcalculation.Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import com.yunuskaya.taxcalculation.Service.TaxReturnService;
import com.yunuskaya.taxcalculation.model.TaxReturn;
import com.yunuskaya.taxcalculation.repository.TaxReturnRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/taxreturns")
public class TaxReturnController {

    private final TaxReturnService taxReturnService;

    public TaxReturnController(TaxReturnService taxReturnService) {
        this.taxReturnService = taxReturnService;
    }
    @GetMapping
    public List<TaxReturn> getAllTaxReturns() {
        return taxReturnService.findAll();
    }

    @PostMapping
    public TaxReturn createTaxReturn() {
        return taxReturnService.createTaxReturn();
    }
    @GetMapping("/{id}/download")
    public ResponseEntity<InputStreamResource> downloadTaxReturn(@PathVariable Long id) {
        TaxReturn taxReturn = taxReturnService.findById(id);
        ByteArrayInputStream bis = generatePdf(taxReturn);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=taxreturn.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    private ByteArrayInputStream generatePdf(TaxReturn taxReturn) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Vergi Beyannamesi"));
            document.add(new Paragraph("Tarih: " + taxReturn.getDate().toString()));
            document.add(new Paragraph("Toplam Gelir: " + taxReturn.getTotalIncome()));
            document.add(new Paragraph("Toplam Gider: " + taxReturn.getTotalExpense()));
            document.add(new Paragraph("Vergiye Tabi Gelir: " + taxReturn.getTaxableIncome()));
            document.add(new Paragraph("Vergi Tutarı: " + taxReturn.getTaxAmount()));
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
