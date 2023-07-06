package com.stcos.server.util;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.ListNumberingType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.stcos.server.entity.form.ContractForm;

import java.io.IOException;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * GPT救我狗命
 * @author masterCheDan
 *
 */

public class ContractUtil {
    public static void generatePDFFromContract(ContractForm contract, String outputFilePath) {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputFilePath));
        Document document = new Document(pdfDocument, PageSize.A4);

        try {
            // 添加页眉和页脚
            PageXofYPageEventHandler eventHandler = new PageXofYPageEventHandler(pdfDocument);
            pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, eventHandler);

            // 封面
            document.add(createTitlePage(contract));

            // 另起一页
            document.add(new AreaBreak());

            // 正文
            document.add(createContentPage(contract));

            // 另起一页
            document.add(new AreaBreak());

            // 签章表格
            document.add(createTablePage(contract));

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 标题页
    private static Div createTitlePage(ContractForm contract) throws IOException {
        Div titlePage = new Div();
        titlePage.setHeight(PageSize.A4.getHeight());
        titlePage.setWidth(PageSize.A4.getWidth());
        titlePage.setMarginTop(150);

        // 设置页面内容居中
        titlePage.setTextAlignment(TextAlignment.CENTER);

        // 创建字体对象
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", true);

        // 添加大标题
        Paragraph title = new Paragraph("软件测试委托合同").setFont(font).setFontSize(28);
        titlePage.add(title);

        titlePage.add(new Paragraph().setMarginTop(4));
        titlePage.add(new Paragraph("项目名称： " + contract.getProjectName()).setFont(font).setFontSize(12));

        titlePage.add(new Paragraph().setMarginTop(1));
        titlePage.add(new Paragraph("委托方(甲方)： " + contract.getClient()).setFont(font).setFontSize(12));

        titlePage.add(new Paragraph().setMarginTop(2));
        titlePage.add(new Paragraph("受托方(乙方)： " + contract.getTrustee()).setFont(font).setFontSize(12));

        titlePage.add(new Paragraph().setMarginTop(6));
        titlePage.add(new Paragraph("签订地点： " + contract.getSignPlace()).setFont(font).setFontSize(12));
        titlePage.add(new Paragraph("签订日期： " + DateTransformer.dateTransformer(contract.getSignDate())).setFont(font).setFontSize(12));
        titlePage.add(new Paragraph("有效日期： " + DateTransformer.dateTransformer(contract.getValidDate())).setFont(font).setFontSize(12));

        return titlePage;
    }

    // 正文页
    private static Div createContentPage(ContractForm contract) throws IOException {
        Div contentPage = new Div();
        contentPage.setHeight(PageSize.A4.getHeight());
        contentPage.setWidth(PageSize.A4.getWidth());
        contentPage.setMarginTop(50);

        PdfFont contentFont = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

        contentPage.setFont(contentFont).setFontSize(12);

        //引言段落
        contentPage.add(
                new Paragraph(
                        "本合同由作为委托方的"
                                + contract.getClient()
                                +"（以下简称“甲方”）与作为受托方的"
                                + contract.getTestFee()
                                +"（以下简称“乙方”）在平等自愿的基础上，依据《中华人民共和国合同法》有关规定就项目的执行，经友好协商后订立。\n"
                )
        );

        //正文第一部分
        contentPage.add(new Paragraph("一、任务描述").setBold());
        contentPage.add(new Paragraph(
                "乙方按照国家软件质量测试标准和测试规范，完成甲方委托的软件"
                + contract.getSoftware()
                + "(下称受测软件)的质量特性"
                + contract.getQualityCharacteristic()
                + "，进行测试，并出具相应的测试报告。\n"
        )).setMarginLeft(20f);

        //正文第二部分
        contentPage.add(new Paragraph("二、双方的主要义务").setBold());
        List list1 = new List(ListNumberingType.DECIMAL);
        list1.setSymbolIndent(20);
        list1.setPostSymbolText(".");

            //甲方义务子列表
            List list1_1 = new List(ListNumberingType.DECIMAL);
            list1_1.setPreSymbolText("(");
            list1_1.setPostSymbolText(")");
            list1_1.setSymbolIndent(40);
            list1_1.add(new ListItem("按照合同约定支付所有费用。"));
            list1_1.add(new ListItem("按照乙方要求以书面形式出具测试需求，包括测试子特性、测试软硬件环境等。"));
            list1_1.add(new ListItem("提供符合交付要求的受测软件产品及相关文档，包括软件功能列表、需求分析、设计文档、用户文档至乙方。"));
            list1_1.add(new ListItem("指派专人配合乙方测试工作，并提供必要的技术培训和技术协助。"));
            list1.add((ListItem) new ListItem("甲方的主要义务:").add(list1_1));

            //甲方义务子列表
            List list1_2 = new List(ListNumberingType.DECIMAL);
            list1_1.setPreSymbolText("(");
            list1_1.setPostSymbolText(")");
            list1_1.setSymbolIndent(40);
            list1_1.add(new ListItem("设计测试用例，制定和实施产品测试方案。"));
            list1_1.add(new ListItem("在测试过程中，定期知会甲方受测软件在测试过程中出现的问题。"));
            list1_1.add(new ListItem("按期完成甲方委托的软件测试工作。"));
            list1_1.add(new ListItem("出具正式的测试报告。"));
            list1.add((ListItem) new ListItem("乙方的主要义务:").add(list1_1));

        contentPage.add(list1);

        //正文第三部分
        contentPage.add(new Paragraph("三、履约地点").setBold());
        contentPage.add(new Paragraph("由甲方将受测软件产品送到乙方实施测试。如果由于被测软件本身特点或其它乙方认可的原因，需要在甲方所在地进行测试时，甲方应负担乙方现场测试人员的差旅和食宿费用。").setFirstLineIndent(2f));

        //正文第四部分
        contentPage.add(new Paragraph("四、合同价款").setBold());
        contentPage.add(new Paragraph(
                "本合同软件测试费用为人民币"
                + NumberToChinese.convertToChinese(contract.getTestFee())
                + "（¥"
                + contract.getTestFee()
                + "）。\n")
                .setFirstLineIndent(2f));

        //正文第五部分
        contentPage.add(new Paragraph("五、测试费用支付方式").setBold());
        contentPage.add(new Paragraph("本合同签定后，十个工作日内甲方合同价款至乙方帐户。\n").setFirstLineIndent(2f));

        //正文第六部分
        contentPage.add(new Paragraph("六、履行的期限").setBold());
        List list2 = new List(ListNumberingType.DECIMAL);
        list2.setPostSymbolText(".");
        list2.setSymbolIndent(20);
        list2.add(new ListItem(
                "本次测试的履行期限为合同生效之日起"
                + contract.getTestTime()
                + "个自然日内完成。"));
        list2.add(new ListItem("经甲乙双方同意，可对测试进度作适当修改，并以修改后的测试进度作为本合同执行的期限。"));
        list2.add(new ListItem("如受测软件在测试过程中出现的问题，导致继续进行测试会影响整体测试进度，则乙方暂停测试并以书面形式通知甲方进行整改。在整个测试过程中，整改次数限于"
                + contract.getRectificationFrequency()
                + "次，每次不超过"
                + contract.getRectificationTime()
                + "天。"));
        list2.add(new ListItem("如因甲方原因，导致测试进度延迟、应由甲方负责,乙方不承担责任。"));
        list2.add(new ListItem("如因乙方原因，导致测试进度延迟，则甲方可酌情提出赔偿要求，赔偿金额不超过甲方已付金额的50%。双方经协商一致后另行签订书面协议，作为本合同的补充。"));
        contentPage.add(list2);

        contentPage.add(new Paragraph("七、资料的保密").setBold());
        contentPage.add(new Paragraph("对于一方向另一方提供使用的秘密信息，另一方负有保密的责任，不得向任何第三方透露。为明确双方的保密义务，双方应签署《软件项目委托测试保密协议》，并保证切实遵守其中条款。\n").setFirstLineIndent(2f));

        contentPage.add(new Paragraph("八、 风险责任的承担").setBold());
        contentPage.add(new Paragraph("乙方人员在本协议有效期间（包括可能的到甲方出差）发生人身意外或罹患疾病时由乙方负责处理。甲方人员在本协议有效期间（包括可能的到乙方出差）发生人身意外或罹患疾病时由甲方负责处理。\n").setFirstLineIndent(2f));

        contentPage.add(new Paragraph("九、验收方法").setBold());
        contentPage.add(new Paragraph("由乙方向甲方提交软件产品鉴定测试报告正本一份，甲方签收鉴定测试报告后，完成验收。\n").setFirstLineIndent(2f));

        contentPage.add(new Paragraph("十、 争议解决").setBold());
        contentPage.add(new Paragraph("双方因履行本合同所发生的一切争议，应通过友好协商解决；如协商解决不成，就提交市级仲裁委员会进行仲裁。裁决对双方当事人具有同等约束力。\n").setFirstLineIndent(2f));

        contentPage.add(new Paragraph("十一、 其他").setBold());
        contentPage.add(new Paragraph(
                "本合同自双方授权代表签字盖章之日起生效，自受托方的主要义务履行完毕之日起终止。\n"
                + "本合同未尽事宜由双方协商解决。\n"
                + "本合同的正本一式肆份，双方各执两份，具有同等法律效力。\n")
                .setFirstLineIndent(2f));

        return contentPage;
    }

    // 表格页
    private static Div createTablePage(ContractForm contract) throws IOException {
        Div tablePage = new Div();

        tablePage.setHeight(PageSize.A4.getHeight());
        tablePage.setWidth(PageSize.A4.getWidth());

        tablePage.add(new Paragraph("十二、签章\n").setBold());

        Table table = new Table(UnitValue.createPointArray(new float[]{1,5,10,3,15}))
                .setWidth(UnitValue.createPercentValue(100))
                .setFixedLayout();
        table.addCell(
                new Cell(7,1)
                        .add(
                                new Paragraph("委\n\n托\n\n方")
                                    .setTextAlignment(TextAlignment.CENTER)
                        )
                )
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell("单位全称").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(
                new Cell(1,3)
                        .add(
                                new Paragraph("\n"
                                        + contract.getClient())
                                        + "\n"
                                        + "                            （签章）"
                )
        );
        table.addCell("授权代表").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getClientInfo().getRepresentative()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("签章\n日期").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getClientInfo().getSignatureDate()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("联系人").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(
                new Cell(1,3).
                        add(contract.getClientInfo().getContact()).setTextAlignment(TextAlignment.LEFT))
                .setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("通讯地址").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(
                        new Cell(1,3).
                                add(contract.getClientInfo().getAddress()).setTextAlignment(TextAlignment.LEFT))
                .setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("电话").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getClientInfo().getTelephone()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("传真").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getClientInfo().getFax()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("开户银行").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(
                        new Cell(1,3).
                                add(contract.getClientInfo().getBank()).setTextAlignment(TextAlignment.LEFT))
                .setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("账号").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getClientInfo().getAccount()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("邮编").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getClientInfo().getPostcode()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell(
                        new Cell(8,1)
                                .add(
                                        new Paragraph("受\n\n托\n\n方")
                                                .setTextAlignment(TextAlignment.CENTER)
                                )
                )
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell("单位全称").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(
                new Cell(1,3)
                        .add(
                                new Paragraph("\n"
                                        + contract.getTrustee())
                                        + "\n"
                                        + "                            （签章）"
                        )
        );
        table.addCell("授权代表").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getTrusteeInfo().getRepresentative()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("签章\n日期").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getTrusteeInfo().getSignatureDate()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("联系人").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(
                        new Cell(1,3).
                                add(contract.getTrusteeInfo().getContact()).setTextAlignment(TextAlignment.LEFT))
                .setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("通讯地址").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getTrusteeInfo().getAddress()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("邮编").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getTrusteeInfo().getPostcode()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("电话").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getTrusteeInfo().getTelephone()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("传真").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(contract.getTrusteeInfo().getFax()).setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("开户银行").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(
                        new Cell(1,3).
                                add("中国工商银行股份有限公司南京汉口路支行").setTextAlignment(TextAlignment.LEFT))
                .setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("户名").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(
                        new Cell(1,3).
                                add("南京大学").setTextAlignment(TextAlignment.LEFT))
                .setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("开户银行").setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(
                        new Cell(1,3).
                                add("4301011309001041656").setTextAlignment(TextAlignment.LEFT))
                .setVerticalAlignment(VerticalAlignment.MIDDLE);

        tablePage.add(table);
        return tablePage;
    }

    //数字转换为大写汉字
    private static class NumberToChinese {
        private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        private static final String[] CN_UPPER_UNIT = {"", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟"};
        private static final String CN_FULL = "整";
        private static final String CN_NEGATIVE = "负";
        private static final String CN_ZERO = "零";
        private static final int MONEY_PRECISION = 2;
        private static final String CN_DOLLAR = "元";
        private static final String CN_TEN_CENT = "角";
        private static final String CN_CENT = "分";
        private static final String CN_INTEGER = "整";

        /**
         * 将浮点数转化为大写汉字字符串
         *
         * @param num 浮点数
         * @return 大写汉字字符串
         */
        public static String convertToChinese(double num) {
            if (num == 0) {
                return CN_ZERO;
            }

            long temp = Math.round(num * 100);
            int numFen = (int) (temp % 10);
            int numJiao = (int) ((temp % 100) / 10);
            int numYuan = (int) (temp / 100);
            boolean isZeroFen = (numFen == 0);
            boolean isZeroJiao = (numJiao == 0);
            boolean isZeroYuan = (numYuan == 0);

            StringBuilder builder = new StringBuilder();
            if (numYuan > 0) {
                builder.append(convertSection(numYuan)).append(CN_DOLLAR);
            }

            if (isZeroFen && isZeroJiao) {
                builder.append(CN_FULL);
            } else if (isZeroFen) {
                builder.append(convertSection(numJiao)).append(CN_TEN_CENT);
            } else {
                if (isZeroJiao) {
                    builder.append(CN_UPPER_NUMBER[numFen]).append(CN_CENT);
                } else {
                    builder.append(convertSection(numJiao)).append(CN_TEN_CENT).append(convertSection(numFen)).append(CN_CENT);
                }
            }

            return builder.toString();
        }

        // 转化整数部分
        private static String convertSection(long section) {
            StringBuilder builder = new StringBuilder();
            boolean zeroFlag = true;
            int unitPos = 0;

            while (section > 0) {
                int v = (int) (section % 10);
                if (v == 0) {
                    if (!zeroFlag) {
                        zeroFlag = true;
                        builder.insert(0, CN_UPPER_NUMBER[v]);
                    }
                } else {
                    zeroFlag = false;
                    builder.insert(0, CN_UPPER_NUMBER[v] + CN_UPPER_UNIT[unitPos]);
                }
                unitPos++;
                section /= 10;
            }

            return builder.toString();
        }
    }

    //日期转换
    private static class DateTransformer{
        static String regex = "\\w+\\s+(\\w+)\\s+(\\d{1,2})\\s+(\\d{4})\\s+\\d{2}:\\d{2}:\\d{2}\\s+\\w+\\+\\d{4}\\s+\\([^\\)]+\\)";

        public static String dateTransformer(String date) {
            // 编译正则表达式
            Pattern pattern = Pattern.compile(regex);

            // 创建Matcher对象
            Matcher matcher = pattern.matcher(date);

            // 查找匹配项
            String year = MonthAbbreviationConverter.convertAbbreviationToNumber(matcher.group(1));
            String month = matcher.group(2);
            String day = matcher.group(3);
            if (matcher.find()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
                Month mon = Month.from(formatter.parse(matcher.group(1)));
                month = mon.toString();
                day = matcher.group(2);
                year = matcher.group(3);
            }
            // 返回提取的日期部分
            return year + " 年 " + month + " 月 " + day + " 日 ";
        }
    }

    //月份转换为数字
    public static class MonthAbbreviationConverter {
        private static final Map<String, String> monthAbbreviations = new HashMap<>();

        static {
            monthAbbreviations.put("Jan", "1");
            monthAbbreviations.put("Feb", "2");
            monthAbbreviations.put("Mar", "3");
            monthAbbreviations.put("Apr", "4");
            monthAbbreviations.put("May", "5");
            monthAbbreviations.put("Jun", "6");
            monthAbbreviations.put("Jul", "7");
            monthAbbreviations.put("Aug", "8");
            monthAbbreviations.put("Sep", "9");
            monthAbbreviations.put("Oct", "10");
            monthAbbreviations.put("Nov", "11");
            monthAbbreviations.put("Dec", "12");
        }

        public static String convertAbbreviationToNumber(String abbreviation) {
            String monthNumber = monthAbbreviations.get(abbreviation);
            if (monthNumber != null) {
                return monthNumber;
            }
            throw new IllegalArgumentException("Invalid month abbreviation");
        }
    }
    private static class PageXofYPageEventHandler implements IEventHandler {
        private PdfFont footerFont;
        private int totalPages;

        public PageXofYPageEventHandler(PdfDocument pdfDocument) throws IOException {
            footerFont = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            totalPages = pdfDocument.getNumberOfPages();
        }

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfCanvas canvas = new PdfCanvas(docEvent.getPage());

            int pageNumber = docEvent.getDocument().getPageNumber(docEvent.getPage());
            String text;
            if (pageNumber != 1) {
                text = "第" + pageNumber + "页，共" + totalPages + "页";
                float x = (docEvent.getPage().getPageSize().getLeft() + docEvent.getPage().getPageSize().getRight()) / 2;
                float y = docEvent.getPage().getPageSize().getBottom() + 20;

                new Canvas(canvas, docEvent.getDocument(), docEvent.getPage().getPageSize())
                        .setFontSize(5)
                        .setFont(footerFont)
                        .showTextAligned(new Paragraph(text), x, y, TextAlignment.CENTER);
            }
        }
    }
}