package com.stcos.server.util;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfidentialityUtil {
    public static void generatePDFFromForm(String outputFilePah) {
        PdfDocument pdfDocument = null;
        try {
            pdfDocument = new PdfDocument(new PdfWriter(outputFilePah));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Document document = new Document(pdfDocument, PageSize.A4);

        try {
            document.add(createPage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Div createPage() throws IOException{
        Div page = new Div();

        PdfFont chineseFont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);
        page.setFont(chineseFont);

        page.setHeight(PageSize.A4.getHeight());
        page.setWidth(PageSize.A4.getWidth());

        page.add(new Paragraph("软件项目委托测试保密协议"))
                .setFontSize(15)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER);
        page.add(new Paragraph("委托方"
                + "（以下简称“甲方”）与南京大学软件测试中心（简称“乙方”）在签订《"
                + "软件项目委托测试》委托合同的前提下，为保证双方的合法权利，经协双方达成如下保密协议：\n")
                .setFirstLineIndent(2f)
                .setFontSize(12)
                .setTextAlignment(TextAlignment.LEFT)
        );
        page.add(new Paragraph("1、甲方 不得向第三方透露在合作期间获得和知晓的乙方（包括其分支机构）的商业秘密和其他有关的保密信息。商业秘密包括技术秘密和经营秘密，其中技术秘密包括计算机软件、数据库、技术报告、测试报告、检测报告、实验数据、测试结果、操作手册、技术文档、相关的函电等。经营秘密包括但不限于双方洽谈的情况、签署的任何文件，包括合同、协议、备忘录等文件中所包含的一切信息、定价政策、设备资源、人力资源信息等。\n" +
                "2、乙方负有对甲方委托测试的软件保密的责任，保密内容包括：软件产品代码、软件可执行程序、测试报告、测试结果、操作手册、技术文档、用户手册等。\n" +
                "3、未经对方书面同意，任何一方不得在双方合作目的之外使用或向第三方透露对方的任何商业秘密，不管这些商业秘密是口头的或是书面的，还是以磁盘、胶片或电子邮件等形式存在的。\n" +
                "4、在对方公司内活动时，应尊重对方有关保密的管理规定，听从接待人员的安排和引导。未经允许不得进入对方中心、办公室内受控的工作环境，与对方技术人员进行的交流，仅限于合作项目有关的内容。\n" +
                "5、如果一方违反上述条款，另一方有权根据违反的程度以及造成的损害采取以下措施：\n" +
                "（1）终止双方的合作；\n" +
                "（2）要求赔偿因失密造成的损失。\n" +
                "在采取上述措施之前，一方将给予违约的另一方合理的在先通知。 \n" +
                "6、负有保密义务的双方，如果涉密人因本方无法控制的原因(如擅自离职)造成由涉密人有意泄密，其相应的民事和法律责任由当事人承担。\n" +
                "7、与本协议有关的任何争议，双方应通过友好协商解决。如协商不成，任何一方可将此争议提交南京市仲裁委员会进行仲裁。仲裁裁决是终局的，对双方均有约束力。\n" +
                "8、本协议作为委托测试合同的附件，一式两份，双方各执一份，与合同具有同等法律效力。\n" +
                "本协议自双方授权代表签字盖章之日起生效，但有效期不限于合同有效期。\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "甲  方：（公章）                           乙  方：（公章）\n" +
                "\n" +
                "\n" +
                "\n" +
                "法人代表：                               法人代表：\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                " \n" +
                "       年     月    日                       年    月    日\n")
                .setFirstLineIndent(2f)
                .setFontSize(12)
                .setTextAlignment(TextAlignment.LEFT)
        );

        return page;
    }
}
