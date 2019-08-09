package com.hvktmm.at12i.controller;

import com.hvktmm.at12i.model.ItemPaint;
import com.hvktmm.at12i.model.Member;
import com.hvktmm.at12i.utils.DateUtil;
import com.hvktmm.at12i.utils.StatusUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.TextField;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class ReportsController {


//    public static void main(String[] args) throws Exception {
//        createPDF_xuatkho();
//
////        createSamplePDF(new String[]{"cường", "hóa đơn bán hàng"}, new String[][]{{"cường", "hà đông"},{"nguyễn", "địa chỉ"}});
//    }
    public static void createSamplePDF(String header[], String body[][]) throws Exception{
        Document documento = new Document();
        //Create new File
        File file = new File("D:/test1.pdf");
        file.createNewFile();
        FileOutputStream fop = new FileOutputStream(file);
        PdfWriter.getInstance(documento, fop);
        documento.open();
        //Fonts



        BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); // khỏi tạo font chữ
        Font font10 = new Font(bf, 10, Font.NORMAL);
        Font font11_bold= new Font(bf, 11, Font.BOLD);
        Phrase phrase = new Phrase("cường", font10);



        Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font fontBody = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL);
        //Table for header

        Paragraph paragraph1 = new Paragraph("Hóa Đơn Bán Hàng",font10);

        //Định dạng đoạn văn bản thứ nhất
        paragraph1.setIndentationLeft(160);
        paragraph1.setIndentationRight(80);
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        paragraph1.setSpacingAfter(15);
        documento.add(paragraph1);

        PdfPTable cabetabla = new PdfPTable(header.length);
        for (int j = 0; j < header.length; j++) {
            Phrase frase = new Phrase(header[j], font10);
            PdfPCell cell = new PdfPCell(frase);

            cell.setBackgroundColor(new BaseColor(Color.lightGray.getRGB()));
            cabetabla.addCell(cell);
        }
        documento.add(cabetabla);
        //Tabla for body
        PdfPTable tabla = new PdfPTable(header.length);
        for (int i = 0; i < body.length; i++) {
            for (int j = 0; j < body[i].length; j++) {
                tabla.addCell(new Phrase(body[i][j], font11_bold));
            }
        }
        documento.add(tabla);
        documento.close();
        fop.flush();
        fop.close();
    }

    public static void createPDF_xuatkho(Member member, List<ItemPaint> list_table,String code_id,String total) throws IOException, DocumentException {
        Document documento = new Document();

        String file_name="XK"+DateUtil.getCurrtentTime2();
        System.out.println(file_name);
        String file_replace=file_name.replaceAll(":","_");
        File file = new File("D:/"+file_replace+".pdf");
        file.createNewFile();
        FileOutputStream fop = new FileOutputStream(file);
        PdfWriter.getInstance(documento, fop);
        documento.open();
        BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); // khỏi tạo font chữ
        Font font10 = new Font(bf, 10, Font.NORMAL);
        Font font11_bold= new Font(bf, 11, Font.BOLD);

        Paragraph paragraph1 = new Paragraph("HÓA ĐƠN BÁN HÀNG",font10);

        paragraph1.setIndentationLeft(80);
        paragraph1.setIndentationRight(80);
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        paragraph1.setSpacingAfter(10);
        documento.add(paragraph1);


        Date date1 =new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);

        Paragraph date = new Paragraph("Ngày "+calendar.get(Calendar.DAY_OF_MONTH)+" tháng "+calendar.get(Calendar.MONTH)+1+" năm "+calendar.get(Calendar.YEAR),font10);

        date.setIndentationLeft(80);
        date.setIndentationRight(80);
        date.setAlignment(Element.ALIGN_CENTER);
        date.setSpacingAfter(10);
        documento.add(date);

        Paragraph code = new Paragraph("Mã Đơn Hàng : "+code_id,font10);

        code.setIndentationLeft(80);
        code.setIndentationRight(80);
        code.setAlignment(Element.ALIGN_CENTER);
        code.setSpacingAfter(20);
        documento.add(code);

        Paragraph ten = new Paragraph("ĐƠN VỊ BÁN HÀNG : CỬA HÀNG BÁN SƠN ĐẠI PHÁT", font10);
        ten.setSpacingAfter(7);
        documento.add(ten);
        Paragraph sdt = new Paragraph("SỐ ĐIỆN THOẠI : 012345678", font10);
        sdt.setSpacingAfter(7);
        documento.add(sdt);
        Paragraph dc = new Paragraph("ĐỊA CHỈ: Số 100 Trần Phú,Hà Đông,Hà Nội,Việt Nam", font10);
        dc.setSpacingAfter(20);
        documento.add(dc);

        Paragraph kh = new Paragraph("TÊN KHÁCH HÀNG :" +member.getName(), font10);
        kh.setSpacingAfter(7);
        documento.add(kh);
        Paragraph dckh = new Paragraph("ĐỊA CHỈ : "+member.getAddress(), font10);
        dckh.setSpacingAfter(7);
        documento.add(dckh);
        Paragraph dtkh = new Paragraph("SỐ ĐIỆN THOẠI : "+member.getPhone(), font10);
        dtkh.setSpacingAfter(20);
        documento.add(dtkh);

        List<String> list=new ArrayList<>();
        list.add("STT");
        list.add("Tên Sơn");
        list.add("Số Lượng");
        list.add("Thành Tiền");
        PdfPTable cabetabla = new PdfPTable(list.size());
        for (int j = 0; j < list.size(); j++) {
            Phrase frase = new Phrase(list.get(j), font10);
            PdfPCell cell = new PdfPCell(frase);

            cell.setBackgroundColor(new BaseColor(Color.WHITE.getRGB()));
            cabetabla.addCell(cell);
        }
        float[] withsKM = { 10.0f, 45.0f, 15.0f, 15.0f};
        cabetabla.setWidthPercentage(100);
        cabetabla.setWidths(withsKM);
        documento.add(cabetabla);
        PdfPTable tabla = new PdfPTable(list.size());
        for (int i = 0; i < list_table.size(); i++) {
            tabla.addCell(new Phrase(String.valueOf(i+1),font10));
            tabla.addCell(new Phrase(list_table.get(i).getName(), font10));
            tabla.addCell(new Phrase(list_table.get(i).getAmount(), font10));
            tabla.addCell(new Phrase(list_table.get(i).getTotal(), font10));
        }
        for (int j = 0; j < (10-list_table.size()); j++) {
            tabla.addCell(new Phrase(" ", font10));
            tabla.addCell(new Phrase(" ", font10));
            tabla.addCell(new Phrase(" ", font10));
            tabla.addCell(new Phrase(" ", font10));
        }
        tabla.setWidthPercentage(100);
        tabla.setWidths(withsKM);
        documento.add(tabla);

        Paragraph tt = new Paragraph("Tổng cộng tiền hàng:                                "+total, font10);
        tt.setSpacingAfter(20);
        tt.setIndentationLeft(50);
        tt.setIndentationRight(80);
        documento.add(tt);

        Paragraph ck = new Paragraph("Người Mua Hàng                                                                                       Người Bán Hàng", font10);
        ck.setSpacingAfter(5);
        ck.setIndentationLeft(50);
        ck.setIndentationRight(80);
        documento.add(ck);

        Paragraph note = new Paragraph("(ký và ghi rõ họ tên)                                                                                  (ký và ghi rõ họ tên) ", font10);
        note.setSpacingAfter(5);
        note.setIndentationLeft(50);
        note.setIndentationRight(80);
        documento.add(note);

        documento.close();
        fop.flush();
        fop.close();
    }

    public static void createPDF_nhapkho(Member member, List<ItemPaint> list_table,String code_id,String total) throws IOException, DocumentException{
        Document documento = new Document();

        Date date1 =new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        String file_name="NK"+DateUtil.getCurrtentTime2();
        System.out.println(file_name);
        String file_replace=file_name.replaceAll(":","_");
        File file = new File("D:/"+file_replace+".pdf");
        file.createNewFile();
        FileOutputStream fop = new FileOutputStream(file);
        PdfWriter.getInstance(documento, fop);
        documento.open();
        BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); // khỏi tạo font chữ
        Font font10 = new Font(bf, 10, Font.NORMAL);
        Font font11_bold= new Font(bf, 11, Font.BOLD);

        Paragraph paragraph1 = new Paragraph("HÓA ĐƠN NHẬP HÀNG",font10);

        paragraph1.setIndentationLeft(80);
        paragraph1.setIndentationRight(80);
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        paragraph1.setSpacingAfter(10);
        documento.add(paragraph1);




        Paragraph date = new Paragraph("Ngày "+calendar.get(Calendar.DAY_OF_MONTH)+" tháng "+calendar.get(Calendar.MONTH)+1+" năm "+calendar.get(Calendar.YEAR),font10);

        date.setIndentationLeft(80);
        date.setIndentationRight(80);
        date.setAlignment(Element.ALIGN_CENTER);
        date.setSpacingAfter(10);
        documento.add(date);

        Paragraph code = new Paragraph("Mã Đơn Hàng : "+code_id,font10);

        code.setIndentationLeft(80);
        code.setIndentationRight(80);
        code.setAlignment(Element.ALIGN_CENTER);
        code.setSpacingAfter(20);
        documento.add(code);

        Paragraph ten = new Paragraph("ĐƠN VỊ BÁN HÀNG : CỬA HÀNG BÁN SƠN ĐẠI PHÁT", font10);
        ten.setSpacingAfter(7);
        documento.add(ten);
        Paragraph sdt = new Paragraph("SỐ ĐIỆN THOẠI : 012345678", font10);
        sdt.setSpacingAfter(7);
        documento.add(sdt);
        Paragraph dc = new Paragraph("ĐỊA CHỈ: Số 100 Trần Phú,Hà Đông,Hà Nội,Việt Nam", font10);
        dc.setSpacingAfter(20);
        documento.add(dc);

        Paragraph kh = new Paragraph("TÊN NHÀ CUNG CẤP :" +member.getName(), font10);
        kh.setSpacingAfter(7);
        documento.add(kh);
        Paragraph dckh = new Paragraph("ĐỊA CHỈ : "+member.getAddress(), font10);
        dckh.setSpacingAfter(7);
        documento.add(dckh);
        Paragraph dtkh = new Paragraph("SỐ ĐIỆN THOẠI : "+member.getPhone(), font10);
        dtkh.setSpacingAfter(20);
        documento.add(dtkh);

        List<String> list=new ArrayList<>();
        list.add("STT");
        list.add("Tên Sơn");
        list.add("Số Lượng");
        list.add("Thành Tiền");
        PdfPTable cabetabla = new PdfPTable(list.size());
        for (int j = 0; j < list.size(); j++) {
            Phrase frase = new Phrase(list.get(j), font10);
            PdfPCell cell = new PdfPCell(frase);

            cell.setBackgroundColor(new BaseColor(Color.WHITE.getRGB()));
            cabetabla.addCell(cell);
        }
        float[] withsKM = { 10.0f, 45.0f, 15.0f, 15.0f};
        cabetabla.setWidthPercentage(100);
        cabetabla.setWidths(withsKM);
        documento.add(cabetabla);
        PdfPTable tabla = new PdfPTable(list.size());
        for (int i = 0; i < list_table.size(); i++) {
            tabla.addCell(new Phrase(String.valueOf(i+1),font10));
            tabla.addCell(new Phrase(list_table.get(i).getName(), font10));
            tabla.addCell(new Phrase(list_table.get(i).getAmount(), font10));
            tabla.addCell(new Phrase(list_table.get(i).getTotal(), font10));
        }
        for (int j = 0; j < (10-list_table.size()); j++) {
            tabla.addCell(new Phrase(" ", font10));
            tabla.addCell(new Phrase(" ", font10));
            tabla.addCell(new Phrase(" ", font10));
            tabla.addCell(new Phrase(" ", font10));
        }
        tabla.setWidthPercentage(100);
        tabla.setWidths(withsKM);
        documento.add(tabla);

        Paragraph tt = new Paragraph("Tổng cộng tiền hàng:                                "+total, font10);
        tt.setSpacingAfter(20);
        tt.setIndentationLeft(50);
        tt.setIndentationRight(80);
        documento.add(tt);

        Paragraph ck = new Paragraph("Người Giao Hàng                                                                                      Người Bán Hàng", font10);
        ck.setSpacingAfter(5);
        ck.setIndentationLeft(50);
        ck.setIndentationRight(80);
        documento.add(ck);

        Paragraph note = new Paragraph("(ký và ghi rõ họ tên)                                                                             (ký và ghi rõ họ tên) ", font10);
        note.setSpacingAfter(5);
        note.setIndentationLeft(50);
        note.setIndentationRight(80);
        documento.add(note);

        documento.close();
        fop.flush();
        fop.close();
    }

}
