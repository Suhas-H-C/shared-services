package com.shared.algo.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import static com.shared.algo.enums.FileConstants.PDF_TITLE;

@Service
public class PDFServiceImpl implements PDFService {

	@Autowired
	private StringContentUtilsService stringContentUtilsService;

	@Override
	public void write(HttpServletResponse response) throws Exception {
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		if (!document.isOpen()) {
			document.open();

			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			titleFont.setSize(18);
			
			Paragraph titleText = new Paragraph(PDF_TITLE.getValue(), titleFont);
			titleText.setAlignment(Paragraph.ALIGN_CENTER);

			Font titleBody = FontFactory.getFont(FontFactory.COURIER);
			titleBody.setSize(14);
			Paragraph bodyText = new Paragraph(stringContentUtilsService.getContent("PDFContent.txt"), titleBody);
			bodyText.setAlignment(Paragraph.ALIGN_LEFT);

			document.add(titleText);
			document.add(bodyText);

			document.close();
		}

	}

}
