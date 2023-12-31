package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.Scanner;

public class ImportExportPDF1 extends ImportExport{

    @Override
    public boolean importAction(String fileName, String configPath) {
        //nema
        return false;
    }

    @Override
    public boolean exportAction(String path) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA, 12);

            float y = 750;
            float yGranica = 100;

            contentStream.beginText();
            contentStream.newLineAtOffset(100, y);

            for (Termin t : this.raspored.getTermini()) {
                if (y < yGranica) {
                    contentStream.endText();
                    contentStream.close();

                    page = new PDPage();
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(PDType1Font.HELVETICA, 12);

                    y = 750;
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, y);
                }

                contentStream.moveTextPositionByAmount(0, -20);
                if(t instanceof Termin1) {
                    contentStream.showText("Dan: " + ((Termin1) t).getDan());
                    contentStream.showText(", Datum: " + ((Termin1) t).getDatum());
                    contentStream.showText(", Pocetak: " + ((Termin1) t).getPocetakVr());
                    contentStream.showText(", Kraj: " + ((Termin1) t).getKrajVr());
                }
                contentStream.showText(", Pocetak: " + (t).getPocetak());
                contentStream.showText(", Kraj: " + (t).getKraj());
                contentStream.showText(", Mesto: " + t.getProstorija());

                y -= 20;
            }
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Unesite destinaciju na kojoj zelite da bude fajl: ");
        Scanner sc = new Scanner(System.in);
        String linija = sc.nextLine();

        try {
            document.save(linija + path);
            document.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
